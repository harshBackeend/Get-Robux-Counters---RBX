package com.gtrbx.gtrbxcounter034.workData

import android.content.Context
import android.text.TextUtils

class SpinBuilder {
    private var spinRowBuilderMKey: String? = null
    private var spinRowBuilderMMode = -1
    private var spinRowBuilderMUseDefault = false
    private var spinRowBuilderMContext: Context? = null

    fun setPrefsName(str: String?): SpinBuilder {
        spinRowBuilderMKey = str
        return this
    }

    fun setContext(context: Context): SpinBuilder {
        spinRowBuilderMContext = context
        return this
    }

    fun setMode(i: Int): SpinBuilder {
        if (i == 0 || i == 1 || i == 2 || i == 4) {
            spinRowBuilderMMode = i
            return this
        }
        throw RuntimeException("Some Error")
    }

    fun setUseDefaultSharedPreference(z: Boolean): SpinBuilder {
        spinRowBuilderMUseDefault = z
        return this
    }

    fun build() {
        if (spinRowBuilderMContext != null) {
            if (TextUtils.isEmpty(spinRowBuilderMKey)) {
                spinRowBuilderMKey = spinRowBuilderMContext!!.packageName
            }
            if (spinRowBuilderMUseDefault) {
                spinRowBuilderMKey += SpinRowPreference.SPIN_ROW_DEFAULT_SUFFIX
            }
            if (spinRowBuilderMMode == -1) {
                spinRowBuilderMMode = 0
            }
            SpinRowPreference.initPrefs(
                spinRowBuilderMContext!!,
                spinRowBuilderMKey!!,
                spinRowBuilderMMode
            )
            return
        }
        throw RuntimeException("instance.")
    }

}