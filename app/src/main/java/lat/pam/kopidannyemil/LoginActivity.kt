package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val username = findViewById<EditText>(R.id.etUsername)
        val password = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLoginUser)

        btnLogin.setOnClickListener {
            val inputUsername = username.text.toString().trim()
            val inputPassword = password.text.toString()

            if (inputUsername.isEmpty() || inputPassword.isEmpty()) {
                Toast.makeText(
                    this,
                    "Username dan password harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val preferences = getSharedPreferences("UserData", MODE_PRIVATE)

                val savedUsername = preferences.getString("username", null)
                val savedPassword = preferences.getString("password", null)

                if (inputUsername == savedUsername &&
                    inputPassword == savedPassword
                ) {
                    Toast.makeText(
                        this,
                        "Login berhasil!",
                        Toast.LENGTH_SHORT
                    ).show()

                    val intent = Intent(this, HomeActivity::class.java)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(
                        this,
                        "Username atau password salah. Silakan daftar terlebih dahulu.",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}
