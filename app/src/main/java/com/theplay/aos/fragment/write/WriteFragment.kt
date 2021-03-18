package com.theplay.aos.fragment.write

import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.akvelon.imagepicker.ImagePicker
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentWriteBinding

class WriteFragment() : BaseKotlinFragment<FragmentWriteBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_write

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
//        ImagePicker.launch(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        if (view != null) {
//            val parentViewGroup = requireView().parent as ViewGroup?
//            parentViewGroup?.removeAllViews()
//        }
    }

    companion object {
        const val TAG = "WriteFragment"
    }
}
