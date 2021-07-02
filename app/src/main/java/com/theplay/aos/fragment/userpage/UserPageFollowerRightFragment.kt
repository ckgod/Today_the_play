package com.theplay.aos.fragment.userpage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogBanFollow
import com.theplay.aos.customview.CustomDialogBanFollowInterface
import com.theplay.aos.customview.CustomDialogCancelFollowing
import com.theplay.aos.customview.CustomDialogCancelFollowingInterface
import com.theplay.aos.databinding.FragmentMyPageFollowerRightBinding
import com.theplay.aos.databinding.FragmentUserPageFollowerRightBinding
import com.theplay.aos.iadapter.MyPageFollowAdapter
import com.theplay.aos.iadapter.MyPageFollowAdapterInterface
import com.theplay.aos.item.MyPageFollowItem

class UserPageFollowerRightFragment() : BaseKotlinFragment<FragmentUserPageFollowerRightBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_follower_right

    var itemList : MutableList<MyPageFollowItem> = mutableListOf()
//    private val viewModel by lazy { MyPageFollowViewModel() }
    private var adapterListener : MyPageFollowAdapterInterface? = null

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun initDataBinding() {
//        viewModel.followingListResponse.observe(this@UserPageFollowerRightFragment, Observer {
//            if(it == null) showNetworkError()
//            else{
//                Log.d(TAG, it.msg)
//                if(it.code == 0) {
//                    itemList = mutableListOf()
//                    for(i in it.list) {
//                        itemList.add(MyPageFollowItem(i.nickname,i.id))
//                    }
//                    binding.rv.adapter = MyPageFollowAdapter(FRAG_RIGHT, requireActivity(), requireContext(), itemList).apply {
//                        adapterListener?.let { listener -> setListener(listener) }
//                    }
//                }
//            }
//        })
    }

    override fun initAfterBinding() {
    }

    override fun reLoadUI() {
//        viewModel.getFollowing()
    }


    companion object {
        const val TAG = "UserPageFollowerRightFragment"
        const val FRAG_RIGHT = 2
    }
}