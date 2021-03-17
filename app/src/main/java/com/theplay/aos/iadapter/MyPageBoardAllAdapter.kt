package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.utils.ViewUtils


class MyPageBoardAllAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<MyPageBoardAllItem>) : RecyclerView.Adapter<MyPageBoardAllAdapter.MyPageBoardAllVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPageBoardAllVH {
        val itemBinding = ItemMyPageBoardAllBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyPageBoardAllVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MyPageBoardAllVH, position: Int) {
        val item: MyPageBoardAllItem = items[position]
        holder.bind(item)
    }

    inner class MyPageBoardAllVH(var binding: ItemMyPageBoardAllBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: MyPageBoardAllItem) {
            var imageHeight = ((ViewUtils.getStandardSize_X(activity)/3 * 0.93) / 104) * 139
////            Log.d(TAG, imageHeight.toString())
            binding.ivMyPageBoardAll.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()
//            binding.ivMyPageBoardAll.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()

//            binding.tvName.text = item.userName
//            //binding.ivProfile.setImageDrawable() -> 여기 서버에서 url받아오고 글라이드나 피카소로 뿌리기
//            //binding.ivProfile.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.img_profile_defalt))
//            Glide.with(context).load(item.userSelfie)
//                    .apply(RequestOptions().transform(CircleCrop(), CenterCrop()))
//                    .into(binding.ivProfile)
        }
    }

    companion object{
        const val TAG = "MyPageBoardAllAdapter"
    }
}
