package com.mrh.listarcontactos.ui.components

import androidx.compose.ui.graphics.vector.ImageVector

data class NavRoute(
    // Texto barra de navegación
    val label: String,
    // Icono de la barra de navegación
    val icon: ImageVector,
    // Objeto de la ruta a la que navegaremos
    val routeObject: Any
)
