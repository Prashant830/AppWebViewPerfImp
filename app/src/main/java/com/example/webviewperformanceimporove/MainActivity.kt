package com.example.webviewperformanceimporove

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.webkit.ConsoleMessage
import android.webkit.JavascriptInterface
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nativelib.NativeLib


class MainActivity : AppCompatActivity() {

    private var WEBVIEW_BASE_URL : String =  "https://main--meek-cuchufli-1de25d.netlify.app/src/html/home.html"
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
        webView.setWebViewClient(object : WebViewClient() {
            fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d("WebViewConsole", consoleMessage.message()) // Print message to Android Logcat
                return true
            }
        })

        webView.addJavascriptInterface(this, "AndroidInterface");


    }


    @JavascriptInterface
    fun showVideoWithJdkMethod(fileUrl: String) {
        val iterations = 10000000 // Adjust the number of iterations as needed
        var totalDuration = 0L
        repeat(iterations) {
            val startTime = System.currentTimeMillis()
            // Perform the operation here
            val endTime = System.currentTimeMillis()
            val duration = endTime - startTime
            totalDuration += duration
        }
        val averageDuration = totalDuration / iterations.toDouble()
        val formattedDuration = String.format("%.10f", averageDuration) // Adjust precision as needed
        Log.d("NativeLib", "log message: average duration: $formattedDuration ms")
        Toast.makeText(this, "log message: average duration in normal JDk or JAVA code in same method: $formattedDuration ms", Toast.LENGTH_SHORT).show()

    }



    @JavascriptInterface
    fun showVideoWithNdkMethod(fileUrl : String) {

        // show log
        val averageDuration =  NativeLib.showLog(fileUrl)


        val formattedDuration = String.format("%.10f", averageDuration)

        // show Toast
        NativeLib.showToast(this,"log message: average duration in JNI or NDK or CPP code in same method: $formattedDuration ms")

        // move goalClass (intent)
        NativeLib.callIntentMethod(this,"com/example/webviewperformanceimporove/GoalClass")

    }
}