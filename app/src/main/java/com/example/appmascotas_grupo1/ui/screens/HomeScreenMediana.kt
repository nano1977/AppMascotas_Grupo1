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

// Pantalla para tablets pequeñas o celular en horizontal (pantalla MEDIANA)
// Diseño: dos columnas -> imagen a la izquierda, contenido a la derecha
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenMediana() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AppMascotas") })
        }
    ) { innerPadding ->

        // Row: divide la pantalla en izquierda y derecha
        Row(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(24.dp),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalAlignment = Alignment.CenterVertically // centra verticalmente
        ) {

            // Lado izquierdo: la imagen (ocupa la mitad del ancho)
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",
                modifier = Modifier
                    .weight(1f)
                    .height(220.dp),
                contentScale = ContentScale.Crop // rellena el espacio recortando bordes
            )

            // Lado derecho: textos y botones (ocupa la otra mitad)
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "📲 Vista mediana",
                    style = MaterialTheme.typography.labelLarge
                )
                Text(
                    text = "¡Bienvenido!",
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.headlineMedium // título más grande
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
        }
    }
}

// Vista previa con tamaño de tablet pequeña (700 x 500 dp)
@Preview(name = "Medium", widthDp = 700, heightDp = 500)
@Composable
fun PreviewMedium() {
    HomeScreenMediana()
}