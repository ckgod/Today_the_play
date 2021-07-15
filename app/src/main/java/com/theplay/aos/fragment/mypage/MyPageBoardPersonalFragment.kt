package com.theplay.aos.fragment.mypage

import android.util.Log
import android.view.Menu
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.api.model.MyPageTopResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogListener
import com.theplay.aos.customview.CustomDialogTwoButton
import com.theplay.aos.databinding.FragmentMyPageBoardPersonalBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.fragment.home.BottomSheetMainPost
import com.theplay.aos.fragment.home.MenuBottomSheetListener
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.iadapter.MyPagePersonalAdapter
import com.theplay.aos.iadapter.MyPagePersonalInterface
import com.theplay.aos.item.FollowItem

class MyPageBoardPersonalFragment() : BaseKotlinFragment<FragmentMyPageBoardPersonalBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_board_personal

    private val viewModel by lazy { MyPageBoardViewModel() }
    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()
    lateinit var bottomSheetListener : MenuBottomSheetListener

    override fun initStartView() {
        binding.rv.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {
        viewModel.postLikeResponse.observe(this@MyPageBoardPersonalFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {

                }
            }
        })
        viewModel.deletePostResponse.observe(this@MyPageBoardPersonalFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    // myPostedPost 목록 갱신
                    // mainPost 갱신
                    // myLikedPost 갱신
                    viewModel.getMyPost(0,50)
                    viewModel.getLikedPost(0,50)
                    viewModel.getMainBoard(0,50)
                }
            }
        })
        viewModel.getMyPostResponse.observe(this@MyPageBoardPersonalFragment, Observer {
            if(it != null) {
                if(it.code == 0) {
                    ApplicationClass.myPostedPost = it.data.content
                }
            }
            hideProgress()
        })
        viewModel.getLikedResponse.observe(this@MyPageBoardPersonalFragment, Observer {
            if(it != null) {
                if(it.code == 0) {
                    ApplicationClass.myLikedPost = it.data.content
                    ApplicationClass.userInfo?.data?.likes = it.data.content.size
                    ApplicationClass.userLiveData.postValue(ApplicationClass.userInfo)
                }
            }
        })
        viewModel.mainBoardResponse.observe(this@MyPageBoardPersonalFragment, Observer {
            if(it != null) {
                if(it.code == 0) {
                    ApplicationClass.mainBoardList = it.data.content
                }
            }
        })
    }

    override fun reLoadUI() {
        ApplicationClass.myPostedPost?.let {
            itemList = mutableListOf()
            for(i in it) {
                itemList.add(i)
            }
            binding.rv.adapter = MyPagePersonalAdapter(this, requireActivity(), requireContext(), itemList).apply {
                setInterface(object : MyPagePersonalInterface{
                    override fun clickLike(postId: Int) {
                        viewModel.postLike(postId)
                    }

                    override fun clickComment(postId: Int, nickName: String) {
                        requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(MyPeedFragmentDirections.actionMyPeedFragmentToCommentFragment(postId,nickName))
                    }

                    override fun clickMore(postId: Int, userId: Int, nickName: String) {
                        val dialog = BottomSheetMainPost(nickName,this@MyPageBoardPersonalFragment).apply {
                            setMenuBottomSheetInterface(object : MenuBottomSheetListener{
                                override fun clickMenu(type: Int) {
                                    when(type) {
                                        1-> { // plan 공유
                                            showCustomToast(getString(R.string.not_implementation))
                                        }
                                        2 -> { // plan 수정
                                            ApplicationClass.myPostedPost?.let { MyPostList ->
                                                for(item in MyPostList) {
                                                    if(item.postId == postId) {
                                                        ApplicationClass.PostTemplate = item
                                                    }
                                                }
                                                requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(MyPeedFragmentDirections.actionMyPeedFragmentToModifyActivity())
                                                dismiss()
                                            }
                                        }
                                        3 -> { // plan 삭제
                                            val dialog = CustomDialogTwoButton(
                                                requireContext(),
                                                getString(R.string.delete_post_title),
                                                getString(R.string.delete_post_content),
                                                getString(R.string.delete_post_delete),
                                                getString(R.string.delete_post_no_delete)
                                            ).apply {
                                                setDialogListener(object : CustomDialogListener {
                                                    override fun onFirstClicked() {
                                                        showProgress()
                                                        viewModel.deletePost(postId)
                                                        for(i in itemList) {
                                                            if(i.postId == postId) {
                                                                itemList.remove(i)
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
                            })
                        }.show(requireActivity().supportFragmentManager, TAG)
                    }
                })
            }
        }
    }


    companion object {
        const val TAG = "MyPageBoardPersonalFragment"
    }
}