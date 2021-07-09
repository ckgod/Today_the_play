package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.ReportRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogListener
import com.theplay.aos.customview.CustomDialogReportPost
import com.theplay.aos.customview.CustomDialogReportPostInterface
import com.theplay.aos.customview.CustomDialogTwoButton
import com.theplay.aos.databinding.FragmentMainBoardDetailBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.iadapter.MainBoardDetailAdapter
import com.theplay.aos.iadapter.MainBoardDetailInterface
import com.theplay.aos.item.FollowItem

class MainBoardDetailFragment() : BaseKotlinFragment<FragmentMainBoardDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main_board_detail

    private val viewModel by lazy { MainBoardViewModel() }
    private val safeArgs : MainBoardDetailFragmentArgs by navArgs()

    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        ApplicationClass.mainBoardList?.let {
            var selectList : MutableList<MainBoardResponse.Content> = mutableListOf()
            for (i in safeArgs.selectIdx until it.size) {
                selectList.add(it[i])
            }
            binding.rv.adapter = MainBoardDetailAdapter(this, requireActivity(), requireContext(), selectList).apply {
                setInterface(object : MainBoardDetailInterface {
                    override fun clickLike(postId: Int) {
                        viewModel.postLike(postId)
                    }

                    override fun clickMore(postId: Int, userId: Int, tagId : Int, nickName : String) {
                        // plan 더보기 메뉴 바텀시트 띄우기
                        var bottomSheet = BottomSheetMainPost(nickName, this@MainBoardDetailFragment).apply {
                            setMenuBottomSheetInterface(object : MenuBottomSheetListener {
                                override fun clickMenu(type: Int) {
                                    ApplicationClass.userInfo?.let { userInfo ->
                                        if(userInfo.data.nickname == nickName) {
                                            when(type) {
                                                1 -> { // plan 공유하기

                                                }
                                                2 -> { // plan 게시물 수정

                                                }
                                                3 -> { // plan 삭제하기
                                                    val dialog = CustomDialogTwoButton(
                                                        requireContext(),
                                                        getString(R.string.delete_post_title),
                                                        getString(R.string.delete_post_content),
                                                        getString(R.string.delete_post_delete),
                                                        getString(R.string.delete_post_no_delete)
                                                    ).apply {
                                                        setDialogListener(object : CustomDialogListener{
                                                            override fun onFirstClicked() {
                                                                showProgress()
                                                                viewModel.deletePost(postId)
                                                                for(i in selectList) {
                                                                    if(i.postId == postId) {
                                                                        selectList.remove(i)
                                                                        break
                                                                    }
                                                                }
                                                                notifyDataSetChanged()
                                                                dismiss()
                                                            }

                                                            override fun onSecondClicked() {
                                                                dismiss()
                                                            }
                                                        })
                                                    }.show()
                                                    dismiss()
                                                }
                                            }
                                        }
                                        else {
                                            when(type) {
                                                1-> { // plan 레시피 저장
                                                    Log.d(TAG, "tag id : $tagId")
                                                    if(tagId == -1) showCustomToast("레시피가 없는 게시글입니다.")
                                                    else {
                                                        viewModel.postSaveRecipe(tagId)
                                                    }
                                                    dismiss()
                                                }
                                                2-> { // plan 팔로우 하기
                                                    viewModel.postFollow(userId)
                                                    dismiss()
                                                }
                                                3-> { // plan 공유하기

                                                }
                                                4-> { // plan 신고하기
                                                    val dialog =  CustomDialogReportPost(requireContext()).apply {
                                                        setDialogListener(object : CustomDialogReportPostInterface{
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
                                    }
                                }
                            })
                        }.show(requireActivity().supportFragmentManager, BottomSheetMainPost.TAG)
                    }
                })
            }
        }
    }

    override fun initDataBinding() {
        viewModel.getLikedResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    val tmpList : MutableList<MainBoardResponse.Content> = mutableListOf()
                    for (con in it.data.content) {
                        tmpList.add(con)
                    }
                    ApplicationClass.myLikedPost = tmpList
                    ApplicationClass.userInfo?.data?.likes = tmpList.size
                }
            }
        })
        viewModel.postLikeResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getLikedPost(0,50)
                }
            }
        })
        viewModel.postFollowingResponse.observe(this@MainBoardDetailFragment, Observer{
            if(it == null) showNetworkError()
            else {
                if(it.code == 0) {
                    showCustomToast("팔로잉 되었습니다.")
                }
                else {
                    showCustomToast(it.msg)
                }
            }
        })
        viewModel.postSaveRecipeResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    if(it.data.saveYn.equals("N")) {
                        showCustomToast("삭제되었습니다.")
                        ApplicationClass.userInfo!!.data.recipes -= 1
                    }
                    else {
                        showCustomToast("저장되었습니다.")
                        ApplicationClass.userInfo!!.data.recipes += 1
                    }
                }
                else showCustomToast(it.msg)
            }
        })
        viewModel.postReportResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    showCustomToast("신고되었습니다.")
                }
                else showCustomToast(it.msg)
            }
        })
        viewModel.deletePostResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    // 지금 보고있는 메인보드 디테일 에서도 지워져야하고
                    // 마이페이지 내 게시글에서도 지워져야하고
                    // 기본 메인화면에서도 지워져야한다.
                    viewModel.getLikedPost(0,50)
                    viewModel.getMyPost(0,50)
                    viewModel.getMainBoard(0,50)
                }
                else showCustomToast(it.msg)
            }
            hideProgress()
        })
        viewModel.getMyPostResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it != null) {
                if(it.code == 0) {
                    ApplicationClass.myPostedPost = it.data.content
                }
            }
        })
        viewModel.mainBoardResponse.observe(this@MainBoardDetailFragment, Observer {
            if(it != null) {
                if(it.code == 0) {
                    ApplicationClass.mainBoardList = it.data.content
                }
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "MainBoardDetailFragment"
    }
}