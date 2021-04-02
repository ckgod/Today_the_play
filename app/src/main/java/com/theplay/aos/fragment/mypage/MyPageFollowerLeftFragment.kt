package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageFollowerLeftBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MyPageFollowAdapter
import com.theplay.aos.item.MyPageFollowItem

class MyPageFollowerLeftFragment() : BaseKotlinFragment<FragmentMyPageFollowerLeftBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_follower_left

    var itemList : MutableList<MyPageFollowItem> = mutableListOf()
    private val viewModel by lazy { MyPageFollowViewModel() }

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.rv.adapter = MyPageFollowAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {
        viewModel.followerListResponse.observe(this@MyPageFollowerLeftFragment, Observer {
            if(it == null) showNetworkError()
            else{
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    itemList = mutableListOf()
                    for(i in it.list) {
                        itemList.add(MyPageFollowItem(i.nickname,i.id))
                    }
                    binding.rv.adapter = MyPageFollowAdapter(requireActivity(), requireContext(), itemList)
                }
            }
        })
    }

    override fun initAfterBinding() {
        viewModel.getFollower()
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MyPageFollowerLeftFragment"
    }
}