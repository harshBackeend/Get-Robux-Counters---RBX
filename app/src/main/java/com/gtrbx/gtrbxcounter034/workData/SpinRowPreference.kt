package com.gtrbx.gtrbxcounter034.workData

import android.content.Context
import android.content.SharedPreferences

class SpinRowPreference {

    companion object {
        const val SPIN_ROW_DEFAULT_SUFFIX = "spin_row_preferences"
        var spinRowMPrefs: SharedPreferences? = null
        fun initPrefs(context: Context, str: String, i: Int) {
            spinRowMPrefs = context.getSharedPreferences(str, i)
        }
    }
}