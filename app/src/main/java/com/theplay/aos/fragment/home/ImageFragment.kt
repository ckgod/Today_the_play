package com.theplay.aos.fragment.home

import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentImageBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.utils.ViewUtils

class ImageFragment(imageString : String) : BaseKotlinFragment<FragmentImageBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_image

    override fun initStartView() {
//        binding.ivImage.setImageDrawable() -> imageString 으로 이미지 세팅
//        binding.ivImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.dummy_image1))

        // 첫 로딩때 안먹힘
        Glide.with(requireContext()).load(R.drawable.dummy_image1)
            .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,requireContext()).toInt())))
            .into(binding.ivImage)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }


    companion object {
        const val TAG = "ImageFragment"
    }
}