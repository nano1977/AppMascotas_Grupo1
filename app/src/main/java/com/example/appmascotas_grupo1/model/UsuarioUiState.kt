package com.example.appmascotas_grupo1.model


// Modelo principal: representa tod lo que hay en el formulario en un momento dado
data class UsuarioUiState(
    val nombre: String = "",                         // lo que se escribió en "Nombre"
    val correo: String = "",                         // lo que se escribió en "Correo"
    val clave: String = "",                          // lo que se escribió en "Contraseña"
    val direccion: String = "",                      // lo que se escribió en "Dirección"
    val aceptaTerminos: Boolean = false,             // si marcó la casilla de términos
    val errores: UsuarioErrores = UsuarioErrores()   // los errores de cada campo
)

// Modelo de errores: guarda el mensaje de error de cada campo.
// Si un campo está bien, su error es null (vacío).
data class UsuarioErrores(
    val nombre: String? = null,
    val correo: String? = null,
    val clave: String? = null,
    val direccion: String? = null,
    val terminos: String? = null    // agregado: error si no acepta los términos
)