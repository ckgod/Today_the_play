package com.theplay.aos.fragment.mypage

import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPeedBinding
import com.theplay.aos.fragment.MainFragmentDirections

interface MyPeedListener {
    fun clickSetting()
}

class MyPeedFragment() : BaseKotlinFragment<FragmentMyPeedBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_peed


    private var viewPagerAdapter: ViewPagerAdapter? = null
    var listener : MyPeedListener? = null

    override fun initStartView() {
        binding.btnSetting.setOnClickListener {
            requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(R.id.action_mainFragment_to_settingFragment)
        }

        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 4 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
            addFragment(STATE_BOARD, MyPageBoardFragment())
            addFragment(STATE_GOOD, MyPageGoodFragment())
            addFragment(STATE_FOLLOW, MyPageFollowerFragment())
            addFragment(STATE_RECIPE, MyPageMyRecipeFragment())
        }
        binding.vpPager.adapter = viewPagerAdapter
        val tabLayoutTextArray = arrayOf("게시물","좋아요","팔로워","나의 레시피")
        TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
            when(position) {
                0 -> {
                    val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                    val tv1 = view.findViewById<TextView>(R.id.tv_title)
                    val tv2 = view.findViewById<TextView>(R.id.tv_count)
                    tv1.text = "게시물"
                    tv2.text = "222"
                    tab.customView = view
                }
                1 -> {
                    val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                    val tv1 = view.findViewById<TextView>(R.id.tv_title)
                    val tv2 = view.findViewById<TextView>(R.id.tv_count)
                    tv1.text = "좋아요"
                    tv2.text = "2333"
                    tab.customView = view
                }
                2 -> {
                    val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                    val tv1 = view.findViewById<TextView>(R.id.tv_title)
                    val tv2 = view.findViewById<TextView>(R.id.tv_count)
                    tv1.text = "팔로워"
                    tv2.text = "10.3만"
                    tab.customView = view
                }
                3 -> {
                    val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                    val tv1 = view.findViewById<TextView>(R.id.tv_title)
                    val tv2 = view.findViewById<TextView>(R.id.tv_count)
                    tv1.text = "나의 레시피"
                    tv2.text = "20"
                    tab.customView = view
                }
            }
//            tab.text = tabLayoutTextArray[position]
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
                0 -> STATE_BOARD
                1 -> STATE_GOOD
                2 -> STATE_FOLLOW
                3 -> STATE_RECIPE
                else -> STATE_RECIPE
            }
            return fragments[rePos]!!
        }

        fun addFragment(status: Int, fragment: Fragment) {
            fragments[status] = fragment
            notifyItemInserted(fragments.size - 1)
        }
    }


    companion object {
        const val TAG = "MyPeedFragment"
        const val STATE_BOARD = 0
        const val STATE_GOOD = 1
        const val STATE_FOLLOW = 2
        const val STATE_RECIPE = 3
    }
}