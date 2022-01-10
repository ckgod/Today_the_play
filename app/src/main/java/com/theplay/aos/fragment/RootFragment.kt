package com.theplay.aos.fragment

import android.annotation.SuppressLint
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentRootBinding
import com.theplay.aos.fragment.write.WriteFragment
import com.theplay.aos.utils.ViewUtils

class RootFragment() : BaseKotlinFragment<FragmentRootBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_root

    private var viewPagerAdapter: ViewPagerAdapter? = null

    private val mainFragmentListener = object : MainFragmentListener {
        override fun goWrite() {
            Log.d(TAG, "setCurrentItem")
//            binding.vpPager.setCurrentItem(0,true)
//            findNavController().navigate(RootFragmentDirections.actionRootFragmentToWriteFragment2())
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun initStartView() {
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 1 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
//            addFragment(STATE_WRITE, WriteFragment())
            addFragment(STATE_MAIN, MainFragment().apply {
                this.listener = mainFragmentListener
            })
        }
        binding.vpPager.adapter = viewPagerAdapter
//        binding.vpPager.post{
//            binding.vpPager.setCurrentItem(1,false)
//        }

    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {

    }

    private inner class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
        var fragments: HashMap<Int, Fragment> = HashMap()

        override fun getItemCount(): Int {
            return fragments.size
        }

        override fun createFragment(position: Int): Fragment {
            val rePos = when (position) {
                0 -> STATE_MAIN
                1 -> STATE_MAIN
                else -> STATE_MAIN
            }
            return fragments[rePos]!!
        }

        fun addFragment(status: Int, fragment: Fragment) {
            fragments[status] = fragment
            notifyItemInserted(fragments.size - 1)
        }



    }
    companion object {
        const val TAG = "RootFragment"
        const val STATE_WRITE = 0
        const val STATE_MAIN = 1
    }
}