package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.R
import com.theplay.aos.databinding.CustomDialogDeleteFollowerBinding

interface CustomDialogDeleteFollowerInterface {
    fun onFirstClicked()
    fun onSecondClicked()
}

class CustomDialogDeleteFollower(
    context: Context,
    private val userName: String,
    private val userId: Int
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogDeleteFollowerBinding
    private lateinit var customDialogListener : CustomDialogDeleteFollowerInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogDeleteFollowerBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.tvContent.text = userName + context.getString(R.string.my_page_follow_delete_explain)
        binding.btnLeft.setOnClickListener {
            customDialogListener.onFirstClicked()
        }
        binding.btnRight.setOnClickListener {
            customDialogListener.onSecondClicked()
        }
    }

    fun setDialogListener(customDialogListener : CustomDialogDeleteFollowerInterface){
        this.customDialogListener = customDialogListener
    }


}