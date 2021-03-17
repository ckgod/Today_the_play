package com.theplay.aos.fragment.mypage

import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageBoardAllBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MyPageBoardAllAdapter
import com.theplay.aos.item.MyPageBoardAllItem

class MyPageBoardAllFragment() : BaseKotlinFragment<FragmentMyPageBoardAllBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_board_all

    var itemList : MutableList<MyPageBoardAllItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        itemList.add(MyPageBoardAllItem("aasdf"))
        binding.rv.adapter = (MyPageBoardAllAdapter(requireActivity(),requireContext(),itemList))
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MyPageBoardAllFragment"
    }
}