package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.utils.ViewUtils


class RecipeImageAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<RecipeImageItem>) : RecyclerView.Adapter<RecipeImageAdapter.RecipeImageVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeImageVH {
        val itemBinding = ItemRecipeImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeImageVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeImageVH, position: Int) {
        val item: RecipeImageItem = items[position]
        holder.bind(item)
    }

    inner class RecipeImageVH(var binding: ItemRecipeImageBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeImageItem) {

        }
    }

    companion object{
        const val TAG = "RecipeImageAdapter"
    }
}
