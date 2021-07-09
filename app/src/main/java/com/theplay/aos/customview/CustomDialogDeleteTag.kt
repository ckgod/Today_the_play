package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import com.theplay.aos.databinding.CustomDialogDeleteTagBinding

interface CustomDialogDeleteTagInterface {
    fun onFirstClicked()
    fun onSecondClicked()
}

open class CustomDialogDeleteTag(
    context: Context,
    var icon : Int,
    var name : String,
    var colorType : Int
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogDeleteTagBinding
    private lateinit var customDialogListener : CustomDialogDeleteTagInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogDeleteTagBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.ivIcon.background = ContextCompat.getDrawable(context, icon)
        binding.ivIcon.backgroundTintList = ContextCompat.getColorStateList(context, colorType)
        binding.tvName.text = name
        binding.tvName.setTextColor(ContextCompat.getColorStateList(context, colorType))
        binding.btnLeft.setOnClickListener {
            customDialogListener.onFirstClicked()
        }
        binding.btnRight.setOnClickListener {
            customDialogListener.onSecondClicked()
        }
    }

    fun setDialogListener(customDialogListener : CustomDialogDeleteTagInterface){
        this.customDialogListener = customDialogListener
    }


}