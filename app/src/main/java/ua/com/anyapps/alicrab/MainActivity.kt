package ua.com.anyapps.alicrab

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ua.com.anyapps.alicrab.databinding.ActivityMainBinding
import ua.com.anyapps.alicrab.di.App
import ua.com.anyapps.alicrab.dialogs.ChartDialog
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


        setSupportActionBar(binding.topAppBar)
        setupNavHostFragmentNavigation()
        /*binding.bottomMenu.apply {
            background = null
            menu.getItem(2).isEnabled = false
        }
        setupNavHostFragmentNavigation()

        binding?.fabBrowse?.setOnClickListener(View.OnClickListener {
            val navView: BottomNavigationView = findViewById(R.id.bottom_menu)
            // Find the menu item and then disable it
            navView.menu.findItem(R.id.chartFragment).isEnabled = false
            navView.menu.setGroupCheckable(0, true, false)
            for (i in 0 until navView.menu.size()) {
                navView.menu.getItem(i).isChecked = false
            }
            navView.menu.setGroupCheckable(0, true, true)

            findNavController(R.id.nav_host_fragment).navigate(R.id.chartFragment)
        })*/

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.homeFragment->navController.navigate(R.id.homeFragment)
            R.id.browseFragment->navController.navigate(R.id.browseFragment)
            R.id.chartFragment->navController.navigate(R.id.chartFragment)
            R.id.historyFragment->navController.navigate(R.id.historyFragment)
            R.id.settingsFragment->navController.navigate(R.id.settingsFragment)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavHostFragmentNavigation(){
        navController = findNavController(R.id.nav_host_fragment)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.top_app_bar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }
}