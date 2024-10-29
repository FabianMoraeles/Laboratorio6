package com.example.laboratorio6

import android.app.Application
import android.content.SharedPreferences
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences =
        application.getSharedPreferences("favorites", Application.MODE_PRIVATE)

    // Sample data for places
    val places = listOf(
        Place("1", "Los Tigres del Norte 2023", "Explanada Cayala"),
        Place("2", "Myke Towers 2023", "Explanada Cayala"),
        Place("3", "Daddy Yankee 2022", "Explanada Cayala")
    )

    // Check if event is marked as favorite
    fun isFavorite(id: String): Boolean {
        return sharedPreferences.getBoolean(id, false)
    }

    // Toggle favorite status
    fun toggleFavorite(id: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val isFavorite = isFavorite(id)
            sharedPreferences.edit().putBoolean(id, !isFavorite).apply()
        }
    }
}
