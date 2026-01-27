package com.mrh.listarcontactos.ui.components

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kotlinx.serialization.Serializable


@Serializable
object PantallaInicio


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = PantallaInicio
    ) {
        composable<PantallaInicio>{
            AppNavigation()
        }

    }
}