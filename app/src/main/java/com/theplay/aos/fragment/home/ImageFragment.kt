package com.theplay.aos.fragment.home

import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentImageBinding
import com.theplay.aos.databinding.FragmentTmpBinding
import com.theplay.aos.utils.ViewUtils
import java.io.File

class ImageFragment() : BaseKotlinFragment<FragmentImageBinding>() {
    private var imageString : String? = null
    private var imageFile : File? = null
    private var isFile : Boolean = false
    override val layoutResourceId: Int
        get() = R.layout.fragment_image
    constructor(imageString: String) : this() {
        this.imageString = imageString
        isFile = false
    }
    constructor(imageFile : File) : this() {
        this.imageFile = imageFile
        isFile = true
    }

    override fun initStartView() {
//        binding.ivImage.setImageDrawable() -> imageString 으로 이미지 세팅
//        binding.ivImage.setImageDrawable(ContextCompat.getDrawable(requireContext(),R.drawable.dummy_image1))


//        var str = "https://d2tkmpefgqef0b.cloudfront.net/KakaoTalk_Photo_2021-03-20-17-24-02-12.png-202129261829059"
        if(isFile) {
            Glide.with(requireContext()).load(imageFile)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,requireContext()).toInt())))
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(binding.ivImage)
        }
        else {
            Glide.with(requireContext()).load(imageString)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,requireContext()).toInt())))
                .transition(DrawableTransitionOptions.withCrossFade(200))
                .into(binding.ivImage)
        }

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