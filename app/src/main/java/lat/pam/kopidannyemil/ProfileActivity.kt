package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        // Informasi akun
        val nameText = findViewById<TextView>(R.id.tvProfileName)
        val usernameText = findViewById<TextView>(R.id.tvProfileUsername)
        val emailText = findViewById<TextView>(R.id.tvProfileEmail)
        val btnLogout = findViewById<Button>(R.id.btnLogout)

        val preferences = getSharedPreferences("UserData", MODE_PRIVATE)

        val name = preferences.getString("name", "Nama Pengguna")
        val username = preferences.getString("username", "username")
        val email = preferences.getString("email", "-")

        nameText.text = name
        usernameText.text = "@$username"
        emailText.text = "Email: $email"

        // Tombol Logout
        btnLogout.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or
                    Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        // Navigasi bawah
        val navHome = findViewById<TextView>(R.id.navHome)
        val navOrder = findViewById<TextView>(R.id.navOrder)
        val navProfile = findViewById<TextView>(R.id.navProfile)

        navHome.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }

        navOrder.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }

        navProfile.setOnClickListener {
            // Tetap di halaman Profile
        }
    }
}
