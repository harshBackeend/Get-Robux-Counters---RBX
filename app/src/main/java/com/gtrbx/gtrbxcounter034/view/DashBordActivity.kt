package com.gtrbx.gtrbxcounter034.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityDashBordBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.pop.ExitDialog
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import kotlin.jvm.java

class DashBordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDashBordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDashBordBinding.inflate(layoutInflater)
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
                binding.layoutBigTopHolder.visibility = View.VISIBLE
                binding.layoutBigBottomHolder.visibility = View.VISIBLE
                showData(it.first!!)
            } else {
                binding.layoutBigTopHolder.visibility = View.GONE
                binding.layoutBigBottomHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent()
        }

        binding.layoutBigTop.textSub.isSelected = true
        binding.layoutBigBottom.textSub.isSelected = true
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.apply {

            binding.imageDollar.setOnClickListener { binding.layoutRTD.performClick() }

            binding.layoutRTD.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ConverterActivity::class.java).apply {
                    putExtra(
                        AppDataHolder.AppDataKey.DISPLAY_NAME,
                        AppDataHolder.DisplayName.rbx_spin_do
                    )
                })
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutDTR.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ConverterActivity::class.java).apply {
                    putExtra(
                        AppDataHolder.AppDataKey.DISPLAY_NAME,
                        AppDataHolder.DisplayName.do_spin_rbx
                    )
                })
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutScratch.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ScratchAndWinActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutBC.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ConverterActivity::class.java).apply {
                    putExtra(
                        AppDataHolder.AppDataKey.DISPLAY_NAME,
                        AppDataHolder.DisplayName.bc_spin
                    )
                })
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutTbc.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ConverterActivity::class.java).apply {
                    putExtra(
                        AppDataHolder.AppDataKey.DISPLAY_NAME,
                        AppDataHolder.DisplayName.tbc_spin
                    )
                })
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutObc.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, ConverterActivity::class.java).apply {
                    putExtra(
                        AppDataHolder.AppDataKey.DISPLAY_NAME,
                        AppDataHolder.DisplayName.obx_spin
                    )
                })
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutQuiz.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, QuizStartActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutSpinWheels.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, SpinWinActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutMeme.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, MemesActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutTAT.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, TipsTricksActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.layoutDictionary.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, DictionaryActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.imageDictionary.setOnClickListener {
                binding.layoutDictionary.performClick()
            }

            binding.layoutSetting.setOnClickListener {
                startActivity(Intent(this@DashBordActivity, SettingActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@DashBordActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
            }

            binding.imageSetting.setOnClickListener {
                binding.layoutSetting.performClick()
            }

        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@DashBordActivity, model, binding.layoutBigTop)
            SmallView2.smallViewFunctionality(this@DashBordActivity, model, binding.layoutBigBottom)
        } else {
            binding.layoutBigTopHolder.visibility = View.GONE
            binding.layoutBigBottomHolder.visibility = View.GONE
        }
    }

    override fun onResume() {
        super.onResume()
        val rank = UseMe.getDataFromLocal(this@DashBordActivity, AppDataHolder.AppDataKey.rank)
        if (!UseMe.isSpinRowEmptyString(rank)) {
            binding.textCoinHolder.text = rank
        }
    }

    private fun backEvent() {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                ExitDialog.viewExitDialog(this@DashBordActivity, object : ExitDialog.DialogEvent {
                    override fun exit() {
                        finishAffinity()
                    }

                    override fun rate() {
                        try {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW,
                                    Uri.parse("market://details?id=$packageName")
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

                })
            }

        }

        onBackPressedDispatcher.addCallback(this, back)
    }
}