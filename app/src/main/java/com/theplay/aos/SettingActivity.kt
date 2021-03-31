package com.theplay.aos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.theplay.aos.databinding.ActivitySettingBinding
import com.theplay.aos.fragment.setting.*


class SettingActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        Log.d(TAG, "onBackPressed() currentFragment:$currentFragment")
        when (currentFragment) {
            is SettingFragment -> {
                currentFragment.removeActivity()
                return
            }
            is SettingProfileFragment -> {
                currentFragment.backStep()
                return
            }
            is SettingChangePwFragment -> {
                currentFragment.backStep()
                return
            }
            is SettingPrivacyFragment -> {
                currentFragment.backStep()
                return
            }
            is SettingNoticeFragment -> {
                currentFragment.backStep()
                return
            }
            is SettingNoticeDetailFragment -> {
                currentFragment.backStep()
                return
            }
            is SettingOpenSourceFragment -> {
                currentFragment.backStep()
                return
            }
            else -> {
                super.onBackPressed()
            }
        }
        super.onBackPressed()
    }

    companion object {
        const val TAG = "SettingActivity"
    }
}