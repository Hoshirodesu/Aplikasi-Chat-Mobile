package com.example.utsmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsmobile.databinding.ActivityLoginBinding
import com.example.utsmobile.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    lateinit var binding : ActivityLoginBinding // inisialisasi activity_login
    lateinit var firebaseAuth: FirebaseAuth // inisialisasi firebase authentication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan instance Firebase Authentication untuk operasi pendaftaran.
        firebaseAuth = FirebaseAuth.getInstance()

        // Ketika teks “Register” diklik, user akan dipindahkan ke RegisterActivity
        binding.txtRegister.setOnClickListener {
            Log.d("LoginActivity", "setOnClickListener: berhasil ke menu Register")
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLogin.setOnClickListener {
            // Mengambil Input dari EditText
            val email = binding.editLoginEmail.text.toString()
            val password = binding.editLoginPassword.text.toString()

            // Cek apakah field kosong
            if (email.isNotEmpty() && password.isNotEmpty()) {
                    // memanggil firebase untuk Login dengan email dan password
                    firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful) {
                            val intent = Intent(this, HomeActivity::class.java)
                            startActivity(intent)
                            // tampilkan pesan toast jika login berhasil
                            Log.d("LoginActivity", "setOnClickListener: Login berhasil")
                            Toast.makeText(this, "Selamat datang, $email", Toast.LENGTH_SHORT).show()
                        } else {
                            // tampilkan pesan toast jika input email dan password salah
                            Log.d("LoginActivity", "setOnClickListener: Email atau password salah")
                            Toast.makeText(this, "Email atau password salah. Silakan coba lagi.", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                // tampilkan pesan toast jika input email dan password tidak diisi
                Log.d("LoginActivity", "setOnClickListener: Semua field harus diisi")
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
        // Tombol kembali untuk ke main_activity
        val backButton: ImageView = findViewById(R.id.btn_loginBack)
        backButton.setOnClickListener {
            Log.d("LoginActivity", "setOnClickListener: Berhasil kembali ke MainActivity")
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}