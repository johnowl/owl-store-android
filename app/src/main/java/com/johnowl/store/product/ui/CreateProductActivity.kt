package com.johnowl.store.product.ui

import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.johnowl.store.R
import com.johnowl.store.app.ui.BaseActivity
import com.johnowl.store.product.domain.Brand
import com.johnowl.store.product.domain.Category
import com.johnowl.store.product.domain.ProductService
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_create_product.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject


@AndroidEntryPoint
class CreateProductActivity: BaseActivity() {

    @Inject
    lateinit var productService: ProductService

    private var hasError: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_product)
        setupControls()
    }

    private fun setupControls() {
        addProductButton.isEnabled = false
        displayProgressBar(true)
        fillBrandSpinner()
        fillCategorySpinner()
        displayProgressBar(false)
        addProductButton.isEnabled = !hasError

        val barcode = intent.getStringExtra(EXTRA_MESSAGE) ?: ""

        if (barcode.isEmpty()) {
            barcodeEditText.requestFocus()
        } else {
            barcodeEditText.append(barcode)
            barcodeEditText.isEnabled = false

            catalogCodeEditText.requestFocus()
        }
    }

    private fun fillCategorySpinner() {
        val categories = listCategories()
        val items = categories.map { it.name }.toTypedArray()
        createSimpleDropdown(items, categorySpinner)
    }

    private fun listCategories(): List<Category> {
        return try {
            val categories: List<Category>
            runBlocking {
                withContext(Dispatchers.IO) {
                    categories = productService.listCategories()
                }
            }
            categories
        } catch (e: Exception) {
            hasError = true
            displayError(e.message)
            emptyList()
        }
    }

    private fun listBrands(): List<Brand> {
        return try {
            val brands: List<Brand>
            runBlocking {
                withContext(Dispatchers.IO) {
                    brands = productService.listBrands()
                }
            }
            brands
        } catch (e: Exception) {
            displayError(e.message)
            hasError = true
            emptyList()
        }
    }

    private fun fillBrandSpinner() {
        val brands = listBrands()
        val items = brands.map { it.name }.toTypedArray()
        createSimpleDropdown(items, brandSpinner)
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
        displayError("Not implemented")
    }
}