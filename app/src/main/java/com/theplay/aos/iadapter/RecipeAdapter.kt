package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.item.*
import com.theplay.aos.utils.ViewUtils

interface RecipeAdapterInterface {
    fun clickMore(tagName : String)
}

class RecipeAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<RecipeItem>) : RecyclerView.Adapter<RecipeAdapter.RecipeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeVH {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeVH(itemBinding)
    }
    private var listener : RecipeAdapterInterface? = null
    var imageList : MutableList<RecipeImageItem> = mutableListOf()
    var colorList : MutableList<RecipeColorItem> = mutableListOf()
    var nameList : MutableList<RecipeNameItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeVH, position: Int) {
        val item: RecipeItem = items[position]
        holder.bind(item)
    }

    fun setInterface(recipeAdapterInterface: RecipeAdapterInterface) {
        this.listener = recipeAdapterInterface
    }

    inner class RecipeVH(var binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeItem) {
            // itemList : 레시피옆 이미지들
            // colorList : 색 채우기
            // nameList : 재료 이름들
            binding.tvName.text = "#${item.name}"

            colorList = mutableListOf()
            nameList = mutableListOf()
            binding.rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rv.adapter = RecipeImageAdapter(activity, context, item.imageList)

            binding.rvBottleColor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvBottleColor.adapter = RecipeColorAdapter(activity, context, item.colorList)
            binding.rvBottleName.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.rvBottleName.adapter = RecipeNameAdapter(activity,context,item.nameList)

            binding.btnShowAll.setOnClickListener {
                listener?.clickMore(item.name)
            }
        }
    }

    companion object{
        const val TAG = "RecipeAdapter"
    }
}
