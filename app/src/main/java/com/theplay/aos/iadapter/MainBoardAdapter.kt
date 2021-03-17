package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.theplay.aos.R
import com.theplay.aos.databinding.ItemMainBoardBinding
import com.theplay.aos.item.MainBoardItem
import com.theplay.aos.utils.ViewUtils


class MainBoardAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MainBoardItem>) : RecyclerView.Adapter<MainBoardAdapter.MainBoardVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainBoardVH {
        val itemBinding = ItemMainBoardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainBoardVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MainBoardVH, position: Int) {
        val item: MainBoardItem = items[position]
        holder.bind(item)
    }

    inner class MainBoardVH(var binding: ItemMainBoardBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MainBoardItem) {
            var imageHeight = ((ViewUtils.getStandardSize_X(activity)/2 * 0.93) / 161) * 214
//            Log.d(TAG, imageHeight.toString())
            binding.ivMainContent.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()
            Glide.with(context).load(R.drawable.dummy_image2)
                .apply(RequestOptions().transform(CenterCrop(), RoundedCorners(ViewUtils.convertDpToPixel(8f,context).toInt())))
                .into(binding.ivMainContent)


//            binding.tvName.text = item.userName
//            //binding.ivProfile.setImageDrawable() -> 여기 서버에서 url받아오고 글라이드나 피카소로 뿌리기
//            //binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img_profile_defalt))
//            Glide.with(context).load(item.userSelfie)
//                    .apply(RequestOptions().transform(CircleCrop(), CenterCrop()))
//                    .into(binding.ivProfile)
        }
    }

    companion object{
        const val TAG = "MainBoardAdapter"
    }
}
