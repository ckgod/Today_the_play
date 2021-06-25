package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.R
import com.theplay.aos.databinding.CustomDialogCancelFollowingBinding

interface CustomDialogCancelFollowingInterface {
    fun onFirstClicked()
    fun onSecondClicked()
}

class CustomDialogCancelFollowing(
    context: Context,
    private val userName: String,
    private val userId: Int
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogCancelFollowingBinding
    private lateinit var customDialogListener : CustomDialogCancelFollowingInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogCancelFollowingBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.tvContent.text = userName + context.getString(R.string.my_page_follow_cancel_explain)
        binding.btnLeft.setOnClickListener {
            customDialogListener.onFirstClicked()
        }
        binding.btnRight.setOnClickListener {
            customDialogListener.onSecondClicked()
        }
    }

    fun setDialogListener(customDialogListener : CustomDialogCancelFollowingInterface){
        this.customDialogListener = customDialogListener
    }


}