package com.example.apptorneosajedrez

import android.os.Bundle // Para manejar el estado de la actividad
import android.view.Menu // Para manejar el menú de opciones
import com.google.android.material.navigation.NavigationView // Para la vista de navegación del drawer
import androidx.navigation.findNavController // Para obtener el controlador de navegación
import androidx.navigation.ui.AppBarConfiguration // Para configurar la barra de aplicación
import androidx.navigation.ui.navigateUp // Para la navegación hacia arriba en la jerarquía de pantallas
import androidx.navigation.ui.setupActionBarWithNavController // Para conectar la barra de acción con el controlador de navegación
import androidx.navigation.ui.setupWithNavController // Para conectar la vista de navegación con el controlador
import androidx.drawerlayout.widget.DrawerLayout // Para el panel lateral deslizable
import androidx.appcompat.app.AppCompatActivity // Actividad base con características de AppCompat
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment // Fragmento contenedor para la navegación
import com.example.apptorneosajedrez.databinding.ActivityMainBinding // Binding generado para el layout de la actividad principal

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
            R.id.nav_inscripciones
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