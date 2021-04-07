package ua.com.anyapps.alicrab.di.component

import dagger.Component
import ua.com.anyapps.alicrab.BaseActivity
import ua.com.anyapps.alicrab.MainActivity
import ua.com.anyapps.alicrab.di.module.AppModule
import ua.com.anyapps.alicrab.di.module.NetworkModule
import ua.com.anyapps.alicrab.di.module.SharedPreferencesModule
import ua.com.anyapps.alicrab.repository.BrowseRepositoryImpl
import ua.com.anyapps.alicrab.repository.SettingsRepositoryImpl
import ua.com.anyapps.kt_test_app.repository.NetworkRepositoryImpl
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class, SharedPreferencesModule::class, NetworkModule::class
    )
)
interface ApplicationComponent {
    fun inject(into: SettingsRepositoryImpl)
    fun inject(into: MainActivity)
    fun inject(into: BaseActivity)
    fun inject(into: BrowseRepositoryImpl)
    fun inject(into: NetworkRepositoryImpl)
}