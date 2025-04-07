package com.example.dentaku

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class History : AppCompatActivity() {

    private lateinit var historyListView: ListView
    private lateinit var historyWebView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history)

        historyListView = findViewById(R.id.history_list_view)
        historyWebView = findViewById(R.id.history_webview)

        val sharedPreferences = getSharedPreferences("history_prefs", Context.MODE_PRIVATE)
        val historyUrls = sharedPreferences.getStringSet("history_urls", emptySet())?.toList() ?: emptyList()

        // Log retrieved URLs for verification
        Log.d("HistoryActivity", "Retrieved URLs: $historyUrls")

        val adapter = ArrayAdapter(this, R.layout.list_item_history, R.id.list_item_text, historyUrls)
        historyListView.adapter = adapter

        // Set item click listener to open URL in WebView
        historyListView.setOnItemClickListener { _, _, position, _ ->
            val url = historyUrls[position]
            historyWebView.visibility = View.VISIBLE
            historyWebView.loadUrl(url)
        }

        // Set WebView client to handle URL loading
        historyWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                if (url != null) {
                    view?.loadUrl(url)
                }
                return true
            }
        }
    }

    override fun onBackPressed() {
        if (historyWebView.canGoBack()) {
            historyWebView.goBack()
        } else {
            historyWebView.visibility = View.GONE
            super.onBackPressed()
        }
    }
}
