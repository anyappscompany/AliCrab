package ua.com.anyapps.alicrab.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.preference.ListPreference
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat
import com.google.android.material.appbar.MaterialToolbar
import ua.com.anyapps.alicrab.R
import ua.com.anyapps.alicrab.viewmodel.SettingsViewModel
import java.util.ArrayList

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var sharedPreferencesViewModel: SettingsViewModel
    private var appThemePreference: ListPreference? = null

    private var topAppBar: MaterialToolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        appThemePreference = preferenceManager.findPreference<ListPreference>(getString(R.string.settings_app_theme))

        setupViewModel()
        initObservers()
        initListeners()
    }

    fun setupMenu(){
        topAppBar?.menu?.clear()
        topAppBar?.inflateMenu(R.menu.default_menu)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        topAppBar = requireActivity().findViewById(R.id.topAppBar)
        setupMenu()

        return view
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }

    private fun setupViewModel(){
        sharedPreferencesViewModel = SettingsViewModel()
    }

    private fun initObservers(){
        // применить тему
        sharedPreferencesViewModel.currentTheme.observe(requireActivity(), Observer {
            requireContext().theme.applyStyle(it, true)
            activity?.recreate()
        })
    }

    private fun initListeners(){
        // тема изменилась
        appThemePreference?.let {
            it.onPreferenceChangeListener = Preference.OnPreferenceChangeListener { preference, newValue ->
                sharedPreferencesViewModel.setCurrentTheme(newValue.toString())
                true
            }
        }
    }
}