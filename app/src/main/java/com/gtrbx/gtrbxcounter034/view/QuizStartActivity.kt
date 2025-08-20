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
import com.gtrbx.gtrbxcounter034.databinding.ActivityQuizStartBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import kotlin.jvm.java

class QuizStartActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuizStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizStartBinding.inflate(layoutInflater)
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

        binding.layoutActionBar.textDisplayTitle.text = getString(R.string.robox_quiz)

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
        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.buttonStartNow.setOnClickListener {
            startActivity(Intent(this, QuizEndActivity::class.java))
            if (first != null && first.spin_small_2 != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                StartApplication.showSpinRowTab(
                    this@QuizStartActivity,
                    first.spin_small_2.random().spin_main_image?.toUri()
                )
            }
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        binding.layoutActionBar.layoutCoinHolder.visibility = View.VISIBLE
        updateRank()
    }

    private fun updateRank() {
        val rank = UseMe.getDataFromLocal(this@QuizStartActivity, AppDataHolder.AppDataKey.rank)
        if (!UseMe.isSpinRowEmptyString(rank)) {
            binding.layoutActionBar.textCoinHolder.text = rank
            binding.textCoinNumber.text = getString(R.string._6000, rank)

            try {
                val fillRate = (rank!!.toInt() / 6000) * 100
                binding.progressBar.progress = fillRate
            } catch (_: Exception) {

            }
        }
    }

    private fun showData(model: SpinRowDataHolder) {

        if (model.spin_big != null) {
            SmallView2.smallViewFunctionality(this@QuizStartActivity, model, binding.layoutBottom)
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
                        this@QuizStartActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}