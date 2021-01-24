package com.example.navigationtestapp.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.SharedPreferencesMigration
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException

class DataStoreBuilder(
    appContext: Context,
    preferencesName: String
) {

    private val dataStore: DataStore<Preferences> = appContext.createDataStore(
        name = preferencesName,
        // Note: keys must has the same name that we used with SharedPreferences.
        // Note: keys are only migrated from SharedPreferences once, so you should discontinue using
        // the old SharedPreferences once the code is migrated to DataStore.
        migrations = listOf(SharedPreferencesMigration(appContext, preferencesName))
    )

    fun get(code: String, default: String): Flow<String> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[stringPreferencesKey(code)] ?: default }

    fun get(code: String, default: Set<String>): Flow<Set<String>> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[stringSetPreferencesKey(code)] ?: default }

    fun get(code: String, default: Int): Flow<Int> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[intPreferencesKey(code)] ?: default }

    fun get(code: String, default: Float): Flow<Float> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[floatPreferencesKey(code)] ?: default }

    fun get(code: String, default: Long): Flow<Long> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[longPreferencesKey(code)] ?: default }

    fun get(code: String, default: Double): Flow<Double> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[doublePreferencesKey(code)] ?: default }

    fun get(code: String, default: Boolean): Flow<Boolean> =
        dataStore.data
            .catch { if (it is IOException) emit(emptyPreferences()) else throw it }
            .map { it[booleanPreferencesKey(code)] ?: default }

    suspend fun set(code: String, value: String) {
        // edit handles data transactionally, ensuring that if the value is updated at the same
        // time from another thread, we won't have conflicts
        dataStore.edit { it[stringPreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Set<String>) {
        dataStore.edit { it[stringSetPreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Int) {
        dataStore.edit { it[intPreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Float) {
        dataStore.edit { it[floatPreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Long) {
        dataStore.edit { it[longPreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Double) {
        dataStore.edit { it[doublePreferencesKey(code)] = value }
    }

    suspend fun set(code: String, value: Boolean) {
        dataStore.edit { it[booleanPreferencesKey(code)] = value }
    }
}