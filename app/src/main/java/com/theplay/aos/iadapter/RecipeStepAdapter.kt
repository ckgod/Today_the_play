package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.api.model.AddPostRequest
import com.theplay.aos.api.model.MainBoardResponse
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.databinding.ItemRecipeStairBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.WriteRecipeStepItem
import com.theplay.aos.utils.ViewUtils


class RecipeStepAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MainBoardResponse.Step>) : RecyclerView.Adapter<RecipeStepAdapter.RecipeStepVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeStepVH {
        val itemBinding = ItemRecipeStairBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeStepVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeStepVH, position: Int) {
        val item: MainBoardResponse.Step = items[position]
        holder.bind(item)
    }

    inner class RecipeStepVH(var binding: ItemRecipeStairBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainBoardResponse.Step) {
//            binding.tvStep.text = item.number.toString()
            binding.tvStep.text = item.number.toString()
            binding.tvContent.text = item.content
        }
    }

    companion object{
        const val TAG = "RecipeStepAdapter"
    }
}
