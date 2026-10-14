package com.example.appmascotas_grupo1.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.viewmodel.MainViewModel
import kotlinx.coroutines.launch

// Pantalla principal con TopAppBar y menú lateral (Navigation Drawer)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PrincipalScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel()
) {
    // Estado del menú lateral: empieza cerrado
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

    // Scope para abrir/cerrar el menú con animación (necesita una corrutina)
    val scope = rememberCoroutineScope()

    // ModalNavigationDrawer: envuelve la pantalla y agrega el menú lateral
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            // Contenido del menú lateral
            ModalDrawerSheet {
                Text(
                    text = "Menú Principal 🐾",
                    modifier = Modifier.padding(16.dp),
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider() // línea separadora

                // Opción 1: ir a Perfil
                NavigationDrawerItem(
                    label = { Text(text = "Ir a Perfil") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() } // cerrar el menú
                        viewModel.navigateTo(Screen.Profile)  // pedir ir a Perfil
                    }
                )

                // Opción 2: ir a Configuración
                NavigationDrawerItem(
                    label = { Text(text = "Ir a Configuración") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Settings)
                    }
                )
                // Opción 3: ir al formulario de Registro (Guía 11)
                NavigationDrawerItem(
                    label = { Text(text = "Registro 📝") },
                    selected = false,
                    onClick = {
                        scope.launch { drawerState.close() }
                        viewModel.navigateTo(Screen.Registro)
                    }
                )

            }
        }
    ) {
        // Contenido normal de la pantalla
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "AppMascotas") },
                    // Ícono ☰ a la izquierda que abre el menú
                    navigationIcon = {
                        IconButton(onClick = {
                            scope.launch { drawerState.open() }
                        }) {
                            Icon(
                                imageVector = Icons.Default.Menu,
                                contentDescription = "Menú"
                            )
                        }
                    }
                )
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
                    text = "Bienvenido a la Página de Inicio (MVVM)",
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = { viewModel.navigateTo(Screen.Settings) }) {
                    Text(text = "Ir a Configuración")
                }
            }
        }
    }
}