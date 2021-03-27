package com.theplay.aos.fragment.write

import android.util.Log
import androidx.core.content.ContextCompat
import androidx.navigation.NavArgs
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.databinding.FragmentWriteRecipeBinding

class WriteRecipeFragment() : BaseKotlinFragment<FragmentWriteRecipeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_write_recipe

    private val safeArgs : WriteRecipeFragmentArgs by navArgs()

    override fun initStartView() {
        Log.d(TAG, findNavController().toString())
        binding.ivIcon.background = ContextCompat.getDrawable(requireContext(), safeArgs.icon)
        binding.tvName.text = safeArgs.name
        binding.ivIcon.backgroundTintList = ContextCompat.getColorStateList(requireContext(), safeArgs.colorType)
        binding.tvName.setTextColor(ContextCompat.getColor(requireContext(), safeArgs.colorType))

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {

    }

    companion object {
        const val TAG = "WriteRecipeFragment"
    }
}