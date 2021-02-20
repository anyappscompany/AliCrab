package ua.com.anyapps.alicrab.di.module

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = arrayOf(AppModule::class))
class SharedPreferencesModule() {

    @Singleton
    @Provides
    fun providesSharedPreferences(context: Context): SharedPreferences{
        return context.getSharedPreferences("pref", MODE_PRIVATE)
    }
}