package com.theplay.aos.fragment.setting

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.R
import com.theplay.aos.api.model.NoticeResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingNoticeBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.NoticeAdapter

class SettingNoticeFragment() : BaseKotlinFragment<FragmentSettingNoticeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_notice

    private val viewModel by lazy { SettingViewModel() }
    private var noticeList : MutableList<NoticeResponse.NoticeId> = mutableListOf()

    override fun initStartView() {
        showProgress()
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.rvNotice.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun initDataBinding() {
        viewModel.noticeResponse.observe(this@SettingNoticeFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    for(notice in it.list){
                        noticeList.add(notice)
                    }
                    binding.rvNotice.adapter = NoticeAdapter(requireActivity(), requireContext(), noticeList)
                }
                else {
                }
            }
            hideProgress()
        })

    }

    override fun initAfterBinding() {
        viewModel.getNotice()
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SettingNoticeFragment"
    }
}