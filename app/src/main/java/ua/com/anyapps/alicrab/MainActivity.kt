package ua.com.anyapps.alicrab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.com.anyapps.alicrab.databinding.ActivityMainBinding
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepository
import ua.com.anyapps.alicrab.repository.SharedPreferencesRepositoryImpl
import ua.com.anyapps.alicrab.viewmodel.SharedPreferencesViewModel

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppTheme(0)
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