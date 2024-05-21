package com.example.dentaku

import android.content.Intent
import android.media.MediaPlayer
import android.webkit.WebView
import android.webkit.WebViewClient
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.os.Vibrator
import android.content.Context

class MainActivity : AppCompatActivity() {
    var textview1: TextView? = null
    var lastNumeric: Boolean = false
    var lastDot: Boolean = false

    private lateinit var vibrator: Vibrator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textview1 = findViewById(R.id.textview1)
        val bt1 = findViewById<Button>(R.id.bt1)
        val bt2 = findViewById<Button>(R.id.bt2)
        val bt3 = findViewById<Button>(R.id.bt3)
        val bt4 = findViewById<Button>(R.id.bt4)
        val bt5 = findViewById<Button>(R.id.bt5)
        val bt6 = findViewById<Button>(R.id.bt6)
        val bt7 = findViewById<Button>(R.id.bt7)
        val bt8 = findViewById<Button>(R.id.bt8)
        val bt9 = findViewById<Button>(R.id.bt9)
        val bt0 = findViewById<Button>(R.id.bt0)
        val btdot = findViewById<Button>(R.id.btdot)
        val btnClr = findViewById<Button>(R.id.btn_clr)
        val btnPlus = findViewById<Button>(R.id.btn_plus)
        val btnMuliply = findViewById<Button>(R.id.bt_multiply)
        val btnMinus = findViewById<Button>(R.id.btn_minus)
        val btnDivide = findViewById<Button>(R.id.bt_divide)
        val btnEqual = findViewById<Button>(R.id.btn_equal)

        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        val mediaPlayer = MediaPlayer.create(this, R.raw.retrosound)
        val mediaPlayerClr = MediaPlayer.create(this, R.raw.clr)
        val mediaPlayerEqual = MediaPlayer.create(this, R.raw.equal)
        val mediaPlayerOperator = MediaPlayer.create(this, R.raw.operator)


        //setting onclick listener

        textview1?.setOnLongClickListener {
            // Intent to start the History activity
            val intent = Intent(this, History::class.java)
            startActivity(intent)
            true
        }

        bt1.setOnClickListener {
            txt_append(1)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1) // Vibrate for 5 milliseconds
        }

        bt2.setOnClickListener {
            txt_append(2)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt3.setOnClickListener {
            txt_append(3)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt4.setOnClickListener {
            txt_append(4)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt5.setOnClickListener {
            txt_append(5)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt6.setOnClickListener {
            txt_append(6)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt7.setOnClickListener {
            txt_append(7)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt8.setOnClickListener {
            txt_append(8)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt9.setOnClickListener {
            txt_append(9)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        bt0.setOnClickListener {
            txt_append(0)
            lastNumeric = true
            lastDot = false
            mediaPlayer.start()
            vibrator.vibrate(1)
        }

        btdot.setOnClickListener {
            decimal()
            lastNumeric = false
            lastDot = true
            mediaPlayerOperator.start()
            vibrator.vibrate(3)
        }

        btnPlus.setOnClickListener {
            onOperator("+")
            mediaPlayerOperator.start()
            vibrator.vibrate(3)
        }

        btnDivide.setOnClickListener {
            onOperator("/")
            mediaPlayerOperator.start()
            vibrator.vibrate(3)
        }

        btnMuliply.setOnClickListener {
            onOperator("x")
            mediaPlayerOperator.start()
            vibrator.vibrate(3)
        }

        btnMinus.setOnClickListener {
            onOperator("-")
            mediaPlayerOperator.start()
            vibrator.vibrate(3)
        }

        btnEqual.setOnClickListener {
            onEqual("=")
            vibrator.vibrate(8)
        }

        btnClr.setOnClickListener {
            clear()
            mediaPlayerClr.start()
            vibrator.vibrate(30)
        }
    }

    private fun txt_append(num: Int) {
        textview1?.append(num.toString())
    }

    private fun clear() {
        textview1?.text = ""
    }

    private fun decimal() {
        if (lastNumeric && !lastDot) {
            textview1?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }

    private fun onOperator(view: String) {
        textview1?.text?.let {
            if (lastNumeric && !isOperatorAdded(it.toString())) {
                textview1?.append(view)
                lastNumeric = false
                lastDot = false
            }
        }
    }

    private fun isOperatorAdded(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("x") || value.contains("+") || value.contains("-")
        }
    }

    private fun onEqual(view: String) {
        val mediaPlayerOppai = MediaPlayer.create(this, R.raw.oppai)
        val mediaPlayerClear = MediaPlayer.create(this, R.raw.clr)
        val mediaPlayerEqual = MediaPlayer.create(this, R.raw.equal)

        var tvValue = textview1?.text.toString()
        var prefix = ""
        try {
            //Minus
            if (tvValue.startsWith("-")) {
                prefix = "-"
                tvValue = tvValue.substring(1)
            }
            if (tvValue.contains("-")) {
                val splitValue = tvValue.split("-")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                val result = one.toDouble() - two.toDouble()
                textview1?.text = result.toString()
                if (result == 106.0) {
                    mediaPlayerOppai.start()
                } else {
                    mediaPlayerEqual.start()
                }
            }

            //Plus
            else if (tvValue.contains("+")) {
                val splitValue = tvValue.split("+")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                textview1?.text = (one.toDouble() + two.toDouble()).toString()
                mediaPlayerEqual.start()
            }

            //Divide
            else if (tvValue.contains("/")) {
                val splitValue = tvValue.split("/")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                textview1?.text = (one.toDouble() / two.toDouble()).toString()
                mediaPlayerEqual.start()
            }

            //Multiply
            else if (tvValue.contains("x")) {
                val splitValue = tvValue.split("x")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()) {
                    one = prefix + one
                }

                val result = one.toDouble() * two.toDouble()
                textview1?.text = result.toString()
                mediaPlayerEqual.start()
            }

            // ------------------- NHENTAI WEBVIEW CLIENT -------------------

            val nhentaiBaseUrl = "https://nhentai.to/g/"
            val webView = findViewById<WebView>(R.id.webView)

            webView.webViewClient = object : WebViewClient() {
                override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                    // Load the URL within the WebView
                    if (url != null) {
                        view?.loadUrl(url)
                    }
                    return true
                }
            }

            val btnClear = findViewById<Button>(R.id.btn_clr)

            btnClear.setOnLongClickListener {
                val textContent = textview1?.text.toString()

                if (textContent.isNotBlank() && textContent.any { it.isDigit() }) {
                    // If textContent is not null, not empty, and contains integers, add it to the URL
                    vibrator.vibrate(10)
                    val nhentaiUrl = nhentaiBaseUrl + textContent
                    webView.loadUrl(nhentaiUrl)
                    webView.visibility = View.VISIBLE
                } else {
                    vibrator.vibrate(30)
                    mediaPlayerClear.start()
                }
                true
            }
        } catch (e: ArithmeticException) {
            e.printStackTrace()
        }
    }

    // Define the onBackPressed method here
    override fun onBackPressed() {
        val webView = findViewById<WebView>(R.id.webView)
        if (webView.canGoBack()) {
            webView.goBack()
        } else {
            webView.visibility = View.GONE
        }
    }
}
