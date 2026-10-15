package com.example.appmascotas_grupo1.ui.screens

import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.appmascotas_grupo1.navigation.Screen
import com.example.appmascotas_grupo1.viewmodel.MainViewModel
import com.example.appmascotas_grupo1.viewmodel.PerfilViewModel

// Pantalla de perfil (Guía 10) + foto de la mascota con cámara y galería (Guía 13)
@Composable
fun PerfilScreen(
    navController: NavController,
    viewModel: MainViewModel = viewModel(),             // navegación (Guía 10)
    perfilViewModel: PerfilViewModel = viewModel()      // foto de la mascota (Guía 13)
) {
    // ---------- Estado ----------
    val foto by perfilViewModel.foto.collectAsState()   // la foto actual (o null)
    val context = LocalContext.current                  // para leer la galería y mostrar avisos

    // ---------- Recurso nativo 1: CÁMARA ----------
    // Abre la app de cámara del sistema y devuelve la foto como Bitmap (miniatura).
    // Si el usuario cancela, devuelve null.
    val lanzarCamara = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.TakePicturePreview()
    ) { imagen ->
        perfilViewModel.guardarFoto(imagen)
    }

    // ---------- Recurso nativo 2: GALERÍA ----------
    // Abre el selector de fotos del sistema y devuelve el Uri (la "dirección") de la foto elegida.
    // Si el usuario cancela, devuelve null.
    val lanzarGaleria = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            // Leemos la foto desde su dirección y la convertimos en Bitmap
            val imagen = cargarBitmapDesdeUri(context, uri)
            if (imagen != null) {
                perfilViewModel.guardarFoto(imagen)
            } else {
                Toast.makeText(context, "No se pudo leer la imagen", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // ---------- Barra inferior (Guía 10) ----------
    val items = listOf(Screen.Home, Screen.Profile)
    var selectedItem by remember { mutableIntStateOf(1) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, screen ->
                    NavigationBarItem(
                        selected = selectedItem == index,
                        onClick = {
                            selectedItem = index
                            viewModel.navigateTo(screen)
                        },
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
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Perfil de mi mascota 🐾",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(Modifier.height(24.dp))

            // ---------- Foto o espacio vacío ----------
            if (foto == null) {
                // Sin foto: círculo gris con un perrito
                Box(
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                        .background(MaterialTheme.colorScheme.surfaceVariant),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = "🐶", fontSize = 80.sp)
                }
                Spacer(Modifier.height(12.dp))
                Text(
                    text = "Aún no hay foto de tu mascota",
                    style = MaterialTheme.typography.bodyLarge
                )
            } else {
                // Con foto: la mostramos recortada en círculo (estilo foto de perfil)
                Image(
                    bitmap = foto!!.asImageBitmap(),
                    contentDescription = "Foto de mi mascota",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(200.dp)
                        .clip(CircleShape)
                )
                Spacer(Modifier.height(12.dp))
                // Reto 1: tamaño de la foto
                Text(
                    text = "Tamaño: ${foto!!.width} × ${foto!!.height} px",
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            Spacer(Modifier.height(24.dp))

            // ---------- Botón: Tomar foto (cámara) ----------
            Button(
                onClick = {
                    try {
                        lanzarCamara.launch(null)
                    } catch (e: ActivityNotFoundException) {
                        Toast.makeText(context, "No se encontró una app de cámara", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = foto == null,   // Reto 3: deshabilitado si ya hay una foto
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Tomar foto 📷")
            }

            Spacer(Modifier.height(8.dp))

            // ---------- Botón: Elegir de la galería ----------
            FilledTonalButton(
                onClick = {
                    // Pedimos SOLO imágenes (no videos)
                    lanzarGaleria.launch(
                        PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                    )
                },
                enabled = foto == null,   // misma regla que la cámara
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Elegir de la galería 🖼️")
            }

            Spacer(Modifier.height(8.dp))

            // ---------- Botón: Eliminar foto (Reto 2) ----------
            OutlinedButton(
                onClick = { perfilViewModel.eliminarFoto() },
                enabled = foto != null,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Eliminar foto 🗑️")
            }
        }
    }
}

// Lee una imagen desde su Uri (dirección en el celular) y la convierte en Bitmap.
// Las fotos de galería pueden ser MUY grandes (más de 4000 px), así que la reducimos
// a un máximo de ~1024 px para no llenar la memoria del celular.
private fun cargarBitmapDesdeUri(context: Context, uri: Uri): Bitmap? {
    return try {
        // 1. Leemos SOLO las medidas de la foto (sin cargarla completa)
        val medidas = BitmapFactory.Options().apply { inJustDecodeBounds = true }
        context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, medidas)
        }

        // 2. Calculamos cuánto achicarla: 1 = igual, 2 = la mitad, 4 = un cuarto...
        var escala = 1
        while (medidas.outWidth / escala > 1024 || medidas.outHeight / escala > 1024) {
            escala *= 2
        }

        // 3. Ahora sí leemos la foto, ya reducida
        val opciones = BitmapFactory.Options().apply { inSampleSize = escala }
        context.contentResolver.openInputStream(uri)?.use {
            BitmapFactory.decodeStream(it, null, opciones)
        }
    } catch (e: Exception) {
        null   // si algo falla, devolvemos null y mostramos un aviso
    }
}