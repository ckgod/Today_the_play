package com.theplay.aos.fragment.setting

import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingOpenSourceBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class SettingOpenSourceFragment() : BaseKotlinFragment<FragmentSettingOpenSourceBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_open_source

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SettingOpenSourceFragment"
    }
}