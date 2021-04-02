package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
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
            for (i in safeArgs.selectIdx until it.size-1) {
                selectList.add(it[i])
            }
            binding.rv.adapter = MainBoardDetailAdapter(this, requireActivity(), requireContext(), selectList).apply {
                setInterface(object : MainBoardDetailInterface{
                    override fun clickLike(postId: Int) {
                        viewModel.postLike(postId)
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
                    viewModel.getLikedPost(0,30)
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