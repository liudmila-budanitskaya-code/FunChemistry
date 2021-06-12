package site.budanitskaya.chemistryquiz.fine.ui.notifications

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log

import androidx.navigation.fragment.findNavController
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceManager
import androidx.preference.SwitchPreference
import site.budanitskaya.chemistryquiz.fine.R

class NotificationsFragment : PreferenceFragmentCompat(), Preference.OnPreferenceClickListener,
    SharedPreferences.OnSharedPreferenceChangeListener {

    companion object {
        const val NOTIFICATION_ON_PREFERENCE_KEY = "preference_notification"
        const val NOTIFICATION_TIME_PREFERENCE_KEY = "preference_notification_time"
        const val DEFAULT_NOTIFICATION_TIME = "21:0"
    }

    private var notificationSwitchPreference: SwitchPreference? = null
    private var notificationTimePreference: Preference? = null

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.app_settings, rootKey)
        init()
        setupClickListeners()
    }

    override fun onResume() {
        super.onResume()
        preferenceManager.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceManager.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onPreferenceClick(preference: Preference?): Boolean =
        when (preference?.key) {
            NOTIFICATION_TIME_PREFERENCE_KEY -> {
                findNavController().navigate(R.id.action_navigation_notifications_to_timePickerFragment)
                true
            }
            else -> false
        }

    override fun onSharedPreferenceChanged(prefs: SharedPreferences?, pref_key: String?) {

        when (pref_key) {
            NOTIFICATION_TIME_PREFERENCE_KEY -> {
                // при изменении времени уведомления мы записываем новое время
                notificationTimePreference?.summary =
                    TimeUtils.formatTimeSavedInPreference(
                        prefs?.getString(
                            pref_key,
                            DEFAULT_NOTIFICATION_TIME
                        )
                    )




                Log.d("onSharedPreferenced", "onSharedPreferenced: ${prefs?.getString(NOTIFICATION_TIME_PREFERENCE_KEY, "")}")
                val isNotificationOn =
                    prefs?.getBoolean(NOTIFICATION_ON_PREFERENCE_KEY, false) ?: false

                if (isNotificationOn) {
                    Log.d("My_tag", "Notification is on!")
                }

                Log.d("My_tag", "${prefs?.getString(NOTIFICATION_TIME_PREFERENCE_KEY, "")}")


            }
            NOTIFICATION_ON_PREFERENCE_KEY ->{
                Log.d("onSharedPreferenceChanged", "onSharedPreferenceChanged: ${prefs?.getBoolean(NOTIFICATION_ON_PREFERENCE_KEY, true)}")
            }

        }

    }

    private fun init() {
        notificationSwitchPreference = findPreference(NOTIFICATION_ON_PREFERENCE_KEY)
        notificationTimePreference = findPreference(NOTIFICATION_TIME_PREFERENCE_KEY)
    }

    private fun setupClickListeners() {
        notificationTimePreference?.onPreferenceClickListener = this
    }

}