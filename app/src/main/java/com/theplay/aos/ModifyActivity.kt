package com.theplay.aos

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.theplay.aos.databinding.ActivityModifyBinding
import com.theplay.aos.databinding.ActivityWriteBinding
import com.theplay.aos.fragment.setting.SettingFragment
import com.theplay.aos.fragment.write.ModifyFragment
import com.theplay.aos.fragment.write.WriteFragment
import com.theplay.aos.fragment.write.WriteRecipeFragment
import com.theplay.aos.utils.DrinkUtil

class ModifyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModifyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModifyBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onBackPressed() {
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.main_nav_host_fragment)
        val currentFragment = navHostFragment?.childFragmentManager?.fragments?.get(0)
        Log.d(TAG, "onBackPressed() currentFragment:$currentFragment")
        when (currentFragment) {
            is ModifyFragment -> {
                DrinkUtil.clearRecipeSaved()
                currentFragment.removeActivity()
                return
            }
            is WriteRecipeFragment -> {
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
        const val TAG = "ModifyActivity"
    }
}