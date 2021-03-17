package com.theplay.aos.fragment.mypage

import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageBoardPersonalBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.item.FollowItem

class MyPageBoardPersonalFragment() : BaseKotlinFragment<FragmentMyPageBoardPersonalBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_board_personal

    var itemList : MutableList<FollowItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        var imageList : MutableList<String> = mutableListOf()
        imageList.add("dafsdf")
        imageList.add("dafsdf")
        imageList.add("dafsdf")
        imageList.add("dafsdf")
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        itemList.add(FollowItem(imageList,"sss"))
        binding.rv.adapter = FollowAdapter(this, requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MyPageBoardPersonalFragment"
    }
}