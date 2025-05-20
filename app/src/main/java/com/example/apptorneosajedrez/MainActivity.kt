package com.example.apptorneosajedrez // Declaración del paquete de la aplicación

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
import androidx.navigation.fragment.NavHostFragment // Fragmento contenedor para la navegación
import com.example.apptorneosajedrez.databinding.ActivityMainBinding // Binding generado para el layout de la actividad principal

class MainActivity : AppCompatActivity() { // Definición de la clase principal que hereda de AppCompatActivity

    // Variables miembro de la clase
    private lateinit var appBarConfiguration: AppBarConfiguration // Configuración de la barra de aplicación, se inicializará después
    private lateinit var binding: ActivityMainBinding // Binding para acceder a vistas del layout, se inicializará después

    override fun onCreate(savedInstanceState: Bundle?) { // Método llamado cuando se crea la actividad
        super.onCreate(savedInstanceState) // Llama al método onCreate de la clase padre

        binding = ActivityMainBinding.inflate(layoutInflater) // Infla el layout usando view binding
        setContentView(binding.root) // Establece la vista raíz del binding como contenido de la actividad

        setSupportActionBar(binding.appBarMain.toolbar) // Configura la toolbar como barra de acción

        // Espera a que los fragmentos se inicialicen completamente
        supportFragmentManager.findFragmentById(R.id.nav_host_fragment_content_main)?.let { navHostFragment ->
            // Uso de let para ejecutar código solo si el fragmento no es nulo
            val navController = (navHostFragment as NavHostFragment).navController // Obtiene el controlador de navegación del fragmento host

            val drawerLayout: DrawerLayout = binding.drawerLayout // Referencia al layout del drawer
            val navView: NavigationView = binding.navView // Referencia a la vista de navegación

            // Configura la AppBarConfiguration con los destinos de nivel superior (que mostrarán el ícono del drawer)
            appBarConfiguration = AppBarConfiguration(
                setOf(
                    R.id.nav_home, R.id.nav_torneos, R.id.nav_jugadores, R.id.nav_inscripciones
                ), drawerLayout
            )

            // Configura la barra de acción con el controlador de navegación
            setupActionBarWithNavController(navController, appBarConfiguration)

            // Configura la vista de navegación con el controlador de navegación
            navView.setupWithNavController(navController)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean { // Método llamado para crear el menú de opciones
        menuInflater.inflate(R.menu.main, menu) // Infla el menú desde el recurso XML
        return true // Devuelve true para mostrar el menú
    }

    override fun onSupportNavigateUp(): Boolean { // Método llamado cuando se presiona el botón de navegación hacia arriba
        val navController = findNavController(R.id.nav_host_fragment_content_main) // Obtiene el controlador de navegación
        // Intenta navegar hacia arriba según la configuración, si falla, usa el comportamiento predeterminado
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
}