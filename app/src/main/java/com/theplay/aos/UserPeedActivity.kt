package com.theplay.aos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import com.theplay.aos.databinding.ActivityUserPeedBinding
import com.theplay.aos.fragment.userpage.UserPeedFragment

class UserPeedActivity : AppCompatActivity() {
    lateinit var binding : ActivityUserPeedBinding
    private val safeArgs : UserPeedActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserPeedBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun getUserId() : Int {
        return safeArgs.userId
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        when(currentFragment) {
            is UserPeedFragment -> {
                currentFragment.removeActivity()
                return
            }
        }
        super.onBackPressed()
    }

    companion object{
        const val TAG = "UserPeedActivity"
    }
}