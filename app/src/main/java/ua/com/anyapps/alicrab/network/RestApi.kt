package ua.com.anyapps.kt_test_app.network

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query
import ua.com.anyapps.alicrab.network.pojo.ChartData

interface RestApi {
        @GET("aliexpress2.php?v=1")
        fun getChartData(@Query("p") p: String): Single<ChartData>
}