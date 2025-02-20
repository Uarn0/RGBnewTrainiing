package com.example.dekan_training

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.dekan_training.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var fragmentContainer: View
    private var isFragmentVisible = false
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttonLogin = binding.btnLogin
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
