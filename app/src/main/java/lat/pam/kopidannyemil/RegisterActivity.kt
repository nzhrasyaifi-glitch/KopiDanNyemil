package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val name = findViewById<EditText>(R.id.etName)
        val username = findViewById<EditText>(R.id.etRegUsername)
        val email = findViewById<EditText>(R.id.etEmail)
        val password = findViewById<EditText>(R.id.etRegPassword)
        val btnRegister = findViewById<Button>(R.id.btnSubmitRegister)

        btnRegister.setOnClickListener {
            val userName = name.text.toString().trim()
            val userUsername = username.text.toString().trim()
            val userEmail = email.text.toString().trim()
            val userPassword = password.text.toString()

            if (userName.isEmpty() || userUsername.isEmpty() ||
                userEmail.isEmpty() || userPassword.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Semua kolom harus diisi!",
                    Toast.LENGTH_SHORT
                ).show()

            } else {
                val preferences = getSharedPreferences("UserData", MODE_PRIVATE)

                preferences.edit()
                    .putString("name", userName)
                    .putString("username", userUsername)
                    .putString("email", userEmail)
                    .putString("password", userPassword)
                    .apply()

                Toast.makeText(
                    this,
                    "Pendaftaran berhasil!",
                    Toast.LENGTH_SHORT
                ).show()

                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
