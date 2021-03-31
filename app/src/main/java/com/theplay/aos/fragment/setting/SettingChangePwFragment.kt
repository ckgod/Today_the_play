package com.theplay.aos.fragment.setting

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.ChangePwRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSettingChangePwBinding

class SettingChangePwFragment() : BaseKotlinFragment<FragmentSettingChangePwBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_setting_change_pw

    private val viewModel by lazy { SettingViewModel() }

    private var pwCheck = false
    private var pwCheckCheck = false

    override fun initStartView() {
        binding.btnBack.setOnClickListener { findNavController().popBackStack() }
        binding.etOriginPw.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etOriginPw.text.isEmpty()) binding.tvOriginPw.visibility = View.INVISIBLE
                else {
                    binding.tvOriginPw.visibility = View.VISIBLE
                }
            }
        })
        binding.etNewPw.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etNewPw.text.isEmpty()) binding.tvNewPw.visibility = View.INVISIBLE
                else {
                    binding.tvNewPw.visibility = View.VISIBLE
                    if(s.toString().length in 6..11) {
                        binding.etNewPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvNewPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        pwCheck = true
                    }
                    else {
                        binding.etNewPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvNewPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        pwCheck = false
                    }
                    if(binding.etNewPw.text.toString() == binding.etPwCheck.text.toString()) {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvPwCheck.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        pwCheckCheck = true
                    }
                    else {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvPwCheck.setTextColor(ContextCompat.getColor(requireContext(),R.color.error1))
                        pwCheckCheck = false
                    }
                }
            }
        })
        binding.etPwCheck.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etPwCheck.text.isEmpty()) binding.tvPwCheck.visibility = View.INVISIBLE
                else {
                    binding.tvPwCheck.visibility = View.VISIBLE
                    if(binding.etNewPw.text.toString() == binding.etPwCheck.text.toString()) {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvPwCheck.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        pwCheckCheck = true
                    }
                    else {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvPwCheck.setTextColor(ContextCompat.getColor(requireContext(),R.color.error1))
                        pwCheckCheck = false
                    }
                }
            }
        })
        binding.btnComplete.setOnClickListener {
            if (!pwCheck) {
                showCustomToast("비밀번호 형식이 맞지 않습니다")
                return@setOnClickListener
            }
            if(!pwCheckCheck) {
                showCustomToast("비밀번호와 비밀번호확인이 일치하지 않습니다.")
                return@setOnClickListener
            }
            if(binding.etOriginPw.text.toString() == binding.etNewPw.text.toString()) {
                showCustomToast("이전 비밀번호와 같은 비밀번호입니다.")
                return@setOnClickListener
            }
            // 기존비밀번호 틀렸을때 -> 409
            showLottie()
            viewModel.putChangePw(ChangePwRequest(binding.etNewPw.text.toString(), binding.etNewPw.text.toString(), binding.etOriginPw.text.toString()))
        }
    }

    override fun initDataBinding() {
        viewModel.putChangePwResponse.observe(this@SettingChangePwFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    showCustomToast("비밀번호가 변경되었습니다.")
                    findNavController().popBackStack()
                }
                else {
                    showCustomToast(it.msg)
                }
            }
            hideLottie()
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SettingChangePwFragment"
    }
}