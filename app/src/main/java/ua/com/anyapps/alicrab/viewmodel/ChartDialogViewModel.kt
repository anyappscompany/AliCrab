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




    init {
        Log.d("debapp", "ChartDialogViewModel INIT")
    }
}