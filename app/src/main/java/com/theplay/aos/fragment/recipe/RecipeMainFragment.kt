package com.theplay.aos.fragment.recipe

import androidx.recyclerview.widget.LinearLayoutManager
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

    var itemList: MutableList<RecipeItem> = mutableListOf()

    var imageList: MutableList<RecipeImageItem> = mutableListOf()
    var colorList: MutableList<RecipeColorItem> = mutableListOf()
    var nameList: MutableList<RecipeNameItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        setDummy()
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

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
        binding.rv.adapter = RecipeAdapter(requireActivity(), requireContext(), itemList)
    }


    companion object {
        const val TAG = "RecipeMainFragment"
    }
}