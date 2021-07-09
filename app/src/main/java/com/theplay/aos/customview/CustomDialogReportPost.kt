package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import com.theplay.aos.databinding.CustomDialogDeleteTagBinding
import com.theplay.aos.databinding.CustomDialogReportPostBinding

interface CustomDialogReportPostInterface {
    fun clickCancel()
    fun clickSpam()
    fun click19()
    fun clickNotMatch()
}

open class CustomDialogReportPost(
    context: Context
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogReportPostBinding
    private lateinit var customDialogListener : CustomDialogReportPostInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogReportPostBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.btnCancel.setOnClickListener {
            customDialogListener.clickCancel()
        }
        binding.btnSpam.setOnClickListener {
            customDialogListener.clickSpam()
        }
        binding.btn19.setOnClickListener {
            customDialogListener.click19()
        }
        binding.btnNotMatch.setOnClickListener {
            customDialogListener.clickNotMatch()
        }
    }

    fun setDialogListener(customDialogListener : CustomDialogReportPostInterface){
        this.customDialogListener = customDialogListener
    }


}