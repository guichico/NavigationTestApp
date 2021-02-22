package com.example.navigationtestapp.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.navigationtestapp.R
import com.example.navigationtestapp.databinding.FragmentNotificationsBinding
import com.example.navigationtestapp.databinding.NotificationItemBinding
import com.example.navigationtestapp.models.Place
import com.example.navigationtestapp.viewmodel.notifications.NotificationsViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotificationsFragment : Fragment() {

    private val notificationsViewModel: NotificationsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding =
            DataBindingUtil.inflate(
                inflater,
                R.layout.fragment_notifications,
                container,
                false
            ) as FragmentNotificationsBinding

        binding.notificationsList.apply {
            setHasFixedSize(true)
            adapter = NotificationsAdapter(
                notificationsViewModel.getNotifications(),
                NotificationsListener { notificationClicked(it) })
        }

        return binding.root
    }

    fun notificationClicked(notification: Notification) {
        val directions = when (notification.type) {
            Type.PLACE -> NotificationsFragmentDirections.actionNotificationToPlace(
                Place(notification.referenceId)
            )
            Type.PRODUCT -> NotificationsFragmentDirections.actionNotificationToProductDetail(
                notification.referenceId
            )
        }

        findNavController().navigate(directions)
    }

    class NotificationsListener(val clickListener: (notification: Notification) -> Unit) {
        fun onClick(notification: Notification) = clickListener(notification)
    }

    class NotificationsAdapter(
        private val notifications: List<Notification>,
        private val clickListener: NotificationsListener
    ) :
        RecyclerView.Adapter<NotificationViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationViewHolder {
            return NotificationViewHolder(
                NotificationItemBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }

        override fun onBindViewHolder(holder: NotificationViewHolder, position: Int) {
            val notification = notifications[position]
            holder.bind(notification, clickListener)
        }

        override fun getItemCount(): Int = notifications.size

    }

    class NotificationViewHolder(private val binding: NotificationItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(notification: Notification, clickListener: NotificationsListener) {
            binding.notification = notification
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }
    }
}