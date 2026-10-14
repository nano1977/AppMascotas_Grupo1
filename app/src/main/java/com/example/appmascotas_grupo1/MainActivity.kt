package com.example.appmascotas_grupo1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appmascotas_grupo1.ui.HomeScreen
import com.example.appmascotas_grupo1.ui.theme.AppMascotas_Grupo1Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppMascotas_Grupo1Theme {
                HomeScreen()   // Pantalla principal que se muestra al abrir la app
            }
        }
    }
}