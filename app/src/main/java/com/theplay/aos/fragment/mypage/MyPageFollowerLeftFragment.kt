package com.theplay.aos.fragment.mypage

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
import com.theplay.aos.iadapter.MyPageFollowAdapter
import com.theplay.aos.iadapter.MyPageFollowAdapterInterface
import com.theplay.aos.item.MyPageFollowItem

class MyPageFollowerLeftFragment() : BaseKotlinFragment<FragmentMyPageFollowerLeftBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_follower_left

    var itemList : MutableList<MyPageFollowItem> = mutableListOf()
    private val viewModel by lazy { MyPageFollowViewModel() }
    private var adapterListener : MyPageFollowAdapterInterface? = null

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterListener = object : MyPageFollowAdapterInterface{
            override fun clickedLeft(userId: Int, userName : String) {
                // plan 삭제하기
                val dialog = CustomDialogDeleteFollower(requireContext(),userName,userId).apply {
                    setDialogListener(object : CustomDialogDeleteFollowerInterface{
                        override fun onFirstClicked() { // plan 다이얼로그 정말로 삭제
                            viewModel.deleteFollower(userId)
                            dismiss()
                        }
                        override fun onSecondClicked() { // plan 다이얼로그 삭제 안 함
                            dismiss()
                        }
                    })
                }
                dialog.show()
            }

            override fun clickedRight(userId: Int, userName : String) {
                // plan 차단하기
                val dialog = CustomDialogBanFollow(requireContext(),userName, userId).apply {
                    setDialogListener(object : CustomDialogBanFollowInterface{
                        override fun onFirstClicked() {
                            viewModel.banFollower(userId)
                            dismiss()
                        }

                        override fun onSecondClicked() {
                            dismiss()
                        }
                    })
                }
                dialog.show()
            }
        }
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
                    binding.rv.adapter = MyPageFollowAdapter(FRAG_LEFT, requireActivity(), requireContext(), itemList).apply {
                        adapterListener?.let { listener -> setListener(listener) }
                    }
                }
                else {
                    showCustomToast(it.msg)
                }
            }
        })
        viewModel.deleteFollowerResponse.observe(this@MyPageFollowerLeftFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollower()
                }
                else showCustomToast(it.msg)
            }
        })
        viewModel.banFollowerResponse.observe(this@MyPageFollowerLeftFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollower()
                }
                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        viewModel.getFollower()
    }


    companion object {
        const val TAG = "MyPageFollowerLeftFragment"
        const val FRAG_LEFT = 1
    }
}