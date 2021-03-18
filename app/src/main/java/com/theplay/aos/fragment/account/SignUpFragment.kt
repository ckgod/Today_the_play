package com.theplay.aos.fragment.account

import android.view.View
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSignUpBinding
import com.theplay.aos.fragment.account.SignUpFragmentDirections

class SignUpFragment() : BaseKotlinFragment<FragmentSignUpBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_sign_up


    override fun initStartView() {
        binding.btnContinue.setOnClickListener(object : OnSingleClickListener(){
            override fun onSingleClick(v: View) {
                findNavController().navigate(SignUpFragmentDirections.actionSignUpFragmentToSignUpNextFragment())
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