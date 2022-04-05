package com.example.acalculator

import androidx.appcompat.app.AppCompatActivity
import com.example.acalculator.databinding.MainActivityBinding
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var binding: MainActivityBinding
    private val opsList = mutableListOf<OperationUi>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(!screenRotated(savedInstanceState)) {
            NavigationManager.goToCalculatorFragment(
                    supportFragmentManager,
                    ArrayList(opsList)
            )
        }
    }

    private fun screenRotated(savedInstanceState: Bundle?): Boolean {
        return savedInstanceState != null
    }

    fun addElem(op: OperationUi){
        opsList.add(op)
    }

    override fun onStart() {
        super.onStart()
        NavigationManager.goToCalculatorFragment(supportFragmentManager,ArrayList(opsList))
        setSupportActionBar(binding.toolbar)
        setUpDrawerMenu()
    }

    private fun setUpDrawerMenu() {
        val toggle = ActionBarDrawerToggle(
                this,
                binding.drawer,
                binding.toolbar,
                R.string.drawer_open,
                R.string.drawer_close
        )
        binding.navDrawer.setNavigationItemSelectedListener {
            onClickNavigationItem(it)
        }
        binding.drawer.addDrawerListener(toggle)
        toggle.syncState()
    }

    private fun onClickNavigationItem(item: MenuItem) : Boolean {
        when(item.itemId) {
            R.id.nav_calculator ->
                NavigationManager.goToCalculatorFragment(
                        supportFragmentManager,
                        ArrayList(opsList)
                )
            R.id.nav_history ->
                NavigationManager.goToHistoryFragment(
                        supportFragmentManager,
                        ArrayList(opsList)
                )
        }
        binding.drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if(binding.drawer.isDrawerOpen(GravityCompat.START)) {
            binding.drawer.closeDrawer(GravityCompat.START)
        }else if (supportFragmentManager.backStackEntryCount == 1){
            finish()
        } else {
            super.onBackPressed()
        }
    }

}