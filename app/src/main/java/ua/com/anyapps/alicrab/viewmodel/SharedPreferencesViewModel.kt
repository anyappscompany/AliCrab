package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepository
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl

class SharedPreferencesViewModel: ViewModel() {
    private val themes = arrayListOf<Int>(R.style.Theme0, R.style.Theme1, R.style.Theme2, R.style.Theme3, R.style.Theme4, R.style.Theme5, R.style.Theme6)

    private var currentTheme = 0
    public fun getThemeObservable(): LiveData<Int>{
        var curT = MutableLiveData<Int>()
        curT.value = currentTheme

        return curT
    }

    private val repository: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    init {
        this.currentTheme = getCurrentTheme(0)
    }


    fun getCurrentTheme(default:Int): Int = repository.getCurrentTheme(default)

    fun setCurrentTheme(theme: Int): LiveData<Int>{
        repository.setCurrentTheme(theme)
        Log.d("debapp", "Set theme: ${theme}")
        var curT = MutableLiveData<Int>()
        curT.value = theme //themes.get(theme)
        return curT
    }
}