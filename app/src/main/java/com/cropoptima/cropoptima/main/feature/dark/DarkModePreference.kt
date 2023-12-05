package com.cropoptima.cropoptima.main.feature.dark

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DarkModePreference private constructor(private val dataStore: DataStore<Preferences>){
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    private val THEME_KEY = booleanPreferencesKey("theme_setting")

    fun getThemeSetting(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[THEME_KEY] ?: false
        }
    }

    suspend fun saveThemeSetting(isDarkModeActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[THEME_KEY] = isDarkModeActive
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: DarkModePreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): DarkModePreference {
            return INSTANCE ?: synchronized(this) {
                val instance = DarkModePreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }

}