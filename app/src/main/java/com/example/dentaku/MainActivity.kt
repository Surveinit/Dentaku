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


class MainActivity : AppCompatActivity() {
    var textview1:TextView ?= null
    var lastNumeric:Boolean = false
    var lastDot:Boolean = false



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

        val mediaPlayer = MediaPlayer.create(this, R.raw.retrosound)
        val mediaPlayerClr = MediaPlayer.create(this, R.raw.clr)
        var mediaPlayerEqual = MediaPlayer.create(this, R.raw.equal)
        val mediaPlayerOperator = MediaPlayer.create(this, R.raw.operator)


        //setting onclick listener
        bt1.setOnClickListener(){
            txt_append(1)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt2.setOnClickListener(){
            txt_append(2)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt3.setOnClickListener(){
            txt_append(3)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt4.setOnClickListener(){
            txt_append(4)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt5.setOnClickListener(){
            txt_append(5)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt6.setOnClickListener(){
            txt_append(6)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt7.setOnClickListener(){
            txt_append(7)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt8.setOnClickListener(){
            txt_append(8)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt9.setOnClickListener(){
            txt_append(9)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        bt0.setOnClickListener(){
            txt_append(0)
            lastNumeric = true
            lastDot = false
            mediaPlayer?.start()
        }

        btdot.setOnClickListener(){
            decimal()
            lastNumeric = false
            lastDot = true
            mediaPlayerOperator?.start()
        }

        btnPlus.setOnClickListener(){
            onOperator("+")
            mediaPlayerOperator?.start()
        }

        btnDivide.setOnClickListener(){
            onOperator("/")
            mediaPlayerOperator?.start()

        }

        btnMuliply.setOnClickListener(){
            onOperator("x")
            mediaPlayerOperator?.start()

        }

        btnMinus.setOnClickListener(){
            onOperator("-")
            mediaPlayerOperator?.start()

        }

        btnEqual.setOnClickListener(){
            onEqual("=")


        }





        btnClr.setOnClickListener(){
            clear()
            mediaPlayerClr?.start()

        }



    }

    private fun txt_append(num:Int){
       // var textview1 = findViewById<TextView>(R.id.textview1)
        textview1?.append(num.toString())

    }

    private fun clear(){
       // var textview1 = findViewById<TextView>(R.id.textview1)
        textview1?.text=""
    }

    fun decimal(){
        if (lastNumeric && !lastDot){
           // var textview1 = findViewById<TextView>(R.id.textview1)
            textview1?.append(".")
            lastNumeric = false
            lastDot = true
        }
    }


    //Arithmetic operations

    private fun onOperator(view: String){
        textview1?.text?.let {
            if(lastNumeric && !isOperatorAdd(it.toString())){
                textview1?.append((view))
                lastNumeric = false
                lastDot = false
            }
        }
    }

    fun isOperatorAdd(value: String):Boolean{
        return if (value.startsWith("-")){
            false
        }else{
            value.contains("/")
                    || value.contains("x")
                    || value.contains("+")
                    || value.contains("-")
        }
    }

    fun onEqual(view: String){
        val mediaPlayerOppai = MediaPlayer.create(this, R.raw.oppai)
        val mediaPlayerClear = MediaPlayer.create(this, R.raw.clr)
        val mediaPlayerEqual = MediaPlayer.create(this, R.raw.equal)

        var tvValue = textview1?.text.toString()
        var prefix = ""
        try {


            //Minus
            if (tvValue.startsWith("-")){
                prefix = "-"
                tvValue = tvValue.substring((1))
            }
            if (tvValue.contains("-")) {
                val splitValue = tvValue.split("-")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }

                var result = one.toDouble() - two.toDouble()

                textview1?.text = (result).toString()
                if (result == 106.0){
                    mediaPlayerOppai?.start()
                }else{
                    mediaPlayerEqual.start()
                }
            }


            //Plus
            else if (tvValue.contains("+")) {
                val splitValue = tvValue.split("+")

                var one = splitValue[0]
                var two = splitValue[1]

                if (prefix.isNotEmpty()){
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

                if (prefix.isNotEmpty()){
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

                if (prefix.isNotEmpty()){
                    one = prefix + one
                }

                var result = one.toDouble() * two.toDouble()

                textview1?.text = (result).toString()

                mediaPlayerEqual.start()


//                // TODO: Solve this bytch
//                val btnClear = findViewById<Button>(R.id.btn_clr)
//                val mediaPlayerClear = MediaPlayer.create(this, R.raw.clr)
//
//                // Nhentai integration
//                btnClear.setOnLongClickListener() {
//                    val resultValue = textview1?.text?.toString()
//
//                    if (resultValue != null && resultValue.isNotEmpty()) {
//                        val baseUrl = "https://nhentai.to/g/"
//                        val url = baseUrl + resultValue
//                        val i = Intent(Intent.ACTION_VIEW)
//                        i.data = Uri.parse(url)
//                        startActivity(i)
//                    } else {
//                        mediaPlayerClear.start()
//                    }
//                    true
//                }






            }

            // NHENTAI WEBVIEW CLIENT

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

            val textview1 = findViewById<TextView>(R.id.textview1)
            val btnClear = findViewById<Button>(R.id.btn_clr)
            val mediaPlayerClear = MediaPlayer.create(this, R.raw.clr)

            btnClear.setOnLongClickListener {
                val textContent = textview1.text.toString()

                if (!textContent.isNullOrBlank() && textContent.any { it.isDigit() }) {
                    // If textContent is not null, not empty, and contains integers, add it to the URL
                    val nhentaiUrl = nhentaiBaseUrl + textContent
                    webView.loadUrl(nhentaiUrl)
                    webView.visibility = View.VISIBLE
                } else {
                    mediaPlayerClear.start()
                }
                true
            }




        }catch (e: ArithmeticException){
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

    //Sound one


}