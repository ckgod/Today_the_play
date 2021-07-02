package com.theplay.aos.fragment.userpage

import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentUserPageBoardAllBinding
import com.theplay.aos.iadapter.MyPageBoardAllAdapter
import com.theplay.aos.item.MyPageBoardAllItem

class UserPageBoardAllFragment() : BaseKotlinFragment<FragmentUserPageBoardAllBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_board_all

    var itemList : MutableList<MyPageBoardAllItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        ApplicationClass.tmpPostList?.let {
            itemList = mutableListOf()
            for(i in it) {
                itemList.add(MyPageBoardAllItem(i.images[0].filePath, i.postId))
            }
            binding.rv.adapter = (MyPageBoardAllAdapter(requireActivity(),requireContext(),itemList))
        }
    }


    companion object {
        const val TAG = "UserPageBoardAllFragment"
    }
}