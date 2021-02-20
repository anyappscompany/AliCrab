package ua.com.anyapps.alicrab.repository

import androidx.lifecycle.MutableLiveData

interface SharedPreferencesRepository {
    fun getCurrentTheme(): Int
    fun setCurrentTheme(theme: Int)

    //
}