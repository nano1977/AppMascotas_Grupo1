package com.example.appmascotas_grupo1.viewmodel


import androidx.lifecycle.ViewModel
import com.example.appmascotas_grupo1.model.Usuario
import com.example.appmascotas_grupo1.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class InicioViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    private val _usuario = MutableStateFlow(repository.obtenerUsuario())
    val usuario: StateFlow<Usuario> = _usuario.asStateFlow()
}