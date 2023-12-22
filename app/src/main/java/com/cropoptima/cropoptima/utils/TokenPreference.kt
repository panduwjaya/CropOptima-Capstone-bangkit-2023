package com.cropoptima.cropoptima.utils

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenPreference private constructor(private val dataStore: DataStore<Preferences>) {

    private val TOKEN_ID = stringPreferencesKey("token_settings")

    suspend fun saveToken(token: String) {
        dataStore.edit { preferences ->
            preferences[TOKEN_ID] = token
        }
    }

    fun readToken(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[TOKEN_ID] ?: ""
        }
    }

    suspend fun removeToken(){
        dataStore.edit { removeKey->
            removeKey.remove(TOKEN_ID)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: TokenPreference? = null

        fun getInstance(dataStore: DataStore<Preferences>): TokenPreference {
            return INSTANCE ?: synchronized(this) {
                val instance = TokenPreference(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}