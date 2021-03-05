package ua.com.anyapps.alicrab.repository

import android.content.Context
import android.content.SharedPreferences
import android.content.SharedPreferences.Editor
import android.preference.PreferenceManager
import android.util.Log
import androidx.lifecycle.MutableLiveData
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.ui.fragments.SettingsFragment
import javax.inject.Inject


class SharedPreferencesRepositoryImpl: SharedPreferencesRepository {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        App.applicationComponent.inject(this)
    }

    override fun getCurrentTheme(default: Int): Int {
        return sharedPreferences.getInt(context.getString(R.string.preference_app_theme), default)
    }

    override fun setCurrentTheme(theme: Int) {
        sharedPreferences.edit().putInt(context.getString(R.string.preference_app_theme), theme).apply()
    }
}