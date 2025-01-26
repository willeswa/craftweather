package com.wanjala.weathercraft.data.sources.local.session

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.wanjala.weathercraft.data.models.City
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject

private const val PREFERENCE_NAME = "user_session"

// Define the DataStore instance
val Context.dataStore by preferencesDataStore(name = PREFERENCE_NAME)


class SessionManagerImpl @Inject constructor(@ApplicationContext private val context: Context)  {

    companion object {
        private val SELECTED_CITY_KEY = stringPreferencesKey("selected_city")
    }

    suspend fun saveSelectedCity(city: City) {
        context.dataStore.edit { preferences ->
            preferences[SELECTED_CITY_KEY] = Json.encodeToString(city)
        }
    }

    fun getSelectedCity(): Flow<City?> {
        return context.dataStore.data.map { preferences ->
            preferences[SELECTED_CITY_KEY]?.let { json ->
                Json.decodeFromString(json)
            }
        }
    }
}
