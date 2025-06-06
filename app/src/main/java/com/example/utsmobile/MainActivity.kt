package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val loginButton: Button = findViewById(R.id.login_button) // Mengambil Input dari button
        val registerButton: Button = findViewById(R.id.register_button) // Mengambil Input dari button

        // Tombol untuk ke activity_login
        loginButton.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            Log.d("mainActivity", "setOnClickListener: Berhasil ke menu Login")
        }
        // Tombol untuk ke activity_register
        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
            Log.d("mainActivity", "setOnClickListener: Berhasil ke menu Register")
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

}