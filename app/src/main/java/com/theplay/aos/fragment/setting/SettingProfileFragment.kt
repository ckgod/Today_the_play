package com.theplay.aos.fragment.setting

import android.util.Log
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.api.model.ChangeNickRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogChangeNick
import com.theplay.aos.customview.CustomDialogChangeNickListener
import com.theplay.aos.databinding.FragmentSettingProfileBinding

class SettingProfileFragment() : BaseKotlinFragment<FragmentSettingProfileBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_profile

    private val viewModel by lazy { SettingViewModel() }
    private var dialog : CustomDialogChangeNick? = null
    var tmpStr : String? = null

    override fun initStartView() {
        dialog = CustomDialogChangeNick(requireContext()).apply {
            setDialogListener(object : CustomDialogChangeNickListener{
                override fun onFirstClicked(nick: String) {
                    showLottie()
                    tmpStr = nick
                    viewModel.putNickName(ChangeNickRequest(nick))
                }
                override fun onSecondClicked() {
                    dismiss()
                }
            })
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.tvChangeNickName.setOnClickListener {
            dialog?.show()
        }
        binding.tvChangePw.setOnClickListener {
            findNavController().navigate(SettingProfileFragmentDirections.actionSettingProfileFragmentToSettingChangePwFragment())
        }
        binding.tvPrivacy.setOnClickListener {
            findNavController().navigate(SettingProfileFragmentDirections.actionSettingProfileFragmentToSettingPrivacyFragment())
        }
    }

    override fun initDataBinding() {
        viewModel.settingProfileResponse.observe(this@SettingProfileFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.toString())
                if(it.code == 0) {
                    binding.tvId.text = it.data.email
                    binding.tvNickName.text = it.data.nickname
                }
            }
        })
        viewModel.putChangeNickNameResponse.observe(this@SettingProfileFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    showCustomToast("닉네임이 변경되었습니다.")
                    userInfo?.let { user ->
                        tmpStr?.let { str ->
                            user.data.nickname = str
                        }
                    }
                    dialog?.dismiss()
                }
            }
            binding.tvNickName.text = tmpStr
            tmpStr = null
            hideLottie()
        })

    }

    override fun initAfterBinding() {
        viewModel.getSettingProfile()
    }

    override fun reLoadUI() {

    }


    companion object {
        const val TAG = "SettingProfileFragment"
    }
}