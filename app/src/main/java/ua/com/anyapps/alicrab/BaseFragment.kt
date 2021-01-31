package ua.com.anyapps.alicrab

import android.app.ActivityManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment: Fragment() {
    private var currentTheme = THEME2

    override fun onCreate(savedInstanceState: Bundle?) {
        currentTheme = PreferenceManager.getDefaultSharedPreferences(requireContext()).getInt(KEY_THEME, THEME2)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    protected fun setAppTheme(themeId: Int){
        /*setTheme(currentTheme)
        getContext().getTheme().applyStyle(styleId, true);*/
        requireContext().theme.applyStyle(currentTheme, true)
    }

    protected fun switchTheme(){
        currentTheme = when(currentTheme){
            THEME0 -> THEME1
            THEME1 -> THEME2
            THEME2 -> THEME3
            THEME3 -> THEME4
            THEME4 -> THEME0
            else -> -1
        }
        PreferenceManager.getDefaultSharedPreferences(requireContext()).edit().putInt(KEY_THEME, currentTheme).apply()
    }

    private fun getColorPrimary() = when(currentTheme){
        THEME0 -> R.color.theme0Color1
        THEME1 -> R.color.theme1Color1
        THEME2 -> R.color.theme2Color1
        THEME3 -> R.color.theme3Color1
        THEME4 -> R.color.theme4Color1
        else -> android.R.color.background_light
    }

    companion object{
        private const val KEY_THEME = "Theme"
        private const val THEME0 = R.style.Theme0
        private const val THEME1 = R.style.Theme1
        private const val THEME2 = R.style.Theme2
        private const val THEME3 = R.style.Theme3
        private const val THEME4 = R.style.Theme4
    }
}