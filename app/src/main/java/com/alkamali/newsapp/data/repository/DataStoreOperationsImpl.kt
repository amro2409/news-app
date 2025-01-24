package com.alkamali.newsapp.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import com.alkamali.newsapp.domain.repository.DataStoreOperations
import com.alkamali.newsapp.util.Constants.ONBOARDING_KEY
import com.alkamali.newsapp.util.Constants.PREFERENCES_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


val Context.datastore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

class DataStoreOperationsImpl(context: Context) : DataStoreOperations {

    private val dataStore = context.datastore

    private object PreferencesKey {
        val onBoardingKey = booleanPreferencesKey(name = ONBOARDING_KEY)
    }

    override suspend fun saveOnBoardingState(completed: Boolean) {
        dataStore.edit { prefs ->
            prefs[PreferencesKey.onBoardingKey] = completed
        }
    }

    override fun readOnBoardingState(): Flow<Boolean> {
        return dataStore.data.catch {e ->
            if (e is IOException) {
                emit(emptyPreferences())
            } else {
                throw Exception(e)
            }
        }.map { prefs ->
            val onBoardingState = prefs[PreferencesKey.onBoardingKey] ?: false
            onBoardingState
        }
    }
}