package com.sadostrich.foodgawker

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.support.customtabs.CustomTabsClient

class FoodGawkerViewModel(application: Application) : AndroidViewModel(application) {
    val foodGawkerDomain = "foodgawker.com"
    val foodGawkerUrl = "https://$foodGawkerDomain/"

    init {
        val connection = FgCustomTabsServiceConnection()
        val ok = CustomTabsClient.bindCustomTabsService(application, "com.android.chrome", connection)
        if (!ok) {
            connection.onServiceDisconnected(null)
        }
    }
}
