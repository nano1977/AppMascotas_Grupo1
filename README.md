# 🐾 AppMascotas

Aplicación móvil Android desarrollada con **Kotlin** y **Jetpack Compose** para la asignatura **DSY1105 – Desarrollo de Aplicaciones Móviles** (Duoc UC).

## 👥 Integrantes – Grupo 1

| Nombre | Rol |
|---|---|
| Manuel Alvarez | Desarrollador |
| Horacio Hidalgo | Desarrollador |

## 📖 Descripción del proyecto

**AppMascotas** es una aplicación pensada para dueños de mascotas. Permite que el usuario se registre, cree el perfil de su mascota con una fotografía tomada con la cámara o elegida desde la galería, y configure preferencias que quedan guardadas en el dispositivo.

La foto de la mascota funciona como un **registro visual** dentro de su perfil, de la misma forma que en otros sistemas se fotografía un objeto para dejar constancia de él.

### Reglas de negocio

- El **nombre** y la **dirección** del usuario son obligatorios.
- El **correo** debe tener un formato válido (contener `@`).
- La **contraseña** debe tener al menos **6 caracteres** y nunca se muestra en pantalla (se reemplaza por asteriscos).
- El usuario debe **aceptar los términos y condiciones** para completar el registro.
- El perfil de la mascota admite **una sola foto** a la vez: para cambiarla, primero se debe eliminar la actual.
- La preferencia del **Modo Especial** se conserva aunque la aplicación se cierre.

## ✨ Funcionalidades implementadas

### Interfaz y navegación
- Pantalla principal con **TopAppBar** y **menú lateral** (`ModalNavigationDrawer`).
- Pantalla de perfil con **barra de navegación inferior** (`NavigationBar`).
- Navegación entre pantallas con **Navigation Compose** (`NavHost`), rutas tipadas con `sealed class` y eventos de navegación emitidos desde un ViewModel.
- **Diseño adaptable** con *Window Size Classes*: versiones compacta, mediana y expandida de la pantalla de inicio.

### Formularios y validaciones
- Formulario de **registro de usuario** con `OutlinedTextField` y `Checkbox`.
- Validación de cada campo con mensaje de error visual y borde rojo.
- Toda la lógica de validación está en el **ViewModel** (`UsuarioViewModel`), desacoplada de la interfaz.
- Pantalla de **resumen** que lee los datos desde un **ViewModel compartido**.

### Gestión de estado y animaciones
- Estado reactivo con `StateFlow` y `collectAsState`.
- Color animado (`animateColorAsState`), texto derivado (`derivedStateOf`), mensaje de confirmación animado (`AnimatedVisibility`), cambio de ícono (`AnimatedContent`) y efecto de rebote (`animateFloatAsState`).
- Indicador de carga (`CircularProgressIndicator`) mientras se leen los datos guardados.
- Contador que sobrevive a la rotación de pantalla (`rememberSaveable`).

### Persistencia local
- Almacenamiento con **DataStore Preferences**: el estado del Modo Especial se mantiene al cerrar y volver a abrir la aplicación.

### Recursos nativos del dispositivo
- 📷 **Cámara:** abre la cámara del sistema mediante Intent (`TakePicturePreview`) para fotografiar a la mascota.
- 🖼️ **Galería:** selector de fotos del sistema (`PickVisualMedia`) para elegir una imagen guardada.
- Ninguno de los dos requiere permisos peligrosos. Si el usuario cancela, la aplicación no se cierra.

## 🏗️ Arquitectura y estructura

El proyecto sigue el patrón **MVVM** (Model – View – ViewModel):

```
app/src/main/java/com/example/appmascotas_grupo1/
├── data/           → Persistencia local (EstadoDataStore)
├── model/          → Clases de datos (Usuario, UsuarioUiState, UsuarioErrores)
├── navigation/     → Rutas, eventos y grafo de navegación (Screen, NavigationEvent, AppNavigation)
├── repository/     → Origen de datos simulado (UsuarioRepository)
├── ui/
│   ├── screens/    → Pantallas Composable
│   ├── theme/      → Colores, tipografía y tema de la aplicación
│   └── utils/      → Utilidades (WindowSizeUtils)
├── viewmodel/      → Lógica de presentación y estado (MainViewModel, UsuarioViewModel, EstadoViewModel, PerfilViewModel)
└── MainActivity.kt → Punto de entrada de la aplicación
```

## 🛠️ Tecnologías

- Kotlin 2.2
- Jetpack Compose (BOM 2026.02.01) y Material 3
- Navigation Compose 2.9.0
- Lifecycle ViewModel Compose
- DataStore Preferences 1.1.1
- Android Gradle Plugin 9.3
- SDK mínimo: API 24 (Android 7.0) · SDK objetivo: API 37

## ▶️ Pasos para ejecutar

### Requisitos
- **Android Studio** actualizado (compatible con Android Gradle Plugin 9.3).
- Un **emulador** o un **celular Android** con Android 7.0 o superior.

### Instalación
1. Clonar el repositorio:
   ```bash
   git clone https://github.com/nano1977/AppMascotas_Grupo1.git
   ```
2. Abrir Android Studio y elegir **File > Open**, seleccionando la carpeta `AppMascotas_Grupo1`.
3. Esperar a que termine la **sincronización de Gradle** (la primera vez puede tardar varios minutos).
4. Seleccionar un emulador o conectar un celular con la **depuración USB** activada.
5. Presionar **Run ▶**.

### Cómo probar las funcionalidades
- **Menú lateral:** tocar el ícono ☰ en la pantalla principal.
- **Registro:** ☰ → *Registro*. Presionar *Registrar* con campos vacíos para ver las validaciones; completar los datos correctamente para ver el resumen.
- **Cámara y galería:** ☰ → *Ir a Perfil* → *Tomar foto* o *Elegir de la galería*.
- **Persistencia y animaciones:** ☰ → *Modo Especial*. Activarlo, cerrar la aplicación y volver a abrirla: el estado se conserva.

> **Nota para el emulador:** la galería de un emulador nuevo está vacía. Para agregar fotos se puede abrir la app *Cámara* del emulador y tomar algunas fotos, o arrastrar una imagen desde el computador hasta la pantalla del emulador.

## 📌 Historial de desarrollo

Cada funcionalidad se desarrolló en su propia rama y se integró a `main` mediante Pull Request:

| Rama | Funcionalidad |
|---|---|
| `main` | Estructura base del proyecto con MVVM |
| `feature/home-screen` | Pantalla base con Jetpack Compose |
| `feature/adaptive-screen` | Diseño adaptable con Window Size Classes |
| `feature/navigation` | Navegación, menú lateral y barra inferior |
| `feature/registro-form` | Formulario de registro con validaciones |
| `feature/estado-animaciones` | Persistencia con DataStore y animaciones |
| `feature/imagen-inteligente` | Recursos nativos: cámara y galería |
