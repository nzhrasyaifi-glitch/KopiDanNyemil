package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.NumberFormat
import java.util.Locale

class OrderConfirmationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_confirmation)

        val productName = findViewById<TextView>(R.id.tvConfirmProduct)
        val quantityText = findViewById<TextView>(R.id.tvConfirmQuantity)
        val totalText = findViewById<TextView>(R.id.tvConfirmTotal)

        val receiverText = findViewById<TextView>(R.id.tvConfirmReceiver)
        val phoneText = findViewById<TextView>(R.id.tvConfirmPhone)
        val addressText = findViewById<TextView>(R.id.tvConfirmAddress)
        val detailText = findViewById<TextView>(R.id.tvConfirmAddressDetail)

        val btnConfirm = findViewById<Button>(R.id.btnConfirmOrder)

        val name = intent.getStringExtra("productName") ?: "Cappuccino"
        val quantity = intent.getIntExtra("quantity", 1)
        val price = intent.getIntExtra("productPrice", 25000)

        val receiver = intent.getStringExtra("receiverName") ?: "-"
        val phone = intent.getStringExtra("phone") ?: "-"
        val address = intent.getStringExtra("address") ?: "-"
        val addressDetail = intent.getStringExtra("addressDetail") ?: "-"

        val total = price * quantity
        val formatter = NumberFormat.getNumberInstance(Locale("in", "ID"))

        productName.text = name
        quantityText.text = "Jumlah: $quantity"
        totalText.text = "Total: Rp ${formatter.format(total)}"

        receiverText.text = "Penerima: $receiver"
        phoneText.text = "Telepon: $phone"
        addressText.text = "Alamat: $address"
        detailText.text = "Patokan: $addressDetail"

        btnConfirm.setOnClickListener {

            val preferences = getSharedPreferences("OrderHistory", MODE_PRIVATE)
            val oldOrders = preferences.getString("orders", "") ?: ""

            val newOrder = listOf(
                name,
                quantity.toString(),
                total.toString(),
                receiver,
                phone,
                address,
                addressDetail
            ).joinToString("|")

            val updatedOrders = if (oldOrders.isEmpty()) {
                newOrder
            } else {
                "$oldOrders\n$newOrder"
            }

            preferences.edit()
                .putString("orders", updatedOrders)
                .apply()

            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
