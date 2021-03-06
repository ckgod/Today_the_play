package com.theplay.aos.fragment.setting

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingPrivacyBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class SettingPrivacyFragment() : BaseKotlinFragment<FragmentSettingPrivacyBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_privacy

    private val viewModel by lazy { SettingViewModel() }

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.switchPrivacy.setOnCheckedChangeListener { view, isChecked ->
            when(isChecked) {
                true -> {

                }
                false -> {

                }
            }
        }

    }

    override fun initDataBinding() {
        viewModel.getPrivacyStatusResponse.observe(this@SettingPrivacyFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    binding.switchPrivacy.isChecked = it.data.privacyYn == "Y"
                }
            }
        })
    }

    override fun initAfterBinding() {
        viewModel.getPrivacy()
    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SettingPrivacyFragment"
    }
}