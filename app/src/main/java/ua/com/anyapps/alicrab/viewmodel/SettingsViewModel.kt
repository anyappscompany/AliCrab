package ua.com.anyapps.alicrab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepository
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl

class SettingsViewModel: ViewModel() {

    private val _currentTheme = MutableLiveData<Int>()
    val currentTheme: LiveData<Int> = _currentTheme

    private val repository: SharedPreferencesRepository = SharedPreferencesRepositoryImpl()

    // Theme0, Theme1, ...
    fun setCurrentTheme(theme: String){
        repository.setCurrentTheme(theme)
        _currentTheme.value = repository.getCurrentTheme()
    }
}