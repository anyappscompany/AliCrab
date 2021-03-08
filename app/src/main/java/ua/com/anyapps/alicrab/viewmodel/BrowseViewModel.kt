package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.repository.BrowseRepositoryImpl
import ua.com.anyapps.alicrab.repository.BrowseReposotiry
import ua.com.anyapps.alicrab.repository.SettingsRepository
import ua.com.anyapps.alicrab.repository.SettingsRepositoryImpl
import ua.com.anyapps.alicrab.utils.Utils

class BrowseViewModel: ViewModel() {

    private val _lastUrl = MutableLiveData<String>()
    val lastUrl: LiveData<String> = _lastUrl

    private val repository: BrowseReposotiry = BrowseRepositoryImpl()

    init {
        _lastUrl.value = repository.getStartUrl()
    }
    fun saveLastUrl(lastUrl: String){
        repository.saveLastUrl(lastUrl)
    }
}