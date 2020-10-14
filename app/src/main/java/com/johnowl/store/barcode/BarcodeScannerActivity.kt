package com.johnowl.store.barcode

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.util.Log
import android.util.SparseArray
import android.view.SurfaceHolder
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.util.isNotEmpty
import com.google.android.gms.vision.CameraSource
import com.google.android.gms.vision.Detector
import com.google.android.gms.vision.barcode.Barcode
import com.google.android.gms.vision.barcode.BarcodeDetector
import com.johnowl.store.product.ui.CreateProductActivity
import com.johnowl.store.R
import kotlinx.android.synthetic.main.activity_barcode_scanner.*

class BarcodeScannerActivity : AppCompatActivity() {

    private val requestCodeCameraPermission = 1001
    private lateinit var cameraSource: CameraSource
    private lateinit var barcodeDetector: BarcodeDetector
    private var receivedCode = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_barcode_scanner)

        if (cameraPermissionWasGranted()) {
            setupControls()
        } else {
            askForCameraPermission()
        }
    }

    private fun setupControls() {
        barcodeDetector = BarcodeDetector.Builder(this@BarcodeScannerActivity).build()
        barcodeDetector.setProcessor(barcodeProcessor)

        cameraSource = CameraSource.Builder(this@BarcodeScannerActivity, barcodeDetector)
            .setAutoFocusEnabled(true)
            .build()
        cameraSurfaceView.holder.addCallback(surfaceCallback)
    }

    private fun cameraPermissionWasGranted() =
        ContextCompat.checkSelfPermission(
            this@BarcodeScannerActivity,
            Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED

    private fun askForCameraPermission() {
        ActivityCompat.requestPermissions(
            this@BarcodeScannerActivity,
            arrayOf(Manifest.permission.CAMERA),
            requestCodeCameraPermission
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == requestCodeCameraPermission && grantResults.isNotEmpty()) {
            setupControls()
        } else {
            val message = "Permission denied."
            Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
        }
    }

    private val surfaceCallback = object: SurfaceHolder.Callback {

        @SuppressLint("MissingPermission")
        override fun surfaceCreated(surfaceHolder: SurfaceHolder?) {
            try {
                cameraSource.start(surfaceHolder)
            } catch (ex: Exception) {
                val message = "Something went wrong."
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        }

        override fun surfaceChanged(surfaceHolder: SurfaceHolder?, p1: Int, p2: Int, p3: Int) {

        }

        override fun surfaceDestroyed(surfaceHolder: SurfaceHolder?) {
            cameraSource.stop()
        }

    }

    private val barcodeProcessor = object: Detector.Processor<Barcode> {

        override fun release() {
            cameraSource.release()
            barcodeDetector.release()
        }

        override fun receiveDetections(detections: Detector.Detections<Barcode>?) {
            if (!receivedCode && detections != null && detections.detectedItems.isNotEmpty()) {
                receivedCode = true
                val codes: SparseArray<Barcode> = detections.detectedItems
                val code = codes.valueAt(0).displayValue
                Log.v("BARCODE_PROCESSOR", "Code found: $code")
                startCreateProductActivity(code)
            } else {
                Log.v("BARCODE_PROCESSOR", "Code not found")
            }
        }

    }

    private fun startCreateProductActivity(barcode: String) {
        val intent = Intent(this, CreateProductActivity::class.java).apply {
            putExtra(EXTRA_MESSAGE, barcode)
        }
        startActivity(intent)
        finish()
    }
}