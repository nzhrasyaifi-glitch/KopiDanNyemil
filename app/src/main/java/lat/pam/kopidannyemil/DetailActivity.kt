package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class DetailActivity : AppCompatActivity() {

    private var quantity = 1
    private var productPrice = 25000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val productName = findViewById<TextView>(R.id.tvProductName)
        val productEmoji = findViewById<TextView>(R.id.tvProductEmoji)
        val priceText = findViewById<TextView>(R.id.tvProductPrice)
        val quantityText = findViewById<TextView>(R.id.tvQuantity)
        val totalText = findViewById<TextView>(R.id.tvTotalPrice)

        val btnMinus = findViewById<Button>(R.id.btnMinus)
        val btnPlus = findViewById<Button>(R.id.btnPlus)
        val btnOrder = findViewById<Button>(R.id.btnOrder)

        productName.text = intent.getStringExtra("productName") ?: "Cappuccino"
        productEmoji.text = intent.getStringExtra("productEmoji") ?: "☕"
        productPrice = intent.getIntExtra("productPrice", 25000)

        fun formatRupiah(amount: Int): String {
            val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))
            return "Rp ${formatter.format(amount)}"
        }

        fun updateTotal() {
            quantityText.text = quantity.toString()
            priceText.text = formatRupiah(productPrice)
            totalText.text = "Total: ${formatRupiah(productPrice * quantity)}"
        }

        btnPlus.setOnClickListener {
            quantity++
            updateTotal()
        }

        btnMinus.setOnClickListener {
            if (quantity > 1) {
                quantity--
                updateTotal()
            }
        }

        btnOrder.setOnClickListener {
            val intent = Intent(this, ShippingAddressActivity::class.java)
            intent.putExtra("productName", productName.text.toString())
            intent.putExtra("productPrice", productPrice)
            intent.putExtra("quantity", quantity)
            startActivity(intent)
        }

        updateTotal()
    }
}
