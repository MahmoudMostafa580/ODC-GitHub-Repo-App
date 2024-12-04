package com.mahmoud.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "on_boarding_state")

class DataStorePreference @Inject constructor(
    context: Context
) {
    companion object {

        private object OnBoardingKey {
            val onBoardingKey = booleanPreferencesKey("on_boarding_completed")
        }
    }

    private val dataStore = context.dataStore

    suspend fun savaOnBoardingState(completed: Boolean) {
        dataStore.edit { preferences ->
            preferences[OnBoardingKey.onBoardingKey] = completed
        }
    }

    suspend fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.map { pref ->
            pref[OnBoardingKey.onBoardingKey] ?: false
        }
    }
}