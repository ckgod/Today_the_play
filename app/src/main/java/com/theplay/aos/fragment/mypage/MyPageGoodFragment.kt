package com.theplay.aos.fragment.mypage

import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageGoodBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MyPageGoodAdapter
import com.theplay.aos.item.MyPageGoodItem

class MyPageGoodFragment() : BaseKotlinFragment<FragmentMyPageGoodBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_good

    var itemList : MutableList<MyPageGoodItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        itemList.add(MyPageGoodItem("asdfasdf"))
        binding.rv.adapter = MyPageGoodAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MyPageGoodFragment"
    }
}