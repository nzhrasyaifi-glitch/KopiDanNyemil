package lat.pam.kopidannyemil

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class OrderActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

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
            // Tetap di halaman Order
        }

        navProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        val content = findViewById<LinearLayout>(R.id.orderContent)

        val preferences = getSharedPreferences("OrderHistory", MODE_PRIVATE)
        val orders = preferences.getString("orders", "") ?: ""

        if (orders.isEmpty()) {
            addText(content, "Belum ada riwayat pesanan.")
        } else {
            val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))

            orders.split("\n").reversed().forEachIndexed { index, order ->
                val data = order.split("|")

                if (data.size == 7) {
                    val card = LinearLayout(this)
                    card.orientation = LinearLayout.VERTICAL
                    card.setPadding(20, 20, 20, 20)

                    val background = GradientDrawable()
                    background.setColor(Color.WHITE)
                    background.cornerRadius = 20f
                    background.setStroke(1, Color.rgb(235, 224, 211))
                    card.background = background

                    val title = TextView(this)
                    title.text = data[0]
                    title.textSize = 19f
                    title.setTypeface(null, Typeface.BOLD)
                    title.setTextColor(Color.rgb(73, 50, 34))
                    card.addView(title)

                    val status = TextView(this)
                    status.text = "Pesanan berhasil dikonfirmasi"
                    status.textSize = 12f
                    status.setTextColor(Color.rgb(65, 125, 75))
                    status.setPadding(0, 8, 0, 12)
                    card.addView(status)

                    addDetail(card, "Jumlah pesanan", data[1])
                    addDetail(card, "Total harga", "Rp ${formatter.format(data[2].toLongOrNull() ?: 0)}")

                    val separator = android.view.View(this)
                    separator.setBackgroundColor(Color.rgb(235, 224, 211))
                    val separatorParams = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, 1
                    )
                    separatorParams.topMargin = 14
                    separatorParams.bottomMargin = 14
                    card.addView(separator, separatorParams)

                    addDetail(card, "Nama penerima", data[3])
                    addDetail(card, "Nomor telepon", data[4])
                    addDetail(card, "Alamat", data[5])
                    addDetail(card, "Patokan", data[6])

                    val params = LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                    )
                    params.bottomMargin = 16
                    content.addView(card, params)
                }
            }
        }
    }

    private fun addDetail(parent: LinearLayout, label: String, value: String) {
        val text = TextView(this)
        text.text = "$label: $value"
        text.textSize = 14f
        text.setTextColor(Color.rgb(105, 87, 72))
        text.setPadding(0, 5, 0, 5)
        parent.addView(text)
    }

    private fun addText(parent: LinearLayout, message: String) {
        val text = TextView(this)
        text.text = message
        text.textSize = 15f
        text.gravity = Gravity.CENTER
        text.setTextColor(Color.rgb(128, 107, 90))
        text.setPadding(10, 30, 10, 30)
        parent.addView(text)
    }
}
