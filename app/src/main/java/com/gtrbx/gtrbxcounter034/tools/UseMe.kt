package com.gtrbx.gtrbxcounter034.tools

import android.app.Activity
import android.app.Application
import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder

object UseMe {

    fun isSpinRowEmptyString(s: String?): Boolean {
        return s == null || s.trim() == "" || s.isEmpty() || s == "null" || s.length == 0
    }

    fun Context.isSpinRowConnectingToInternet(): Boolean {
        val connectivityManager =
            getSystemService(Application.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network: Network? = connectivityManager.activeNetwork
        if (network != null) {
            val capabilities = connectivityManager.getNetworkCapabilities(network)
            return capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(
                NetworkCapabilities.TRANSPORT_CELLULAR
            ))
        }
        return false
    }

    fun getDataFromLocal(activity: Context, valueKey: String?): String? {
        val dataHolder =
            activity.getSharedPreferences(AppDataHolder.AppDataKey.mainKey, Context.MODE_PRIVATE)
        return dataHolder.getString(valueKey, "")
    }

    fun setDataHolder(activity: Context,valueKey: String?,value: String?){
        val dataHolder = activity.getSharedPreferences(AppDataHolder.AppDataKey.mainKey, Context.MODE_PRIVATE)
        val dataAdd = dataHolder.edit()
        dataAdd.putString(valueKey,value)
        dataAdd.apply()
    }

    fun View.hideKeyboardFrom(activity: Activity) {
        val imm = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(this.windowToken, 0)
    }
}