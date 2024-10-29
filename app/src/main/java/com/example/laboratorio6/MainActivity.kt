package com.example.laboratorio6

import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector // Importación de ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.laboratorio6.ui.theme.Laboratorio6Theme
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.PaddingValues

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio6Theme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) },
        content = { paddingValues ->
            NavigationGraph(navController = navController, paddingValues = paddingValues)
        }
    )
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(
        Screen.Concerts,
        Screen.Places,
        Screen.Profile
    )
    NavigationBar(
        containerColor = Color(0xFF4CAF50) // Fondo verde para la barra de navegación
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(imageVector = screen.icon, contentDescription = null) },
                label = { Text(screen.label) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, paddingValues: PaddingValues) {
    NavHost(navController, startDestination = Screen.Concerts.route, Modifier.padding(paddingValues)) {
        composable(Screen.Concerts.route) { ConcertScreen() }
        composable(Screen.Places.route) { PlaceScreen() }
        composable(Screen.Profile.route) { ProfileScreen() }
    }
}

// Definición de pantallas con sus rutas e íconos
sealed class Screen(val route: String, val icon: ImageVector, val label: String) {
    object Concerts : Screen("concerts", Icons.Filled.MusicNote, "Eventos")
    object Places : Screen("places", Icons.Filled.LocationOn, "Lugares")
    object Profile : Screen("profile", Icons.Filled.Person, "Perfil")
}

@Composable
fun ConcertScreen() {
    Text("Lista de Eventos")
}

@Composable
fun PlaceScreen() {
    Text("Lista de Lugares")
}

@Composable
fun ProfileScreen() {
    Text("Perfil del Usuario")
}
