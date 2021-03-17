package com.theplay.aos.fragment.account

import android.view.View
import android.view.animation.AnimationUtils
import android.view.animation.TranslateAnimation
import androidx.navigation.fragment.findNavController
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentPrevLoginBinding
import com.theplay.aos.fragment.account.PrevLoginFragmentDirections
import com.theplay.aos.utils.ViewUtils

class PrevLoginFragment() : BaseKotlinFragment<FragmentPrevLoginBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_prev_login

    override fun initStartView() {
        binding.btnGoLogin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                findNavController().navigate(PrevLoginFragmentDirections.actionPrevLoginFragmentToLoginFragment())
            }
        })
        binding.btnJoin.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                findNavController().navigate(PrevLoginFragmentDirections.actionPrevLoginFragmentToSignUpFragment())
            }
        })
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        val animationSlideUp = TranslateAnimation(0f, 0f, 0F, -ViewUtils.convertDpToPixel(98f,requireContext()))
        animationSlideUp.duration = 500
        animationSlideUp.fillAfter = true
        binding.ivLogo.startAnimation(animationSlideUp)
        binding.ctlLateContainer.startAnimation(AnimationUtils.loadAnimation(requireContext(),R.anim.fade_in_animation))
        binding.ctlLateContainer.visibility = View.VISIBLE
    }


    companion object {
        const val TAG = "PrevLoginFragment"
    }
}