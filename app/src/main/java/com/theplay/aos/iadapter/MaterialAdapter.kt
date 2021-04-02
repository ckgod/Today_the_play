package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass.Companion.colorHashMap
import com.theplay.aos.ApplicationClass.Companion.colorToCodeHashMap
import com.theplay.aos.ApplicationClass.Companion.iconHashMap
import com.theplay.aos.R
import com.theplay.aos.api.model.AddPostRequest
import com.theplay.aos.databinding.ItemDrinksBinding
import com.theplay.aos.databinding.ItemRecipeContentBinding
import com.theplay.aos.fragment.write.WriteFragmentDirections
import com.theplay.aos.item.DrinkItem
import com.theplay.aos.utils.DrinkUtil


class MaterialAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<AddPostRequest.Ingredient>) : RecyclerView.Adapter<MaterialAdapter.MaterialVH>() {
    var isExpanded : Boolean = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MaterialVH {
        val itemBinding = ItemRecipeContentBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return MaterialVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: MaterialVH, position: Int) {
        val item: AddPostRequest.Ingredient = items[position]
        holder.bind(item)
    }

    inner class MaterialVH(var binding: ItemRecipeContentBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: AddPostRequest.Ingredient) {
            binding.ivIcon.background = ContextCompat.getDrawable(context, iconHashMap[item.iconName]!!)
            binding.ivIcon.backgroundTintList = ContextCompat.getColorStateList(context, colorHashMap[item.color]!!)
            binding.tvName.text = "${item.name}  ${item.quantity}"
            binding.btnColor.backgroundTintList = ContextCompat.getColorStateList(context, colorHashMap[item.color]!!)
            binding.tvName.setTextColor(ContextCompat.getColorStateList(context, colorHashMap[item.color]!!))
            binding.btnColor.setOnClickListener {
                if(!isExpanded) {
                    binding.expandedView.expand()
                }
                else {
                    binding.expandedView.collapse()
                }
                isExpanded = !isExpanded
            }

            binding.color1.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color1.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color1.backgroundTintList
                binding.tvName.setTextColor(binding.color1.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.colorWhite]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color2.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color2.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color2.backgroundTintList
                binding.tvName.setTextColor(binding.color2.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre7]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color3.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color3.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color3.backgroundTintList
                binding.tvName.setTextColor(binding.color3.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre5]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color4.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color4.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color4.backgroundTintList
                binding.tvName.setTextColor(binding.color4.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre6]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color5.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color5.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color5.backgroundTintList
                binding.tvName.setTextColor(binding.color5.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre8]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color6.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color6.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color6.backgroundTintList
                binding.tvName.setTextColor(binding.color6.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.mainColor]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color7.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color7.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color7.backgroundTintList
                binding.tvName.setTextColor(binding.color7.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre3]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color8.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color8.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color8.backgroundTintList
                binding.tvName.setTextColor(binding.color8.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color9.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color9.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color9.backgroundTintList
                binding.tvName.setTextColor(binding.color9.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre4]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
            binding.color10.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color10.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color10.backgroundTintList
                binding.tvName.setTextColor(binding.color10.backgroundTintList)
                item.color = colorToCodeHashMap[R.color.ingre2]!!
                item.iconName = DrinkUtil.convertIconFromColor(item.iconName, item.color)
                Log.d(TAG, "iconName : ${item.iconName}")
            }
        }
    }

    companion object{
        const val TAG = "MaterialAdapter"
    }
}
