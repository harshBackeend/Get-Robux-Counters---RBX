package com.gtrbx.gtrbxcounter034.view.pop

import android.R
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gtrbx.gtrbxcounter034.databinding.DialogExitLayoutBinding

object ExitDialog {
    fun viewExitDialog(
        context: Context,
        dialogEvent: DialogEvent
    ) {
        val pop = Dialog(context)
        val binding = DialogExitLayoutBinding.inflate(LayoutInflater.from(context))
        pop.setContentView(binding.root)
        pop.setCancelable(false)
        pop.window!!.setBackgroundDrawableResource(R.color.transparent)
        pop.window!!.setDimAmount(0.9f)
        val width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        pop.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)


        binding.buttonRateApp.setOnClickListener {
            pop.dismiss()
            dialogEvent.rate()
        }

        binding.buttonExit.setOnClickListener {
            pop.dismiss()
            dialogEvent.exit()
        }

        pop.show()
    }

    interface DialogEvent{
        fun exit()
        fun rate()
    }
}