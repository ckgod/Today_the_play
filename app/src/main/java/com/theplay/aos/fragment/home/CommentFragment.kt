package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.api.model.AddCommentRequest
import com.theplay.aos.api.model.CommentResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentCommentBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.CommentAdapter

class CommentFragment() : BaseKotlinFragment<FragmentCommentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_comment

    private val safeArgs : CommentFragmentArgs by navArgs()
    private val viewModel by lazy { CommentViewModel() }

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvNickName.text = "${safeArgs.nickname}님 게시글"
        userInfo?.let {
            binding.tvMyNickName.text = it.data.nickname
        }
        binding.rvComment.layoutManager =LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.btnWriteComment.setOnClickListener {
            if(binding.etWriteComment.text.isEmpty()){
                return@setOnClickListener
            }
            showProgress()
            viewModel.addPostComment(safeArgs.postId, AddCommentRequest(binding.etWriteComment.text.toString(),0))
        }
    }

    override fun initDataBinding() {
        viewModel.commentResponse.observe(this@CommentFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    var comList : MutableList<CommentResponse.Comment> = mutableListOf()
                    for(c in it.list) {
                        comList.add(c)
                    }
                    binding.rvComment.adapter = CommentAdapter(requireActivity(), requireContext(), comList)
                    binding.etWriteComment.setText("")
                    binding.etWriteComment.clearFocus()
                }
            }
            hideProgressNoShadow()
            hideProgress()
        })

        viewModel.addCommentResponse.observe(this@CommentFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    viewModel.getComment(safeArgs.postId)
                }
            }
        })

    }

    override fun initAfterBinding() {
        showProgressNoShadow()
        viewModel.getComment(safeArgs.postId)
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "CommentFragment"
    }
}