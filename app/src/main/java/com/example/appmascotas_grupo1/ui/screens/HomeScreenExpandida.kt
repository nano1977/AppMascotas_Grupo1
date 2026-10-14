package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmascotas_grupo1.R

// Pantalla para tablets grandes o computadores (pantalla EXPANDIDA)
// Diseño: tres columnas -> imagen | bienvenida | tarjetas de servicios
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenExpandida() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AppMascotas") })
        }
    ) { innerPadding ->

        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(32.dp),
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Columna 1: imagen grande
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",
                modifier = Modifier
                    .weight(1.2f)       // un poco más ancha que las otras columnas
                    .height(320.dp),
                contentScale = ContentScale.Crop
            )

            // Columna 2: bienvenida y botones
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "🖥️ Vista expandida",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "¡Bienvenido!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.displaySmall // el título más grande
                )
                Text(
                    text = "Encuentra todo para el cuidado de tu mascota.",
                    style = MaterialTheme.typography.bodyLarge
                )
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = { /* acción futura */ }) { Text("Perros 🐶") }
                    OutlinedButton(onClick = { /* acción futura */ }) { Text("Gatos 🐱") }
                }
            }

            // Columna 3: tarjetas de servicios (solo aparece en pantallas grandes)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Text(
                    text = "Nuestros servicios",
                    style = MaterialTheme.typography.titleMedium
                )
                ServicioCard("🛁 Baño y peluquería")
                ServicioCard("🩺 Veterinaria")
                ServicioCard("🦴 Alimentos y accesorios")
            }
        }
    }
}

// Componente reutilizable: una tarjeta con el nombre de un servicio
@Composable
private fun ServicioCard(texto: String) {
    Card(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = texto,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.bodyLarge
        )
    }
}

// Vista previa con tamaño de tablet grande (1000 x 600 dp)
@Preview(name = "Expanded", widthDp = 1000, heightDp = 600)
@Composable
fun PreviewExpanded() {
    HomeScreenExpandida()
}