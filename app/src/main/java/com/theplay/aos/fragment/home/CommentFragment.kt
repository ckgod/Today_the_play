package com.theplay.aos.fragment.home

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.api.model.AddCommentRequest
import com.theplay.aos.api.model.CommentResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentCommentBinding
import com.theplay.aos.iadapter.CommentAdapter
import com.theplay.aos.iadapter.CommentAdapterListener
import kotlinx.coroutines.*
import android.os.Handler
import android.os.Looper

class CommentFragment() : BaseKotlinFragment<FragmentCommentBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_comment

    private val safeArgs : CommentFragmentArgs by navArgs()
    private val viewModel by lazy { CommentViewModel() }
    private var isAddCommentCommentMode : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply {
        postValue(false)
    }
    private var currentCommentId = 0
    lateinit var commentAdapterListener : CommentAdapterListener

    override fun initStartView() {
        commentAdapterListener = object : CommentAdapterListener{
            override fun clickAddComment(commentId: Int) {
                isAddCommentCommentMode.postValue(true)
            }

            override fun clickLike(commentId: Int) {
                viewModel.postCommentLike(commentId)
            }
        }
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
        isAddCommentCommentMode.observe(this, Observer {
            if(it) {
                binding.ctlCommentOfComment.visibility = View.VISIBLE
            }
            else {
                binding.ctlCommentOfComment.visibility = View.GONE
            }
        })
        viewModel.commentResponse.observe(this@CommentFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    var comList : MutableList<CommentResponse.Comment> = it.list
                    binding.rvComment.adapter = CommentAdapter(requireActivity(), requireContext(), comList).apply {
                        setAdapterListener(commentAdapterListener)
                    }
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
        viewModel.postCommentLikResponse.observe(this@CommentFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {

                }
            }
        })

    }

    override fun initAfterBinding() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            showProgressNoShadow()
            viewModel.getComment(safeArgs.postId)
        },200)
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "CommentFragment"
    }
}