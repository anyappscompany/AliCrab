package ua.com.anyapps.alicrab

import android.os.Bundle
import android.util.Log
import android.view.Menu
import androidx.activity.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.com.anyapps.alicrab.databinding.ActivityMainBinding
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.viewmodel.SharedViewModel


class MainActivity : BaseActivity() {
    private lateinit var navController: NavController

    private val viewModel: SharedViewModel by viewModels<SharedViewModel>()

    init {
        App.applicationComponent.inject(this)
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupNavHostFragmentNavigation()
    }

    private fun setupNavHostFragmentNavigation(){
        navController = findNavController(R.id.nav_host_fragment)
        binding.bottomMenu.setupWithNavController(navController)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }
}