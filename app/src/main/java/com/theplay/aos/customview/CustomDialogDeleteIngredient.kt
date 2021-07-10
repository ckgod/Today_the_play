package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import androidx.core.content.ContextCompat
import com.theplay.aos.databinding.CustomDialogDeleteIngredientBinding
import com.theplay.aos.databinding.CustomDialogDeleteTagBinding

interface CustomDialogDeleteIngredientInterface {
    fun onFirstClicked()
    fun onSecondClicked()
}

open class CustomDialogDeleteIngredient(
    context: Context,
    var icon : Int,
    var name : String,
    var colorType : Int
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogDeleteIngredientBinding
    private lateinit var customDialogListener : CustomDialogDeleteIngredientInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogDeleteIngredientBinding.inflate(layoutInflater)
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

    fun setDialogListener(customDialogListener : CustomDialogDeleteIngredientInterface){
        this.customDialogListener = customDialogListener
    }


}