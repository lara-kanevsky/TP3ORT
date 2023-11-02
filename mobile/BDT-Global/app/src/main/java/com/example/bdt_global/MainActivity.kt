package com.example.bdt_global

import android.content.ContentProvider
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.bdt_global.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val PREF_NAME = "Preferences"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        val sharedPreferences : SharedPreferences = getApplicationContext().getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)

    }

    override fun onBackPressed() {
//        var i = supportFragmentManager.getBackStackEntryAt(supportFragmentManager.backStackEntryCount)
        super.onBackPressedDispatcher.onBackPressed()
    }

}
