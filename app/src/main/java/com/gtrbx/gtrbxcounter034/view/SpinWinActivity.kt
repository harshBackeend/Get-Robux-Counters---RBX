package com.gtrbx.gtrbxcounter034.view

import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.DecelerateInterpolator
import android.view.animation.RotateAnimation
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivitySpinWinBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class SpinWinActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpinWinBinding
    private val part: Array<Int> = arrayOf(0, 108, 200, 120, 350, 0, 408, 408, 440, 460, 0, 308)
    private val partSize: IntArray = IntArray(part.size)
    private val gamete = java.util.Random()
    private var partAgel: Int = 0
    private var isRunning = false

    private val kalakInMinits = 24 * 60 * 60 * 1000L
    private var todaySpinCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpinWinBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        // Show the system bars.
        windowInsetsController.show(WindowInsetsCompat.Type.systemBars())
        WindowCompat.getInsetsController(window, window.decorView).isAppearanceLightStatusBars =
            true

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.spin_win)

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                showData(it.first!!)
            } else {
                binding.layoutBigHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent(it?.first)
        }
        setSpinCount()
        getAgal()

        binding.layoutBig.textSub.isSelected = true
    }

    private fun setSpinCount() {
        val localData = getSharedPreferences(AppDataHolder.AppDataKey.mainKey, MODE_PRIVATE)
        val todayOpen = localData.getLong(AppDataHolder.AppDataKey.today_click, 0L)
        val chaluTime = System.currentTimeMillis()
        val todaySpin =
            UseMe.getDataFromLocal(applicationContext, AppDataHolder.AppDataKey.spin_count)

        todaySpinCount = try {
            todaySpin!!.toInt()
        } catch (_: Exception) {
            0
        }

        if (chaluTime - todayOpen >= kalakInMinits) {
            todaySpinCount = 9
            UseMe.setDataHolder(
                applicationContext,
                AppDataHolder.AppDataKey.spin_count,
                todaySpinCount.toString()
            )
            binding.textFreeSpin.text = getString(R.string.free_spin, todaySpinCount.toString())
            localData.edit().putLong(AppDataHolder.AppDataKey.today_click, chaluTime).apply()
        } else {
            binding.textFreeSpin.text = getString(R.string.free_spin, todaySpinCount.toString())
        }
    }

    private fun getAgal() {
        val rankAgal = 360 / part.size

        for (i in part.indices) {
            partSize[i] = (i + 1) * rankAgal
        }
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonSpinNow.setOnClickListener {
            if (!isRunning && todaySpinCount > 0) {
                todaySpinCount--
                binding.textFreeSpin.text = getString(R.string.free_spin, todaySpinCount.toString())
                val localData = getSharedPreferences(AppDataHolder.AppDataKey.mainKey, MODE_PRIVATE)
                val chaluTime = System.currentTimeMillis()
                localData.edit().putLong(AppDataHolder.AppDataKey.today_click, chaluTime).apply()
                UseMe.setDataHolder(
                    this@SpinWinActivity,
                    AppDataHolder.AppDataKey.spin_count,
                    todaySpinCount.toString()
                )
                running(first)
                isRunning = true
            } else {
                Toast.makeText(
                    this@SpinWinActivity,
                    getString(R.string.please_spin_tomorrow), Toast.LENGTH_LONG
                )
                    .show()
            }
        }

    }

    private fun running(first: SpinRowDataHolder?) {
        partAgel = gamete.nextInt(part.size - 1)
        val circleEffect = RotateAnimation(
            0f,
            ((360 * part.size) + partSize[partAgel]).toFloat(),
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f,
            RotateAnimation.RELATIVE_TO_SELF,
            0.5f
        )
        circleEffect.duration = 4000
        circleEffect.fillAfter = true
        circleEffect.interpolator = DecelerateInterpolator()
        circleEffect.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                var rank = UseMe.getDataFromLocal(
                    this@SpinWinActivity,
                    AppDataHolder.AppDataKey.rank
                )

                if (!UseMe.isSpinRowEmptyString(rank)) {
                    var rankU: Int = rank!!.toInt()
                    rankU = rankU + part[part.size - (partAgel + 1)]
                    UseMe.setDataHolder(
                        this@SpinWinActivity,
                        AppDataHolder.AppDataKey.rank,
                        rankU.toString()
                    )
                    if (part[part.size - (partAgel + 1)] > 0) {
                        Toast.makeText(
                            this@SpinWinActivity,
                            getString(
                                R.string.robox_added,
                                part[part.size - (partAgel + 1)].toString()
                            ),
                            Toast.LENGTH_SHORT
                        ).show()
                    } else {
                        Toast.makeText(
                            this@SpinWinActivity,
                            getString(R.string.spin_again),
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                            first.spin_status
                        ) && first.spin_status == "1"
                    ) {
                        StartApplication.showSpinRowTab(
                            this@SpinWinActivity,
                            first.spin_small_2.random().spin_main_image?.toUri()
                        )
                    }
                    updateRank()
                }

                isRunning = false
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

        binding.imageSpin.startAnimation(circleEffect)

    }

    override fun onResume() {
        super.onResume()
        binding.layoutActionBar.layoutCoinHolder.visibility = View.VISIBLE
        updateRank()
    }

    private fun updateRank() {
        val rank = UseMe.getDataFromLocal(this@SpinWinActivity, AppDataHolder.AppDataKey.rank)
        if (!UseMe.isSpinRowEmptyString(rank)) {
            binding.layoutActionBar.textCoinHolder.text = rank
        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@SpinWinActivity, model, binding.layoutBig)
        } else {
            binding.layoutBigHolder.visibility = View.GONE
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
                        this@SpinWinActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}