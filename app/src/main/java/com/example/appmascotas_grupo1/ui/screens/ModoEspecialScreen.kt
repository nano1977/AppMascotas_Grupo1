package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.viewmodel.EstadoViewModel

// Pantalla del "modo especial": interruptor con estado guardado y animaciones
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ModoEspecialScreen(
    navController: NavController,
    viewModel: EstadoViewModel = viewModel()
) {
    // Estados que vienen del ViewModel
    val estado by viewModel.activo.collectAsState()                 // null / true / false
    val mostrarMensaje by viewModel.mostrarMensaje.collectAsState() // mostrar "¡Guardado!"

    // EXTRA (rememberSaveable): cuenta los cambios en esta sesión.
    // A diferencia de "remember", NO se pierde al girar el celular.
    var cambios by rememberSaveable { mutableIntStateOf(0) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Modo Especial ✨") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Volver")
                    }
                }
            )
        }
    ) { innerPadding ->

        if (estado == null) {
            // ---------- PARTE 3: Simulación de carga (loader) ----------
            Box(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator()       // círculo de carga girando
                    Spacer(Modifier.height(16.dp))
                    Text("Cargando estado guardado...")
                }
            }
        } else {
            val estaActivo = estado == true

            // ---------- PARTE 1: Color animado según el estado ----------
            val colorAnimado by animateColorAsState(
                targetValue = if (estaActivo) Color(0xFF4CAF50) else Color(0xFFB0BEC5), // verde / gris
                animationSpec = tween(durationMillis = 500),  // la transición dura medio segundo
                label = "colorBoton"
            )

            // ---------- PARTE 1: Texto derivado del estado ----------
            val textoBoton by remember(estaActivo) {
                derivedStateOf { if (estaActivo) "Desactivar" else "Activar" }
            }

            // EXTRA (animateFloatAsState): el ícono "rebota" y crece al activar
            val escala by animateFloatAsState(
                targetValue = if (estaActivo) 1.3f else 1f,
                animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
                label = "escalaIcono"
            )

            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize()
                    .padding(32.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // EXTRA (AnimatedContent): el ícono cambia con una transición suave
                AnimatedContent(targetState = estaActivo, label = "icono") { activo ->
                    Text(
                        text = if (activo) "🐕" else "💤",
                        fontSize = 72.sp,
                        modifier = Modifier.scale(escala)
                    )
                }

                Spacer(Modifier.height(16.dp))

                Text(
                    text = if (estaActivo) "Modo especial ACTIVADO" else "Modo especial desactivado",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(Modifier.height(24.dp))

                // Botón con el color animado
                Button(
                    onClick = {
                        viewModel.alternarEstado()   // cambia y guarda en DataStore
                        cambios++                    // suma 1 al contador de la sesión
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = colorAnimado),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp)
                ) {
                    Text(text = textoBoton, style = MaterialTheme.typography.titleLarge)
                }

                Spacer(Modifier.height(24.dp))

                // ---------- PARTE 4: Mensaje animado (aparece y desaparece) ----------
                AnimatedVisibility(
                    visible = mostrarMensaje,
                    enter = fadeIn() + slideInVertically(),    // aparece deslizándose
                    exit = fadeOut() + slideOutVertically()    // desaparece deslizándose
                ) {
                    Text(
                        text = "¡Estado guardado exitosamente!",
                        color = Color(0xFF4CAF50),
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                Spacer(Modifier.height(16.dp))

                // Contador de la sesión (rememberSaveable)
                Text(
                    text = "Cambios en esta sesión: $cambios",
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
    }
}