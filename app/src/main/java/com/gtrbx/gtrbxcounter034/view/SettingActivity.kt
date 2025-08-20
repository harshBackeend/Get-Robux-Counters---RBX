package com.gtrbx.gtrbxcounter034.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivitySettingBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        enableEdgeToEdge()
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

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.setting)

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                showData(it.first!!)
            } else {
                binding.layoutBigHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent(it?.first)
        }

        binding.layoutBig.textSub.isSelected = true

    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@SettingActivity, model, binding.layoutBig)
        } else {
            binding.layoutBigHolder.visibility = View.GONE
        }
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.layoutRateApp.setOnClickListener {
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

        binding.imageRateApp.setOnClickListener {
            binding.layoutRateApp.performClick()
        }

        binding.layoutShareApp.setOnClickListener {
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

        binding.imageShareApp.setOnClickListener {
            binding.layoutShareApp.performClick()
        }

        binding.layoutPrivacyPolicy.setOnClickListener {
            if (first != null && !UseMe.isSpinRowEmptyString(first.spin_pcs)) {
                try {
                    val intent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(first.spin_pcs))
                    startActivity(intent)
                } catch (e: Exception) {

                }
            }else{
                Toast.makeText(this@SettingActivity,
                    getString(R.string.please_on_internet), Toast.LENGTH_SHORT).show()
            }
        }

        binding.imagePrivacyPolicy.setOnClickListener {
            binding.layoutPrivacyPolicy.performClick()
        }

    }

    private fun backEvent(holder: SpinRowDataHolder?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (holder != null && holder.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                        holder.spin_status
                    ) && holder.spin_status == "1" && holder.spin_small_2.isNotEmpty()
                ) {
                    StartApplication.showSpinRowTab(
                        this@SettingActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}