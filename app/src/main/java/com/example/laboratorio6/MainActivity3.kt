package com.example.laboratorio6

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.ArrowForward
import com.example.laboratorio6.ui.theme.Laboratorio6Theme

class MainActivity3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Laboratorio6Theme {
                UserProfileScreen() // Pantalla de perfil principal
            }
        }
    }
}

@Composable
fun UserProfileScreen() {  // Renombrado si es necesario
    val notificationEnabled = remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Círculo que representa la imagen de perfil
        Box(
            modifier = Modifier
                .size(100.dp)
                .background(Color.Gray, shape = CircleShape)
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Nombre del usuario
        Text(
            text = "Cecilia Castillo",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botones de opciones
        ProfileOption(icon = Icons.Default.Person, label = "Edit Profile")
        ProfileOption(icon = Icons.Default.Lock, label = "Reset Password")
        ProfileOptionToggle(
            icon = Icons.Default.Notifications,
            label = "Notifications",
            isChecked = notificationEnabled.value,
            onCheckedChange = { notificationEnabled.value = it }
        )
        ProfileOption(icon = Icons.Default.Favorite, label = "Favorites")
    }
}

@Composable
fun ProfileOption(icon: ImageVector, label: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Icon(
            imageVector = Icons.Default.ArrowForward, // Icono de flecha
            contentDescription = null,
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun ProfileOptionToggle(icon: ImageVector, label: String, isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = label,
            fontSize = 16.sp,
            modifier = Modifier.weight(1f)
        )
        Switch(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
    }
}
