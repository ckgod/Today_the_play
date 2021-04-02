package com.theplay.aos.fragment.mypage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPeedBinding

class MyPeedFragment() : BaseKotlinFragment<FragmentMyPeedBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_peed

    private val viewModel by lazy { MyPageViewModel() }

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        binding.btnSetting.setOnClickListener {
            findNavController().navigate(MyPeedFragmentDirections.actionMyPeedFragmentToSettingActivity())
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
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView")
        super.onDestroyView()
    }

    override fun onResume() {
        Log.d(TAG, "onResume")
        userInfo?.let { // 홈프레그먼트에서 유저정보를 받아왔다면
            binding.tvNickName.text = it.data.nickname
            TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
                when(position) {
                    0 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                        val tv1 = view.findViewById<TextView>(R.id.tv_title)
                        val tv2 = view.findViewById<TextView>(R.id.tv_count)
                        tv1.text = "게시물"
                        tv2.text = it.data.posts.toString()
                        tab.customView = view
                    }
                    1 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                        val tv1 = view.findViewById<TextView>(R.id.tv_title)
                        val tv2 = view.findViewById<TextView>(R.id.tv_count)
                        tv1.text = "좋아요"
                        tv2.text = it.data.likes.toString()
                        tab.customView = view
                    }
                    2 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                        val tv1 = view.findViewById<TextView>(R.id.tv_title)
                        val tv2 = view.findViewById<TextView>(R.id.tv_count)
                        tv1.text = "팔로워"
                        tv2.text = it.data.followers.toString()
                        tab.customView = view
                    }
                    3 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                        val tv1 = view.findViewById<TextView>(R.id.tv_title)
                        val tv2 = view.findViewById<TextView>(R.id.tv_count)
                        tv1.text = "나의 레시피"
                        tv2.text = it.data.recipes.toString()
                        tab.customView = view
                    }
                }
            }.attach()
        }
        super.onResume()
    }


    override fun initDataBinding() {
        viewModel.myPageTopResponse.observe(this@MyPeedFragment, Observer {
            if(it == null) {
                showNetworkError()
            }
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    binding.tvNickName.text = it.data.nickname
                    TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
                        when(position) {
                            0 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                                val tv1 = view.findViewById<TextView>(R.id.tv_title)
                                val tv2 = view.findViewById<TextView>(R.id.tv_count)
                                tv1.text = "게시물"
                                tv2.text = it.data.posts.toString()
                                tab.customView = view
                            }
                            1 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                                val tv1 = view.findViewById<TextView>(R.id.tv_title)
                                val tv2 = view.findViewById<TextView>(R.id.tv_count)
                                tv1.text = "좋아요"
                                tv2.text = it.data.likes.toString()
                                tab.customView = view
                            }
                            2 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                                val tv1 = view.findViewById<TextView>(R.id.tv_title)
                                val tv2 = view.findViewById<TextView>(R.id.tv_count)
                                tv1.text = "팔로워"
                                tv2.text = it.data.followers.toString()
                                tab.customView = view
                            }
                            3 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page,null)
                                val tv1 = view.findViewById<TextView>(R.id.tv_title)
                                val tv2 = view.findViewById<TextView>(R.id.tv_count)
                                tv1.text = "나의 레시피"
                                tv2.text = it.data.recipes.toString()
                                tab.customView = view
                            }
                        }
                    }.attach()
                }
            }
        })
    }

    override fun initAfterBinding() {
        if(userInfo == null) {
            viewModel.getMyPageTopInfo()
        }
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