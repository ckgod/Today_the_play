package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemMyPageGoodBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.MyPageGoodItem
import com.theplay.aos.utils.ViewUtils


class MyPageGoodAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MyPageGoodItem>) : RecyclerView.Adapter<MyPageGoodAdapter.MyPageGoodVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageGoodVH {
        val itemBinding = ItemMyPageGoodBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageGoodVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPageGoodVH, position: Int) {
        val item: MyPageGoodItem = items[position]
        holder.bind(item)
    }

    inner class MyPageGoodVH(var binding: ItemMyPageGoodBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyPageGoodItem) {
            var imageHeight = ((ViewUtils.getStandardSize_X(activity)/3 * 0.93) / 104) * 139
            binding.ivMyPageGood.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()
            Glide.with(context).load(item.image)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,context).toInt())))
                .into(binding.ivMyPageGood)
        }
    }

    companion object{
        const val TAG = "MyPageGoodAdapter"
    }
}
