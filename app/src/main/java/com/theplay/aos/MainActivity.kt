package com.theplay.aos

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.theplay.aos.databinding.ActivityMainBinding
import com.theplay.aos.fragment.account.LoginFragment
import com.theplay.aos.fragment.account.PrevLoginFragment
import com.theplay.aos.fragment.account.SignUpFragment
import com.theplay.aos.fragment.account.SignUpNextFragment
import com.theplay.aos.fragment.setting.SettingFragment
import com.theplay.aos.fragment.write.WriteFragment
import com.theplay.aos.fragment.write.WriteRecipeFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var backBtnTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataBinding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        Log.d(TAG, "onBackPressed() currentFragment:$currentFragment")
        when (currentFragment) {
            is WriteFragment -> {
                if (currentFragment.interceptBackPressed()) {
                    return
                }
            }
            is SettingFragment -> {
                if(currentFragment.interceptBackPressed()) {
                    return
                }
            }
            is WriteRecipeFragment -> {
                if(currentFragment.interceptBackPressed()) {
                    return
                }
            }
            is LoginFragment -> {
                if(currentFragment.interceptBackPressed()) {
                    return
                }
            }
            is SignUpFragment -> {
                if(currentFragment.interceptBackPressed()) {
                    return
                }
            }
            is SignUpNextFragment -> {
                if(currentFragment.interceptBackPressed()) {
                    return
                }
            }
            else -> {
                super.onBackPressed()
            }
        }
        super.onBackPressed()
    }


//    val curTime = System.currentTimeMillis()
//    val gapTime = curTime - backBtnTime
//
//    if (gapTime in 0..2000) {
//        super.onBackPressed()
//    } else {
//        backBtnTime = curTime;
//        Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
//    }
    companion object {
        const val TAG = "MainActivity"
    }
}