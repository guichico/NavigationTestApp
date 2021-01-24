package com.example.navigationtestapp.repositiory

import android.content.Context
import com.example.navigationtestapp.ui.notifications.Notification
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

class NotificationsRepository(val context: Context) {

    fun getNotifications(): List<Notification> {
        val jsonAdapter: JsonAdapter<List<Notification>> = Moshi.Builder()
            .build()
            .adapter(
                Types.newParameterizedType(
                    MutableList::class.java,
                    Notification::class.java
                )
            )
        return jsonAdapter.fromJson(
            context.assets.open("notifications.json")
                .bufferedReader()
                .use { it.readText() }
        ) as List<Notification>
    }
}