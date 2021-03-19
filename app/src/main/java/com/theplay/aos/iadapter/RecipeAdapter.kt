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


class RecipeAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<RecipeItem>) : RecyclerView.Adapter<RecipeAdapter.RecipeVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeVH {
        val itemBinding = ItemRecipeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RecipeVH(itemBinding)
    }

    var itemList : MutableList<RecipeImageItem> = mutableListOf()
    var colorList : MutableList<RecipeColorItem> = mutableListOf()
    var nameList : MutableList<RecipeNameItem> = mutableListOf()

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: RecipeVH, position: Int) {
        val item: RecipeItem = items[position]
        holder.bind(item)
    }

    inner class RecipeVH(var binding: ItemRecipeBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: RecipeItem) {
            // itemList : 레시피옆 이미지들
            // colorList : 색 채우기
            // nameList : 재료 이름들
            colorList = mutableListOf()
            nameList = mutableListOf()
            binding.rv.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            itemList.add(RecipeImageItem("asdfasdf"))
            binding.rv.adapter = RecipeImageAdapter(activity, context, itemList)
            binding.rvBottleColor.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            colorList.add(RecipeColorItem(R.color.ingre7))
            colorList.add(RecipeColorItem(R.color.mainColor))
            colorList.add(RecipeColorItem(R.color.ingre8))
            binding.rvBottleColor.adapter = RecipeColorAdapter(activity, context, colorList)
            binding.rvBottleName.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            nameList.add(RecipeNameItem("카스", R.color.ingre7))
            nameList.add(RecipeNameItem("참이슬",R.color.mainColor))
            nameList.add(RecipeNameItem("콜라",R.color.ingre8))
            binding.rvBottleName.adapter = RecipeNameAdapter(activity,context,nameList)

//            var imageHeight = ((ViewUtils.getStandardSize_X(activity)/3 * 0.93) / 104) * 139
////            Log.d(TAG, imageHeight.toString())
//            binding.ivMyPageBoardAll.layoutParams.height = ViewUtils.convertDpToPixel(imageHeight.toFloat(),context).toInt()
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
        const val TAG = "RecipeAdapter"
    }
}
