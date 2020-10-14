package com.johnowl.store.app.ui

import android.app.ProgressDialog
import android.opengl.Visibility
import android.view.Gravity
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.johnowl.store.R

abstract class BaseActivity: AppCompatActivity()  {


    fun displayProgressBar(isVisible: Boolean) {
        val progressBar = findViewById<ProgressBar>(R.id.progressbar)
        progressBar.visibility = if (isVisible) {
            View.VISIBLE
        } else {
            View.GONE
        }
    }

    fun displayError(message: String?) {
        val toast = Toast.makeText(this, message ?: "Unknown error", Toast.LENGTH_LONG)
        val view = toast.view

        val text = view.findViewById<View>(android.R.id.message) as TextView
        text.setTextColor(resources.getColor(R.color.errorText))
        text.setShadowLayer(0f, 0f, 0f, 0)

        view.setBackgroundResource(R.color.errorBackground)

        toast.setGravity(Gravity.FILL_HORIZONTAL, 0, 0)
        toast.setMargin(10F, 10F)
        toast.show()
    }
}