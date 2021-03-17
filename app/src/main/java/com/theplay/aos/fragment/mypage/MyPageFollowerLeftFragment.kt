package com.theplay.aos.fragment.mypage

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

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        itemList.add(MyPageFollowItem("재밌는 칵테일"))
        binding.rv.adapter = MyPageFollowAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MyPageFollowerLeftFragment"
    }
}