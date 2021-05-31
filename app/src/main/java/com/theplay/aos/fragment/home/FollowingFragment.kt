package com.theplay.aos.fragment.home

import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentFollowingBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.item.FollowItem

class FollowingFragment() : BaseKotlinFragment<FragmentFollowingBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_following

    var itemList : MutableList<FollowItem> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        var imageList : MutableList<String> = mutableListOf()
        binding.rv.adapter = FollowAdapter(this, requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }

    companion object {
        const val TAG = "FollowingFragment"
    }
}