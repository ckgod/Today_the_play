package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogBanFollow
import com.theplay.aos.customview.CustomDialogBanFollowInterface
import com.theplay.aos.customview.CustomDialogDeleteFollower
import com.theplay.aos.customview.CustomDialogDeleteFollowerInterface
import com.theplay.aos.databinding.FragmentMyPageFollowerLeftBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.databinding.FragmentUserPageFollowerLeftBinding
import com.theplay.aos.iadapter.MyPageFollowAdapter
import com.theplay.aos.iadapter.MyPageFollowAdapterInterface
import com.theplay.aos.item.MyPageFollowItem

class UserPageFollowerLeftFragment(private val userId : Int) : BaseKotlinFragment<FragmentUserPageFollowerLeftBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_follower_left

    var itemList : MutableList<MyPageFollowItem> = mutableListOf()
    private val viewModel by lazy { UserPageFollowViewModel() }

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
//        binding.rv.adapter = MyPageFollowAdapter(requireActivity(), requireContext(), itemList)
    }

    override fun initDataBinding() {
        viewModel.followerListResponse.observe(this@UserPageFollowerLeftFragment, Observer {
            if(it == null) showNetworkError()
            else{
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    itemList = mutableListOf()
                    for(i in it.list) {
                        itemList.add(MyPageFollowItem(i.nickname,i.id))
                    }
                    binding.rv.adapter = MyPageFollowAdapter(3, requireActivity(), requireContext(), itemList)
                }
                else {
//                    showCustomToast(it.msg)
                }
            }
        })
    }

    override fun initAfterBinding() {
        viewModel.getFollower(userId)
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "UserPageFollowerLeftFragment"
    }
}