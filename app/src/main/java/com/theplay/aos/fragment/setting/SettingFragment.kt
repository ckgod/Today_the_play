package com.theplay.aos.fragment.setting

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.MainActivity
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.iadapter.SettingAdapter
import com.theplay.aos.item.SettingItem

class SettingFragment() : BaseKotlinFragment<FragmentSettingBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting
    override val showBottomNav: Boolean
        get() = false

    var profileList : MutableList<SettingItem> = mutableListOf()
    var appList : MutableList<SettingItem> = mutableListOf()

    override fun initStartView() {
        profileList = mutableListOf()
        appList = mutableListOf()

        binding.btnBack.setOnClickListener {
            requireActivity().finish()
            requireActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out)
        }

        binding.rvProfile.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvApp.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        profileList.add(SettingItem(getString(R.string.setting_p_1), 1))
        profileList.add(SettingItem(getString(R.string.setting_p_3),2))
        appList.add(SettingItem(getString(R.string.setting_a_1),3))
        appList.add(SettingItem(getString(R.string.setting_a_2),4))
        appList.add(SettingItem(getString(R.string.setting_a_3),5))
        appList.add(SettingItem(getString(R.string.setting_a_4),6))
        appList.add(SettingItem(getString(R.string.setting_a_5),7))
        binding.rvProfile.adapter = SettingAdapter(requireActivity(),requireContext(),profileList)
        binding.rvApp.adapter = SettingAdapter(requireActivity(),requireContext(),appList)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        userInfo?.let {
            binding.tvNickName.text = it.data.nickname
        }
    }

    fun removeActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }


    companion object {
        const val TAG = "SettingFragment"
    }
}