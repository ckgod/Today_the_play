package com.theplay.aos.fragment.write

import android.content.Intent
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theplay.aos.imagepicker.ImagePicker
import com.theplay.aos.imagepicker.ImagePicker.getImages
import com.theplay.aos.imagepicker.ImagePicker.shouldResolve
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentWriteBinding
import com.theplay.aos.fragment.home.ImageFragment
import java.io.File


class WriteFragment() : BaseKotlinFragment<FragmentWriteBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_write

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = true
        viewPagerAdapter = ViewPagerAdapter(this)
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
        //https://github.com/akvelon/android-image-picker
        ImagePicker.launch(this)
    }

    override fun onDestroyView() {
        super.onDestroyView()
//        if (view != null) {
//            val parentViewGroup = requireView().parent as ViewGroup?
//            parentViewGroup?.removeAllViews()
//        }
    }


    //            get a single image only
//            val image: File? = getSingleImageOrNull(data)
//            Log.d(TAG, image.toString())
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (shouldResolve(requestCode, resultCode)) {
            if (data?.extras?.getInt("cancelSelect") == -1) {
                findNavController().popBackStack()
                return
            }
            Log.d(TAG, "onActivity Result")
            // Get a list of picked images
            val images: List<File> = getImages(data)
            Log.d(TAG, images.toString())
            if (images.isEmpty()) {
                showCustomToast("사진을 선택해주세요")
            } else {
                for (item in images) {
                    viewPagerAdapter?.addFragment(ImageFragment(item))
                }
                binding.vpPager.adapter = viewPagerAdapter
                binding.wormDotsIndicator.setViewPager2(binding.vpPager)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    private inner class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        var fragments: MutableList<Fragment> = mutableListOf()

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            return fragments[position]
        }

        fun addFragment(fragment: Fragment) {
            fragments.add(fragment)
            notifyItemInserted(fragments.size - 1)
        }
    }

    companion object {
        const val TAG = "WriteFragment"
    }
}
