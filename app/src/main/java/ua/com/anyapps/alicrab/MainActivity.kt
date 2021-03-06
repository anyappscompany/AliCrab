package ua.com.anyapps.alicrab

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.com.anyapps.alicrab.databinding.ActivityMainBinding
import ua.com.anyapps.alicrab.di.App
import javax.inject.Inject


class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    /*@Inject
    lateinit var sharedPreferences: SharedPreferences*/

    init {
        App.applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        /*val stringArray = resources.getStringArray(R.array.appThemesValues)
        //setAppTheme(0)
        Log.d("debapp", "Theme ${sharedPreferences.getInt(getResources().getString(R.string.preference_app_theme), resources.getIdentifier(getResources().getString(R.string.app_themes_default), "style", packageName))}")*/


        setContentView(R.layout.activity_main)
        //binding = ActivityMainBinding.inflate(layoutInflater)
        /*setAppTheme(0)
        setContentView(binding.root)*/
        /*val sdsd:SharedPreferencesViewModel = SharedPreferencesViewModel()
        val sp: SharedPreferencesRepositoryImpl = SharedPreferencesRepositoryImpl()
        Log.d("debapp", "Curtheme: ${sp.getCurrentTheme()}")
        sp.setCurrentTheme(8)
        Log.d("debapp", "Curtheme: ${sp.getCurrentTheme()}")*/

        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_menu)

        setupNavHostFragmentNavigation();
    }

    private fun setupNavHostFragmentNavigation(){
        navController = host.navController
        bottomNavigationView.setupWithNavController(navController)
    }
}