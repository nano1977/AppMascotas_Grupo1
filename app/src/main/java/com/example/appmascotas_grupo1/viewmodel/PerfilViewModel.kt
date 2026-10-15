package com.example.appmascotas_grupo1.viewmodel

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

// ViewModel del perfil: guarda la foto de la mascota tomada con la cámara (Guía 13)
class PerfilViewModel : ViewModel() {

    // La foto actual. null = todavía no se ha tomado ninguna foto
    private val _foto = MutableStateFlow<Bitmap?>(null)
    val foto: StateFlow<Bitmap?> = _foto.asStateFlow()

    // Guarda la foto que devolvió la cámara.
    // Si el usuario CANCELÓ la cámara, llega null y no hacemos nada
    // (así no se borra la foto anterior por accidente)
    fun guardarFoto(nuevaFoto: Bitmap?) {
        if (nuevaFoto != null) {
            _foto.value = nuevaFoto
        }
    }

    // Reto 2: borra la foto (vuelve a null)
    fun eliminarFoto() {
        _foto.value = null
    }
}