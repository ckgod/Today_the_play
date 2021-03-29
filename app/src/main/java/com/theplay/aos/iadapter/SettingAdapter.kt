package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.theplay.aos.R
import com.theplay.aos.customview.CustomDialogListener
import com.theplay.aos.customview.CustomDialogTwoButton
import com.theplay.aos.databinding.ItemMyPageBoardAllBinding
import com.theplay.aos.databinding.ItemRecipeColorBinding
import com.theplay.aos.databinding.ItemRecipeImageBinding
import com.theplay.aos.databinding.ItemSettingBinding
import com.theplay.aos.item.MyPageBoardAllItem
import com.theplay.aos.item.RecipeColorItem
import com.theplay.aos.item.RecipeImageItem
import com.theplay.aos.item.SettingItem
import com.theplay.aos.utils.ViewUtils


class SettingAdapter(
    private val activity: Activity,
    private val context: Context,
    private val items: MutableList<SettingItem>
) : RecyclerView.Adapter<SettingAdapter.SettingVH>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SettingVH {
        val itemBinding =
            ItemSettingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SettingVH(itemBinding)
    }

    override fun getItemCount(): Int = items.size


    override fun onBindViewHolder(holder: SettingVH, position: Int) {
        val item: SettingItem = items[position]
        holder.bind(item)
    }

    inner class SettingVH(var binding: ItemSettingBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SettingItem) {
            binding.tvName.text = item.name
            itemView.setOnClickListener {
                when (item.type) {
                    MODIFY_PROFILE -> {

                    }
                    BLOCK_USER -> {

                    }
                    LOGOUT -> {
                        var dialog = CustomDialogTwoButton(
                            context,
                            context.getString(R.string.logout_title),
                            context.getString(R.string.logout_content),
                            context.getString(R.string.logout_title),
                            context.getString(R.string.cancel)
                        ).apply {
                            setDialogListener(object : CustomDialogListener {
                                override fun onFirstClicked() {
                                    var preferences: SharedPreferences = context.getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE)
                                    var editor = preferences.edit()
                                    editor.clear()
                                    editor.apply()
                                    activity.findNavController(R.id.main_nav_host_fragment).navigate(R.id.action_settingFragment_to_prevLoginFragment)
                                    dismiss()
                                }
                                override fun onSecondClicked() {
                                    dismiss()
                                }
                            })
                        }
                        dialog.show()
                    }
                    NOTICE -> {

                    }
                    ASK -> {

                    }
                    SET_ALARM -> {

                    }
                    QUIT_ACCOUNT -> {

                    }
                    OPEN_SOURCE -> {

                    }
                }
            }
        }
    }

    companion object {
        const val TAG = "SettingAdapter"
        const val MODIFY_PROFILE = 1
        const val BLOCK_USER = 2
        const val LOGOUT = 3
        const val NOTICE = 4
        const val ASK = 5
        const val SET_ALARM = 6
        const val QUIT_ACCOUNT = 7
        const val OPEN_SOURCE = 8
    }
}
