package com.theplay.aos.iadapter

import android.animation.Animator
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.*
import android.view.animation.AlphaAnimation
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.MotionEventCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.ItemMainBoardBinding
import com.theplay.aos.fragment.home.HomeFragmentDirections
import com.theplay.aos.utils.ViewUtils

interface MainBoardAdapterListener{
    fun DoubleTap(postId : Int)
    fun clickedLike(postId : Int)
}

class MainBoardAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MainBoardResponse.Content>) : RecyclerView.Adapter<MainBoardAdapter.MainBoardVH>() {
    var listener : MainBoardAdapterListener? = null

    fun setInterface(mainBoardAdapterListener: MainBoardAdapterListener) {
        this.listener = mainBoardAdapterListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBoardVH {
        val itemBinding = ItemMainBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainBoardVH(itemBinding)
    }


    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainBoardVH, position: Int) {
        val item: MainBoardResponse.Content = items[position]
        holder.bind(item, position)
    }

    inner class MainBoardVH(var binding: ItemMainBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        var visibleTag = false
        private lateinit var mDetector: GestureDetectorCompat

        @SuppressLint("ClickableViewAccessibility")
        fun bind(item: MainBoardResponse.Content, position: Int) {
            var imageHeight = ((ViewUtils.getStandardSize_X(activity)/2 * 0.93) / 161) * 214
//            Log.d(TAG, imageHeight.toString())
            binding.ivMainContent.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()
            var layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager.stackFromEnd = true
            binding.rvTag.layoutManager = layoutManager
            var tagList : MutableList<MainBoardResponse.AlcoholTag> = mutableListOf()
            for(tag in item.alcoholTags) {
                tagList.add(tag)
            }
            binding.rvTag.adapter = MainBoardTagAdapter(activity,context,tagList)
            binding.ctlFirst.setOnClickListener { clickTag() }
            binding.ctlSecond.setOnClickListener { clickTag() }
            binding.ctlThird.setOnClickListener { clickTag() }
            binding.ctlMore.setOnClickListener { clickTag() }

            Glide.with(context).load(item.images[0].filePath)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,context).toInt())))
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(binding.ivMainContent)

            if(item.postLikeYn == "Y"){
                binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
            }
            else {
                binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
            }

            when(item.alcoholTags.size) {
                3 -> {
                    binding.ctlMore.visibility = View.GONE
                    binding.ivFirst.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[0].iconName]!!))
                    binding.ivSecond.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[1].iconName]!!))
                    binding.ivThird.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[2].iconName]!!))
                }
                2 -> {
                    binding.ctlMore.visibility = View.GONE
                    binding.ctlThird.visibility = View.GONE
                    binding.ivFirst.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[0].iconName]!!))
                    binding.ivSecond.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[1].iconName]!!))
                }
                1 -> {
                    binding.ctlMore.visibility = View.GONE
                    binding.ctlThird.visibility = View.GONE
                    binding.ctlSecond.visibility = View.GONE
                    binding.ivFirst.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[0].iconName]!!))
                }
                else -> {
                    binding.ivFirst.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[0].iconName]!!))
                    binding.ivSecond.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[1].iconName]!!))
                    binding.ivThird.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.alcoholTags[2].iconName]!!))
                }
            }
            binding.ivMainContent.setOnClickListener {
//                activity.findNavController(R.id.main_nav_host_fragment).navigate(HomeFragmentDirections.actionHomeFragmentToMainBoardDetailFragment(position))
            }
            binding.ivGood.setOnClickListener {
                Log.d(TAG, "heart clicked!!")
                listener?.clickedLike(postId = item.postId)
                if(item.postLikeYn == "Y") {
                    binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
                    item.postLikeYn = "N"
                    item.postLikeCnt -= 1
                }
                else {
                    binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
                    item.postLikeYn = "Y"
                    item.postLikeCnt += 1
                    binding.lottieLike.playAnimation()
                }
                ApplicationClass.mainBoardList = items
            }
            binding.lottieLike.addAnimatorListener(object : Animator.AnimatorListener{
                override fun onAnimationRepeat(animation: Animator?) {}
                override fun onAnimationEnd(animation: Animator?) {
                    binding.lottieLike.visibility = View.INVISIBLE
                }
                override fun onAnimationCancel(animation: Animator?) {}
                override fun onAnimationStart(animation: Animator?) {}
            })
            mDetector = GestureDetectorCompat(context,MyGestureListener(item.postId))
            mDetector.setOnDoubleTapListener(object : GestureDetector.OnDoubleTapListener{
                override fun onDoubleTap(e: MotionEvent?): Boolean {
                    Log.d(TAG, "double tap")
                    listener?.DoubleTap(item.postId)
                    if(item.postLikeYn == "Y") {
                        binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_false))
                        binding.lottieLike.visibility = View.INVISIBLE
                        item.postLikeYn = "N"
                        item.postLikeCnt -= 1
                    }
                    else {
                        binding.ivGood.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_heart_true))
                        binding.lottieLike.visibility = View.VISIBLE
                        item.postLikeYn = "Y"
                        item.postLikeCnt += 1
                        binding.lottieLike.playAnimation()
                    }
                    ApplicationClass.mainBoardList = items
                    return true
                }

                override fun onDoubleTapEvent(e: MotionEvent?): Boolean {
                    return true
                }

                override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                    Log.d(TAG, "single tap")
                    activity.findNavController(R.id.main_nav_host_fragment).navigate(HomeFragmentDirections.actionHomeFragmentToMainBoardDetailFragment(position))
                    return true
                }
            })
            binding.ivMainContent.setOnTouchListener { v, event ->
                mDetector.onTouchEvent(event)
            }
        }
        inner class MyGestureListener(var postId: Int) : GestureDetector.SimpleOnGestureListener() {
            override fun onDoubleTap(e: MotionEvent?): Boolean {
                return super.onDoubleTap(e)
            }

            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                return super.onSingleTapConfirmed(e)
            }
        }
        private fun clickTag() {
            if(!visibleTag) {
                val animation = AlphaAnimation(0f, 1f)
                animation.setDuration(150)
                binding.rvTag.visibility = View.VISIBLE
                binding.rvTag.animation = animation
            }
            else {
                val animation = AlphaAnimation(1f, 0f)
                animation.setDuration(150)
                binding.rvTag.visibility = View.INVISIBLE
                binding.rvTag.animation = animation
            }
            visibleTag = !visibleTag
        }
    }



    companion object{
        const val TAG = "MainBoardAdapter"
    }
}
