package com.theplay.aos.fragment.recipe

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentRecipeMainBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.RecipeAdapter
import com.theplay.aos.iadapter.RecipeAdapterInterface
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

    lateinit var adapterListener : RecipeAdapterInterface

    override fun initStartView() {
        adapterListener = object : RecipeAdapterInterface{
            override fun clickMore(tagName: String) {
                Log.d(TAG, "click : $tagName")
                findNavController().navigate(RecipeMainFragmentDirections.actionRecipeMainFragmentToHotRecipeActivity(tagName))
            }
        }

        binding.rv.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getHotRecipe(0,20)
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
                    itemList = mutableListOf()
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
                    binding.rv.adapter = RecipeAdapter(requireActivity(), requireContext(), itemList).apply {
                        setInterface(adapterListener)
                    }
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


    companion object {
        const val TAG = "RecipeMainFragment"
    }
}