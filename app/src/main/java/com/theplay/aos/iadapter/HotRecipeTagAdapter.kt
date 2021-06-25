package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.api.model.HotRecipeDetailResponse
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.*
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.RecipeNameItem
import com.theplay.aos.utils.ViewUtils


class HotRecipeTagAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<HotRecipeDetailResponse.AlcoholTag>) : RecyclerView.Adapter<HotRecipeTagAdapter.HotRecipeTagVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotRecipeTagVH {
        val itemBinding = ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HotRecipeTagVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: HotRecipeTagVH, position: Int) {
        val item: HotRecipeDetailResponse.AlcoholTag = items[position]
        holder.bind(item)
    }

    inner class HotRecipeTagVH(var binding: ItemTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: HotRecipeDetailResponse.AlcoholTag) {
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.iconName]!!))
            binding.tvName.text = item.name
            binding.tvName.setTextColor(ContextCompat.getColor(context, colorHashMap[item.color]!!))
        }
    }

    companion object{
        const val TAG = "HotRecipeTagAdapter"
    }
}
