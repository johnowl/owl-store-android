package com.johnowl.store.app.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock
import android.view.View
import com.johnowl.store.R
import com.johnowl.store.barcode.BarcodeScannerActivity
import com.johnowl.store.product.ui.CreateProductActivity

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun goToCreateProductActivity(view: View) {
        val intent = Intent(this, CreateProductActivity::class.java).apply {
            putExtra(AlarmClock.EXTRA_MESSAGE, "")
        }
        startActivity(intent)
        finish()
    }

    fun goToBarcodeScannerActivity(view: View) {
        val intent = Intent(this, BarcodeScannerActivity::class.java)
        startActivity(intent)
        finish()
    }
}