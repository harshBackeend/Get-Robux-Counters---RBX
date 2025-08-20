package com.gtrbx.gtrbxcounter034.view

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityInfoBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.pop.ExitDialog
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import kotlin.jvm.java

class InfoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityInfoBinding
    private var imageInfo: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityInfoBinding.inflate(layoutInflater)
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

        showInfo(imageInfo)
        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                binding.layoutBottomHolder.visibility = View.VISIBLE
                showData(it.first!!)
            } else {
                binding.layoutBottomHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent(it?.first)
        }

        binding.layoutBottom.textSub.isSelected = true
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.buttonNext.setOnClickListener {
            if (imageInfo == 1) {
                imageInfo = 2
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    showData(first)
                    StartApplication.showSpinRowTab(
                        this@InfoActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                showInfo(imageInfo)
                binding.layoutScroll.scrollTo(0, 0)
            } else if (imageInfo == 2) {
                imageInfo = 3
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    showData(first)
                    StartApplication.showSpinRowTab(
                        this@InfoActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                showInfo(imageInfo)
                binding.layoutScroll.scrollTo(0, 0)
            } else if (imageInfo == 3) {
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    showData(first)
                    StartApplication.showSpinRowTab(
                        this@InfoActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                imageInfo = 4
                showInfo(imageInfo)
                binding.layoutScroll.scrollTo(0, 0)
            } else {
                startActivity(Intent(this@InfoActivity, DashBordActivity::class.java))
                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    StartApplication.showSpinRowTab(
                        this@InfoActivity,
                        first.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }
        }
    }

    private fun showInfo(info: Int) {
        binding.apply {
            when (info) {
                1 -> {
                    imageHolder.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@InfoActivity,
                            R.drawable.ic_info_1
                        )
                    )
                    textTitle.text =
                        getString(R.string.spin_wheel_and_get_rewards_easy_to_use_robux_spin_wheel)
                    textSub.text =
                        getString(R.string.welcome_to_free_robux_spin_wheel_are_you_eager_to_the_for_thrills_of_authentic_spin_wheel_fresh_from_the_robux_slot_machine_for_the_game)
                    textSub.textAlignment = View.TEXT_ALIGNMENT_CENTER
                }

                2 -> {
                    imageHolder.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@InfoActivity,
                            R.drawable.ic_info_2
                        )
                    )
                    textTitle.text = getString(R.string.scratch_and_win_rbx_coins)
                    textSub.text =
                        getString(R.string.unlock_new_scratch_cards_daily_and_experience_the_joy_of_winning_robux_rewards_are_you_ready_for_an_exciting_adventure_start_scratching_now_and_make_every_scratch_count)
                    textSub.textAlignment = View.TEXT_ALIGNMENT_CENTER
                }

                3 -> {
                    imageHolder.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@InfoActivity,
                            R.drawable.ic_info_3
                        )
                    )
                    textTitle.text = getString(R.string.robux_calc_quiz_master)
                    textSub.text =
                        getString(R.string.get_free_robux_counts_and_to_get_them_more_into_the_game_and_know_the_actual_robux_gratis_and_the_actual_robux_value_and_codes_as_well_if_you_get_the_correct_answer_you_can_raise_the_level_every_time_the_level_goes_up_we_give_you_a_gift)
                    textSub.textAlignment = View.TEXT_ALIGNMENT_CENTER
                }

                4 -> {
                    imageHolder.setImageDrawable(
                        ContextCompat.getDrawable(
                            this@InfoActivity,
                            R.drawable.ic_info_4
                        )
                    )
                    textTitle.text = getString(R.string.robx_calculators_features)
                    textSub.text =
                        getString(R.string.rbx_free_calculator_usd_to_rbx_converter_fast_robux_counter_rbx_to_usd_calc_tbc_to_rbx_converter_bc_to_rbx_converter_obc_to_rbx_converter)
                    textSub.textAlignment = View.TEXT_ALIGNMENT_VIEW_START
                }
            }
        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@InfoActivity, model, binding.layoutBottom)
        } else {
            binding.layoutBottomHolder.visibility = View.GONE
        }
    }

    private fun backEvent(first: SpinRowDataHolder?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (imageInfo == 4) {
                    imageInfo = 3
                    showInfo(imageInfo)
                    binding.layoutScroll.scrollTo(0, 0)
                } else if (imageInfo == 3) {
                    imageInfo = 2
                    showInfo(imageInfo)
                    binding.layoutScroll.scrollTo(0, 0)
                } else if (imageInfo == 2) {
                    imageInfo = 1
                    showInfo(imageInfo)
                    binding.layoutScroll.scrollTo(0, 0)
                } else {
                    ExitDialog.viewExitDialog(this@InfoActivity, object : ExitDialog.DialogEvent {
                        override fun exit() {
                            finishAffinity()
                        }

                        override fun rate() {
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

                    })
                }
            }

        }

        onBackPressedDispatcher.addCallback(this, back)
    }
}