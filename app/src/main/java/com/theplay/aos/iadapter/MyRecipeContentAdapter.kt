package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass
import com.theplay.aos.R
import com.theplay.aos.databinding.ItemMyRecipeContentBinding
import com.theplay.aos.item.MyRecipeContentItem

class MyRecipeContentAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MyRecipeContentItem>) : RecyclerView.Adapter<MyRecipeContentAdapter.MyRecipeContentVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipeContentVH {
        val itemBinding = ItemMyRecipeContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRecipeContentVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyRecipeContentVH, position: Int) {
        val item: MyRecipeContentItem = items[position]
        holder.bind(item)
    }

    inner class MyRecipeContentVH(var binding: ItemMyRecipeContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyRecipeContentItem) {
            binding.tvName.text = item.name
            binding.tvName.setTextColor(ContextCompat.getColor(context, R.color.mainBlack))
            binding.ctlMainContent.backgroundTintList = ApplicationClass.colorHashMap[item.color]?.let { ContextCompat.getColorStateList(context, it)}
            binding.ivIcon.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.drinks_soju_b))
        }
    }

    companion object{
        const val TAG = "MyRecipeContentAdapter"
    }
}