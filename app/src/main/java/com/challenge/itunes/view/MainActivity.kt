package com.challenge.itunes.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.challenge.itunes.R
import com.challenge.itunes.viewmodel.ItunesMainViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val viewModel: ItunesMainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initData()
        setupUi()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun initData() {
        viewModel.getAllMusic()
    }

    private fun setupUi() {
        setContentView(R.layout.activity_main)
        //Setup nav controller
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupActionBarWithNavController(navController)
        //Setup nav actionbar
        val appBarConfiguration = AppBarConfiguration(setOf(R.id.splashFragment, R.id.listFragment))
        setupActionBarWithNavController(navController, appBarConfiguration)
    }
}