package ua.com.anyapps.alicrab.repository

import android.content.Context
import android.content.SharedPreferences
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import javax.inject.Inject


class SettingsRepositoryImpl: SettingsRepository {

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        App.applicationComponent.inject(this)
    }

    // resource ID
    override fun getCurrentTheme(): Int {
        return context.resources.getIdentifier(sharedPreferences.getString(context.resources.getString(R.string.settings_app_theme), context.resources.getStringArray(R.array.appThemesValues).get(0)), "style", context.packageName)
    }

    // Theme0, Theme1, ...
    override fun setCurrentTheme(theme: String) {
        sharedPreferences.edit().putString(context.getString(R.string.settings_app_theme), theme).apply()
    }
}