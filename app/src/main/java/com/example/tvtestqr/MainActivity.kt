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

// La clase MainActivity hereda de FragmentActivity, lo que nos permite usar Fragmentos
class MainActivity : FragmentActivity() {

    // Declaro variables para manejar la imagen del código QR y el texto del código
    private lateinit var qrCodeImageView: ImageView
    private lateinit var codeTextView: TextView
    // Handler para manejar la ejecución diferida y en el hilo principal
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Establezco el layout que se mostrará en esta actividad
        setContentView(R.layout.activity_main)

        // Inicializo las variables para la imagen del QR y el texto con los componentes de la UI
        qrCodeImageView = findViewById(R.id.qrCodeImageView)
        codeTextView = findViewById(R.id.codeTextView)

        // Llamo a la función que actualiza el código QR y el texto
        updateQRCodeAndText()
    }

    // Función para actualizar el código QR y el texto que se muestra junto a él
    private fun updateQRCodeAndText() {
        // Defino un Runnable que se ejecutará cada cierto tiempo
        val updateRunnable = object : Runnable {
            override fun run() {
                // Genero un nuevo código aleatorio
                val code = generateRandomCode()
                // Genero el bitmap del código QR a partir del código
                val qrCodeBitmap = generateQRCode("https://megago.cl/iniciar?code=$code")
                // Actualizo la imagen del QR y el texto en la UI
                qrCodeImageView.setImageBitmap(qrCodeBitmap)
                codeTextView.text = code
                // Reprogramo el Runnable para que se ejecute nuevamente después de un tiempo
                handler.postDelayed(this, 15000)
            }
        }
        // Ejecuto el Runnable por primera vez
        handler.post(updateRunnable)
    }

    // Función para generar un código aleatorio de 8 caracteres alfanuméricos
    private fun generateRandomCode(): String {
        val chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        return (1..8)
            .map { chars.random() }
            .joinToString("")
    }

    // Función para generar un bitmap del código QR a partir de un texto
    private fun generateQRCode(text: String): Bitmap {
        val writer = QRCodeWriter()
        // Creo una matriz de bits para el QR a partir del texto
        val bitMatrix = writer.encode(text, BarcodeFormat.QR_CODE, 400, 400)
        // Defino el ancho y alto para el bitmap
        val width = bitMatrix.width
        val height = bitMatrix.height
        // Creo un bitmap y lo relleno con los bits de la matriz para formar el QR
        val bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565)
        for (x in 0 until width) {
            for (y in 0 until height) {
                bmp.setPixel(x, y, if (bitMatrix[x, y]) Color.BLACK else Color.WHITE)
            }
        }
        return bmp
    }

    // onDestroy se llama cuando se destruye la actividad
    override fun onDestroy() {
        // Elimino todos los callbacks y mensajes que puedan estar en cola en el Handler
        handler.removeCallbacksAndMessages(null)
        super.onDestroy()
    }
}
