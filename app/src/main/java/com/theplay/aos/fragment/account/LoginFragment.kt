package com.theplay.aos.fragment.account

import android.view.View
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentLoginBinding
import com.theplay.aos.fragment.account.LoginFragmentDirections

class LoginFragment() : BaseKotlinFragment<FragmentLoginBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login


    override fun initStartView() {
        binding.btnLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToMainFragment())
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
        const val TAG = "LoginFragment"
    }
}