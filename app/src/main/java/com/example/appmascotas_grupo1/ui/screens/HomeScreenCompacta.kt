package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appmascotas_grupo1.R

// Pantalla para celulares en vertical (pantalla COMPACTA)
// Diseño: todo en una sola columna, uno debajo del otro
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenCompacta() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("AppMascotas") })
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            // Etiqueta para saber qué versión se está mostrando
            Text(
                text = "📱 Vista compacta",
                style = MaterialTheme.typography.labelLarge
            )

            // Texto de bienvenida con colores del tema
            Text(
                text = "¡Bienvenido!",
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleLarge
            )

            // Botones uno al lado del otro
            Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                Button(onClick = { /* acción futura */ }) { Text("Perros 🐶") }
                OutlinedButton(onClick = { /* acción futura */ }) { Text("Gatos 🐱") }
            }

            // Imagen a todo el ancho
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo App",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                contentScale = ContentScale.Fit
            )
        }
    }
}

// Vista previa con el tamaño de un celular (360 x 800 dp)
@Preview(name = "Compact", widthDp = 360, heightDp = 800)
@Composable
fun PreviewCompact() {
    HomeScreenCompacta()
}