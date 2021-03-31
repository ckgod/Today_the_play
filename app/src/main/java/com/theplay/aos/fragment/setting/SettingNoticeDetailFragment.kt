package com.theplay.aos.fragment.setting

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingNoticeDetailBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class SettingNoticeDetailFragment() : BaseKotlinFragment<FragmentSettingNoticeDetailBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_notice_detail

    private val viewModel by lazy { SettingViewModel() }
    private val safeArgs : SettingNoticeDetailFragmentArgs by navArgs()

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initDataBinding() {
        viewModel.noticeDetailResponse.observe(this@SettingNoticeDetailFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    binding.tvNoticeTitle.text = it.data.title
                    binding.tvNoticeContent.text = it.data.content
                }
            }
        })

    }

    override fun initAfterBinding() {
        viewModel.getNoticeDetail(safeArgs.noticeId)
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SettingNoticeDetailFragment"
    }
}