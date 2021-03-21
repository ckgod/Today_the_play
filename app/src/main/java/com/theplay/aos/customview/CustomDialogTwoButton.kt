package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.databinding.CustomDialogTwoButtonBinding

interface CustomDialogListener {
    fun onFirstClicked()
    fun onSecondClicked()
}

class CustomDialogTwoButton(
    mContext: Context,
    private val msg1: String,
    private val msg2: String,
    private val firstBtnMsg: String,
    private val secondBtnMsg: String
) : AppCompatDialog(mContext) {
    private lateinit var binding: CustomDialogTwoButtonBinding
    private lateinit var customDialogListener : CustomDialogListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogTwoButtonBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.tvTitle.text = msg1
        binding.tvContent.text = msg2
        binding.btnLeft.text = firstBtnMsg
        binding.btnLeft.setOnClickListener { customDialogListener.onFirstClicked()}
        binding.btnRight.text = secondBtnMsg
        binding.btnRight.setOnClickListener { customDialogListener.onSecondClicked()}
    }

    fun setDialogListener(customDialogListener : CustomDialogListener){
        this.customDialogListener = customDialogListener
    }


}