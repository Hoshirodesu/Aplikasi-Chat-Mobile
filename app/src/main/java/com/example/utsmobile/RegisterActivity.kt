package com.example.utsmobile

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsmobile.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegisterBinding // inisialisasi activity_register
    lateinit var firebaseAuth: FirebaseAuth // inisialisasi firebase authentication
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Mendapatkan instance Firebase Authentication untuk operasi pendaftaran.
        firebaseAuth = FirebaseAuth.getInstance()

        // Ketika teks “Login” diklik, user akan dipindahkan ke LoginActivity
        binding.txtLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        binding.btnRegister.setOnClickListener {
            // Mengambil Input dari EditText
            val email = binding.editEmail.text.toString()
            val password = binding.editPassword.text.toString()
            val confirmPassword = binding.editConfirmpassword.text.toString()
            // Cek apakah field kosong
            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()) {
                if (password == confirmPassword) {
                    // memanggil firebase untuk membuat akun
                    firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val intent = Intent(this, LoginActivity::class.java)
                                startActivity(intent)
                                // tampilkan pesan toast jika registrasi berhasil
                                Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                // tampilkan pesan toast jika email telah terdaftar
                                Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    // tampilkan pesan toast jika password tidak sesuai
                    Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }
            } else {
                // tampilkan pesan toast jika semua field harus diisi
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
        // Tombol kembali ke main_activity
        val backButton: ImageView = findViewById(R.id.btn_registerBack)
        backButton.setOnClickListener {
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}