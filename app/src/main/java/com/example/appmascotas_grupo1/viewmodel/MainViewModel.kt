package com.example.appmascotas_grupo1.viewmodel



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmascotas_grupo1.navigation.NavigationEvent
import com.example.appmascotas_grupo1.navigation.Screen
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

// ViewModel que centraliza la navegación de la app.
// Las pantallas le PIDEN moverse, y él EMITE el evento correspondiente.
class MainViewModel : ViewModel() {

    // Canal privado donde el ViewModel "transmite" los eventos (solo él puede emitir)
    private val _navigationEvents = MutableSharedFlow<NavigationEvent>()

    // Versión de solo lectura: la MainActivity la "escucha", pero no puede emitir
    val navigationEvents: SharedFlow<NavigationEvent> = _navigationEvents.asSharedFlow()

    // Pedir ir a una pantalla específica
    fun navigateTo(screen: Screen) {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateTo(route = screen))
        }
    }

    // Pedir volver a la pantalla anterior
    fun navigateBack() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.PopBackStack)
        }
    }

    // Pedir subir a la pantalla "padre"
    fun navigateUp() {
        viewModelScope.launch {
            _navigationEvents.emit(NavigationEvent.NavigateUp)
        }
    }
}