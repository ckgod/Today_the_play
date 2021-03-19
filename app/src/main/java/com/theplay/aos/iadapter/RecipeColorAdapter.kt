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
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.utils.ViewUtils


class RecipeColorAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<RecipeColorItem>) : RecyclerView.Adapter<RecipeColorAdapter.RecipeColorVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeColorVH {
        val itemBinding = ItemRecipeColorBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeColorVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: RecipeColorVH, position: Int) {
        val item: RecipeColorItem = items[position]
        holder.bind(item)
    }

    inner class RecipeColorVH(var binding: ItemRecipeColorBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeColorItem) {
            Log.d(TAG, "colorList size : ${items.size}")
            binding.ctlRoot.backgroundTintList = ContextCompat.getColorStateList(context, item.color)
            val h = 76.5/items.size
            Log.d(TAG, "height : $h")
            binding.vColor.layoutParams.height = ViewUtils.convertDpToPixel(h.toFloat(), context).toInt()
        }
    }

    companion object{
        const val TAG = "RecipeColorAdapter"
    }
}
