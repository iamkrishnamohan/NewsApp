package `in`.newtel.newsapp.ui

import `in`.newtel.newsapp.R
import `in`.newtel.newsapp.databinding.ActivityMainBinding
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI.setupWithNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        navController = Navigation.findNavController(this, R.id.newNavHostFragment)
        setupWithNavController(binding.bottomNavigationView, navController)
        /*val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.newNavHostFragment) as NavHostFragment
        val navController = navHostFragment.navController*/
        //binding.bottomNavigationView.setupWithNavController(binding.newNavHostFragment.findNavController())
    }
}