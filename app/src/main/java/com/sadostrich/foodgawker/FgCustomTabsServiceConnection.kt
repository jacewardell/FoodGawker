package com.sadostrich.foodgawker

import android.content.ComponentName
import android.support.customtabs.CustomTabsClient
import android.support.customtabs.CustomTabsServiceConnection

class FgCustomTabsServiceConnection : CustomTabsServiceConnection() {
    private var client: CustomTabsClient? = null

    override fun onCustomTabsServiceConnected(name: ComponentName?, client: CustomTabsClient?) {
        client?.warmup(0)
    }

    override fun onServiceDisconnected(name: ComponentName?) {
        client = null
    }
}