package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.viewmodel.UsuarioViewModel

// Pantalla que muestra los datos del registro.
// Lee el MISMO ViewModel que RegistroScreen, por eso ya tiene los datos.
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResumenScreen(
    navController: NavController,
    viewModel: UsuarioViewModel     // ViewModel COMPARTIDO con RegistroScreen
) {
    // Leemos el estado: son los mismos datos que se escribieron en el formulario
    val estado by viewModel.estado.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Resumen ✅") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Resumen del Registro",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary
            )

            // Tarjeta con los datos ingresados
            Card(modifier = Modifier.fillMaxWidth()) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(text = "Nombre: ${estado.nombre}")
                    Text(text = "Correo: ${estado.correo}")
                    Text(text = "Dirección: ${estado.direccion}")
                    // La contraseña NUNCA se muestra: se reemplaza por asteriscos
                    Text(text = "Contraseña: ${"*".repeat(estado.clave.length)}")
                    Text(text = "Términos: ${if (estado.aceptaTerminos) "Aceptados" else "No aceptados"}")
                }
            }

            // Botón para volver al inicio
            Button(
                onClick = { navController.navigate(Screen.Home.route) },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Volver al Inicio")
            }
        }
    }
}