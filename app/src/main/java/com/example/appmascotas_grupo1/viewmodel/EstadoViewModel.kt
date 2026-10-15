package com.example.appmascotas_grupo1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.appmascotas_grupo1.data.EstadoDataStore
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

// ViewModel que extiende AndroidViewModel porque necesita el "contexto" de la app
// (DataStore necesita el contexto para saber dónde guardar en el celular)
class EstadoViewModel(application: Application) : AndroidViewModel(application) {

    // La "libreta" donde guardamos el estado
    private val estadoDataStore = EstadoDataStore(application)

    // ¿Está activado el modo especial?
    // null = todavía cargando | true = activado | false = desactivado
    private val _activo = MutableStateFlow<Boolean?>(null)
    val activo: StateFlow<Boolean?> = _activo.asStateFlow()

    // ¿Mostramos el mensaje "¡Estado guardado!"?
    private val _mostrarMensaje = MutableStateFlow(false)
    val mostrarMensaje: StateFlow<Boolean> = _mostrarMensaje.asStateFlow()

    // init: se ejecuta automáticamente cuando se crea el ViewModel
    init {
        cargarEstado()
    }

    // Lee el estado guardado en el celular
    fun cargarEstado() {
        viewModelScope.launch {
            delay(1500) // Simula una carga de 1,5 segundos (para ver el loader)
            // first() = toma el primer valor guardado. Si no hay nada (null), usamos false
            _activo.value = estadoDataStore.obtenerEstado().first() ?: false
        }
    }

    // Cambia el estado (activado <-> desactivado), lo guarda y muestra el mensaje
    fun alternarEstado() {
        viewModelScope.launch {
            // 1. Invertimos el valor actual (true -> false, false -> true)
            val nuevoValor = !(_activo.value ?: false)

            // 2. Lo guardamos en el celular
            estadoDataStore.guardarEstado(nuevoValor)

            // 3. Actualizamos el estado para que la pantalla cambie
            _activo.value = nuevoValor

            // 4. Mostramos el mensaje de éxito...
            _mostrarMensaje.value = true

            // 5. ...y lo ocultamos después de 2 segundos
            delay(2000)
            _mostrarMensaje.value = false
        }
    }
}