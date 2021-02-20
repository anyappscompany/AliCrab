package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepository
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl

class SharedPreferencesViewModel: ViewModel() {
    val KEY_THEME = "Theme"
    val THEME0 = R.style.Theme0
    val THEME1 = R.style.Theme1
    val THEME2 = R.style.Theme2
    val THEME3 = R.style.Theme3
    val THEME4 = R.style.Theme4

    private val repository: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    fun getCurrentTheme(sdad:Int): Int = repository.getCurrentTheme()

    fun setCurrentTheme(theme: Int) = repository.setCurrentTheme(theme)
}