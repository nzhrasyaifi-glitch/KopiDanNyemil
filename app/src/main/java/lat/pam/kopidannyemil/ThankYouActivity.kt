package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ThankYouActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_thank_you)

        val greeting = findViewById<TextView>(R.id.tvThankYouGreeting)
        val btnDone = findViewById<Button>(R.id.btnThankYouDone)

        val preferences = getSharedPreferences("UserData", MODE_PRIVATE)
        val name = preferences.getString("name", "Pengguna")

        greeting.text = "Halo, $name!"

        btnDone.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
            finish()
        }
    }
}
