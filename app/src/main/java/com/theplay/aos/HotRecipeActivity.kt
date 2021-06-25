package com.theplay.aos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import com.theplay.aos.databinding.ActivityHotRecipeBinding
import com.theplay.aos.fragment.recipe.HotRecipeFragment

class HotRecipeActivity : AppCompatActivity() {
    private lateinit var binding : ActivityHotRecipeBinding
    private val safeArgs : HotRecipeActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHotRecipeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    fun returnArgs(): HotRecipeActivityArgs {
        return safeArgs
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        Log.d(TAG, "onBackPressed() currentFragment:$currentFragment")
        when(currentFragment) {
            is HotRecipeFragment -> {
                currentFragment.removeActivity()
                return
            }
        }
        super.onBackPressed()
    }

    companion object{
        const val TAG = "HotRecipeActivity"
    }
}