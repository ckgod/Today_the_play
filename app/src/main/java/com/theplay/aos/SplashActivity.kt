package com.theplay.aos

import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.theplay.aos.databinding.ActivityMainBinding
import com.theplay.aos.databinding.ActivitySplashBinding
import com.theplay.aos.fragment.account.LoginFragment
import com.theplay.aos.fragment.account.PrevLoginFragment
import com.theplay.aos.fragment.account.SignUpFragment
import com.theplay.aos.fragment.account.SignUpNextFragment
import com.theplay.aos.fragment.setting.SettingFragment
import com.theplay.aos.fragment.write.WriteFragment
import com.theplay.aos.fragment.write.WriteRecipeFragment


class SplashActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    companion object {
        const val TAG = "SplashActivity"
    }
}