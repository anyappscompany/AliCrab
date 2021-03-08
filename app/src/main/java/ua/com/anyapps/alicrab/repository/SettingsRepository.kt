package ua.com.anyapps.alicrab.repository

interface SettingsRepository {
    // resource ID
    fun getCurrentTheme(): Int
    // Theme0, Theme1, ...
    fun setCurrentTheme(theme: String)
}