package lat.pam.kopidannyemil

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ShippingAddressActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shipping_address)

        val receiverName = findViewById<EditText>(R.id.etReceiverName)
        val phone = findViewById<EditText>(R.id.etPhone)
        val address = findViewById<EditText>(R.id.etAddress)
        val addressDetail = findViewById<EditText>(R.id.etAddressDetail)
        val btnSave = findViewById<Button>(R.id.btnSaveAddress)

        val productName = intent.getStringExtra("productName") ?: "Cappuccino"
        val quantity = intent.getIntExtra("quantity", 1)
        val price = intent.getIntExtra("productPrice", 25000)

        btnSave.setOnClickListener {
            val name = receiverName.text.toString().trim()
            val phoneNumber = phone.text.toString().trim()
            val fullAddress = address.text.toString().trim()
            val detail = addressDetail.text.toString().trim()

            if (name.isEmpty() || phoneNumber.isEmpty() ||
                fullAddress.isEmpty() || detail.isEmpty()
            ) {
                Toast.makeText(
                    this,
                    "Semua data alamat harus diisi",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                val intent = Intent(this, OrderConfirmationActivity::class.java)

                intent.putExtra("receiverName", name)
                intent.putExtra("phone", phoneNumber)
                intent.putExtra("address", fullAddress)
                intent.putExtra("addressDetail", detail)

                intent.putExtra("productName", productName)
                intent.putExtra("quantity", quantity)
                intent.putExtra("productPrice", price)

                startActivity(intent)
            }
        }
    }
}
