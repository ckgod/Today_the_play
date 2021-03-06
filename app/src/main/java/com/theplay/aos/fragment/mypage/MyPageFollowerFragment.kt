package com.theplay.aos.fragment.mypage

import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageFollowerBinding
import com.theplay.aos.databinding.FragmentTmpBinding

class MyPageFollowerFragment() : BaseKotlinFragment<FragmentMyPageFollowerBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_follower

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
            addFragment(MyPageBoardFragment.STATE_LEFT, MyPageFollowerLeftFragment())
            addFragment(MyPageBoardFragment.STATE_RIGHT, MyPageFollowerRightFragment())
        }
        binding.vpPager.adapter = viewPagerAdapter
        val tabLayoutTextArray = arrayOf("팔로워","팔로잉")
        TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
            tab.text = tabLayoutTextArray[position]
        }.attach()
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
                0 -> STATE_LEFT
                1 -> STATE_RIGHT
                else -> STATE_RIGHT
            }
            return fragments[rePos]!!
        }


        fun addFragment(status: Int, fragment: Fragment) {
            fragments[status] = fragment
            notifyItemInserted(fragments.size - 1)
        }
    }


    companion object {
        const val TAG = "MyPageFollowerFragment"
        const val STATE_LEFT = 0
        const val STATE_RIGHT = 1

    }
}