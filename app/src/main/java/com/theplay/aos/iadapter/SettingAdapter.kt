package com.theplay.aos.iadapter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.theplay.aos.ApplicationClass.Companion.X_ACCESS_TOKEN
import com.theplay.aos.R
import com.theplay.aos.SplashActivity
import com.theplay.aos.customview.CustomDialogListener
import com.theplay.aos.customview.CustomDialogTwoButton
import com.theplay.aos.databinding.ItemSettingBinding
import com.theplay.aos.fragment.setting.SettingFragmentDirections
import com.theplay.aos.item.SettingItem


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
            setIcon(binding, item)
            itemView.setOnClickListener {
                when (item.type) {
                    MODIFY_PROFILE -> {
                        activity.findNavController(R.id.main_nav_host_fragment).navigate(SettingFragmentDirections.actionSettingFragmentToSettingProfileFragment())
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
                                    val nextIntent = Intent(context, SplashActivity::class.java)
                                    nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(nextIntent)
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
                        activity.findNavController(R.id.main_nav_host_fragment).navigate(SettingFragmentDirections.actionSettingFragmentToSettingNoticeFragment())
                    }
                    ASK -> {
                        val browserIntent = Intent(Intent.ACTION_SEND)
                        browserIntent.type = "plain/text"
                        val address = arrayOf(context.getString(R.string.ask_email))
                        browserIntent.putExtra(Intent.EXTRA_EMAIL, address)
                        activity.startActivity(browserIntent)
                    }
                    SET_ALARM -> {

                    }
                    QUIT_ACCOUNT -> {
                        var dialog = CustomDialogTwoButton(
                            context,
                            context.getString(R.string.quit_title),
                            context.getString(R.string.quit_content),
                            context.getString(R.string.quit_title),
                            context.getString(R.string.cancel)
                        ).apply {
                            setDialogListener(object : CustomDialogListener {
                                override fun onFirstClicked() {
                                    var preferences: SharedPreferences = context.getSharedPreferences(X_ACCESS_TOKEN, Context.MODE_PRIVATE)
                                    var editor = preferences.edit()
                                    editor.clear()
                                    editor.apply()
                                    val nextIntent = Intent(context, SplashActivity::class.java)
                                    nextIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
                                    nextIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                                    context.startActivity(nextIntent)
                                    dismiss()
                                }
                                override fun onSecondClicked() {
                                    dismiss()
                                }
                            })
                        }
                        dialog.show()
                    }
                    OPEN_SOURCE -> {
                        activity.findNavController(R.id.main_nav_host_fragment).navigate(SettingFragmentDirections.actionSettingFragmentToSettingOpenSourceFragment())
                    }
                }
            }
        }
    }

    fun setIcon(binding: ItemSettingBinding, item: SettingItem) {
        when (item.type) {
            MODIFY_PROFILE -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_profile_setting))
            }
            LOGOUT -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_logout))
            }
            NOTICE -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_notice))
            }
            ASK -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_ask_the_play))
            }
            SET_ALARM -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_alarm_setting))
            }
            QUIT_ACCOUNT -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_quit_account))
            }
            OPEN_SOURCE -> {
                binding.ivSetting.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_open_source))
            }
        }
    }

    companion object {
        const val TAG = "SettingAdapter"
        const val MODIFY_PROFILE = 1
//        const val BLOCK_USER = 2
        const val LOGOUT = 2
        const val NOTICE = 3
        const val ASK = 4
        const val SET_ALARM = 5
        const val QUIT_ACCOUNT = 6
        const val OPEN_SOURCE = 7
    }
}
