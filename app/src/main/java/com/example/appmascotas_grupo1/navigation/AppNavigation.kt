package com.example.appmascotas_grupo1.navigation

import com.example.appmascotas_grupo1.viewmodel.PerfilViewModel
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appmascotas_grupo1.ui.screens.ConfiguracionScreen
import com.example.appmascotas_grupo1.ui.screens.HomeScreenAdaptable
import com.example.appmascotas_grupo1.ui.screens.PerfilScreen
import com.example.appmascotas_grupo1.ui.screens.PrincipalScreen
import com.example.appmascotas_grupo1.ui.screens.RegistroScreen
import com.example.appmascotas_grupo1.ui.screens.ResumenScreen
import com.example.appmascotas_grupo1.viewmodel.MainViewModel
import com.example.appmascotas_grupo1.viewmodel.UsuarioViewModel
import kotlinx.coroutines.flow.collectLatest
import com.example.appmascotas_grupo1.ui.screens.ModoEspecialScreen

// Grafo de navegación de TODA la app: aquí se definen todas las pantallas
@Composable
fun AppNavigation() {

    // Controlador de navegación (el "conductor")
    val navController = rememberNavController()

    // ViewModel de navegación (Guía 10)
    val mainViewModel: MainViewModel = viewModel()

    // ViewModel del formulario (Guía 11).
    // Se crea UNA sola vez aquí, y se COMPARTE entre Registro y Resumen
    val usuarioViewModel: UsuarioViewModel = viewModel()


    // ViewModel de la foto de perfil (Guía 13).
    // Se crea aquí para que la foto NO se pierda al cambiar de pantalla
    val perfilViewModel: PerfilViewModel = viewModel()

    // Escuchar los eventos de navegación del MainViewModel (Guía 10)
    LaunchedEffect(Unit) {
        mainViewModel.navigationEvents.collectLatest { event ->
            when (event) {
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
                is NavigationEvent.PopBackStack -> navController.popBackStack()
                is NavigationEvent.NavigateUp -> navController.navigateUp()
            }
        }
    }

    // El "mapa" de la app: cada ruta con su pantalla
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = Modifier.fillMaxSize()
    ) {
        // ----- Pantallas de la Guía 10 -----
        composable(route = Screen.Home.route) {
            PrincipalScreen(navController = navController, viewModel = mainViewModel)
        }
        composable(route = Screen.Profile.route) {
            PerfilScreen(
                navController = navController,
                viewModel = mainViewModel,
                perfilViewModel = perfilViewModel   // foto compartida (Guía 13)
            )
        }
        composable(route = Screen.Settings.route) {
            ConfiguracionScreen(navController = navController, viewModel = mainViewModel)
        }

        // ----- Pantallas de la Guía 11 (comparten usuarioViewModel) -----
        composable(route = Screen.Registro.route) {
            RegistroScreen(navController = navController, viewModel = usuarioViewModel)
        }
        composable(route = Screen.Resumen.route) {
            ResumenScreen(navController = navController, viewModel = usuarioViewModel)
        }
        // ----- Pantalla de la Guía 12 -----
        composable(route = Screen.ModoEspecial.route) {
            ModoEspecialScreen(navController = navController)
        }
        // ----- Pantalla de la Guía 9 -----
        composable(route = Screen.Adaptable.route) {
            HomeScreenAdaptable()
        }
    }
}