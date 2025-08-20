package com.gtrbx.gtrbxcounter034.view

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.splashscreen.SplashScreen
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val splashScreen: SplashScreen = installSplashScreen()
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        splashScreen.setKeepOnScreenCondition { true }

        StartApplication.spinRowResponse.observe(this) {
            startActivity(Intent(this@MainActivity, LetStartsActivity::class.java))
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1" && it.first?.spin_small_2 != null) {
                StartApplication.showSpinRowTab(
                    this@MainActivity,
                    it.first?.spin_small_2?.random()?.spin_main_image?.toUri()
                )
                finish()
            } else {
                finish()
            }
        }
    }
}