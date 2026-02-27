package ru.practicum.android.diploma.ui.root

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R

class RootActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.rootFragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        val bottomNavigationSeparatorLine = findViewById<View>(R.id.bottomNavigationSeparatorLine)

        bottomNavigationView.setupWithNavController(navController)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filterSettingsFragment,
                R.id.workplaceFilterFragment,
                R.id.industryFilterFragment,
                R.id.countryFilterFragment,
                R.id.regionFilterFragment,
                R.id.vacancyFragment -> {
                    bottomNavigationView.visibility = View.GONE
                    bottomNavigationSeparatorLine.visibility = View.GONE
                }
                else -> {
                    bottomNavigationView.visibility = View.VISIBLE
                    bottomNavigationSeparatorLine.visibility = View.VISIBLE
                }
            }

        }

        // Пример использования access token для HeadHunter API
        networkRequestExample(accessToken = BuildConfig.API_ACCESS_TOKEN)
    }

    private fun networkRequestExample(accessToken: String) {
        // ...
    }

}
