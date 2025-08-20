package com.gtrbx.gtrbxcounter034.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.gtrbx.gtrbxcounter034.R
import com.gtrbx.gtrbxcounter034.databinding.ActivityConverterBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.tools.UseMe.hideKeyboardFrom
import com.gtrbx.gtrbxcounter034.workData.AppDataHolder
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder
import java.text.DecimalFormat

class ConverterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConverterBinding

    private var displayType: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityConverterBinding.inflate(layoutInflater)
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

        getDataFromOtherScreen()

        setDataInDisplay()

        StartApplication.spinRowResponse.observe(this) {
            if (it?.first != null && !UseMe.isSpinRowEmptyString(it.first!!.spin_status) && it.first!!.spin_status == "1") {
                showData(it.first!!)
            } else {
                binding.bottomImage.visibility = View.GONE
            }
            setPushEvent(it?.first)
            backEvent(it?.first)
        }

        binding.layoutBigTop.textSub.isSelected = true
    }

    private fun getDataFromOtherScreen() {
        try {
            if (intent != null) {
                displayType = intent.getStringExtra(AppDataHolder.AppDataKey.DISPLAY_NAME)
            }
        } catch (e: Exception) {

        }
    }

    private fun setDataInDisplay() {
        binding.apply {
            var displayTitle: String? = null
            var saval: String? = null
            var savalHint: String? = null
            when (displayType) {

                AppDataHolder.DisplayName.bc_spin -> {
                    displayTitle = resources.getString(R.string.b_to_r)
                    saval = resources.getString(R.string.enter_your_b_q)
                    savalHint = resources.getString(R.string.enter_number_of_b_q_h)
                }

                AppDataHolder.DisplayName.tbc_spin -> {
                    displayTitle = resources.getString(R.string.t_to_r)
                    saval = resources.getString(R.string.enter_your_t_q)
                    savalHint = resources.getString(R.string.enter_number_of_t_q_h)
                }

                AppDataHolder.DisplayName.obx_spin -> {
                    displayTitle = resources.getString(R.string.o_to_r)
                    saval = resources.getString(R.string.enter_your_o_q)
                    savalHint = resources.getString(R.string.enter_number_of_o_q_h)
                }

                AppDataHolder.DisplayName.rbx_spin_do -> {
                    displayTitle = resources.getString(R.string.rbx_to_dollar_converter_t)
                    saval = resources.getString(R.string.enter_your_rbx_q)
                    savalHint = resources.getString(R.string.enter_number_of_rbx_q_h)
                }

                AppDataHolder.DisplayName.do_spin_rbx -> {
                    displayTitle = resources.getString(R.string.do_to_t)
                    saval = resources.getString(R.string.enter_your_dollar_q)
                    savalHint = resources.getString(R.string.enter_number_of_dollar_q_h)
                }
            }

            if (!UseMe.isSpinRowEmptyString(displayTitle)
                && !UseMe.isSpinRowEmptyString(saval)
                && !UseMe.isSpinRowEmptyString(savalHint)
            ) {
                layoutActionBar.textDisplayTitle.text = displayTitle
                textQ.text = saval
                inputText.hint = savalHint
                textE.text = getString(R.string.please, savalHint)
            }
        }
    }

    private fun setPushEvent(first: SpinRowDataHolder?) {
        binding.buttonConvert.setOnClickListener {
            if (!UseMe.isSpinRowEmptyString(binding.inputText.text.toString())) {
                getCalculation()
                binding.textE.visibility = View.GONE
                binding.textTitle.visibility = View.VISIBLE
                binding.textAns.visibility = View.VISIBLE
                binding.buttonDone.visibility = View.VISIBLE

                if (first != null && !UseMe.isSpinRowEmptyString(first.spin_status) && first.spin_status == "1") {
                    if (first.spin_big != null) {
                        binding.layoutBigTopHolder.visibility = View.VISIBLE
                        SmallView2.smallViewFunctionality(
                            this@ConverterActivity,
                            first,
                            binding.layoutBigTop
                        )
                    } else {
                        binding.layoutBigTopHolder.visibility = View.GONE
                    }
                } else {
                    binding.layoutBigTopHolder.visibility = View.GONE
                }

            } else {
                binding.textE.visibility = View.VISIBLE
                binding.textTitle.visibility = View.GONE
                binding.textAns.visibility = View.GONE
                binding.layoutBigTopHolder.visibility = View.GONE
                binding.buttonDone.visibility = View.GONE
            }
        }

        binding.buttonDone.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.layoutActionBar.imageBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getCalculation() {
        binding.apply {
            inputText.hideKeyboardFrom(this@ConverterActivity)
            var fD: Double
            val uD: Double = if (!UseMe.isSpinRowEmptyString(inputText.text.toString())) {
                try {
                    inputText.text.toString().toDouble()
                } catch (e: Exception) {
                    0.0
                }
            } else {
                0.0
            }


            try {

                when (displayType) {

                    AppDataHolder.DisplayName.bc_spin -> {
                        fD = uD * 17.85
                        textAns.text = getString(R.string.rbx, DecimalFormat("#.####").format(fD))
                    }

                    AppDataHolder.DisplayName.tbc_spin -> {
                        fD = uD * 38.85
                        textAns.text = getString(R.string.rbx, DecimalFormat("#.####").format(fD))
                    }

                    AppDataHolder.DisplayName.obx_spin -> {
                        fD = uD * 70.2
                        textAns.text = getString(R.string.rbx, DecimalFormat("#.####").format(fD))
                    }

                    AppDataHolder.DisplayName.rbx_spin_do -> {
                        fD = uD * 67.24
                        textAns.text =
                            getString(R.string.dollar, DecimalFormat("#.####").format(fD))
                    }

                    AppDataHolder.DisplayName.do_spin_rbx -> {
                        fD = uD * 67.24
                        textAns.text = getString(R.string.rbx, DecimalFormat("#.####").format(fD))
                    }


                }

            } catch (e: Exception) {
                fD = 00.00
                textAns.text = fD.toString()
            }

        }
    }


    private fun showData(model: SpinRowDataHolder) {
        if (model.spin_small_2 != null) {
            binding.bottomImage.setOnClickListener {
                StartApplication.showSpinRowTab(
                    this@ConverterActivity,
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

    private fun backEvent(holder: SpinRowDataHolder?) {
        val back: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (holder != null && holder.spin_small_2 != null && !UseMe.isSpinRowEmptyString(
                        holder.spin_status
                    ) && holder.spin_status == "1" && holder.spin_small_2.isNotEmpty()
                ) {
                    StartApplication.showSpinRowTab(
                        this@ConverterActivity,
                        holder.spin_small_2.random().spin_main_image?.toUri()
                    )
                }
                finish()
            }

        }
        onBackPressedDispatcher.addCallback(this, back)
    }
}