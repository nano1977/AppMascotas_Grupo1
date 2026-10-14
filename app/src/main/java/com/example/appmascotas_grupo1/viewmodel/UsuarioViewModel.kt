package com.example.appmascotas_grupo1.viewmodel


import androidx.lifecycle.ViewModel
import com.example.appmascotas_grupo1.model.UsuarioErrores
import com.example.appmascotas_grupo1.model.UsuarioUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

// ViewModel del formulario: guarda lo que se escribe y valida los datos
class UsuarioViewModel : ViewModel() {

    // Estado interno modificable (solo el ViewModel puede cambiarlo)
    private val _estado = MutableStateFlow(UsuarioUiState())

    // Estado de solo lectura para la pantalla
    val estado: StateFlow<UsuarioUiState> = _estado.asStateFlow()

    // ---------- Funciones que se llaman cada vez que el usuario escribe ----------

    // Actualiza el nombre y borra su error (porque el usuario lo está corrigiendo)
    fun onNombreChange(valor: String) {
        _estado.update { it.copy(nombre = valor, errores = it.errores.copy(nombre = null)) }
    }

    // Actualiza el correo y borra su error
    fun onCorreoChange(valor: String) {
        _estado.update { it.copy(correo = valor, errores = it.errores.copy(correo = null)) }
    }

    // Actualiza la contraseña y borra su error
    fun onClaveChange(valor: String) {
        _estado.update { it.copy(clave = valor, errores = it.errores.copy(clave = null)) }
    }

    // Actualiza la dirección y borra su error
    fun onDireccionChange(valor: String) {
        _estado.update { it.copy(direccion = valor, errores = it.errores.copy(direccion = null)) }
    }

    // Actualiza la casilla de términos y borra su error
    fun onAceptarTerminosChange(valor: Boolean) {
        _estado.update { it.copy(aceptaTerminos = valor, errores = it.errores.copy(terminos = null)) }
    }

    // ---------- Validación: se llama al presionar "Registrar" ----------

    // Revisa todos los campos. Devuelve true si TODO está correcto.
    fun validarFormulario(): Boolean {
        val estadoActual = _estado.value

        // Revisamos cada campo: si está mal, guardamos el mensaje; si está bien, null
        val errores = UsuarioErrores(
            nombre = if (estadoActual.nombre.isBlank()) "Campo obligatorio" else null,
            correo = if (!estadoActual.correo.contains("@")) "Correo inválido" else null,
            clave = if (estadoActual.clave.length < 6) "Debe tener al menos 6 caracteres" else null,
            direccion = if (estadoActual.direccion.isBlank()) "Campo obligatorio" else null,
            terminos = if (!estadoActual.aceptaTerminos) "Debes aceptar los términos" else null
        )

        // ¿Hay al menos un error? (listOfNotNull descarta los null y deja solo los mensajes)
        val hayErrores = listOfNotNull(
            errores.nombre,
            errores.correo,
            errores.clave,
            errores.direccion,
            errores.terminos
        ).isNotEmpty()

        // Guardamos los errores en el estado para que la pantalla los muestre
        _estado.update { it.copy(errores = errores) }

        // Es válido solo si NO hay errores
        return !hayErrores
    }
}