package com.theplay.aos.fragment.home

import android.util.Log
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMainBoardBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.MainBoardAdapter
import com.theplay.aos.item.MainBoardItem

class MainBoardFragment() : BaseKotlinFragment<FragmentMainBoardBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main_board

    private val viewModel by lazy { HomeViewModel() }
    var itemList : MutableList<MainBoardResponse.Content> = mutableListOf()

    override fun initStartView() {
        binding.rv.layoutManager = GridLayoutManager(requireContext(),2)
        binding.refreshLayout.setOnRefreshListener {
            viewModel.getMainBoard(0,10)
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
                    binding.rv.adapter = MainBoardAdapter(requireActivity(), requireContext(), itemList)
                }
            }
            hideProgress()
            binding.refreshLayout.isRefreshing = false
        })
    }

    override fun initAfterBinding() {
        showProgress()
        viewModel.getMainBoard(0,10)
    }

    override fun reLoadUI() {
    }

    override fun onResume() {
        super.onResume()
        ApplicationClass.mainBoardList?.let {
            binding.rv.adapter = MainBoardAdapter(requireActivity(),requireContext(),it)
        }
    }


    companion object {
        const val TAG = "MainBoardFragment"
    }
}