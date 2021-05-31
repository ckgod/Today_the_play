package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemPostMenuBinding
import com.theplay.aos.item.PostMenuItem

interface PostMenuInterface{
    fun clickMenu(type : Int)
}

class PostMenuAdapter(
    private val activity: Activity,
    private val context: Context,
    private val items: MutableList<PostMenuItem>
) : RecyclerView.Adapter<PostMenuAdapter.PostMenuVH>() {

    var listener : PostMenuInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostMenuVH {
        val itemBinding =
            ItemPostMenuBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostMenuVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: PostMenuVH, position: Int) {
        val item: PostMenuItem = items[position]
        holder.bind(item)
    }

    inner class PostMenuVH(var binding: ItemPostMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: PostMenuItem) {
            binding.tv.text = item.title
            if (adapterPosition == items.size - 1) {
                binding.vLineGray.visibility = View.INVISIBLE
            }
            itemView.setOnClickListener {
                listener?.clickMenu(item.type)
            }
        }
    }

    fun setMenuInterface(postMenuInterface: PostMenuInterface) {
        this.listener = postMenuInterface
    }

    companion object {
        const val TAG = "PostMenuAdapter"
    }
}
