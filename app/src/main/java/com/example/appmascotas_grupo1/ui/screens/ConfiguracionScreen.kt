package com.example.appmascotas_grupo1.ui.screens



import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.viewmodel.MainViewModel

// Pantalla de configuración: sin barras, solo botones de navegación dirigida
@Composable
fun ConfiguracionScreen(
    navController: NavController,               // controlador de navegación
    viewModel: MainViewModel = viewModel()      // ViewModel que centraliza la navegación
) {
    // Estructura visual centrada
    Column(
        modifier = Modifier
            .fillMaxSize()                       // ocupar toda la pantalla
            .padding(16.dp),                     // margen interno
        verticalArrangement = Arrangement.Center,            // centrar verticalmente
        horizontalAlignment = Alignment.CenterHorizontally   // centrar horizontalmente
    ) {
        // Título
        Text(
            text = "Pantalla de Configuración ⚙️",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botón para ir al Inicio
        Button(onClick = { viewModel.navigateTo(Screen.Home) }) {
            Text(text = "Volver al Inicio")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botón para ir al Perfil
        Button(onClick = { viewModel.navigateTo(Screen.Profile) }) {
            Text(text = "Ir a Perfil")
        }

        Spacer(modifier = Modifier.height(10.dp))

        // Botón extra: vuelve a la pantalla ANTERIOR (usa el evento PopBackStack)
        OutlinedButton(onClick = { viewModel.navigateBack() }) {
            Text(text = "← Atrás")
        }
    }
}