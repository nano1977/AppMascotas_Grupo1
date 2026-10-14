package com.example.appmascotas_grupo1.navigation


// Representa los distintos tipos de movimiento que una pantalla puede pedir.
// Las pantallas NO navegan solas: piden un evento y otro se encarga de ejecutarlo.
sealed class NavigationEvent {

    // Evento 1: ir a una pantalla específica
    data class NavigateTo(
        val route: Screen,                   // a qué pantalla ir (ej: Screen.Profile)
        val popupToRoute: Screen? = null,    // opcional: hasta qué pantalla "limpiar" el historial
        val inclusive: Boolean = false,      // si también se borra esa pantalla del historial
        val singleTop: Boolean = false       // evita abrir la misma pantalla dos veces seguidas
    ) : NavigationEvent()

    // Evento 2: volver a la pantalla anterior (como el botón ◁ del celular)
    data object PopBackStack : NavigationEvent()

    // Evento 3: subir a la pantalla "padre" en la jerarquía de la app
    data object NavigateUp : NavigationEvent()
}