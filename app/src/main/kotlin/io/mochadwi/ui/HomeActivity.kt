package io.mochadwi.ui

import android.app.SearchManager
import android.content.ComponentName
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
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
import io.mochadwi.util.ext.coroutineLaunch
import io.mochadwi.util.ext.default
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
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

    val viewModel by viewModel<MovieViewModel>()

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
        setupSearchBar(menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.actLanguage) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setupSearchBar(menu: Menu) {
        val searchManager = ContextCompat.getSystemService(this, SearchManager::class.java)
        val componentName = ComponentName(this, HomeActivity::class.java)
        val searchItem = menu.findItem(R.id.actSearch)

        var searchFor = ""
        (searchItem?.actionView as SearchView).apply {
            // TODO: @mochadwi Definitely must using paging library, or upsert / delsert manually to the room
            setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String?): Boolean {
                        coroutineLaunch(Dispatchers.Main) {
                            viewModel.keywords.send(query.default)
                        }
                        return true
                    }

                    override fun onQueryTextChange(newText: String?): Boolean {
                        val searchText = newText.default.trim()

                        if (searchText == searchFor) return false

                        searchFor = searchText
                        coroutineLaunch(Dispatchers.Main) {
                            delay(300)
                            if (searchText != searchFor)
                                return@coroutineLaunch

                            viewModel.keywords.send(newText.default)
                        }
                        return true
                    }
                }
            )
            setSearchableInfo(searchManager?.getSearchableInfo(componentName))
        }
    }
    private fun setupNavController() {
        mNavHost = supportFragmentManager
                .findFragmentById(R.id.navHostFragment) as NavHostFragment? ?: return
        mNavController = mNavHost.navController
    }

    private fun setupAppBar() {
        appBarConfiguration = AppBarConfiguration(
            setOf(R.id.movieFragmentDest, R.id.tvFragmentDest),
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