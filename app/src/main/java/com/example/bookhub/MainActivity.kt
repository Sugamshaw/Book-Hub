package com.example.bookhub

import android.os.Bundle
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Toast
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

        design_navigation_view.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.dashboard -> {
                    Toast.makeText(this, "Dashboard", Toast.LENGTH_SHORT).show()
                }

                R.id.favourites -> {
                    Toast.makeText(this, "favourites", Toast.LENGTH_SHORT).show()
                }

                R.id.profile -> {
                    Toast.makeText(this, "profile", Toast.LENGTH_SHORT).show()
                }

                R.id.about_app -> {
                    Toast.makeText(this, "about_app", Toast.LENGTH_SHORT).show()
                }


            }

            return@setNavigationItemSelectedListener true
        }


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


}