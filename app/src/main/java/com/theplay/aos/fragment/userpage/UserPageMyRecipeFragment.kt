package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageMyRecipeBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.databinding.FragmentUserPageMyRecipeBinding
import com.theplay.aos.iadapter.MyRecipeAdapter
import com.theplay.aos.item.MyRecipeContentItem
import com.theplay.aos.item.MyRecipeItem

class UserPageMyRecipeFragment(private val userId : Int) : BaseKotlinFragment<FragmentUserPageMyRecipeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_my_recipe
    private var itemList : MutableList<MyRecipeItem> = mutableListOf()
    private val viewModel by lazy { UserPageMyRecipeViewModel() }

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
        binding.rv.adapter = MyRecipeAdapter(requireActivity(),requireContext(),itemList)
    }

    override fun initDataBinding() {
        viewModel.getUserRecipeResponse.observe(this@UserPageMyRecipeFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    Log.d(TAG, it.toString())
                    var recipeList : MutableList<MyRecipeItem> = mutableListOf()
                    for (recipe in it.data.content) {
                        var contentList : MutableList<MyRecipeContentItem> = mutableListOf()
                        for(content in recipe.ingredients) {
                            contentList.add(MyRecipeContentItem(content.name, content.color,content.color))
                        }
                        recipeList.add(MyRecipeItem(recipe.alcoholTag.name, contentList))
                    }
                    binding.rv.adapter = MyRecipeAdapter(requireActivity(), requireContext(), recipeList)
                }
//                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        viewModel.getMyRecipe(userId, 0,20)
    }


    companion object {
        const val TAG = "UserPageMyRecipeFragment"
    }
}