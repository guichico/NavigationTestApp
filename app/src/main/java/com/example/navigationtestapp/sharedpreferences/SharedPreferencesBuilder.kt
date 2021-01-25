package com.example.navigationtestapp.sharedpreferences

import android.content.Context
import android.content.SharedPreferences

class SharedPreferencesBuilder(
    appContext: Context,
    preferencesName: String
) {

    private val preferences: SharedPreferences =
        appContext.getSharedPreferences(preferencesName, Context.MODE_PRIVATE)

    fun get(code: String, default: Float) = preferences.getFloat(code, default)

    fun get(code: String, default: Boolean) = preferences.getBoolean(code, default)

    fun get(code: String, default: String) = preferences.getString(code, default)

    fun get(code: String, default: Int) = preferences.getInt(code, default)

    fun get(code: String, default: Long) = preferences.getLong(code, default)

    fun get(code: String, default: Double) =
        java.lang.Double.longBitsToDouble(
            preferences.getLong(
                code,
                java.lang.Double.doubleToLongBits(default)
            )
        )

    fun get(code: String, default: List<String>) =
        preferences.getStringSet(code, default.toSet())?.toList()

    fun set(code: String, value: Float) {
        preferences.edit().putFloat(code, value).apply()
    }

    fun set(code: String, value: Boolean) {
        preferences.edit().putBoolean(code, value).apply()
    }

    fun set(code: String, value: String) {
        preferences.edit().putString(code, value).apply()
    }

    fun set(code: String, value: Int) {
        preferences.edit().putInt(code, value).apply()
    }

    fun set(code: String, value: Long) {
        preferences.edit().putLong(code, value).apply()
    }

    fun set(code: String, value: Double) {
        preferences.edit().putLong(code, java.lang.Double.doubleToRawLongBits(value)).apply()
    }

    fun set(code: String, value: List<String>) {
        preferences.edit().putStringSet(code, value.toSet()).apply()
    }
}