package com.theplay.aos.fragment.account

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.theplay.aos.ApplicationClass.Companion.emailPattern
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSignUpBinding
import com.theplay.aos.fragment.account.SignUpFragmentDirections

class SignUpFragment() : BaseKotlinFragment<FragmentSignUpBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up


    var emailCheck = false
    var pwCheck = false
    var pwCheckCheck = false

    override fun initStartView() {
        binding.btnContinue.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                if(!emailCheck) {
                    return
                }
                if(!pwCheck) {
                    return
                }
                if(!pwCheckCheck) {
                    return
                }

                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignUpNextFragment(binding.etEmail.text.toString(), binding.etPw.text.toString()))
            }
        })
        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etEmail.text.isEmpty()) binding.tvEmailInfo.visibility = View.INVISIBLE
                else {
                    binding.tvEmailInfo.visibility = View.VISIBLE
                    if(s.toString().trim { it <= ' ' }.matches(emailPattern.toRegex())) { // 이메일 맞을때
                        binding.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvEmailInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        emailCheck = true
                    }
                    else {
                        binding.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvEmailInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        emailCheck = false
                    }
                }
            }
        })
        binding.etPw.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etPw.text.isEmpty()) binding.tvPwInfo.visibility = View.INVISIBLE
                else {
                    binding.tvPwInfo.visibility = View.VISIBLE
                    if(s.toString().length in 6..11) {
                        binding.etPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvPwInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        pwCheck = true
                    }
                    else {
                        binding.etPw.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvPwInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        pwCheck = false
                    }
                    if(binding.etPw.text.toString() == binding.etPwCheck.text.toString()) {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvPwCheckInfo.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        pwCheckCheck = true
                    }
                    else {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvPwCheckInfo.setTextColor(ContextCompat.getColor(requireContext(),R.color.error1))
                        pwCheckCheck = false
                    }
                }
            }
        })
        binding.etPwCheck.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etPwCheck.text.isEmpty()) binding.tvPwCheckInfo.visibility = View.INVISIBLE
                else {
                    binding.tvPwCheckInfo.visibility = View.VISIBLE
                    if(binding.etPw.text.toString() == binding.etPwCheck.text.toString()) {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvPwCheckInfo.setTextColor(ContextCompat.getColor(requireContext(),R.color.white))
                        pwCheckCheck = true
                    }
                    else {
                        binding.etPwCheck.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvPwCheckInfo.setTextColor(ContextCompat.getColor(requireContext(),R.color.error1))
                        pwCheckCheck = false
                    }
                }
            }
        })

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SignUpFragment"
    }
}