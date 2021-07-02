package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentFollowingBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.iadapter.MainBoardDetailAdapter
import com.theplay.aos.iadapter.MainBoardDetailInterface
import com.theplay.aos.item.FollowItem

class FollowingFragment() : BaseKotlinFragment<FragmentFollowingBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_following

    private val viewModel by lazy { FollowViewModel() }
    var mainBoardDetailInterface : MainBoardDetailInterface? = null

    override fun initStartView() {
        mainBoardDetailInterface = object : MainBoardDetailInterface {
            override fun clickLike(postId: Int) {
                viewModel.postLike(postId)
            }
            override fun clickMore(postId: Int, userId: Int, tagId : Int) {
                // plan 더보기 메뉴 바텀시트 띄우기
                var bottomSheet = BottomSheetMainPost(userId, this@FollowingFragment).apply {
                    setMenuBottomSheetInterface(object : MenuBottomSheetListener {
                        override fun clickMenu(type: Int) {
                            Log.d(MainBoardDetailFragment.TAG, "$type clicked!")
                            when(type) {
                                1-> { // plan 레시피 저장
                                    Log.d(TAG, "tag id : $tagId")
                                    if(tagId == -1) showCustomToast("레시피가 없는 게시글입니다.")
                                    else {

                                    }
                                }
                                2-> { // plan 공유하기
                                }
                                3-> { // plan 신고하기

                                }
                            }
                        }
                    })
                }.show(requireActivity().supportFragmentManager, TAG)
            }

        }
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        viewModel.getFollowPeed(0,20)
    }

    override fun initDataBinding() {
        viewModel.followPeedResponse.observe(this@FollowingFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    var tmp : MutableList<MainBoardResponse.Content> = mutableListOf()
                    for(i in it.data.content) {
                        tmp.add(i)
                    }
                    ApplicationClass.followingPostList = tmp
                    binding.rv.adapter = MainBoardDetailAdapter(this, requireActivity(), requireContext(), tmp).apply {
                        mainBoardDetailInterface?.let { mainBoardDetailInterface -> setInterface(mainBoardDetailInterface) }
                    }
                }
            }
        })
        viewModel.postLikeResponse.observe(this@FollowingFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {

                }
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        ApplicationClass.followingPostList?.let {
            var itemList = it
            binding.rv.adapter = MainBoardDetailAdapter(this, requireActivity(), requireContext(), itemList).apply {
                mainBoardDetailInterface?.let { mainBoardDetailInterface -> setInterface(mainBoardDetailInterface) }
            }
        }
    }

    companion object {
        const val TAG = "FollowingFragment"
    }
}