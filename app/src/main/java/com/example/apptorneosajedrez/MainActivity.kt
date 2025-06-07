package com.example.apptorneosajedrez

import android.os.Bundle
import android.view.Menu
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.apptorneosajedrez.databinding.ActivityMainBinding
import android.content.Intent
import com.example.apptorneosajedrez.ui.mapa.MapaActivity
import androidx.navigation.ui.NavigationUI

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeViewBinding()
        configureToolbar()
        setupNavigationSystem()
    }

    private fun initializeViewBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun configureToolbar() {
        setSupportActionBar(binding.appBarMain.toolbar)
    }

    private fun setupNavigationSystem() {
        val navHostFragment = getNavHostFragment() ?: return
        val navController = extractNavController(navHostFragment)

        configureAppBarAndDrawer(navController)
    }

    private fun getNavHostFragment(): Fragment? {
        return supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)
    }

    private fun extractNavController(navHostFragment: Fragment): NavController {
        return (navHostFragment as NavHostFragment).navController
    }

    private fun configureAppBarAndDrawer(navController: NavController) {
        val drawerLayout = binding.drawerLayout
        val navigationView = binding.navView

        setupAppBarConfiguration(drawerLayout)
        connectActionBarWithNavigation(navController)
        connectDrawerWithNavigation(navigationView, navController)

        navigationView.setNavigationItemSelectedListener { menuItem ->
            if (menuItem.itemId == R.id.nav_mapa) {
                // Abrir SecondActivity manualmente
                val intent = Intent(this, MapaActivity::class.java)
                startActivity(intent)
                binding.drawerLayout.closeDrawers()
                true
            } else {
                // Dejar que NavigationUI maneje los demás
                val handled = NavigationUI.onNavDestinationSelected(menuItem, navController)
                if (handled) binding.drawerLayout.closeDrawers()
                handled
            }
        }
    }

    private fun setupAppBarConfiguration(drawerLayout: DrawerLayout) {
        appBarConfiguration = AppBarConfiguration(
            getTopLevelDestinations(),
            drawerLayout
        )
    }

    private fun getTopLevelDestinations(): Set<Int> {
        return setOf(
            R.id.nav_home,
            R.id.nav_torneos,
            R.id.nav_jugadores,
            R.id.nav_inscripciones,
            R.id.nav_mapa
        )
    }

    private fun connectActionBarWithNavigation(navController: NavController) {
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    private fun connectDrawerWithNavigation(navigationView: NavigationView, navController: NavController) {
        navigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}