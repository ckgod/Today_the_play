package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemDrinkIconBinding
import com.theplay.aos.item.AddIconItem


class AddIconAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<AddIconItem>) : RecyclerView.Adapter<AddIconAdapter.AddIconVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddIconVH {
        val itemBinding = ItemDrinkIconBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return AddIconVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: AddIconVH, position: Int) {
        val item: AddIconItem = items[position]
        holder.bind(item)
    }

    inner class AddIconVH(var binding: ItemDrinkIconBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AddIconItem) {
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, item.icon))
//            itemView.setOnClickListener { v -> v?.let { callback?.onItemClicked(it) } }
        }
    }

    companion object{
        const val TAG = "RecipeImageAdapter"
    }
}
