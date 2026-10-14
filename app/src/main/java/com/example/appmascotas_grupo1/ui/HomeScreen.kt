package com.example.appmascotas_grupo1.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmascotas_grupo1.R

// TopAppBar todavía es "experimental" en Material 3,
// por eso debemos aceptar su uso con @OptIn
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {

    // Scaffold: estructura base de la pantalla (barra superior + contenido)
    Scaffold(
        // Barra superior con el título de la app
        topBar = {
            TopAppBar(title = { Text("Mi App Kotlin") })
        }
    ) { innerPadding ->

        // Column: ordena los elementos uno debajo del otro
        Column(
            modifier = Modifier
                .padding(innerPadding)   // evita que el contenido quede debajo de la barra
                .fillMaxSize()           // ocupa toda la pantalla
                .padding(16.dp),         // margen interno en los bordes
            verticalArrangement = Arrangement.spacedBy(20.dp) // espacio uniforme entre elementos
        ) {

            // Texto de bienvenida
            // Texto de bienvenida con color y estilo del tema de la app
            Text(
                text = "¡Bienvenido!",
                color = MaterialTheme.colorScheme.primary,   // color principal del tema
                style = MaterialTheme.typography.titleLarge  // tamaño de título grande
            )

            // Botón (por ahora no hace nada)
            Button(onClick = { /* acción futura */ }) {
                Text("Presióname")
            }
            // Row: coloca los elementos uno AL LADO del otro (horizontal)
            Row(
                horizontalArrangement = Arrangement.spacedBy(12.dp) // espacio entre los botones
            ) {
                Button(onClick = { /* acción futura */ }) {
                    Text("Perros 🐶")
                }
                OutlinedButton(onClick = { /* acción futura */ }) {
                    Text("Gatos 🐱")
                }
            }

            // Imagen del logo desde res/drawable
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",   // descripción para accesibilidad
                modifier = Modifier
                    .fillMaxWidth()                // ancho completo
                    .height(150.dp),               // alto fijo
                contentScale = ContentScale.Fit    // ajusta la imagen sin deformarla
            )
        }
    }
}

// Vista previa: permite ver la pantalla en Android Studio sin ejecutar la app
@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}