package com.anantjava.recordkeeper

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.addCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.commit
import com.anantjava.recordkeeper.cycling.CyclingFragment
import com.anantjava.recordkeeper.databinding.ActivityMainBinding
import com.anantjava.recordkeeper.running.RunningFragment
import com.google.android.material.navigation.NavigationBarView

class MainActivity : AppCompatActivity(), NavigationBarView.OnItemSelectedListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.bottomNav.setOnItemSelectedListener(this)
        supportFragmentManager.commit {
            add(R.id.fragment_container, RunningFragment())
        }

        onBackPressedDispatcher.addCallback { showdialog() }

    }

    private fun showdialog() {

        AlertDialog.Builder(this)
            .setTitle("Warning")
           // .setMessage("Are you sure you want to exit?")
            .setView(R.layout.dialog_layout)
            .setPositiveButton("Yes") { _, _ -> finish() }
            .setNegativeButton("No") { dialog, _ -> dialog.dismiss() }
            .show()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.options_menu, menu)
        return true
    }

    private fun onRunningCLicked(): Boolean {

        supportFragmentManager.commit {
            replace(R.id.fragment_container, RunningFragment())
        }

        return true
    }

    private fun onCyclingCLicked(): Boolean {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, CyclingFragment())
        }

        return true

    }

    override fun onNavigationItemSelected(it: MenuItem) = when (it.itemId) {
        R.id.running_icon -> onRunningCLicked()
        R.id.cycling_icon -> onCyclingCLicked()
        else -> false
    }
}

