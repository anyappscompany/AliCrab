package ua.com.anyapps.alicrab

import android.os.Bundle
import android.view.Menu
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel


class MainActivity : BaseActivity() {

    private lateinit var host: NavHostFragment
    private lateinit var navController: NavController
    private lateinit var bottomNavigationView: BottomNavigationView

    private val viewModel: SharedViewModel by viewModels()

    init {
        App.applicationComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        host = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_menu)

        setupNavHostFragmentNavigation()
    }

    private fun setupNavHostFragmentNavigation(){
        navController = host.navController
        bottomNavigationView.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}