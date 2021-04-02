package com.theplay.aos.fragment.account

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import com.theplay.aos.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.theplay.aos.MainActivity
import com.theplay.aos.R
import com.theplay.aos.api.model.LoginRequest
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentLoginBinding

class LoginFragment() : BaseKotlinFragment<FragmentLoginBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    // viewModel
    private val viewModel by lazy { LoginFragmentViewModel() }

    override fun initStartView() {
        binding.btnLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                showLottie()
                viewModel.postLogin(LoginRequest(binding.etEmail.text.toString(), binding.etPw.text.toString()))
            }
        })
        binding.etEmail.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etEmail.text.isEmpty()) {
                    binding.tvEmailInfo.visibility = View.INVISIBLE
                }
                else {
                    binding.tvEmailInfo.visibility = View.VISIBLE
                }
            }
        })
        binding.etPw.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {}
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                if(binding.etPw.text.isEmpty()) {
                    binding.tvPwInfo.visibility = View.INVISIBLE
                }
                else {
                    binding.tvPwInfo.visibility = View.VISIBLE
                }
            }
        })
    }

    override fun initDataBinding() {
        viewModel.loginResponse.observe(this@LoginFragment, Observer {
            hideLottie()
            if(it == null) {
                showNetworkError()
            }
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    it.data?.let { it1 -> Log.d(TAG, it1) }
                    val preferences: SharedPreferences = requireContext().getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE)
                    val editor = preferences.edit()
                    editor.putString(X_ACCESS_TOKEN, it.data)
                    editor.apply()
                    val nextIntent = Intent(requireContext(), MainActivity::class.java)
                    startActivity(nextIntent)
                    requireActivity().finish()
                }
                else {
                    showCustomToast(it.msg)
                }
            }
        })
    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "LoginFragment"
    }
}