package com.example.testsmartapps

import android.os.Bundle
import android.os.PersistableBundle
import android.webkit.CookieManager
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.example.testsmartapps.databinding.WebviewLayoutBinding


class WebViewActivity : AppCompatActivity() {
    private lateinit var binding: WebviewLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = WebviewLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding.webview) {
            webViewClient = WebViewClient()
            loadUrl("https://www.google.com")
            settings.javaScriptEnabled = true
            settings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        }
        CookieManager.getInstance().setAcceptCookie(true)
    }

    override fun onBackPressed() {
        if (binding.webview.canGoBack()) {
            binding.webview.goBack()
        } else {
            super.onBackPressed()
        }

    }
}