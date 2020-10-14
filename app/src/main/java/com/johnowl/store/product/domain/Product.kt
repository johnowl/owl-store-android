package com.johnowl.store.product.domain

data class Product(
    val id: String,
    val barcode: String,
    val catalogCode: String,
    val category: String,
    val brand: String
)