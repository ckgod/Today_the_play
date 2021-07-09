package com.theplay.aos.customview

import android.animation.Animator
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.R
import com.theplay.aos.databinding.CustomDialogDeleteFollowerBinding
import com.theplay.aos.databinding.CustomDialogFollowLottieBinding

interface CustomDialogFollowLottieInterface {
}

class CustomDialogFollowLottie(
    context: Context
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogFollowLottieBinding
    private lateinit var customDialogListener : CustomDialogFollowLottieInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogFollowLottieBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.lottieFollow.addAnimatorListener(object : Animator.AnimatorListener{
            override fun onAnimationRepeat(animation: Animator?) {
            }

            override fun onAnimationEnd(animation: Animator?) {
                dismiss()
            }

            override fun onAnimationCancel(animation: Animator?) {
            }

            override fun onAnimationStart(animation: Animator?) {
            }
        })
    }

    fun setDialogListener(customDialogListener : CustomDialogFollowLottieInterface){
        this.customDialogListener = customDialogListener
    }


}