package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.ApplicationClass.Companion.myLikedPost
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageGoodBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MyPageGoodAdapter
import com.theplay.aos.item.MyPageGoodItem

class MyPageGoodFragment() : BaseKotlinFragment<FragmentMyPageGoodBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_good

    private val viewModel by lazy { MyPageGoodViewModel() }

    var itemList : MutableList<MyPageGoodItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun initDataBinding() {
        viewModel.getLikedResponse.observe(this@MyPageGoodFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    Log.d(TAG, "good posts size : ${it.data.content.size}")
                    itemList = mutableListOf()
                    myLikedPost = mutableListOf()
                    for(content in it.data.content) {
                        itemList.add(MyPageGoodItem(content.images[0].filePath, content.postId))
                        myLikedPost?.add(content)
                    }
                    binding.rv.adapter = MyPageGoodAdapter(requireActivity(), requireContext(), itemList)
                }
            }
        })
    }

    override fun initAfterBinding() {
        if(myLikedPost == null) {
            viewModel.getLikedPost(0,30)
        }
    }

    override fun reLoadUI() {
        myLikedPost?.let {
            itemList = mutableListOf()
            for(content in it) {
                itemList.add(MyPageGoodItem(content.images[0].filePath, content.postId))
            }
            binding.rv.adapter = MyPageGoodAdapter(requireActivity(), requireContext(), itemList)
        }
    }

    companion object {
        const val TAG = "MyPageGoodFragment"
    }
}