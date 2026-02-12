package com.mrh.listarcontactos.ui.components

import PantallaDetalle
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import kotlinx.serialization.Serializable

@Serializable
object PantallaInicio

@Serializable
object PantallaAjustes

@Serializable
data class DetalleContactoDestination(
    val nombre :String,
    val apellido :String,
    val mail :String,
    val telefono :String,
)

@SuppressLint("RestrictedApi")
@Composable
fun AppNavigation() {

    val navBarRoutes = listOf(
        NavRoute(
            label = "Inicio",
            icon = Icons.Default.Home,
            routeObject = PantallaInicio
        ),
        NavRoute(
            label = "Ajustes",
            icon = Icons.Default.Settings,
            routeObject = PantallaAjustes
        ))

    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val showNavBar = navBarRoutes.any{ navRoute ->
        currentDestination?.hasRoute(navRoute.routeObject::class) == true
    }

    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = {
            AnimatedVisibility(
                visible = showNavBar,
                enter = slideInVertically { it },
                exit = slideOutVertically { it }
            ) {
                NavigationBar {
                    navBarRoutes.forEach { navRoute ->
                        NavigationBarItem(
                            selected = currentDestination?.hasRoute(navRoute.routeObject::class) == true,
                            onClick = { navController.navigate(navRoute.routeObject) },
                            label = {
                                Text(text = navRoute.label)
                            },
                            icon = {
                                Icon(imageVector = navRoute.icon, contentDescription = "Inicio")
                            }
                        )
                    }
                }
            }
        }
    ){ innerPadding ->
        NavHost(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            navController = navController,
            startDestination = PantallaInicio
        ) {
            composable<PantallaInicio>{
                MainView(navController)
            }
            composable<PantallaAjustes>{
                Text("hola")
            }
            composable<DetalleContactoDestination> {rutaNavegacion ->
                val contacto = rutaNavegacion.toRoute<DetalleContactoDestination>()
                PantallaDetalle(
                    datosContacto = contacto,
                    onBackClick = {
                        navController.popBackStack()
                    })

            }

        }
    }
}