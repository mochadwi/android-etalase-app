package io.mochadwi.ui

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import io.mochadwi.R
import io.mochadwi.databinding.HomeActivityBinding
import io.mochadwi.ui.movie.MovieViewModel
import io.mochadwi.util.base.BaseActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 *
 * In syaa Allah created & modified
 * by mochadwi on 10/08/19
 * dedicated to build etalase-app
 *
 */

class HomeActivity : BaseActivity() {
    private val viewBinding: HomeActivityBinding by lazy {
        DataBindingUtil.setContentView<HomeActivityBinding>(this, R.layout.home_activity)
    }
    private lateinit var mNavHost: NavHostFragment
    private lateinit var mNavController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    private val viewModel by viewModel<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme_Launcher)
        super.onCreate(savedInstanceState)

        viewBinding.executePendingBindings()
        setupNavController()
        setupAppBar()
        if (::mNavController.isInitialized && ::appBarConfiguration.isInitialized) {
            setupActionBar(mNavController, appBarConfiguration)
            setupBottomNav(mNavController)
        }
    }

    override fun onBackPressed() {
        if (::mNavHost.isInitialized) {
            val fragmentsSize = mNavHost.childFragmentManager.fragments.size

            if (fragmentsSize >= 1) {
                super.onBackPressed()
            } else {
                findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return if (::mNavHost.isInitialized) {
            findNavController(R.id.navHostFragment).navigateUp(appBarConfiguration)
        } else {
            false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.actLanguage -> {
                val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(mIntent)
            }
            R.id.actNotif -> {
                startActivity(Intent(this@HomeActivity, NotifSettingActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupNavController() {
        mNavHost = supportFragmentManager
                .findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: return
        mNavController = mNavHost.navController
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(
                setOf(R.id.movieFragmentDest, R.id.tvFragmentDest, R.id.favFragmentDest),
            null
        )
    }

    private fun setupActionBar(
        navController: NavController,
        appBarConfig: AppBarConfiguration
    ) {
        setupToolbar(viewBinding.toolbar.tbCustom)
        setupActionBarWithNavController(navController, appBarConfig)
    }

    private fun setupBottomNav(navController: NavController) {
        viewBinding.apply {
            homeBottomNavView.setupWithNavController(navController)
        }
    }
}