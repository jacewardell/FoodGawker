package com.sadostrich.foodgawker

import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.support.annotation.RequiresApi
import android.support.customtabs.CustomTabsIntent
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient

class FoodGawkerWebViewClient(val domain: String?, val listener: WebViewClientCallbacks) : WebViewClient() {

    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        val uri = Uri.parse(url)
        return handleUri(view, uri)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return handleUri(view, request?.url)
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        listener.onPageStarted()
        super.onPageStarted(view, url, favicon)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        listener.onPageFinished()
        super.onPageFinished(view, url)
    }

    private fun handleUri(view: WebView?, uri: Uri?): Boolean {
        return if (uri?.host?.equals(domain) == true) {
            view?.loadUrl(uri.toString())
            false
        } else {
            openInCustomTabs(view, uri)
            true
        }
    }

    private fun openInCustomTabs(view: WebView?, url: Uri?) {
        val builder = CustomTabsIntent.Builder()
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(view?.context, url)
    }
}