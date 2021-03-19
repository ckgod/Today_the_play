package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeColorBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.databinding.ItemRecipeNameBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.RecipeNameItem
import com.theplay.aos.utils.ViewUtils


class RecipeNameAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<RecipeNameItem>) : RecyclerView.Adapter<RecipeNameAdapter.RecipeNameVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeNameVH {
        val itemBinding = ItemRecipeNameBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeNameVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: RecipeNameVH, position: Int) {
        val item: RecipeNameItem = items[position]
        holder.bind(item)
    }

    inner class RecipeNameVH(var binding: ItemRecipeNameBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeNameItem) {
            val h = 76.5/items.size
            binding.ctlRoot.layoutParams.height = ViewUtils.convertDpToPixel(h.toFloat(),context).toInt()
            binding.vOval.backgroundTintList = ContextCompat.getColorStateList(context, item.color)
            binding.tvName.text = item.name
        }
    }

    companion object{
        const val TAG = "RecipeColorAdapter"
    }
}
