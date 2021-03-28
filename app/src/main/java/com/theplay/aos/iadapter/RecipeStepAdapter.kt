package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.databinding.ItemRecipeStairBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.WriteRecipeStepItem
import com.theplay.aos.utils.ViewUtils


class RecipeStepAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<WriteRecipeStepItem>) : RecyclerView.Adapter<RecipeStepAdapter.RecipeStepVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepVH {
        val itemBinding = ItemRecipeStairBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeStepVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeStepVH, position: Int) {
        val item: WriteRecipeStepItem = items[position]
        holder.bind(item)
    }

    inner class RecipeStepVH(var binding: ItemRecipeStairBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: WriteRecipeStepItem) {
            binding.tvStep.text = item.step.toString()
        }
    }

    companion object{
        const val TAG = "RecipeStepAdapter"
    }
}
