package com.theplay.aos

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.theplay.aos.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private var backBtnTime: Long = 0
    private var currentNavController: LiveData<NavController>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.bottomNav.itemIconTintList = null

        supportFragmentManager.addOnBackStackChangedListener {
            val backStackEntryCount = supportFragmentManager.backStackEntryCount
            val fragments = supportFragmentManager.fragments
            val fragmentCount = fragments.size
        }
        if (savedInstanceState == null) {
            binding.bottomNav.selectedItemId = R.id.nav_home
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState



        binding.btnWrite.setOnClickListener {
//            Toast.makeText(this, "write clicked", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, WriteActivity::class.java)
            startActivity(intent)
            overridePendingTransition(R.anim.slide_from_left, R.anim.slide_to_right)
        }
    }
    private fun setupBottomNavigationBar() {
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        val navGraphIds = listOf(
            R.navigation.nav_tmp,
            R.navigation.nav_home,
            R.navigation.nav_recipe,
            R.navigation.nav_my_peed
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.main_nav_host_fragment,
            intent = intent
        )

        currentNavController = controller
//        subscribeBottomNavigation(currentNavController!!)
    }

    override fun onSupportNavigateUp(): Boolean {
        return currentNavController?.value?.navigateUp() ?: false
    }

//    private fun subscribeBottomNavigation(controller: LiveData<NavController>) {
//        controller.observe(this, Observer { navController ->
//            Log.d(TAG, navController.currentDestination?.id.toString())
//            when(navController.currentDestination?.id) {
//                R.id.settingFragment -> hideBottomNav()
//                else -> showBottomNav()
//            }
//        })
//    }

    fun setBottomNavVisible(flag : Boolean){
        binding.bottomNav.visibility = if(flag) View.VISIBLE else View.GONE
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