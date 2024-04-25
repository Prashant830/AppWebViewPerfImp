package com.example.webviewperformanceimporove

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    var WEBVIEW_BASE_URL : String =  "https://main--meek-cuchufli-1de25d.netlify.app/src/html/home.html"
    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val webView = findViewById<WebView>(R.id.web)
        webView.loadUrl(WEBVIEW_BASE_URL)

        // this will enable the javascript.
        webView.getSettings().javaScriptEnabled = true
        webView.getSettings().setSupportZoom(true);
        webView.setWebViewClient(WebViewClient())

        webView.addJavascriptInterface(this, "AndroidInterface");


    }


    @JavascriptInterface
    fun showVideoWithJdkMethod(fileUrl : String) {
        Toast.makeText(this, fileUrl, Toast.LENGTH_SHORT).show()
    }


    @JavascriptInterface
    fun showVideoWithNdkMethod(fileUrl : String) {
        Toast.makeText(this, fileUrl, Toast.LENGTH_SHORT).show()
    }
}