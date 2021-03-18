package com.theplay.aos.fragment

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.theplay.aos.R
import com.theplay.aos.base.BaseKotlinFragment
import com.theplay.aos.databinding.FragmentMainBinding
import com.theplay.aos.setupWithNavController
import eightbitlab.com.blurview.RenderScriptBlur

interface MainFragmentListener{
    fun goWrite()
}

class MainFragment() : BaseKotlinFragment<FragmentMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_main

    private lateinit var bottomNavigationView: BottomNavigationView
    private var currentNavController: LiveData<NavController>? = null
    var listener : MainFragmentListener? = null


    override fun initStartView() {
        binding.btnWrite.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(v: View) {
                Log.d(TAG, "write click")
//                listener?.goWrite()
                requireActivity().findNavController(R.id.main_nav_host_fragment).navigate(MainFragmentDirections.actionMainFragmentToWriteFragment())
            }
        })
    }

    override fun initDataBinding() {

    }

    override fun initAfterBinding() {

    }

    override fun reLoadUI() {
    }

    fun interceptBackPressed() : Boolean {
//        if(binding.vpPager.currentItem == 0) {
//            binding.vpPager.setCurrentItem(1, true)
//            return true
//        }
        return false
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null) {
            setupBottomNavigationBar()
        } // Else, need to wait for onRestoreInstanceState
    }


    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        // Now that BottomNavigationBar has restored its instance state
        // and its selectedItemId, we can proceed with setting up the
        // BottomNavigationBar with Navigation
        setupBottomNavigationBar()
    }

    /**
     * Called on first creation and when restoring state.
     */
    private fun setupBottomNavigationBar() {


        val bottomNavigationView = binding.bottomNav
        binding.bottomNav.itemIconTintList = null
        binding.bottomNav.selectedItemId = R.id.nav_home

        val navGraphIds = listOf(
                R.navigation.nav_write,
                R.navigation.nav_home,
                R.navigation.nav_recipe,
                R.navigation.nav_my_peed
        )

        // Setup the bottom navigation view with a list of navigation graphs
        val controller = bottomNavigationView.setupWithNavController(
                navGraphIds = navGraphIds,
                fragmentManager = childFragmentManager,
                containerId = R.id.nav_host_container,
                intent = requireActivity().intent
        )

        // Whenever the selected controller changes, setup the action bar.
        subscribeBottomNavigation(controller)

//        addNotificationBadge()
    }

    private fun subscribeBottomNavigation(controller: LiveData<NavController>) {
        controller.observe(viewLifecycleOwner, Observer { navController ->
            val appBarConfig = AppBarConfiguration(navController.graph)
            //binding.bottomNav.setupWithNavController(navController, appBarConfig)
        })
    }



//    private fun addNotificationBadge() {
//        // Add badge to bottom navigation
//        val bottomNavigationView = binding.bottomNav
//        val menuItemId = bottomNavigationView.menu.getItem(2).itemId
//        val badge = bottomNavigationView.getOrCreateBadge(menuItemId)
//        badge.number = 2
//    }


    companion object {
        const val TAG = "MainFragment"
    }
}