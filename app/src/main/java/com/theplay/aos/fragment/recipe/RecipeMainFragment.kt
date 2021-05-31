package com.theplay.aos.fragment.recipe

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentRecipeMainBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.RecipeAdapter
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.RecipeItem
import com.theplay.aos.item.RecipeNameItem

class RecipeMainFragment() : BaseKotlinFragment<FragmentRecipeMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recipe_main

    private val viewModel by lazy { RecipeMainViewModel() }

    var itemList: MutableList<RecipeItem> = mutableListOf()

    var imageList: MutableList<RecipeImageItem> = mutableListOf()
    var colorList: MutableList<RecipeColorItem> = mutableListOf()
    var nameList: MutableList<RecipeNameItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        setDummy()
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getHotRecipe(0,10)
        }
    }

    override fun initDataBinding() {
        viewModel.hotRecipeResponse.observe(this@RecipeMainFragment, Observer {
            if(it == null) {
                showNetworkError()
            }
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    for(item in it.data.content) {
                        imageList = mutableListOf()
                        colorList = mutableListOf()
                        nameList = mutableListOf()
                        for(image in item.images) {
                            imageList.add(RecipeImageItem(image))
                        }
                        for(material in item.ingredients) {
                            colorList.add(RecipeColorItem(colorHashMap[material.color]!!))
                            nameList.add(RecipeNameItem(material.name, colorHashMap[material.color]!!))
                        }
                        itemList.add(RecipeItem(item.alcoholTagName,imageList,colorList,nameList))
                    }
                    binding.rv.adapter = RecipeAdapter(requireActivity(), requireContext(), itemList)
                }
            }
            hideProgress()
            binding.refreshLayout.isRefreshing = false
        })

    }

    override fun initAfterBinding() {
        showProgress()
        viewModel.getHotRecipe(0,10)
    }

    override fun reLoadUI() {
    }

    fun setDummy() {
        itemList = mutableListOf()

        imageList = mutableListOf(
            RecipeImageItem("tmp"),
            RecipeImageItem("tmp"),
            RecipeImageItem("tmp"),
            RecipeImageItem("tmp"),
            RecipeImageItem("tmp")
        )

        colorList = mutableListOf(
            RecipeColorItem(R.color.ingre7),
            RecipeColorItem(R.color.mainColor),
            RecipeColorItem(R.color.ingre8)
        )

        nameList = mutableListOf(
            RecipeNameItem("카스", R.color.ingre7),
            RecipeNameItem("참이슬", R.color.mainColor),
            RecipeNameItem("콜라", R.color.ingre8)
        )

        itemList.add(RecipeItem("고진감래", imageList, colorList, nameList))

        colorList = mutableListOf(
            RecipeColorItem(R.color.ingre4),
            RecipeColorItem(R.color.colorWhite)
        )

        nameList = mutableListOf(
            RecipeNameItem("사이다", R.color.ingre4),
            RecipeNameItem("스미노프", R.color.colorWhite)
        )

        itemList.add(RecipeItem("모히또",imageList,colorList,nameList))
    }


    companion object {
        const val TAG = "RecipeMainFragment"
    }
}