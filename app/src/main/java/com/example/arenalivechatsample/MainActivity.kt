package com.example.arenalivechatsample

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val wvLiveChat: WebView = findViewById(R.id.wvLiveChat)
//        setUpWebView(wvLiveChat)

        startActivity(Intent(this, ActivityChat::class.java))
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setUpWebView(wvLiveChat: WebView) {
        val webSettings = wvLiveChat.settings
        webSettings.javaScriptEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.databaseEnabled = true
        webSettings.cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
        //webSettings.javaScriptCanOpenWindowsAutomatically = false

        wvLiveChat.setWebChromeClient(object : WebChromeClient() {
            override fun onConsoleMessage(consoleMessage: ConsoleMessage): Boolean {
                Log.d("tttt", consoleMessage.message() + " -- From line "
                        + consoleMessage.lineNumber() + " of "
                        + consoleMessage.sourceId())
                return super.onConsoleMessage(consoleMessage)
            }
        })
        wvLiveChat.setWebViewClient(WebViewClient())

        try {
            wvLiveChat.loadUrl("https://go.arena.im/embed/chat/bongo-15773/bongo-15773-global?token=Y2ZhMzBhNDUtN2MxNy00NmQyLTk4M2MtNTc2MTlkOTY0ZDk2")
        } catch (e: Exception) {
            Log.d("tttt", "ex >" + e.message)
        }
    }
}