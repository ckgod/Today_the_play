package com.theplay.aos.fragment.recipe

import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentRecipeMainBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.RecipeAdapter
import com.theplay.aos.item.RecipeItem

class RecipeMainFragment() : BaseKotlinFragment<FragmentRecipeMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_recipe_main

    var itemList : MutableList<RecipeItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        itemList.add(RecipeItem("adfasdf"))
        itemList.add(RecipeItem("adfasdf"))
        itemList.add(RecipeItem("adfasdf"))
        itemList.add(RecipeItem("adfasdf"))
        binding.rv.adapter = RecipeAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "RecipeMainFragment"
    }
}