package com.theplay.aos.fragment.account

import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentFindPasswordCompleteBinding

class FindPasswordCompleteFragment() : BaseKotlinFragment<FragmentFindPasswordCompleteBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_find_password_complete

    private val safeArgs : FindPasswordCompleteFragmentArgs by navArgs()

    override fun initStartView() {
        binding.tvEmail.text = safeArgs.email
        binding.btnLogin.setOnClickListener {
            findNavController().navigate(FindPasswordCompleteFragmentDirections.actionFindPasswordCompleteFragmentToPrevLoginFragment())
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "FindPasswordCompleteFragment"
    }
}