package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.R
import com.theplay.aos.customview.CustomDialogDeleteTag
import com.theplay.aos.databinding.ItemDrinksBinding
import com.theplay.aos.fragment.write.WriteFragmentDirections
import com.theplay.aos.item.DrinkItem

interface DrinkAdapterInterface {
    fun clickDelete(icon : Int, colorType : Int, name : String, position : Int)
    fun clickRecipe(icon : Int, name : String, colorType: Int)
}

class DrinkAdapter(private val activity : Activity, private val context: Context, private val items: MutableList<DrinkItem>) : RecyclerView.Adapter<DrinkAdapter.DrinkVH>() {
    var isExpanded : Boolean = false
    private var listener : DrinkAdapterInterface? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DrinkVH {
        val itemBinding = ItemDrinksBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return DrinkVH(itemBinding)
    }

    fun setInterface(drinkAdapterInterface: DrinkAdapterInterface) {
        this.listener = drinkAdapterInterface
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: DrinkVH, position: Int) {
        val item: DrinkItem = items[position]
        holder.bind(item)
    }

    inner class DrinkVH(var binding: ItemDrinksBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: DrinkItem) {
            binding.ivIcon.background = ContextCompat.getDrawable(context, item.icon)
            binding.ivIcon.backgroundTintList = ContextCompat.getColorStateList(context, item.colorType)
            binding.tvName.text = item.name
            binding.btnColor.backgroundTintList = ContextCompat.getColorStateList(context, item.colorType)
            binding.tvName.setTextColor(ContextCompat.getColorStateList(context, item.colorType))
            binding.btnColor.setOnClickListener {
                if(!isExpanded) {
                    binding.expandedView.expand()
                }
                else {
                    binding.expandedView.collapse()
                }
                isExpanded = !isExpanded
            }
            binding.btnDelete.setOnClickListener {
                listener?.clickDelete(item.icon, item.colorType, item.name, adapterPosition)
            }
            if(item.hasRecipe) {
                binding.btnRecipe.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_recipe_true))
            }
            else {
                binding.btnRecipe.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_recipe_false))
            }

            binding.btnRecipe.setOnClickListener {
                for(check in items) {
                    if(check.hasRecipe) {
                        Toast.makeText(context, "레시피는 하나만 추가할 수 있습니다",Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                listener?.clickRecipe(item.icon,item.name,item.colorType)
//                activity.findNavController(R.id.main_nav_host_fragment).navigate(WriteFragmentDirections.actionWriteFragmentToWriteRecipeFragment(item.icon,item.name,item.colorType))
//                activity.findNavController(R.id.main_nav_host_fragment).navigate(WriteFragmentDirections.actionWriteFragmentToWriteRecipeFragment(item.icon,item.name, item.colorType))
            }

            binding.color1.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color1.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color1.backgroundTintList
                binding.tvName.setTextColor(binding.color1.backgroundTintList)
                item.colorType = R.color.colorWhite
            }
            binding.color2.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color2.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color2.backgroundTintList
                binding.tvName.setTextColor(binding.color2.backgroundTintList)
                item.colorType = R.color.ingre7
            }
            binding.color3.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color3.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color3.backgroundTintList
                binding.tvName.setTextColor(binding.color3.backgroundTintList)
                item.colorType = R.color.ingre5
            }
            binding.color4.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color4.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color4.backgroundTintList
                binding.tvName.setTextColor(binding.color4.backgroundTintList)
                item.colorType = R.color.ingre6
            }
            binding.color5.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color5.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color5.backgroundTintList
                binding.tvName.setTextColor(binding.color5.backgroundTintList)
                item.colorType = R.color.ingre8
            }
            binding.color6.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color6.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color6.backgroundTintList
                binding.tvName.setTextColor(binding.color6.backgroundTintList)
                item.colorType = R.color.mainColor
            }
            binding.color7.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color7.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color7.backgroundTintList
                binding.tvName.setTextColor(binding.color7.backgroundTintList)
                item.colorType = R.color.ingre3
            }
            binding.color8.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color8.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color8.backgroundTintList
                binding.tvName.setTextColor(binding.color8.backgroundTintList)
                item.colorType = R.color.ingre
            }
            binding.color9.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color9.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color9.backgroundTintList
                binding.tvName.setTextColor(binding.color9.backgroundTintList)
                item.colorType = R.color.ingre4
            }
            binding.color10.setOnClickListener {
                binding.btnColor.backgroundTintList = binding.color10.backgroundTintList
                binding.ivIcon.backgroundTintList = binding.color10.backgroundTintList
                binding.tvName.setTextColor(binding.color10.backgroundTintList)
                item.colorType = R.color.ingre2
            }

        }
    }

    companion object{
        const val TAG = "DrinkAdapter"
    }
}
