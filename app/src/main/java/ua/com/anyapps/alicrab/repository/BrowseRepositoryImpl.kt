package ua.com.anyapps.alicrab.repository

import android.content.Context
import android.content.SharedPreferences
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.di.App
import javax.inject.Inject

class BrowseRepositoryImpl: BrowseReposotiry {
    val LAST_URL: String = "last_url"
    //val HTML: String = "html"

    @Inject
    lateinit var context: Context

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    init {
        App.applicationComponent.inject(this)
    }

    // по умолчанию грузить главную мобильной версии
    override fun getStartUrl(): String {
        return sharedPreferences.getString(LAST_URL, context.resources.getString(R.string.start_url)).toString()
    }

    // последняя посещённая страница
    override fun saveLastUrl(lastUrl: String) {
        sharedPreferences.edit().putString(LAST_URL, lastUrl).apply()
        //sharedPreferences.edit().putString(HTML, html).apply()
    }
}