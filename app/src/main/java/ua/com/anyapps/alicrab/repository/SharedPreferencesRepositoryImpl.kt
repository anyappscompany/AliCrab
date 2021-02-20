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
    private val KEY_THEME = "Theme"
    private val THEME0 = R.style.Theme0
    private val THEME1 = R.style.Theme1
    private val THEME2 = R.style.Theme2
    private val THEME3 = R.style.Theme3
    private val THEME4 = R.style.Theme4

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        App.applicationComponent.inject(this)
    }

    override fun getCurrentTheme(): Int {
        return sharedPreferences.getInt(KEY_THEME, THEME0)
    }

    override fun setCurrentTheme(theme: Int) {
        sharedPreferences.edit().putInt(KEY_THEME, theme).apply()
    }
}