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
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.*
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.RecipeNameItem
import com.theplay.aos.utils.ViewUtils


class IngredientFlexAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MainBoardResponse.Ingredient>) : RecyclerView.Adapter<IngredientFlexAdapter.IngredientFlexVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientFlexVH {
        val itemBinding = ItemFlexBoxTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return IngredientFlexVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: IngredientFlexVH, position: Int) {
        val item: MainBoardResponse.Ingredient = items[position]
        holder.bind(item)
    }

    inner class IngredientFlexVH(var binding: ItemFlexBoxTagBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainBoardResponse.Ingredient) {
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context, iconHashMap[item.iconName]!!))
            binding.tvName.text = "${item.name}  ${item.quantity}"
            binding.tvName.setTextColor(ContextCompat.getColor(context, colorHashMap[item.color]!!))
        }
    }

    companion object{
        const val TAG = "IngredientFlexAdapter"
    }
}
