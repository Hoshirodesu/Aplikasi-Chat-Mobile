```
Nama: Mochammad Raffi Fadhillah
NPM: 23552011066
Kelas: TIF RP 23 CID B
```
# Aplikasi Chat mobile
![splashscreen](https://github.com/user-attachments/assets/9a2dfcf0-6aff-46aa-834b-c91c73ba2a55) | ![mainActivity](https://github.com/user-attachments/assets/4f732193-f148-41ae-8241-ebed65744026) | ![login](https://github.com/user-attachments/assets/3f1310b9-e5b3-41e4-bbf8-b4547dad84ea) | ![register](https://github.com/user-attachments/assets/bc1df954-295d-4bb7-ae9f-a98325aa6bf5) | ![home](https://github.com/user-attachments/assets/a7793e32-b61a-43c3-84eb-9e6e3558e8ae)
|:-------------------------------:|:-------------------------------:|:-------------------------------:|:-------------------------------:|:-------------------------------:|

### Aplikasi ini dikembangkan menggunakan android (kotlin) dan mengimplementasikan 4 jenis activity yang terdiri dari:
1. SplashScreen Activity
2. Login Activity
3. Register Activity
4. List Chating

## Fitur dan penjelasan
### 1. SplashScreen Activity
![splashscreen](https://github.com/user-attachments/assets/35a7f2fb-e502-4a8d-9a95-f5ebc6edc20a)

#### Menampilkan animasi SplashScreen saat aplikasi diluncurkan pertama kali sebelum beralih ke ```activity_main.xml``` (Pilihan untuk Login dan register)
- Durasi tampilan: 1 detik
- Implementasi SpashActivity.kt:
```kotlin
package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_splash)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // Delay 3 detik, kemudian buka Main_activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}
```

### 2. Login Activity
<img src="https://github.com/user-attachments/assets/e5e6552b-bf65-42c6-b553-8220f2a113eb" alt="login" width="340" height="760">

#### Form autentikasi pengguna menggunakan email dan password
- ```EditText```: untuk input email dan password
- ```login_button```: untuk mengecek validasi pada akun yang telah di register dan jika form-nya kosong
- implementasi LoginActivity.kt:
```kotlin
package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsmobile.databinding.ActivityLoginBinding
import com.google.firebase.auth.FirebaseAuth

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
```

### 3. Register Activity
<img src="https://github.com/user-attachments/assets/1f481481-97ac-4711-a976-6ff68ddbbac4" alt="login" width="340" height="760">

#### Form pendaftaran untuk akun baru dengan Email, Password, dan ConfirmPassword
- semua textfield wajib diisi termasuk Email, Password, dan ConfirmPassword
<img src="https://github.com/user-attachments/assets/8abf9ca5-2ec5-497d-9429-069f47d16ce3" alt="toast" width="260" height="100">

- Event log:
```kotlin
2025-05-07 21:27:49.935 25252-25252 RegisterActivity        com.example.utsmobile                D  setOnClickListener: Semua field harus diisi
```
- Jika password tidak sesuai maka akan muncul pesan toast “password tidak sesuai”
<img src="https://github.com/user-attachments/assets/2b904cb7-4ab4-445d-832f-012c572ae0db" alt="toast1" width="260" height="100">

- Event log:
```kotlin
2025-05-07 21:41:02.024 28927-28927 RegisterActivity        com.example.utsmobile                D  setOnClickListener: Password tidak sesuai
```
- Jika email sudah terdaftar maka akan muncul pesan toast “email sudah terdaftar”
<img src="https://github.com/user-attachments/assets/508c6224-1cb2-429a-a0c9-9b01834a20d7" alt="toast2" width="260" height="100">

- Event log:
```kotlin
2025-05-07 21:50:08.614 28927-28927 RegisterActivity        com.example.utsmobile                D  setOnClickListener: Email sudah terdaftar
```
- Jika sudah register akan langsung masuk ke LoginActivity, lalu muncul pesan toast “registrasi berhasil”
<img src="https://github.com/user-attachments/assets/0755300d-a214-4ba7-95d7-472be20011c4" alt="toast3" width="260" height="100">

- Event log:
```kotlin
2025-05-07 21:45:49.144 28927-28927 RegisterActivity        com.example.utsmobile                D  setOnClickListener: register berhasil
```
- Data yang sudah di register akan disimpan di firebase server
- Di bagian RegisterActivity terdapat beberapa objek yaitu TextView, EditText, Button, dan ImageView
- Implementasi RegisterActivity:
```kotlin 
package com.example.utsmobile

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.utsmobile.databinding.ActivityRegisterBinding
import com.google.firebase.auth.FirebaseAuth

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
            Log.d("LoginActivity", "setOnClickListener: berhasil ke menu Login")
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
                                Log.d("RegisterActivity", "setOnClickListener: register berhasil")
                                Toast.makeText(this, "Registrasi berhasil", Toast.LENGTH_SHORT)
                                    .show()
                            } else {
                                // tampilkan pesan toast jika email telah terdaftar
                                Log.d("RegisterActivity", "setOnClickListener: Email sudah terdaftar")
                                Toast.makeText(this, "Email sudah terdaftar", Toast.LENGTH_SHORT)
                                    .show()
                            }
                        }
                } else {
                    // tampilkan pesan toast jika password tidak sesuai
                    Log.d("RegisterActivity", "setOnClickListener: Password tidak sesuai")
                    Toast.makeText(this, "Password tidak sesuai", Toast.LENGTH_SHORT).show()
                }
            } else {
                // tampilkan pesan toast jika semua field harus diisi
                Log.d("RegisterActivity", "setOnClickListener: Semua field harus diisi")
                Toast.makeText(this, "Semua field harus diisi", Toast.LENGTH_SHORT).show()
            }
        }
        // Tombol kembali ke main_activity
        val backButton: ImageView = findViewById(R.id.btn_registerBack)
        backButton.setOnClickListener {
            Log.d("RegisterActivity", "setOnClickListener: Berhasil kembali ke MainActivity")
            finish()
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}
```

### 4. List Chating
<img src="https://github.com/user-attachments/assets/03ac7bf4-793d-467e-8895-eb25fd8b5a16" alt="home" width="340" height="760">

#### Menampilkan daftar percakapan menggunakan ```RecyclerView``` dengan GridLayoutManager, dan memanfaatkan Glide untuk memuat gambar avatar
-  Berisi list objek Chat berisi nama kontak dan pesan terakhir
-  Implementasi HomeActivity.kt:
```kotlin
package com.example.utsmobile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Chat"
        // mengganti warna toolbar menu menjadi warna putih
        toolbar.overflowIcon?.let { drawable ->
            val wrapped = DrawableCompat.wrap(drawable)
            DrawableCompat.setTint(wrapped.mutate(), Color.WHITE)
            toolbar.overflowIcon = wrapped
        }

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = GridLayoutManager(this, 1) // Mengatur RecyclerView menampilkan item dalam satu kolom
        recyclerView.setHasFixedSize(true) // Memberi tahu bahwa ukuran RecyclerView tidak berubah berdasarkan konten
        // menyimpan daftar itemList yang berisi objek ItemList (judul, subJudul, iamgeUrl)
        val itemList = listOf(
            ItemList("Vivian Banshee", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/3/31/Avatar_Vivian_Banshee.png/revision/latest?cb=20250423022832"),
            ItemList("Hoshimi Miyabi", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/7/79/Avatar_Hoshimi_Miyabi.png/revision/latest?cb=20241220224623"),
            ItemList("Tsukishiro Yanagi", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/a/a3/Avatar_Tsukishiro_Yanagi.png/revision/latest?cb=20241117203653"),
            ItemList("Astra Yao", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/2/27/Avatar_Astra_Yao.png/revision/latest?cb=20250123000846"),
            ItemList("Evelyn Chevalier", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/9/94/Avatar_Evelyn_Chevalier.png/revision/latest?cb=20250212183017"),
            ItemList("Zhu Yuan", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/5/53/Avatar_Zhu_Yuan.png/revision/latest?cb=20241117203557"),
            ItemList("Jane Doe", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/5/59/Avatar_Jane_Doe.png/revision/latest?cb=20241117203544"),
            ItemList("Ellen Joe", "Lorem ipsum dolor sit amet",
                "https://static.wikia.nocookie.net/zenless-zone-zero/images/2/21/Avatar_Ellen_Joe.png/revision/latest?cb=20241117203511"),

        )
        val adapter = AdapterList(itemList)
        recyclerView.adapter = adapter
    }

    // Menu Toolbar (Logout)
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate menu_main.xml ke dalam toolbar
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_logout -> {
                showLogoutConfirmation()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
    // AlertDialog Konfirmasi Logout
    private fun showLogoutConfirmation() {
        AlertDialog.Builder(this)
            .setTitle("Konfirmasi Logout")
            .setMessage("Apakah Anda yakin ingin logout?")
            .setPositiveButton("Ya") { dialog, _ ->
                dialog.dismiss()
                performLogout()
            }
            .setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            .setCancelable(true)
            .show()
    }

    // Proses Logout
    private fun performLogout() {
        FirebaseAuth.getInstance().signOut()
        // Kembali ke LoginActivity
        val intent = Intent(this, LoginActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }
}
```

- ```AdapterList.kt```: bertanggung jawab menghubungkan data model ItemList ke tampilan item pada RecyclerView
- Implementasi ```AdapterList.kt```:
```kotlin 
package com.example.utsmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.NonNull
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdapterList (private val itemList: kotlin.collections.List<ItemList>):RecyclerView.Adapter<AdapterList.ViewHolder>() {
    // Menyimpan referensi ke view pada layout item_data.xml
    class ViewHolder (@NonNull itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView. findViewById(R.id.item_image)
        val judul: TextView = itemView.findViewById(R.id.title)
        val subJudul: TextView = itemView.findViewById(R.id.sub_title)
    }
    // Membuat instance ViewHolder baru saat RecyclerView membutuhkan tampilan baru
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterList.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_data, parent, false)
        return ViewHolder (view)
    }
    // Mengikat (bind) data dari itemList[position] ke tampilan di holder.
    override fun onBindViewHolder(holder: AdapterList.ViewHolder, position: Int) {
        val item = itemList [position]
        holder. judul.text = item.judul
        holder.subJudul.text = item.subJudul
        Glide.with(holder.imageView.context).load(item.imageUrl).into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
}
```

- ```ItemList.kt```:  data class yang berfungsi sebagai model data untuk setiap item yang terdiri dari judul, subJudul, dan imageUrl
- Implementasi ```ItemList.kt```:
```kotlin
package com.example.utsmobile

// Untuk menyimpan data judul, subJudul, dan imageUrl
data class ItemList (
    var judul: String,
    var subJudul: String,
    var imageUrl: String
)
```

## Library yang digunakan:
| Library      | Fungsi |
| --------------- | ------ |
| Lottiefiles      | Untuk membuat animasi Splash screen   |
| Firebase         | Untuk operasi autentikasi login dan register dan menyimpan datanya di server   |
| Glide        | Untuk memuat gambar (imageUrl) lebih cepat dan efisien  |
| Material Components for Android        | Untuk implementasi komponen UI dari Material Design   |

## Tools yang digunakan:
| Tools      | Fungsi |
| --------------- | ------ |
| Figma     | Untuk membuat desain/mockup UI aplikasi yang dibuat   |
| Android Studio         | IDE untuk mengembangkan aplikasi android   |

