package com.theplay.aos.iadapter

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexWrap
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.ItemFollowingBinding
import com.theplay.aos.fragment.home.ImageFragment
import com.theplay.aos.fragment.home.MainBoardDetailFragmentDirections

interface MainBoardDetailInterface {
    fun clickLike(postId : Int)
}

class MainBoardDetailAdapter(private val rootfa : Fragment, private val activity : Activity, private val context: Context, private val items: MutableList<MainBoardResponse.Content>) : RecyclerView.Adapter<MainBoardDetailAdapter.MainBoardDetailVH>() {

    private var viewPagerAdapter: ViewPagerAdapter? = null
    var listener : MainBoardDetailInterface? = null

    fun setInterface(mainBoardDetailInterface: MainBoardDetailInterface) {
        this.listener = mainBoardDetailInterface
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBoardDetailVH {
        val itemBinding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainBoardDetailVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainBoardDetailVH, position: Int) {
        val item: MainBoardResponse.Content = items[position]
        holder.bind(item)
    }

    inner class MainBoardDetailVH(var binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root) {
        var isExpanded = false

        fun bind(item: MainBoardResponse.Content) {
            if(item.postLikeYn == "Y") {
                binding.btnHeart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
            }
            else {
                binding.btnHeart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
            }
            if(item.saveRecipeYn == "Y") {
                binding.btnBookMark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_book_mark_1))
            }
            else {
                binding.btnBookMark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_book_mark_0))
            }

            // 레시피 세팅 ---------------------------------------------------------------
            if(item.haveRecipeYn == "Y") {
                binding.ctlRecipe.visibility = View.VISIBLE
                for(tag in item.alcoholTags) {
                    if(tag.recipeYn == "Y") {
                        binding.tvRecipeName.text = tag.name
                        binding.tvRecipeName.setTextColor(ContextCompat.getColor(context, colorHashMap[tag.color]!!))
                        binding.ivRecipeIcon.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[tag.iconName]!!))
                    }
                }
                FlexboxLayoutManager(context).apply {
                    flexWrap = FlexWrap.WRAP
                    flexDirection = FlexDirection.ROW
                    justifyContent = JustifyContent.FLEX_START
                }.let {
                    binding.rvRecipeDetail.layoutManager = it
                    val tagList : MutableList<MainBoardResponse.Ingredient> = mutableListOf()
                    for(ingre in item.ingredients) {
                            tagList.add(ingre)
                    }
                    binding.rvRecipeDetail.adapter = IngredientFlexAdapter(activity,context,tagList)
                }
                if(item.steps.isNotEmpty()) {
                    binding.rvRecipeStep.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
                    val stepList : MutableList<MainBoardResponse.Step> = mutableListOf()
                    for(s in item.steps) {
                        stepList.add(s)
                    }
                    binding.rvRecipeStep.adapter = RecipeStepAdapter(activity,context,stepList)
                }
                binding.ctlRecipeContent.setOnClickListener {
                    if(!isExpanded) {
                        binding.ctlRecipeContent.background = ContextCompat.getDrawable(context, R.drawable.custom_round_background_black_and_body_14dp)
                        binding.ivRecipeUnderArrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_under_arrow_1))
                        binding.exlRecipeDetail.expand()
                    }
                    else {
                        binding.ctlRecipeContent.background = ContextCompat.getDrawable(context, R.drawable.custom_round_background_black_14dp)
                        binding.ivRecipeUnderArrow.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_under_arrow_0))
                        binding.exlRecipeDetail.collapse()
                    }
                    isExpanded = !isExpanded
                }
            }
            else {
                binding.ctlRecipe.visibility = View.GONE
            }
            // 레시피 세팅 ---------------------------------------------------------------
            binding.lottieLike.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    binding.lottieLike.visibility = View.INVISIBLE
                }
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {
                    binding.lottieLike.visibility = View.VISIBLE
                }
            })

            binding.tvGoodCnt.text = "좋아요 ${item.postLikeCnt}개"
            binding.tvDate.text = item.createdDate.substring(0,10)
            if (item.commentCnt != 0) {
                binding.ctlCommentContainer.visibility = View.VISIBLE
                binding.tvCommentCnt.text = "댓글 ${item.commentCnt}개 모두보기"
                binding.tvFirstCommentName.text = item.commentNickname
                binding.tvFirstCommentContent.text = item.comment
            }
            else {
                binding.ctlCommentContainer.visibility = View.GONE
            }
            binding.tvNickName.text = item.nickname
            binding.tvContent.text = item.content
            binding.vpPager.isSaveEnabled = false
            binding.vpPager.isUserInputEnabled = true
            viewPagerAdapter = ViewPagerAdapter(rootfa).apply {
                for (img in item.images) {
                    addFragment(ImageFragment(img.filePath))
                }
            }
            binding.vpPager.adapter = viewPagerAdapter
            binding.wormDotsIndicator.setViewPager2(binding.vpPager)
            FlexboxLayoutManager(context).apply {
                flexWrap = FlexWrap.WRAP
                flexDirection = FlexDirection.ROW
                justifyContent = JustifyContent.FLEX_START
            }.let {
                binding.rvTag.layoutManager = it
                val tagList : MutableList<MainBoardResponse.AlcoholTag> = mutableListOf()
                for(tag in item.alcoholTags) {
                    if(tag.recipeYn == "N") {
                        tagList.add(tag)
                    }
                }
                binding.rvTag.adapter = MainBoardTagFlexAdapter(activity,context,tagList)
            }
            binding.btnHeart.setOnClickListener {

                listener?.clickLike(item.postId)
                if(item.postLikeYn == "Y") {
                    binding.btnHeart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
                    item.postLikeYn = "N"
                    item.postLikeCnt -= 1
                    binding.tvGoodCnt.text = "좋아요 ${item.postLikeCnt}개"
                }
                else {
                    binding.btnHeart.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
                    item.postLikeYn = "Y"
                    item.postLikeCnt += 1
                    binding.tvGoodCnt.text = "좋아요 ${item.postLikeCnt}개"
                    binding.lottieLike.playAnimation()
                }
                ApplicationClass.mainBoardList = items
            }
            binding.btnComment.setOnClickListener {
                activity.findNavController(R.id.main_nav_host_fragment).navigate(MainBoardDetailFragmentDirections.actionMainBoardDetailFragmentToCommentFragment(item.postId, item.nickname))
            }
            binding.ctlCommentContainer.setOnClickListener {
                activity.findNavController(R.id.main_nav_host_fragment).navigate(MainBoardDetailFragmentDirections.actionMainBoardDetailFragmentToCommentFragment(item.postId, item.nickname))
            }
        }
    }

    private inner class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
//        var fragments: HashMap<Int, Fragment> = HashMap()
        var fragments : MutableList<Fragment> = mutableListOf()

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            notifyItemInserted(fragments.size - 1)
        }
    }

    companion object{
        const val TAG = "MainBoardDetailAdapter"
    }
}
