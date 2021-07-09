package com.theplay.aos.fragment.userpage

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.theplay.aos.ApplicationClass
import com.theplay.aos.ApplicationClass.Companion.userInfo
import com.theplay.aos.R
import com.theplay.aos.UserPeedActivity
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.customview.CustomDialogFollowLottie
import com.theplay.aos.databinding.FragmentMyPeedBinding
import com.theplay.aos.databinding.FragmentUserPeedBinding
import kotlin.properties.Delegates

class UserPeedFragment() : BaseKotlinFragment<FragmentUserPeedBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_user_peed

    private val viewModel by lazy { UserPageViewModel() }
//    private val safeArgs : UserPeedFragmentArgs by navArgs()
    private var userId : Int = 1
    private var isFollowing = false

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun initStartView() {
        when(val act = requireActivity()) {
            is UserPeedActivity -> {
                userId = act.getUserId()
            }
        }
        binding.btnFollow.setOnClickListener {
            if(isFollowing) {
                binding.btnFollow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.ingre7)
                binding.btnFollow.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                binding.btnFollow.text = "팔로우"
                viewModel.cancelFollowing(userId)
            }
            else {
                binding.btnFollow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.body)
                binding.btnFollow.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
                binding.btnFollow.text = "팔로잉"
                viewModel.postFollow(userId)
                val dialog = CustomDialogFollowLottie(requireContext()).show()
            }
            isFollowing = !isFollowing
        }

        binding.btnBack.setOnClickListener { removeActivity() }
        binding.btnSetting.visibility = View.INVISIBLE
        binding.vpPager.isSaveEnabled = false
        binding.vpPager.isUserInputEnabled = false
        binding.vpPager.offscreenPageLimit = 4 // 뷰페이저 미리 로딩
        viewPagerAdapter = ViewPagerAdapter(this).apply {
            addFragment(STATE_BOARD, UserPageBoardFragment(userId))
            addFragment(STATE_GOOD, UserPageGoodFragment(userId))
            addFragment(STATE_FOLLOW, UserPageFollowerFragment(userId))
            addFragment(STATE_RECIPE, UserPageMyRecipeFragment(userId))
        }
        binding.vpPager.adapter = viewPagerAdapter
    }

    override fun initDataBinding() {
        viewModel.userPageTopResponse.observe(this@UserPeedFragment, Observer {
            if(it == null) {
                showNetworkError()
            }
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    binding.tvNickName.text = it.data.nickname
                    ApplicationClass.userInfo?.let { myInfo ->
                        if(myInfo.data.nickname.equals(it.data.nickname)) binding.btnFollow.visibility = View.INVISIBLE
                        else binding.btnFollow.visibility = View.VISIBLE
                    }
                    if(it.data.followingYn == "Y") {
                        isFollowing = true
                        binding.btnFollow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.body)
                        binding.btnFollow.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite))
                        binding.btnFollow.text = "팔로잉"
                    }
                    else {
                        isFollowing = false
                        binding.btnFollow.backgroundTintList = ContextCompat.getColorStateList(requireContext(), R.color.ingre7)
                        binding.btnFollow.setTextColor(ContextCompat.getColor(requireContext(), R.color.mainBlack))
                        binding.btnFollow.text = "팔로우"
                    }
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
                                tv1.text = "레시피"
                                tv2.text = it.data.recipes.toString()
                                tab.customView = view
                            }
                        }
                    }.attach()
                }
            }
        })
        viewModel.postFollowingResponse.observe(this@UserPeedFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollowPeed(0,30)
                }
            }
        })
        viewModel.followPeedResponse.observe(this@UserPeedFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    ApplicationClass.followingPostList = it.data.content
                }
            }
        })
        viewModel.cancelFollowingResponse.observe(this@UserPeedFragment, Observer {
            if(it == null) showNetworkError()
            else {
                Log.d(TAG, it.msg)
                if(it.code == 0) {
                    viewModel.getFollowPeed(0,30)
                }
            }
        })
    }

    override fun initAfterBinding() {
        viewModel.getUserTopInfo(userId)
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

    fun removeActivity() {
        requireActivity().finish()
        requireActivity().overridePendingTransition(R.anim.right_in, R.anim.right_out)
    }
    companion object {
        const val TAG = "UserPeedFragment"
        const val STATE_BOARD = 0
        const val STATE_GOOD = 1
        const val STATE_FOLLOW = 2
        const val STATE_RECIPE = 3
    }
}