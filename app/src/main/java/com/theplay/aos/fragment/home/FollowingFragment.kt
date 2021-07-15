package com.theplay.aos.fragment.home

import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.ReportRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogReportPost
import com.theplay.aos.customview.CustomDialogReportPostInterface
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
            override fun clickMore(postId: Int, userId: Int, tagId : Int, nickName : String) {
                // plan 더보기 메뉴 바텀시트 띄우기
                var bottomSheet = BottomSheetMainPost(nickName, this@FollowingFragment).apply {
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
                                    showCustomToast(getString(R.string.not_implementation))
                                }
                                3-> { // plan 신고하기
                                    val dialog =  CustomDialogReportPost(requireContext()).apply {
                                        setDialogListener(object : CustomDialogReportPostInterface {
                                            override fun clickCancel() {
                                                dismiss()
                                            }
                                            override fun clickSpam() {
                                                viewModel.postReport(ReportRequest("0",postId))
                                                dismiss()
                                            }
                                            override fun click19() {
                                                viewModel.postReport(ReportRequest("1",postId))
                                                dismiss()
                                            }
                                            override fun clickNotMatch() {
                                                viewModel.postReport(ReportRequest("2",postId))
                                                dismiss()
                                            }
                                        })
                                    }.show()
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
                    if(tmp.isEmpty()) {
                        binding.ctlEmpty.visibility = View.VISIBLE
                    }
                    else {
                        binding.ctlEmpty.visibility = View.GONE
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
        viewModel.postReportResponse.observe(this@FollowingFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    showCustomToast("신고되었습니다.")
                }
                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun onResume() {
        super.onResume()
        ApplicationClass.followingPostList?.let {
            var itemList = it
            if(itemList.isEmpty()) {
                binding.ctlEmpty.visibility = View.VISIBLE
            }
            else {
                binding.ctlEmpty.visibility = View.GONE
            }
            binding.rv.adapter = MainBoardDetailAdapter(this, requireActivity(), requireContext(), itemList).apply {
                mainBoardDetailInterface?.let { mainBoardDetailInterface -> setInterface(mainBoardDetailInterface) }
            }
        }
    }

    override fun reLoadUI() {

    }

    companion object {
        const val TAG = "FollowingFragment"
    }
}