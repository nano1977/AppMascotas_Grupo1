package com.example.appmascotas_grupo1.data


import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// Creamos la "libreta" DataStore con el nombre 'preferencias_usuario'.
// Se declara UNA sola vez en toda la app, fuera de cualquier clase.
val Context.dataStore by preferencesDataStore(name = "preferencias_usuario")

// Clase que sabe guardar y leer el estado del "modo especial"
class EstadoDataStore(private val context: Context) {

    // La "etiqueta" con la que guardamos el dato (como el nombre de una casilla)
    private val ESTADO_ACTIVADO = booleanPreferencesKey("modo_activado")

    // Guardar el estado (true = activado, false = desactivado)
    // "suspend" = es una tarea que tarda un poco, se ejecuta en segundo plano
    suspend fun guardarEstado(valor: Boolean) {
        context.dataStore.edit { preferencias ->
            preferencias[ESTADO_ACTIVADO] = valor
        }
    }

    // Leer el estado. Devuelve un Flow: un "flujo" que avisa cada vez que cambia.
    // Boolean? porque la primera vez que se abre la app todavía no hay nada guardado (null)
    fun obtenerEstado(): Flow<Boolean?> {
        return context.dataStore.data.map { preferencias ->
            preferencias[ESTADO_ACTIVADO]
        }
    }
}