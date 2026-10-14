package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.viewmodel.MainViewModel

// Pantalla de perfil con barra de navegación inferior (BottomBar)
@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    // Las pantallas que aparecen en la barra inferior
    val items = listOf(Screen.Home, Screen.Profile)

    // Cuál está seleccionada: 0 = Home, 1 = Perfil (empezamos en Perfil)
    var selectedItem by remember { mutableIntStateOf(1) }

    Scaffold(
        // Barra de navegación inferior
        bottomBar = {
            NavigationBar {
                // Crear un botón por cada pantalla de la lista
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            viewModel.navigateTo(screen)
                        },
                        // Texto amigable en vez de la ruta técnica ("home_page")
                        label = { Text(text = if (screen is Screen.Home) "Inicio" else "Perfil") },
                        icon = {
                            Icon(
                                imageVector = if (screen is Screen.Home) Icons.Default.Home else Icons.Default.Person,
                                contentDescription = screen.route
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Bienvenido al Perfil de Usuario 👤",
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(onClick = { viewModel.navigateTo(Screen.Home) }) {
                Text(text = "Volver al Inicio")
            }
        }
    }
}