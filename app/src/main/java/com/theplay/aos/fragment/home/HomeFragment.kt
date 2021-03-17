package com.theplay.aos.fragment.home

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentHomeBinding

class HomeFragment() : BaseKotlinFragment<FragmentHomeBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_home


    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
            addFragment(STATE_MAIN_BOARD, MainBoardFragment())
            addFragment(STATE_FOLLOWING, FollowingFragment())
        }
        binding.vpPager.adapter = viewPagerAdapter
        val tabLayoutTextArray = arrayOf("오늘 한 주", "팔로잉")
        TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()
//        binding.tlTab.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
//            override fun onTabReselected(tab: TabLayout.Tab?) {}
//            override fun onTabUnselected(tab: TabLayout.Tab?) {}
//            override fun onTabSelected(tab: TabLayout.Tab?) {
//                tab?.position?.let { binding.vpPager?.setCurrentItem(it, false) }
//            }
//        })
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
                0 -> STATE_MAIN_BOARD
                1 -> STATE_FOLLOWING
                else -> STATE_FOLLOWING
            }
            return fragments[rePos]!!
        }

        fun addFragment(status: Int, fragment: Fragment) {
            fragments[status] = fragment
            notifyItemInserted(fragments.size - 1)
        }
    }

    companion object {
        const val TAG = "HomeFragment"
        const val STATE_MAIN_BOARD = 0
        const val STATE_FOLLOWING = 1
    }
}