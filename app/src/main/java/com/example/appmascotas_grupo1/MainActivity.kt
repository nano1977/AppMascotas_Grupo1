package com.example.appmascotas_grupo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmascotas_grupo1.navigation.NavigationEvent
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.ui.screens.ConfiguracionScreen
import com.example.appmascotas_grupo1.ui.screens.PerfilScreen
import com.example.appmascotas_grupo1.ui.screens.PrincipalScreen
import com.example.appmascotas_grupo1.ui.theme.AppMascotas_Grupo1Theme
import com.example.appmascotas_grupo1.viewmodel.MainViewModel
import kotlinx.coroutines.flow.collectLatest

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMascotas_Grupo1Theme {

                // 1. Crear el ViewModel y el controlador de navegación
                val viewModel: MainViewModel = viewModel()
                val navController = rememberNavController()

                // 2. Escuchar los eventos de navegación que emite el ViewModel
                LaunchedEffect(Unit) {
                    viewModel.navigationEvents.collectLatest { event ->
                        when (event) {
                            // Ir a una pantalla específica
                            is NavigationEvent.NavigateTo -> {
                                navController.navigate(event.route.route) {
                                    event.popupToRoute?.let { popup ->
                                        popUpTo(popup.route) {
                                            inclusive = event.inclusive
                                        }
                                    }
                                    launchSingleTop = event.singleTop
                                    restoreState = true
                                }
                            }
                            // Volver a la pantalla anterior
                            is NavigationEvent.PopBackStack -> navController.popBackStack()
                            // Subir a la pantalla "padre"
                            is NavigationEvent.NavigateUp -> navController.navigateUp()
                        }
                    }
                }

                // 3. El "mapa" de la app: qué pantalla corresponde a cada ruta
                NavHost(
                    navController = navController,
                    startDestination = Screen.Home.route,   // pantalla inicial
                    modifier = Modifier.fillMaxSize()
                ) {
                    composable(route = Screen.Home.route) {
                        PrincipalScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Profile.route) {
                        PerfilScreen(navController = navController, viewModel = viewModel)
                    }
                    composable(route = Screen.Settings.route) {
                        ConfiguracionScreen(navController = navController, viewModel = viewModel)
                    }
                }
            }
        }
    }
}