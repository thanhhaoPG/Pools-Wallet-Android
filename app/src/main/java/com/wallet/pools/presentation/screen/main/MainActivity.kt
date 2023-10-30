package com.wallet.pools.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wallet.pools.R
import com.wallet.pools.base.BaseActivity
import com.wallet.pools.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>() {


    override fun getViewBinding() = ActivityMainBinding.inflate(layoutInflater)
    override val viewModel: MainViewModel by viewModels()

    private lateinit var navController: NavController



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBottomNavigationBar()


    }

    fun hideBottomView() {
        binding.apply {
            viewShadow.visibility = View.GONE
            bottomNavigationViewMain.visibility = View.GONE
        }


    }


    fun showBottomView() {

        binding.apply {
            viewShadow.visibility = View.VISIBLE
            bottomNavigationViewMain.visibility = View.VISIBLE

        }


    }


    private fun setupBottomNavigationBar() {
        val navHostFragment = supportFragmentManager.findFragmentById(
            R.id.navigationHostFragment
        ) as NavHostFragment

        navController = navHostFragment.navController

        // Setup the bottom navigation view with navController
        binding.bottomNavigationViewMain.setupWithNavController(navController)
        binding.bottomNavigationViewMain.itemTextAppearanceInactive =
            R.style.MyBottomNavigationTitleText
        binding.bottomNavigationViewMain.itemTextAppearanceActive =
            R.style.MyBottomNavigationTitleText
        binding.bottomNavigationViewMain.selectedItemId = R.id.wallet


    }


}
