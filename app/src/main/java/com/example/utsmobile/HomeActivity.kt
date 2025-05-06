package com.example.utsmobile

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.annotation.NonNull
import androidx.core.graphics.drawable.DrawableCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth


class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_home)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Chat"
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