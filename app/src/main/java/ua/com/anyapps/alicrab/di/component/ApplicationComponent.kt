package ua.com.anyapps.alicrab.di.component

import dagger.Component
import ua.com.anyapps.alicrab.di.module.AppModule
import ua.com.anyapps.alicrab.di.module.SharedPreferencesModule
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl
import javax.inject.Singleton

@Singleton
@Component(
    modules = arrayOf(
        AppModule::class, SharedPreferencesModule::class
    )
)
interface ApplicationComponent {
    fun inject(into: SharedPreferencesRepositoryImpl)
}