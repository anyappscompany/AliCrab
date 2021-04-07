package ua.com.anyapps.alicrab.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.internal.schedulers.IoScheduler
import ua.com.anyapps.alicrab.network.pojo.ChartData
import ua.com.anyapps.kt_test_app.repository.NetworkRepository
import ua.com.anyapps.kt_test_app.repository.NetworkRepositoryImpl

class ChartDialogViewModel: ViewModel() {
    private val networkRepository: NetworkRepository = NetworkRepositoryImpl()
    private val networkRepositoryLoadError = MutableLiveData<Boolean>()
    private val repositoryLoading = MutableLiveData<Boolean>()

    private val chartData = MutableLiveData<ChartData>()

    fun getLoading(): LiveData<Boolean> {
        return repositoryLoading
    }

    fun getError(): LiveData<Boolean>{
        return networkRepositoryLoadError
    }

    fun getChartData(): LiveData<ChartData>{
        return chartData
    }

    fun getChartDataBtnClick(url: String){
        repositoryLoading.value = true
        networkRepository.getChartData(url).subscribeOn(IoScheduler()).observeOn(AndroidSchedulers.mainThread())
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
}