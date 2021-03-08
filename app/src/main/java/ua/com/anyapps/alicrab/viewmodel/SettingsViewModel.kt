package ua.com.anyapps.alicrab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.repository.SettingsRepository
import ua.com.anyapps.alicrab.repository.SettingsRepositoryImpl

class SettingsViewModel: ViewModel() {

    private val _currentTheme = MutableLiveData<Int>()
    val currentTheme: LiveData<Int> = _currentTheme

    private val repository: SettingsRepository = SettingsRepositoryImpl()

    // Theme0, Theme1, ...
    fun setCurrentTheme(theme: String){
        repository.setCurrentTheme(theme)
        _currentTheme.value = repository.getCurrentTheme()
    }
}