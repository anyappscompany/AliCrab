package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepository
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl

class SettingsViewModel: ViewModel() {

    //private var currentTheme = 0

    private val repository: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    init {
        //this.currentTheme = getCurrentTheme(0)
    }


    fun getCurrentTheme(default:Int): Int = repository.getCurrentTheme(default)

    fun setCurrentTheme(theme: Int, theme2: String): LiveData<Int>{
        repository.setCurrentTheme(theme2)
        Log.d("debapp", "Set theme: ${theme}")
        var curT = MutableLiveData<Int>()
        curT.value = theme //themes.get(theme)
        return curT
    }
}