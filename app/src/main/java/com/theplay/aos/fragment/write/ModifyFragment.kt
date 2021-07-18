package com.theplay.aos.fragment.write

import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.ModifyPostRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.AddDrinkDialog
import com.theplay.aos.customview.AddDrinkListener
import com.theplay.aos.customview.CustomDialogDeleteTag
import com.theplay.aos.customview.CustomDialogDeleteTagInterface
import com.theplay.aos.databinding.FragmentModifyBinding
import com.theplay.aos.fragment.home.ImageFragment
import com.theplay.aos.iadapter.DrinkAdapter
import com.theplay.aos.iadapter.DrinkAdapterInterface
import com.theplay.aos.item.DrinkItem
import com.theplay.aos.item.RecipeMaterialItem
import com.theplay.aos.utils.DrinkUtil


class ModifyFragment() : BaseKotlinFragment<FragmentModifyBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_modify

    private var viewPagerAdapter: ViewPagerAdapter? = null
    private val viewModel by lazy { WriteViewModel() }

    var hasRecipe : Boolean = false
    private var postId : Int = -1

    var drinkList : MutableList<DrinkItem> = mutableListOf()
    var ingredientList : MutableList<ModifyPostRequest.Ingredient> = mutableListOf()
    var stepList : MutableList<ModifyPostRequest.Step> = mutableListOf()
    var alcoholTagList : MutableList<ModifyPostRequest.AlcoholTag> = mutableListOf()
    lateinit var images: MutableList<MainBoardResponse.Image>
    lateinit var drinkAdapterListener : DrinkAdapterInterface

    override fun initStartView() {
        ApplicationClass.PostTemplate?.let {
            postId = it.postId
            images = it.images
            binding.etContent.setText(it.content)
            var ingreList : MutableList<RecipeMaterialItem> = mutableListOf()
            for(i in it.ingredients) {
                ingreList.add(RecipeMaterialItem(iconHashMap[i.iconName]!!,i.name, colorHashMap[i.color]!!))
            }
            for(i in it.alcoholTags) {
//                alcoholTagList.add(ModifyPostRequest.AlcoholTag(i.color,i.iconName,i.name,i.recipeYn))
                var hasRecipe = false
                if (i.recipeYn == "Y") {
                    hasRecipe = true
                }
                drinkList.add(DrinkItem(iconHashMap[i.iconName]!!,i.name,hasRecipe, colorHashMap[i.color]!!,ingreList))
            }
        }
        binding.btnBack.setOnClickListener {
            DrinkUtil.clearRecipeSaved()
            requireActivity().finish()
//            requireActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
        }

        // note --------------- 이미지 뷰페이저 세팅 -----------------------------------------------------------------------------------------
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = true
        viewPagerAdapter = ViewPagerAdapter(this)
        for (item in images) {
            viewPagerAdapter?.addFragment(ImageFragment(item.filePath))
        }
        binding.vpPager.adapter = viewPagerAdapter
        binding.wormDotsIndicator.setViewPager2(binding.vpPager)
        // note --------------- 이미지 뷰페이저 세팅 -----------------------------------------------------------------------------------------


        binding.btnPlusRecipe.setOnClickListener {
            if (drinkList.size >= 6) {
                showCustomToast("최대 6개까지만 태그할 수 있습니다.")
                return@setOnClickListener
            }
            val dialog = AddDrinkDialog(requireContext(), requireActivity()).apply {
                setDialogListener(object : AddDrinkListener{
                    override fun onComplete(item: DrinkItem) {
                        drinkList.add(item)
                        binding.rvDrinks.adapter?.notifyDataSetChanged()
                        dismiss()
                    }
                })
            }
            dialog.show()
        }

        binding.rvDrinks.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvDrinks.adapter = DrinkAdapter(requireActivity(), requireContext(), drinkList).apply {
            setInterface(object : DrinkAdapterInterface{
                override fun clickDelete(icon: Int, colorType: Int, name: String, position: Int) {
                    val dialog = CustomDialogDeleteTag(requireContext(), icon, name, colorType).apply {
                        setDialogListener(object : CustomDialogDeleteTagInterface{
                            override fun onFirstClicked() { // plan 삭제
                                drinkList.removeAt(position)
                                binding.rvDrinks.adapter?.notifyDataSetChanged()
                                dismiss()
                            }

                            override fun onSecondClicked() { // plan 취소
                                dismiss()
                            }
                        })
                    }.show()
                }

                override fun clickRecipe(icon: Int, name: String, colorType: Int) {
                    requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(ModifyFragmentDirections.actionModifyFragmentToWriteRecipeFragment(icon, name, colorType))
                }
            })
        }

        binding.btnComplete.setOnClickListener {
            if(binding.etContent.text.isEmpty()) {
                showCustomToast("글을 입력해주세요")
                return@setOnClickListener
            }
            if(drinkList.isEmpty()) {
                showCustomToast("술을 하나 이상 태그해주세요")
                return@setOnClickListener
            }

            var hasRecipeYn = "N"
            if(hasRecipe) {
                hasRecipeYn = "Y"
            }
            for(drink in drinkList) {
                var reYn = "N"
                if(drink.hasRecipe) reYn = "Y"

                val iconName = iconHashMap.filterValues { it == drink.icon }.keys.elementAt(0)
                val color = ApplicationClass.colorToCodeHashMap[drink.colorType]!!
                val realIconName = DrinkUtil.convertIconFromColor(iconName, color)
                Log.d(TAG, "drink color is ${color}, icon is ${realIconName}, name is ${drink.name}")
                alcoholTagList.add(ModifyPostRequest.AlcoholTag(color,realIconName,drink.name,reYn))
            }
            val modifyPostRequest = ModifyPostRequest(alcoholTagList, binding.etContent.text.toString(),hasRecipeYn,ingredientList,stepList)
            showProgress()
            viewModel.putModifyPost(postId,modifyPostRequest)
        }
    }

    override fun initDataBinding() {
        viewModel.getMyPostResponse.observe(this@ModifyFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    val tmpList : MutableList<MainBoardResponse.Content> = mutableListOf()
                    for(i in it.data.content) {
                        tmpList.add(i)
                    }
                    userInfo?.let {
                        it.data.posts += 1
                    }
                    ApplicationClass.myPostedPost = tmpList
                    removeActivity()
                }
            }
            hideProgress()
        })
        viewModel.mainBoardResponse.observe(this@ModifyFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    ApplicationClass.mainBoardList = it.data.content
                    viewModel.getMyPost(0,50)
                }
            }
        })
        viewModel.putModifyResponse.observe(this@ModifyFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getMainBoard(0,50)
                }
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        if(DrinkUtil.checkSavedRecipe()) {
            for (drink in drinkList) {
                drink.hasRecipe = drink.name == DrinkUtil.DrinkName
            }
            hasRecipe = true
            ingredientList = mutableListOf()
            for(i in DrinkUtil.materialList) {
                ingredientList.add(ModifyPostRequest.Ingredient(i.color,i.iconName,i.name,i.quantity))
            }
            stepList = mutableListOf()
            for(i in DrinkUtil.stepList) {
                stepList.add(ModifyPostRequest.Step(i.content,i.number))
            }
            binding.rvDrinks.adapter?.notifyDataSetChanged()
        }
        Log.d(TAG, "recipe exist = $hasRecipe")
    }

    fun removeActivity() {
        requireActivity().finish()
//        requireActivity().overridePendingTransition(R.anim.slide_from_right,R.anim.slide_to_left)
    }


    private inner class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        var fragments: MutableList<Fragment> = mutableListOf()

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

    companion object {
        const val TAG = "ModifyFragment"
    }
}
