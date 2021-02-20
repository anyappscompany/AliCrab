package ua.com.anyapps.alicrab.ui.fragments

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.viewmodel.SharedPreferencesViewModel

class SettingsFragment : Fragment() {

    private lateinit var sharedPreferencesViewModel: SharedPreferencesViewModel
    private var currentTheme = THEME0

    override fun onCreate(savedInstanceState: Bundle?) {
        setupViewModel()
        currentTheme = PreferenceManager.getDefaultSharedPreferences(requireContext()).getInt(KEY_THEME, THEME0)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        setAppTheme(0)
        val view: View  = inflater.inflate(R.layout.fragment_settings, container, false)
        // Inflate the layout for this fragment
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.button).setOnClickListener(View.OnClickListener {
            switchTheme()
            activity?.recreate()
        })
    }

    private fun setupViewModel(){
        sharedPreferencesViewModel = SharedPreferencesViewModel()
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SettingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SettingsFragment()

        private const val KEY_THEME = "Theme"
        private const val THEME0 = R.style.Theme0
        private const val THEME1 = R.style.Theme1
        private const val THEME2 = R.style.Theme2
        private const val THEME3 = R.style.Theme3
        private const val THEME4 = R.style.Theme4
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
}