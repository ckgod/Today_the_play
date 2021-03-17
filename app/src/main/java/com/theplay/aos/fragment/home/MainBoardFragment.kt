package com.theplay.aos.fragment.home

import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMainBoardBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MainBoardAdapter
import com.theplay.aos.item.MainBoardItem

class MainBoardFragment() : BaseKotlinFragment<FragmentMainBoardBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main_board


    var itemList : MutableList<MainBoardItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        itemList.add(MainBoardItem(true,1))
        binding.rv.adapter = MainBoardAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MainBoardFragment"
    }
}