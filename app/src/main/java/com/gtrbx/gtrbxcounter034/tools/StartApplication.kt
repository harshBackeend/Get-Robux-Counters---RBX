package com.gtrbx.gtrbxcounter034.tools

import android.annotation.SuppressLint
import android.app.Application
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.lifecycle.MutableLiveData
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue
import com.gtrbx.gtrbxcounter034.tools.UseMe.isSpinRowConnectingToInternet
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinBuilder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class StartApplication : Application() {

    private lateinit var spinRowDataBase: FirebaseDatabase

    private lateinit var spinDataBaseReference: DatabaseReference

    companion object {
        var spinRowResponse = MutableLiveData<Pair<SpinRowDataHolder?, Boolean>>()

        fun showSpinRowTab(mContext: Context, uri: Uri?) {
            val builder = CustomTabsIntent.Builder()
            builder.setShowTitle(true)
            val customTabsIntent = builder.build()

            @SuppressLint("IntentReset")
            val browserIntent = Intent()
                .setAction(Intent.ACTION_VIEW)
                .addCategory(Intent.CATEGORY_BROWSABLE)
                .setType("text/plain")
                .setData(Uri.fromParts("http", "", null))
            var possibleBrowsers: List<ResolveInfo>
            possibleBrowsers = mContext.packageManager.queryIntentActivities(
                browserIntent,
                PackageManager.MATCH_DEFAULT_ONLY
            )
            if (possibleBrowsers.isEmpty()) {
                possibleBrowsers = mContext.packageManager.queryIntentActivities(
                    browserIntent,
                    PackageManager.MATCH_ALL
                )
            }
            try {
                if (possibleBrowsers.isNotEmpty()) {
                    customTabsIntent.intent.setPackage(possibleBrowsers[0].activityInfo.packageName)
                    customTabsIntent.launchUrl(mContext, uri!!)
                } else {
                    val browserIntent2 = Intent(Intent.ACTION_VIEW, uri)
                    mContext.startActivity(browserIntent2)
                }
            } catch (e: ActivityNotFoundException) {

            }
        }
    }

    override fun onCreate() {
        super.onCreate()

        FirebaseApp.initializeApp(this)

        spinRowDataBase = FirebaseDatabase.getInstance()
        spinDataBaseReference = spinRowDataBase.getReference("spin_row")

        SpinBuilder()
            .setContext(this)
            .setMode(0)
            .setPrefsName(packageName)
            .setUseDefaultSharedPreference(true)
            .build()

        val getRank = UseMe.getDataFromLocal(applicationContext, AppDataHolder.AppDataKey.rank)
        if (UseMe.isSpinRowEmptyString(getRank)) {
            UseMe.setDataHolder(applicationContext, AppDataHolder.AppDataKey.rank, "0")
        }

        val todaySpinCount =
            UseMe.getDataFromLocal(applicationContext, AppDataHolder.AppDataKey.spin_count)
        if (UseMe.isSpinRowEmptyString(todaySpinCount)) {
            UseMe.setDataHolder(applicationContext, AppDataHolder.AppDataKey.spin_count, "9")
        }

        if (isSpinRowConnectingToInternet()) {
            getDataFromRemote()
        } else {
            spinRowResponse.postValue(
                Pair(
                    null,
                    false
                )
            )
        }
    }

    private fun getDataFromRemote() {
        spinDataBaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val remoteData: SpinRowDataHolder? = try {
                    snapshot.getValue<SpinRowDataHolder>()
                } catch (e: Exception) {
                    null
                }
                spinRowResponse.postValue(Pair(remoteData, remoteData != null))
            }

            override fun onCancelled(error: DatabaseError) {
                spinRowResponse.postValue(Pair(null, false))
            }

        })
    }
}