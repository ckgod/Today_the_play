package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.theplay.aos.databinding.ItemFollowingBinding
import com.theplay.aos.databinding.ItemMainBoardBinding
import com.theplay.aos.fragment.home.ImageFragment
import com.theplay.aos.fragment.mypage.MyPeedFragment
import com.theplay.aos.item.FollowItem
import com.theplay.aos.item.MainBoardItem
import com.theplay.aos.utils.ViewUtils


class FollowAdapter(private val rootfa : Fragment, private val activity : Activity, private val context: Context, private val items: MutableList<FollowItem>) : RecyclerView.Adapter<FollowAdapter.FollowVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowVH {
        val itemBinding = ItemFollowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FollowVH(itemBinding)
    }

    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: FollowVH, position: Int) {
        val item: FollowItem = items[position]
        holder.bind(item)
    }

    inner class FollowVH(var binding: ItemFollowingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: FollowItem) {
            binding.vpPager.isSaveEnabled = false
            binding.vpPager.isUserInputEnabled = true
            viewPagerAdapter = ViewPagerAdapter(rootfa).apply {
                addFragment(ImageFragment("image"))
                addFragment(ImageFragment("image"))
                addFragment(ImageFragment("image"))
                addFragment(ImageFragment("image"))
                addFragment(ImageFragment("image"))
            }
            binding.vpPager.adapter = viewPagerAdapter
            binding.wormDotsIndicator.setViewPager2(binding.vpPager)
        }
    }

    private inner class ViewPagerAdapter(fa: Fragment) : FragmentStateAdapter(fa) {
//        var fragments: HashMap<Int, Fragment> = HashMap()
        var fragments : MutableList<Fragment> = mutableListOf()

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

    companion object{
        const val TAG = "FollowAdapter"
    }
}
