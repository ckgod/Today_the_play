package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMainBoardBinding
import com.theplay.aos.iadapter.MainBoardAdapter
import com.theplay.aos.iadapter.MainBoardAdapterListener

class MainBoardFragment() : BaseKotlinFragment<FragmentMainBoardBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main_board

    private val viewModel by lazy { HomeViewModel() }
    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()
    var mainBoardAdapterListener : MainBoardAdapterListener? = null

    override fun initStartView() {
        mainBoardAdapterListener = object : MainBoardAdapterListener{
            override fun DoubleTap(postId: Int) {
                viewModel.postLike(postId)
            }
        }
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getMainBoard(0,25)
//            showCustomToast("refresh")
        }
    }

    override fun initDataBinding() {
        viewModel.mainBoardResponse.observe(this@MainBoardFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    itemList = mutableListOf()
                    for(item in it.data.content) {
                        itemList.add(item)
                    }
                    ApplicationClass.mainBoardList = itemList
                    binding.rv.adapter = MainBoardAdapter(requireActivity(), requireContext(), itemList).apply {
                        mainBoardAdapterListener?.let {listener ->
                            setInterface(listener)
                        }
                    }
                }
            }
            hideProgress()
            binding.refreshLayout.isRefreshing = false
        })
        viewModel.getLikedResponse.observe(this@MainBoardFragment, Observer {
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
        viewModel.postLikeResponse.observe(this@MainBoardFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getLikedPost(0,30)
                }
            }
        })
    }

    override fun initAfterBinding() {
        showProgress()
        viewModel.getMainBoard(0,25)
    }

    override fun reLoadUI() {
    }

    override fun onResume() {
        super.onResume()
        ApplicationClass.mainBoardList?.let {
            binding.rv.adapter = MainBoardAdapter(requireActivity(),requireContext(),it).apply {
                mainBoardAdapterListener?.let {listener ->
                    setInterface(listener)
                }
            }
        }
    }


    companion object {
        const val TAG = "MainBoardFragment"
    }
}