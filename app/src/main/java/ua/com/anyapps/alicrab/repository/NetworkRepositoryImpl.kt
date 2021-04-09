package ua.com.anyapps.kt_test_app.repository

import android.util.Log
import io.reactivex.Single
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.network.pojo.ChartData
import ua.com.anyapps.kt_test_app.network.RestApi
import javax.inject.Inject

class NetworkRepositoryImpl: NetworkRepository {
    @Inject
    lateinit var restApi: RestApi

    init {
        App.applicationComponent.inject(this)
    }

    override fun getChartData(p: String): Single<ChartData> {
        Log.d("debapp", "Start getting data")
        return restApi.getChartData(p)
    }
}