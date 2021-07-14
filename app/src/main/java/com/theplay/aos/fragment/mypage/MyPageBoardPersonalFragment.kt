package com.theplay.aos.fragment.mypage

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageBoardPersonalBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.FollowAdapter
import com.theplay.aos.iadapter.MyPagePersonalAdapter
import com.theplay.aos.iadapter.MyPagePersonalInterface
import com.theplay.aos.item.FollowItem

class MyPageBoardPersonalFragment() : BaseKotlinFragment<FragmentMyPageBoardPersonalBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_board_personal

    private val viewModel by lazy { MyPageBoardViewModel() }
    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()

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

                    }
                })
            }
        }
    }


    companion object {
        const val TAG = "MyPageBoardPersonalFragment"
    }
}