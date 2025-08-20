package com.gtrbx.gtrbxcounter034.view

import android.content.Intent
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
import com.gtrbx.gtrbxcounter034.databinding.ActivityTipsTricksDetailBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class TipsTricksDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTipsTricksDetailBinding

    private var displayTitle: String? = null
    private var displaySubTitle: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTipsTricksDetailBinding.inflate(layoutInflater)
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

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.tips_amp_tricks)


        getDataFromOtherScreen()

        setDataInDisplay()

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                binding.layoutBottomHolder.visibility = View.VISIBLE
                showData(it.first!!)
            } else {
                binding.layoutBottomHolder.visibility = View.GONE
            }
            setPushEvent()
            backEvent(it?.first)
        }

        binding.layoutBottom.textSub.isSelected = true
    }

    private fun setPushEvent() {
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.imageShare.setOnClickListener {
            val intent: Intent = Intent().setAction(Intent.ACTION_SEND)
            intent.setType("text/plain")
            intent.putExtra(Intent.EXTRA_TEXT, "$displayTitle\n$displaySubTitle")
            startActivity(Intent.createChooser(intent, "Share via"))
        }
    }

    private fun setDataInDisplay() {
        if (!UseMe.isSpinRowEmptyString(displayTitle) && !UseMe.isSpinRowEmptyString(displaySubTitle)) {
            binding.textTitle.text = displayTitle
            binding.textSub.text = displaySubTitle
        }
    }

    private fun getDataFromOtherScreen() {
        try {
            if (intent != null) {
                displayTitle = intent.getStringExtra(AppDataHolder.AppDataKey.DISPLAY_NAME)
                displaySubTitle = intent.getStringExtra(AppDataHolder.AppDataKey.DISPLAY_SUB_NAME)
            }
        } catch (_: Exception) {

        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(
                this@TipsTricksDetailActivity,
                model,
                binding.layoutBottom
            )
        } else {
            binding.layoutBottomHolder.visibility = View.GONE
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
                        this@TipsTricksDetailActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}