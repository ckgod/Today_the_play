package com.theplay.aos.fragment.write

import android.view.ViewGroup
import com.akvelon.imagepicker.ImagePicker
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentWriteBinding

class WriteFragment() : BaseKotlinFragment<FragmentWriteBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_write

    override fun initStartView() {

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
//        ImagePicker.launch(this)
    }

    companion object {
        const val TAG = "WriteFragment"
    }
}