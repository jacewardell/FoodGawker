package com.sadostrich.foodgawker

import android.annotation.SuppressLint
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.webkit.WebView
import kotlinx.android.synthetic.main.activity_food_gawker.*

interface WebViewClientCallbacks {
    fun onPageStarted()
    fun onPageFinished()
}

class FoodGawkerActivity : AppCompatActivity(), WebViewClientCallbacks {
    private lateinit var viewModel: FoodGawkerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_food_gawker)

        viewModel = ViewModelProviders.of(this).get(FoodGawkerViewModel::class.java)
        setupWebView()
    }

    override fun onPageStarted() {
        loadingProgressBar.visibility = View.VISIBLE
    }

    override fun onPageFinished() {
        loadingProgressBar.visibility = View.GONE
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupWebView() {
        WebView.setWebContentsDebuggingEnabled(true)
        webview.settings.javaScriptEnabled = true
        setupWebViewClient()
        webview.loadUrl(viewModel.foodGawkerUrl)
    }

    private fun setupWebViewClient() {
        webview.webViewClient = FoodGawkerWebViewClient(viewModel.foodGawkerDomain, this)
    }

    override fun onBackPressed() {
        webview.goBack()
    }
}
