package com.gtrbx.gtrbxcounter034.view.pop

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.gtrbx.gtrbxcounter034.databinding.DialogScratchLayoutBinding

object ScratchDialog {
    fun viewScratchDialog(
        context: Context,
        winingRank: String,
        closeEvent: CloseEvent
    ) {
        val pop = Dialog(context)
        val binding = DialogScratchLayoutBinding.inflate(LayoutInflater.from(context))
        pop.setContentView(binding.root)
        pop.setCancelable(false)
        pop.window!!.setBackgroundDrawableResource(android.R.color.transparent)
        pop.window!!.setDimAmount(0.9f)
        val width = (context.resources.displayMetrics.widthPixels * 0.90).toInt()
        pop.window!!.setLayout(width, ViewGroup.LayoutParams.WRAP_CONTENT)

        binding.textRank.text = winingRank

        binding.buttonCollect.setOnClickListener {
            pop.dismiss()
            closeEvent.close()
        }

        pop.show()
    }

    interface CloseEvent{
        fun close()
    }
}