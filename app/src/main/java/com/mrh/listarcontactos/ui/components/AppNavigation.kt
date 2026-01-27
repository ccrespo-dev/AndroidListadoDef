package com.mrh.listarcontactos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable


@Serializable
object PantallaInicio

@Serializable
data class DetalleContactoDestination(
    val nombre :String,
    val apellido :String,
    val mail :String,
    val telefono :String,
)

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio
    ) {
        composable<PantallaInicio>{
            MainView(navController)
        };
        composable<DetalleContactoDestination> {rutaNavegacion ->
            val contacto = rutaNavegacion.toRoute<DetalleContactoDestination>()
        }

    }
}