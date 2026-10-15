package com.example.appmascotas_grupo1.navigation



// Sealed class: define TODAS las rutas posibles de la app en un solo lugar.
// "sealed" significa que nadie puede crear rutas nuevas fuera de este archivo,
// así evitamos errores al escribir mal una ruta.
sealed class Screen(val route: String) {

    // Rutas simples (sin datos extra)
    // "data object" = existe una sola copia de cada ruta
    data object Home : Screen(route = "home_page")          // Pantalla de inicio
    data object Profile : Screen(route = "profile_page")    // Pantalla de perfil
    data object Settings : Screen(route = "settings_page")  // Pantalla de configuración
    data object Registro : Screen(route = "registro_page")  // Formulario de registro (Guía 11)
    data object Resumen : Screen(route = "resumen_page")    // Resumen del registro (Guía 11)

    data object ModoEspecial : Screen(route = "modo_especial_page")  // Estado y animaciones (Guía 12)

    // Ejemplo de ruta CON un dato extra (no se usa en esta guía, pero es importante)
    // Sirve, por ejemplo, para abrir el detalle de una mascota específica
    data class Detail(val itemId: String) : Screen(route = "detail_page/{itemId}") {
        // Construye la ruta final reemplazando {itemId} por el valor real
        // Ejemplo: Detail("5").buildRoute() -> "detail_page/5"
        fun buildRoute(): String = route.replace("{itemId}", itemId)
    }
}