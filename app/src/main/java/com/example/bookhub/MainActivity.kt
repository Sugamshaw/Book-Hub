package com.example.bookhub

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawer_layout: DrawerLayout
    private lateinit var coordinator_layout: CoordinatorLayout
    private lateinit var toolbar: Toolbar
    private lateinit var frame_layout: FrameLayout
    private lateinit var design_navigation_view: NavigationView

    var previous_menuitem: MenuItem? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        drawer_layout = findViewById(R.id.drawer_layout)
        coordinator_layout = findViewById(R.id.coordinator_layout)
        toolbar = findViewById(R.id.toolbar)
        frame_layout = findViewById(R.id.frame_layout)
        design_navigation_view = findViewById(R.id.design_navigation_view)

        setUpToolBar()


        val actionBarDrawerToggle =
            ActionBarDrawerToggle(this, drawer_layout, R.string.open_drawer, R.string.close_drawer)
        drawer_layout.addDrawerListener(actionBarDrawerToggle)
        actionBarDrawerToggle.syncState()


        openDashboard()
        design_navigation_view.setNavigationItemSelectedListener {

            if(previous_menuitem!=null){
                previous_menuitem?.isChecked=false
            }
            it.isCheckable = true
            it.isChecked = true
            previous_menuitem = it
            when (it.itemId) {
                R.id.dashboard -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, DashboardFragment())
                        .commit()
                    supportActionBar?.title = "Dashboard"

                    drawer_layout.closeDrawers()
                }

                R.id.favourites -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, FavouritesFragment())
                        .commit()
                    supportActionBar?.title = "Favourites"
                    drawer_layout.closeDrawers()
                }

                R.id.profile -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, ProfileFragment())
                        .commit()
                    supportActionBar?.title = "Profile"
                    drawer_layout.closeDrawers()
                }

                R.id.about_app -> {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, AboutFragment())
                        .commit()
                    supportActionBar?.title = "About App"
                    drawer_layout.closeDrawers()
                }


            }

            return@setNavigationItemSelectedListener true
        }


    }

    private fun openDashboard() {
        val fragment = DashboardFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_layout, fragment)
        transaction.commit()

        supportActionBar?.title = "Dashboard"
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Book Hub"
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            drawer_layout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val fragment = supportFragmentManager.findFragmentById(R.id.frame_layout)

        when (fragment) {
            !is DashboardFragment -> {
                openDashboard()
            }

            else -> {
                super.onBackPressed()
            }
        }
    }


}