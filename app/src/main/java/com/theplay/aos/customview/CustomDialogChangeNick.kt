package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.databinding.CustomDialogEditNickNameBinding

interface CustomDialogChangeNickListener {
    fun onFirstClicked(nick : String)
    fun onSecondClicked()
}

class CustomDialogChangeNick(
    mContext: Context
) : AppCompatDialog(mContext) {
    private lateinit var binding: CustomDialogEditNickNameBinding
    private lateinit var customDialogListener : CustomDialogChangeNickListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogEditNickNameBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.btnLeft.setOnClickListener {
            if(binding.etNickName.text.toString().isEmpty()) {
                Toast.makeText(context,"닉네임을 입력해주세요",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            customDialogListener.onFirstClicked(binding.etNickName.text.toString())
        }
        binding.btnRight.setOnClickListener { customDialogListener.onSecondClicked()}
    }

    fun setDialogListener(customDialogListener : CustomDialogChangeNickListener){
        this.customDialogListener = customDialogListener
    }
}