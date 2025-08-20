package com.gtrbx.gtrbxcounter034.view

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
import com.gtrbx.gtrbxcounter034.databinding.ActivityScratchAndWinBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.view.pop.ScratchDialog
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import `in`.myinnos.androidscratchcard.ScratchCard
import kotlin.random.Random

class ScratchAndWinActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScratchAndWinBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityScratchAndWinBinding.inflate(layoutInflater)
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

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.scratch_win)

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                showData(it.first!!)
            } else {
                binding.layoutBigHolder.visibility = View.GONE
            }
            setPushEvent(it?.first)
            startEvent(it?.first)
            backEvent(it?.first)
        }

        binding.layoutBig.textSub.isSelected = true
    }

    private fun startEvent(first: SpinRowDataHolder?) {
        binding.event.visibility = View.VISIBLE
        binding.event.setScratchDrawable(
            ContextCompat.getDrawable(
                this@ScratchAndWinActivity,
                R.drawable.ic_scratch_card
            )
        )
        binding.event.setOnScratchListener(object : ScratchCard.OnScratchListener {
            override fun onScratch(
                scratchCard: ScratchCard?,
                visiblePercent: Float
            ) {
                if (visiblePercent > 0.5) {

                    binding.event.visibility = View.GONE

                    var rank = UseMe.getDataFromLocal(
                        this@ScratchAndWinActivity,
                        AppDataHolder.AppDataKey.rank
                    )
                    val randomRank = Random.nextInt(50, 250)

                    ScratchDialog.viewScratchDialog(
                        this@ScratchAndWinActivity,
                        randomRank.toString(),
                        object :
                            ScratchDialog.CloseEvent {
                            override fun close() {
                                if (!UseMe.isSpinRowEmptyString(rank)) {
                                    var rankU: Int = rank!!.toInt()
                                    rank = "${rankU + randomRank}"
                                }

                                UseMe.setDataHolder(
                                    this@ScratchAndWinActivity,
                                    AppDataHolder.AppDataKey.rank,
                                    rank
                                )

                                updateRank()
                                if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                                        first.spin_status
                                    ) && first.spin_status == "1"
                                ) {
                                    showData(first)
                                    StartApplication.showSpinRowTab(
                                        this@ScratchAndWinActivity,
                                        first.spin_small_2.random().spin_main_image?.toUri()
                                    )
                                }
                                onBackPressedDispatcher.onBackPressed()
                            }
                        })

                }
            }

        })
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.layoutActionBar.layoutCoinHolder.visibility = View.VISIBLE
        updateRank()
    }

    private fun updateRank() {
        val rank = UseMe.getDataFromLocal(this@ScratchAndWinActivity, AppDataHolder.AppDataKey.rank)
        if (!UseMe.isSpinRowEmptyString(rank)) {
            binding.layoutActionBar.textCoinHolder.text = rank
        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@ScratchAndWinActivity, model, binding.layoutBig)
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
                        this@ScratchAndWinActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}