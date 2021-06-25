package com.theplay.aos.customview

import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatDialog
import com.theplay.aos.R
import com.theplay.aos.databinding.CustomDialogBanFollowBinding

interface CustomDialogBanFollowInterface {
    fun onFirstClicked()
    fun onSecondClicked()
}

class CustomDialogBanFollow(
    context: Context,
    private val userName: String,
    private val userId: Int
) : AppCompatDialog(context) {
    private lateinit var binding: CustomDialogBanFollowBinding
    private lateinit var customDialogListener : CustomDialogBanFollowInterface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = CustomDialogBanFollowBinding.inflate(layoutInflater)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val view = binding.root
        setContentView(view)
        initView()
    }

    private fun initView(){
        binding.tvContent.text = userName + context.getString(R.string.my_page_follow_ban_explain)
        binding.btnLeft.setOnClickListener {
            customDialogListener.onFirstClicked()
        }
        binding.btnRight.setOnClickListener {
            customDialogListener.onSecondClicked()
        }
    }

    fun setDialogListener(customDialogListener : CustomDialogBanFollowInterface){
        this.customDialogListener = customDialogListener
    }


}