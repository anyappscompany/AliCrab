package ua.com.anyapps.alicrab.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {
    private val _url = MutableLiveData<String>()

    fun setUrl(url: String) {
        _url.value = url
    }
    fun getUrl(): LiveData<String> = _url
}