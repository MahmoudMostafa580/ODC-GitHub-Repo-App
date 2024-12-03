package com.mahmoud.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_state")

class DataStorePreference(
    context: Context
) {
    companion object {
        private object PreferenceKeys {
            val isFirstTimeKey = booleanPreferencesKey("isFirstTime")
        }

        private object OnBoardingKey {
            val onBoardingKey = booleanPreferencesKey("on_boarding_completed")
        }
    }

    private val dataStore = context.dataStore

    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean = true) {
        dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.isFirstTimeKey] = isFirstTime == true
        }
    }

    suspend fun readIsFirstTimeEnterApp(): Boolean? {
        return dataStore.data.first()[PreferenceKeys.isFirstTimeKey]

    }

    suspend fun savaOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[OnBoardingKey.onBoardingKey] = completed
        }
    }

    suspend fun readOnBoardingState(): Boolean? {
        return dataStore.data.first()[OnBoardingKey.onBoardingKey]
    }
}