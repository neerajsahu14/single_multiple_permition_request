package com.example.permitionrequest

import android.graphics.Bitmap
import android.util.Log
import android.webkit.WebView
import com.google.accompanist.web.AccompanistWebViewClient

class CustomWebViewClient : AccompanistWebViewClient() {
    override fun onPageStarted(view: WebView, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        Log.d("Accompanist WebView", "Page started loading for $url")
    }
}