package com.example.appmascotas_grupo1.repository

import com.example.appmascotas_grupo1.model.Usuario

class UsuarioRepository {
    fun obtenerUsuario(): Usuario {
        return Usuario(nombre = "Estudiante Duoc")
    }
}