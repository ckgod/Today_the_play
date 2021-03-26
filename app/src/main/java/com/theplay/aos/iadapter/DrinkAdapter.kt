package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemDrinkIconBinding
import com.theplay.aos.databinding.ItemDrinksBinding
import com.theplay.aos.item.AddIconItem
import com.theplay.aos.item.DrinkItem


class DrinkAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<DrinkItem>) : RecyclerView.Adapter<DrinkAdapter.DrinkVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkVH {
        val itemBinding = ItemDrinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DrinkVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DrinkVH, position: Int) {
        val item: DrinkItem = items[position]
        holder.bind(item)
    }

    inner class DrinkVH(var binding: ItemDrinksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DrinkItem) {
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, item.icon))
            binding.tvName.text = item.name
            binding.btnColor.backgroundTintList = ContextCompat.getColorStateList(context, item.colorType)
            binding.tvName.setTextColor(ContextCompat.getColorStateList(context, item.colorType))
        }
    }

    companion object{
        const val TAG = "RecipeImageAdapter"
    }
}
