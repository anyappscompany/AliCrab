package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ua.com.anyapps.alicrab.repository.BrowseRepositoryImpl
import ua.com.anyapps.alicrab.repository.BrowseReposotiry

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

    fun navHomeClick(){
        Log.d("debapp", "HOME CLICK")
    }

    fun navBackClick(){
        Log.d("debapp", "BACK CLICK")
    }

    fun navForwardClick(){
        Log.d("debapp", "FORWARD CLICK")
    }

    fun navRefreshClick(){
        Log.d("debapp", "REFRESH CLICK")
    }


    /*private val loading: MutableLiveData<Boolean> = MutableLiveData()
    fun getLoading(): LiveData<Boolean> {
        return loading
    }
    fun setLoading(b: Boolean){
        loading.value = b
    }*/
}