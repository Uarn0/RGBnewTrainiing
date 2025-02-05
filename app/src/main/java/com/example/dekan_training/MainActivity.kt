package com.example.dekan_training

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer: View
    private var isFragmentVisible = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLogin = findViewById<Button>(R.id.btnLogin)
        fragmentContainer = findViewById(R.id.fragment_container)

        buttonLogin.setOnClickListener {
            if (!isFragmentVisible) {
                showFragment()
            }
        }
    }

    private fun showFragment() {
        val fragment = FragmentRGB()
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
            .replace(R.id.fragment_container, fragment)
            .commit()
        fragmentContainer.visibility = View.VISIBLE
        isFragmentVisible = true
    }
}
