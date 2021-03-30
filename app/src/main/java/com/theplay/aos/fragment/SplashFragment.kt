package com.theplay.aos.fragment

import android.content.Intent
import android.os.Handler
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.theplay.aos.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.theplay.aos.ApplicationClass.Companion.spToken
import com.theplay.aos.MainActivity
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentSplashBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class SplashFragment() : BaseKotlinFragment<FragmentSplashBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash

    override fun initStartView() {
        Handler().postDelayed({
            val jwtToken: String? = spToken?.getString(X_ACCESS_TOKEN, null)
            if(jwtToken == null) {
//                val nextIntent = Intent(requireContext(), MainActivity::class.java)
//                startActivity(nextIntent)
//                requireActivity().finish()
                view?.findNavController()?.navigate(SplashFragmentDirections.actionSplashFragmentToPrevLoginFragment())
            }
            else {
                val nextIntent = Intent(requireContext(), MainActivity::class.java)
                startActivity(nextIntent)
                requireActivity().finish()
//                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainActivity())
//                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToMainFragment())
            }
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToPrevLoginFragment())
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