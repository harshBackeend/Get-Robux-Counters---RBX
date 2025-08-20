package com.gtrbx.gtrbxcounter034.view

import android.content.Context
import android.view.View
import androidx.core.net.toUri
import com.bumptech.glide.Glide
import com.gtrbx.gtrbxcounter034.databinding.Small2ImageLayoutBinding
import com.gtrbx.gtrbxcounter034.tools.StartApplication
import com.gtrbx.gtrbxcounter034.tools.UseMe
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

object SmallView2 {

    fun smallViewFunctionality(
        context: Context,
        model: SpinRowDataHolder,
        binding: Small2ImageLayoutBinding
    ) {
        if (model.spin_big != null && model.spin_big.isNotEmpty()) {
            binding.smallLayoutHolder.visibility = View.VISIBLE
            val bigMode = model.spin_big.random()
            binding.apply {

                Glide.with(binding.imageProfile).load(bigMode.spin_p)
                    .into(binding.imageProfile)

                if (!UseMe.isSpinRowEmptyString(bigMode.spin_main_text)) {
                    textMain.visibility = View.VISIBLE
                    textMain.text = bigMode.spin_main_text
                } else {
                    textMain.visibility = View.GONE
                }

                if (!UseMe.isSpinRowEmptyString(bigMode.spin_main_sub_text)) {
                    textSub.visibility = View.VISIBLE
                    textSub.text = bigMode.spin_main_sub_text
                } else {
                    textSub.visibility = View.GONE
                }

                if (!UseMe.isSpinRowEmptyString(bigMode.spin_main_image)) {
                    imageMain.visibility = View.VISIBLE
                    Glide.with(binding.imageMain).load(bigMode.spin_main_image)
                        .into(binding.imageMain)
                } else {
                    imageMain.visibility = View.GONE
                }

                if (!UseMe.isSpinRowEmptyString(bigMode.spin_main_button)) {
                    button.visibility = View.VISIBLE
                    button.text = bigMode.spin_main_button
                } else {
                    button.visibility = View.GONE
                }

                smallLayoutHolder.setOnClickListener {
                    if (model.spin_small_2 != null && model.spin_small_2.isNotEmpty()){
                        StartApplication.showSpinRowTab(
                            context,
                            model.spin_small_2.random().spin_main_image?.toUri()
                        )
                    }
                }

                imageMain.setOnClickListener {
                    smallLayoutHolder.performClick()
                }

                button.setOnClickListener {
                    smallLayoutHolder.performClick()
                }

            }
        }else{
            binding.smallLayoutHolder.visibility = View.GONE
        }
    }
}