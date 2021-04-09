package ua.com.anyapps.kt_test_app.repository

import io.reactivex.Single
import ua.com.anyapps.alicrab.network.pojo.ChartData

interface NetworkRepository {
    fun getChartData(p: String): Single<ChartData>
}