package com.gtrbx.gtrbxcounter034.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityLetStartsBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import kotlin.jvm.java

class LetStartsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLetStartsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLetStartsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val windowInsetsController =
            WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView)
            .isAppearanceLightStatusBars = true

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                showData(it.first!!)
            } else {
                binding.bottomImage.visibility = View.GONE
            }
            setPushEvent(it?.first)
        }
    }

    private fun showData(model: SpinRowDataHolder) {
        if (model.spin_small_2 != null) {
            binding.bottomImage.setOnClickListener {
                StartApplication.showSpinRowTab(
                    this@LetStartsActivity,
                    model.spin_small_2.random().spin_main_image?.toUri()
                )
            }
        }

        if (model.spin_small != null) {
            binding.bottomImage.visibility = View.VISIBLE
            Glide.with(binding.bottomImage)
                .load(model.spin_small.random().spin_main_image)
                .into(binding.bottomImage)
        } else {
            binding.bottomImage.visibility = View.GONE
        }
    }

    private fun setPushEvent(model: SpinRowDataHolder?) {

        binding.buttonLetStart.setOnClickListener {
            if (model != null && model.spin_small_2 != null && !UseMe.isSpinRowEmptyString(model.spin_status) && model.spin_status == "1") {
                startActivity(Intent(this@LetStartsActivity, InfoActivity::class.java))
                StartApplication.showSpinRowTab(
                    this@LetStartsActivity,
                    model.spin_small_2.random().spin_main_image?.toUri()
                )
            } else {
                startActivity(Intent(this@LetStartsActivity, DashBordActivity::class.java))
            }
        }

        binding.buttonRateApp.setOnClickListener {
            try {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(getString(R.string.market_details_id, packageName))
                    )
                )
            } catch (e: ActivityNotFoundException) {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(
                            getString(
                                R.string.https_play_google_com_store_apps_details_id,
                                packageName
                            ))
                    )
                )
            }
        }

        binding.buttonShareApp.setOnClickListener {
            val intent = Intent()
            intent.setAction("android.intent.action.SEND")
            intent.putExtra(
                Intent.EXTRA_TEXT,
                getString(
                    R.string.check_out_the_app_at_https_play_google_com_store_apps_details_id,
                    packageName
                )
            )
            intent.setType("text/plain")
            startActivity(intent)
        }

        binding.buttonPrivacyPolicy.setOnClickListener {
            if (model != null && !UseMe.isSpinRowEmptyString(model.spin_pcs)) {
                try {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(model.spin_pcs))
                    startActivity(intent)
                } catch (e: Exception) {

                }
            } else {
                Toast.makeText(
                    this@LetStartsActivity,
                    getString(R.string.please_on_internet),
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}