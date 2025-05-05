
```
Nama: Mochammad Raffi Fadhillah
NPM: 23552011066
Kelas: TIF RP 23 CID B
```
# Aplikasi Chat mobile
### Aplikasi ini dikembangkan menggunakan android (kotlin) dan mengimplementasikan 4 jenis activity yang terdiri dari:
1. SplashScreen Activity
2. Login Activity
3. Register Activity
4. List Chating

## Fitur dan penjelasan
### 1. SpashScreen Activity
![splashscreen](https://github.com/user-attachments/assets/35a7f2fb-e502-4a8d-9a95-f5ebc6edc20a)

#### Menampilkan animasi SplashScreen saat aplikasi diluncurkan pertama kali sebelum beralih ke ```activity_main.xml``` (Pilihan untuk Login dan register)
- Durasi tampilan: 1 detik
- Implementasi:
```kotlin
package com.hoshirodesu.aplikasichatmobile

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        // Delay 3 detik, kemudian buka Main_activity
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }, 1000)
    }
}
```

### 2. Login Activity
<img src="https://github.com/user-attachments/assets/e5e6552b-bf65-42c6-b553-8220f2a113eb" alt="login" width="340" height="800">

#### Form autentikasi pengguna menggunakan email dan password
- ```EditText```: untuk input email dan password
- ```login_button```: untuk mengecek validasi pada akun yang telah di register dan jika form-nya kosong
- Implementasi:
