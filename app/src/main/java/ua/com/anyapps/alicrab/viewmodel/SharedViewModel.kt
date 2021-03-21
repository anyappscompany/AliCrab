package ua.com.anyapps.alicrab.viewmodel

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _selectedNews = MutableLiveData<String>()

    fun setSelectedNews(str: String) {
        _selectedNews.value = str
    }
    fun getSelectedNews() = _selectedNews.value
}