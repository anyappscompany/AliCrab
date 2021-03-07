package ua.com.anyapps.alicrab

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Применить текущую тему
        setTheme(resources.getIdentifier(sharedPreferences.getString(getResources().getString(R.string.settings_app_theme), resources.getStringArray(R.array.appThemesValues).get(0)), "style", packageName))
    }
}