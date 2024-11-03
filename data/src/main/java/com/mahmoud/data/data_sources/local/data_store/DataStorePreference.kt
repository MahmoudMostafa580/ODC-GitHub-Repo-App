package com.mahmoud.data.data_sources.local.data_store

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class DataStorePreference @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private object PreferenceKeys {
            val isFirstTimeKey = booleanPreferencesKey("isFirstTime")
        }

        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
            name = ""
        )
    }

    suspend fun saveIsFirstTimeEnterApp(isFirstTime: Boolean = true) {
        context.dataStore.edit { mutablePreferences ->
            mutablePreferences[PreferenceKeys.isFirstTimeKey] = isFirstTime == true
        }
    }

    suspend fun readIsFirstTimeEnterApp(): Boolean? {
        return context.dataStore.data.first()[PreferenceKeys.isFirstTimeKey]

    }
}