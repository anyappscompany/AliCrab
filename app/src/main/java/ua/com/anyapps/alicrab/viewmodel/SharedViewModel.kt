package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import ua.com.anyapps.alicrab.network.pojo.ChartData
import ua.com.anyapps.kt_test_app.repository.NetworkRepository
import ua.com.anyapps.kt_test_app.repository.NetworkRepositoryImpl

class SharedViewModel(private val savedStateHandle: SavedStateHandle): ViewModel() {
    private val _url = MutableLiveData<String>()

    private val networkRepositoryLoadError = MutableLiveData<Boolean>()
    private val repositoryLoading = MutableLiveData<Boolean>()
    private val chartData = MutableLiveData<ChartData>()

    private val networkRepository: NetworkRepository = NetworkRepositoryImpl()

    fun setUrl(url: String) {
        _url.value = url
    }
    fun getUrl(): LiveData<String> = _url

    init {
        Log.d("debapp", "SharedViewModel INIT")
    }

    fun getLoading(): LiveData<Boolean> {
        return repositoryLoading
    }

    fun getError(): LiveData<Boolean>{
        return networkRepositoryLoadError
    }

    fun getChartData(): LiveData<ChartData>{
        return chartData
    }

    fun getChartDataBtnClick(){
        repositoryLoading.value = true
        networkRepository.getChartData(_url.value!!).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result ->
                            // показать результаты поиска
                            networkRepositoryLoadError.value = false
                            chartData.value = result
                            //Log.d("debapp", "Response: ${result.product_title}")
                            // отключить лоадер
                            repositoryLoading.value = false
                        },
                        { error ->
                            // загрузить фрагмент "ошибка"
                            //Log.d("debapp", "Error : ${error.localizedMessage}")
                            networkRepositoryLoadError.value = true
                            repositoryLoading.postValue(false)
                        }
                )
    }

    override fun onCleared() {
        super.onCleared()
    }
}