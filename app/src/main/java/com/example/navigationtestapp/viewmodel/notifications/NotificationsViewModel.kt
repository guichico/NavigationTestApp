package com.example.navigationtestapp.viewmodel.notifications

import androidx.lifecycle.ViewModel
import com.example.navigationtestapp.repositiory.NotificationsRepository
import com.example.navigationtestapp.ui.notifications.Notification

class NotificationsViewModel(private val notificationsRepository: NotificationsRepository) : ViewModel() {

    fun getNotifications(): List<Notification> = notificationsRepository.getNotifications()
}