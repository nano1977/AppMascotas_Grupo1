package com.example.appmascotas_grupo1.ui.screens


import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import com.example.appmascotas_grupo1.ui.utils.obtenerWindowSizeClass

// Pantalla principal "inteligente": mide el ancho de la pantalla
// y decide cuál de las 3 versiones mostrar
@Composable
fun HomeScreenAdaptable() {

    // 1. Medimos la pantalla usando nuestra función reutilizable
    val windowSizeClass = obtenerWindowSizeClass()

    // 2. Según el ANCHO, elegimos qué diseño mostrar
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> HomeScreenCompacta()    // celular vertical
        WindowWidthSizeClass.Medium -> HomeScreenMediana()      // tablet chica / celular horizontal
        WindowWidthSizeClass.Expanded -> HomeScreenExpandida()  // tablet grande / computador
        else -> HomeScreenCompacta()                            // por seguridad: la compacta
    }
}