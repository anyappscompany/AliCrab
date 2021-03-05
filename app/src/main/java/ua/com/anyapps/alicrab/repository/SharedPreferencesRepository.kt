package ua.com.anyapps.alicrab.repository

import androidx.lifecycle.MutableLiveData

interface SharedPreferencesRepository {
    fun getCurrentTheme(default: Int): Int
    fun setCurrentTheme(theme: Int)

    //
}