package ua.com.anyapps.alicrab

import android.app.ActivityManager
import android.content.SharedPreferences
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import javax.inject.Inject

abstract class BaseActivity: AppCompatActivity() {

    @Inject
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val prefrenceAppName = getResources().getString(R.string.preference_app_theme)
        val appThemeDefault = getResources().getString(R.string.app_themes_default)
        val themeInt = resources.getIdentifier(appThemeDefault, "style", packageName)

        setTheme(resources.getIdentifier(sharedPreferences.getString(prefrenceAppName, "Theme0"), "style", packageName))
    }
}