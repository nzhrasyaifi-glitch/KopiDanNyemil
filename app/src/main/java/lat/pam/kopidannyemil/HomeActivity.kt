package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        // Menampilkan nama pengguna
        val greeting = findViewById<TextView>(R.id.tvHomeGreeting)

        val preferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val name = preferences.getString("name", "Pengguna")

        greeting.text = "Halo, $name!"

        // Tombol Profil Saya
        val btnProfile = findViewById<Button>(R.id.btnOpenProfile)

        btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Navigasi bawah
        val navHome = findViewById<TextView>(R.id.navHome)
        val navOrder = findViewById<TextView>(R.id.navOrder)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        navHome.setOnClickListener {
            // Tetap di halaman Home
        }

        navOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        // Daftar produk
        val cappuccino = findViewById<TextView>(R.id.productCappuccino)
        val latte = findViewById<TextView>(R.id.productLatte)
        val americano = findViewById<TextView>(R.id.productAmericano)
        val croissant = findViewById<TextView>(R.id.productCroissant)
        val roti = findViewById<TextView>(R.id.productRoti)

        fun openDetail(name: String, emoji: String, price: Int) {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra("productName", name)
            intent.putExtra("productEmoji", emoji)
            intent.putExtra("productPrice", price)
            startActivity(intent)
        }

        cappuccino.setOnClickListener {
            openDetail("Cappuccino", "☕", 25000)
        }

        latte.setOnClickListener {
            openDetail("Cafe Latte", "☕", 23000)
        }

        americano.setOnClickListener {
            openDetail("Americano", "☕", 20000)
        }

        croissant.setOnClickListener {
            openDetail("Croissant", "🥐", 18000)
        }

        roti.setOnClickListener {
            openDetail("Roti Bakar", "🍞", 15000)
        }
    }
}
