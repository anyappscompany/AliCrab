package ua.com.anyapps.alicrab.di

import android.app.Application
import ua.com.anyapps.alicrab.di.component.ApplicationComponent
import ua.com.anyapps.alicrab.di.component.DaggerApplicationComponent
import ua.com.anyapps.alicrab.di.module.AppModule

class App : Application() {
    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        initializeDagger()
    }

    private fun initializeDagger() {
        applicationComponent = DaggerApplicationComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    fun getApplciationComponent(): ApplicationComponent {
        return applicationComponent
    }
}