package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyRecipeBinding
import com.theplay.aos.item.MyRecipeContentItem
import com.theplay.aos.item.MyRecipeItem

class MyRecipeAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MyRecipeItem>) : RecyclerView.Adapter<MyRecipeAdapter.MyRecipeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyRecipeVH {
        val itemBinding = ItemMyRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyRecipeVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyRecipeVH, position: Int) {
        val item: MyRecipeItem = items[position]
        holder.bind(item)
    }

    inner class MyRecipeVH(var binding: ItemMyRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        private var contentList : MutableList<MyRecipeContentItem> = mutableListOf()
        fun bind(item: MyRecipeItem) {
            binding.tvName.text = "#${item.name}"
            binding.rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rv.adapter = MyRecipeContentAdapter(activity,context,item.contentList)
        }
    }

    companion object{
        const val TAG = "MyRecipeAdapter"
    }
}
