package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.api.model.NoticeResponse
import com.theplay.aos.databinding.ItemNoticeBinding
import com.theplay.aos.fragment.setting.SettingNoticeFragmentDirections


class NoticeAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<NoticeResponse.NoticeId>) : RecyclerView.Adapter<NoticeAdapter.NoticeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoticeVH {
        val itemBinding = ItemNoticeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoticeVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: NoticeVH, position: Int) {
        val item: NoticeResponse.NoticeId = items[position]
        holder.bind(item)
    }

    inner class NoticeVH(var binding: ItemNoticeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: NoticeResponse.NoticeId) {
            binding.tvName.text = item.title
            itemView.setOnClickListener {
                activity.findNavController(R.id.main_nav_host_fragment).navigate(SettingNoticeFragmentDirections.actionSettingNoticeFragmentToSettingNoticeDetailFragment(item.id))
            }
        }
    }

    companion object{
        const val TAG = "NoticeAdapter"
    }
}
