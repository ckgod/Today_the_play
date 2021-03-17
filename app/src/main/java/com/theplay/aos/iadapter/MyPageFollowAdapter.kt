package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemMyPageFollowBinding
import com.theplay.aos.databinding.ItemMyPageGoodBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.MyPageFollowItem
import com.theplay.aos.item.MyPageGoodItem
import com.theplay.aos.utils.ViewUtils


class MyPageFollowAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MyPageFollowItem>) : RecyclerView.Adapter<MyPageFollowAdapter.MyPageFollowVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageFollowVH {
        val itemBinding = ItemMyPageFollowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageFollowVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPageFollowVH, position: Int) {
        val item: MyPageFollowItem = items[position]
        holder.bind(item)
    }

    inner class MyPageFollowVH(var binding: ItemMyPageFollowBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyPageFollowItem) {
           binding.tvName.text = item.name
        }
    }

    companion object{
        const val TAG = "MyPageFollowAdapter"
    }
}
