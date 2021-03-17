package com.theplay.aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.Toast
import com.theplay.aos.databinding.ActivityMainBinding
import com.theplay.aos.fragment.MainFragment
import com.theplay.aos.fragment.RootFragment


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var backBtnTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.d(TAG, "touch")
        return super.onTouchEvent(event)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        Log.d(TAG, "onBackPressed() currentFragment:$currentFragment")
        when (currentFragment) {
            is MainFragment -> {
                if (currentFragment.interceptBackPressed()) {
                    return
                } else {
                    val curTime = System.currentTimeMillis()
                    val gapTime = curTime - backBtnTime

                    if (gapTime in 0..2000) {
                        super.onBackPressed()
                    } else {
                        backBtnTime = curTime;
                        Toast.makeText(this, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else -> {
                super.onBackPressed()
            }
        }
    }

    companion object {
        const val TAG = "MainActivity"
    }
}