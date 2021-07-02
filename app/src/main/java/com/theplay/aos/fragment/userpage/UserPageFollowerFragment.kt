package com.theplay.aos.fragment.userpage

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentUserPageFollowerBinding

class UserPageFollowerFragment(private val userId : Int) : BaseKotlinFragment<FragmentUserPageFollowerBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_follower

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
            addFragment(STATE_LEFT, UserPageFollowerLeftFragment(userId))
            addFragment(STATE_RIGHT, UserPageFollowerRightFragment())
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
        const val TAG = "UserPageFollowerFragment"
        const val STATE_LEFT = 0
        const val STATE_RIGHT = 1

    }
}