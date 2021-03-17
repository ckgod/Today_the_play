package com.theplay.aos.fragment

import android.os.Handler
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSplashBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class SplashFragment() : BaseKotlinFragment<FragmentSplashBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash

    override fun initStartView() {
        Handler().postDelayed({
            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToPrevLoginFragment())
        }, SPLASH_TIME_OUT)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "SplashFragment"
        const val SPLASH_TIME_OUT: Long = 1500
    }
}