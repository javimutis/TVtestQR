package com.example.tvtestqr

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import java.util.*

class MainActivity : FragmentActivity() {

    private lateinit var qrCodeImageView: ImageView
    private lateinit var codeTextView: TextView
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        codeTextView = findViewById(R.id.codeTextView)

        updateQRCodeAndText()
    }

    private fun updateQRCodeAndText() {
        val updateRunnable = object : Runnable {
            override fun run() {
                val code = generateRandomCode()
                val qrCodeBitmap = generateQRCode("https://megago.cl/iniciar?code=$code")
                qrCodeImageView.setImageBitmap(qrCodeBitmap)
                codeTextView.text = code
                handler.postDelayed(this, 15000)
            }
        }
        handler.post(updateRunnable)
    }

    private fun generateRandomCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }

    private fun generateQRCode(text: String): Bitmap {
        val writer = QRCodeWriter()
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400)
        val width = bitMatrix.width
        val height = bitMatrix.height
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bmp
    }

    override fun onDestroy() {
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
