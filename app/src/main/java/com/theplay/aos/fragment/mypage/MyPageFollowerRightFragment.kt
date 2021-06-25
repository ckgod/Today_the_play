package com.theplay.aos.fragment.mypage

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
import com.theplay.aos.iadapter.MyPageFollowAdapter
import com.theplay.aos.iadapter.MyPageFollowAdapterInterface
import com.theplay.aos.item.MyPageFollowItem

class MyPageFollowerRightFragment() : BaseKotlinFragment<FragmentMyPageFollowerRightBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_follower_right

    var itemList : MutableList<MyPageFollowItem> = mutableListOf()
    private val viewModel by lazy { MyPageFollowViewModel() }
    private var adapterListener : MyPageFollowAdapterInterface? = null

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        adapterListener = object : MyPageFollowAdapterInterface{
            override fun clickedLeft(userId: Int, userName: String) {
                val dialog = CustomDialogCancelFollowing(requireContext(),userName, userId).apply {
                    setDialogListener(object : CustomDialogCancelFollowingInterface{
                        override fun onFirstClicked() {
                            viewModel.cancelFollowing(userId)
                            dismiss()
                        }

                        override fun onSecondClicked() {
                            dismiss()
                        }

                    })
                }
                dialog.show()
            }

            override fun clickedRight(userId: Int, userName: String) {
                val dialog = CustomDialogBanFollow(requireContext(), userName, userId).apply {
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
    }

    override fun initDataBinding() {
        viewModel.followingListResponse.observe(this@MyPageFollowerRightFragment, Observer {
            if(it == null) showNetworkError()
            else{
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    itemList = mutableListOf()
                    for(i in it.list) {
                        itemList.add(MyPageFollowItem(i.nickname,i.id))
                    }
                    binding.rv.adapter = MyPageFollowAdapter(FRAG_RIGHT, requireActivity(), requireContext(), itemList).apply {
                        adapterListener?.let { listener -> setListener(listener) }
                    }
                }
            }
        })
        viewModel.cancelFollowingResponse.observe(this@MyPageFollowerRightFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollowing()
                }
                else showCustomToast(it.msg)
            }
        })
        viewModel.banFollowerResponse.observe(this@MyPageFollowerRightFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollowing()
                }
                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {
    }

    override fun reLoadUI() {
        Log.d(TAG, "reLoadUI") // note 탭 옮길때마다 리로드
        viewModel.getFollowing()
    }


    companion object {
        const val TAG = "MyPageFollowerRightFragment"
        const val FRAG_RIGHT = 2
    }
}