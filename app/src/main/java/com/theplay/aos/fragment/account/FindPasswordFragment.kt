package com.theplay.aos.fragment.account

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.FindPasswordRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentFindPasswordBinding

class FindPasswordFragment() : BaseKotlinFragment<FragmentFindPasswordBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_find_password

    private val viewModel by lazy { FindPasswordViewModel() }
    private var emailCheck = false

    override fun initStartView() {
        binding.etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etEmail.text.isEmpty()) binding.tvEmailInfo.visibility = View.INVISIBLE
                else {
                    binding.tvEmailInfo.visibility = View.VISIBLE
                    if(s.toString().trim { it <= ' ' }.matches(ApplicationClass.emailPattern.toRegex())) { // 이메일 맞을때
                        binding.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.tvEmailInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.white))
                        binding.btnFindPw.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.mainColor)
                        binding.btnFindPw.setTextColor(ContextCompat.getColor(requireContext(),R.color.mainBlack))
                        binding.btnFindPw.isClickable = true
                        emailCheck = true
                    }
                    else {
                        binding.etEmail.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.tvEmailInfo.setTextColor(ContextCompat.getColor(requireContext(), R.color.error1))
                        binding.btnFindPw.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.body)
                        binding.btnFindPw.setTextColor(ContextCompat.getColor(requireContext(),R.color.subGray))
                        binding.btnFindPw.isClickable = false
                        emailCheck = false
                    }
                }
            }
        })
        binding.btnFindPw.setOnClickListener {
            showProgress()
            viewModel.postFindPassword(FindPasswordRequest( binding.etEmail.text.toString()))
        }
    }

    override fun initDataBinding() {
        viewModel.findPasswordResponse.observe(this@FindPasswordFragment, Observer {
            hideProgress()
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    findNavController().navigate(FindPasswordFragmentDirections.actionFindPasswordFragmentToFindPasswordCompleteFragment(binding.etEmail.text.toString()))
                }
                else showCustomToast(it.msg)
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "FindPasswordFragment"
    }
}