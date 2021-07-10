package com.theplay.aos.fragment.userpage

import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentUserPageBoardBinding

class UserPageBoardFragment(private val userId : Int) : BaseKotlinFragment<FragmentUserPageBoardBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_page_board

    private val viewModel by lazy { UserPageBoardViewModel() }
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {

    }

    override fun initDataBinding() {
        viewModel.getUserPostsResponse.observe(this@UserPageBoardFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
//                    var tmpList : MutableList<MainBoardResponse.Content> = it.data.content
                    ApplicationClass.tmpPostList = it.data.content
                    binding.vpPager.isSaveEnabled = false
                    binding.vpPager.isUserInputEnabled = false
                    binding.vpPager.offscreenPageLimit = 2 // 뷰페이저 미리 로딩
                    viewPagerAdapter = ViewPagerAdapter(this).apply {
                        addFragment(STATE_LEFT, UserPageBoardPersonalFragment())
                        addFragment(STATE_RIGHT, UserPageBoardAllFragment())
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
            hideProgressNoShadow()
        })
    }

    override fun initAfterBinding() {
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            showProgressNoShadow()
            viewModel.getUserPosts(userId,0,25)
        },200)
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
        const val TAG = "UserPageBoardFragment"
        const val STATE_LEFT = 0
        const val STATE_RIGHT = 1

    }
}