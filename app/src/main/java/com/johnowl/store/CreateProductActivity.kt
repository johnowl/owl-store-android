package com.johnowl.store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_create_product.*

class CreateProductActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        setupControls()
    }

    private fun setupControls() {
        val barcode = intent.getStringExtra(EXTRA_MESSAGE)
        barcodeEditText.append(barcode)
        barcodeEditText.isEnabled = false

        fillCategorySpinner()
        fillBrandSpinner()

        catalogCodeEditText.requestFocus()
    }

    private fun fillCategorySpinner() {
        createSimpleDropdown(arrayOf("Hidratante", "Shampoo", "Sabonete"), categorySpinner)
    }

    private fun fillBrandSpinner() {
        createSimpleDropdown(arrayOf("Natura", "Avon", "Ruby Rose"), brandSpinner)
    }

    private fun createSimpleDropdown(items: Array<String>, spinner: Spinner) {
        val adapter = ArrayAdapter<String>(
            this@CreateProductActivity,
            android.R.layout.simple_spinner_item,
            items
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
    }

    fun saveProductButton(view: View) {
        Toast.makeText(applicationContext, "Ainda não está pronto =/", Toast.LENGTH_SHORT).show()
    }
}