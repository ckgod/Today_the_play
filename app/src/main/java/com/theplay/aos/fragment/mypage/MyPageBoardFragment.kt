package com.theplay.aos.fragment.mypage

import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.myPostedPost
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMyPageBoardBinding

class MyPageBoardFragment() : BaseKotlinFragment<FragmentMyPageBoardBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_my_page_board

    private val viewModel by lazy { MyPageBoardViewModel() }
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        viewModel.getMyPostResponse.observe(this@MyPageBoardFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    var tmpList : MutableList<MainBoardResponse.Content> = mutableListOf()
                    for(content in it.data.content) {
                        tmpList.add(content)
                    }
                    myPostedPost = tmpList
                    binding.vpPager.isSaveEnabled = false
                    binding.vpPager.isUserInputEnabled = false
                    binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
                    viewPagerAdapter = ViewPagerAdapter(this).apply {
                        addFragment(STATE_LEFT, MyPageBoardPersonalFragment())
                        addFragment(STATE_RIGHT, MyPageBoardAllFragment())
                    }
                    binding.vpPager.adapter = viewPagerAdapter
                    TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
                        when(position) {
                            0 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page_board, null)
                                val iv = view.findViewById<ImageView>(R.id.iv_my_page_board)
                                iv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.my_page_board_selector1))
                                tab.customView = view
                            }
                            1 -> {
                                val view = layoutInflater.inflate(R.layout.tab_my_page_board, null)
                                val iv = view.findViewById<ImageView>(R.id.iv_my_page_board)
                                iv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.my_page_board_selector2))
                                tab.customView = view
                            }
                        }
                    }.attach()
                    binding.tlTab.tabIconTint = null
                }
            }
        })
    }

    override fun initDataBinding() {
    }

    override fun initAfterBinding() {
        if(myPostedPost == null) {
            viewModel.getMyPost(0,30)
        }
        else {
            binding.vpPager.isSaveEnabled = false
            binding.vpPager.isUserInputEnabled = false
            binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
            viewPagerAdapter = ViewPagerAdapter(this).apply {
                addFragment(STATE_LEFT, MyPageBoardPersonalFragment())
                addFragment(STATE_RIGHT, MyPageBoardAllFragment())
            }
            binding.vpPager.adapter = viewPagerAdapter
            TabLayoutMediator(binding.tlTab, binding.vpPager) { tab, position ->
                when(position) {
                    0 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page_board, null)
                        val iv = view.findViewById<ImageView>(R.id.iv_my_page_board)
                        iv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.my_page_board_selector1))
                        tab.customView = view
                    }
                    1 -> {
                        val view = layoutInflater.inflate(R.layout.tab_my_page_board, null)
                        val iv = view.findViewById<ImageView>(R.id.iv_my_page_board)
                        iv.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.my_page_board_selector2))
                        tab.customView = view
                    }
                }
            }.attach()
            binding.tlTab.tabIconTint = null
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
        const val TAG = "MyPageBoardFragment"
        const val STATE_LEFT = 0
        const val STATE_RIGHT = 1

    }
}