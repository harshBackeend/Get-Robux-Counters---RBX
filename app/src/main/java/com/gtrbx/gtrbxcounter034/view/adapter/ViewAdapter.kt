package com.gtrbx.gtrbxcounter034.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.gtrbx.gtrbxcounter034.databinding.ItemMemesBinding
import com.gtrbx.gtrbxcounter034.databinding.ItemTipsAndTricksBinding
import com.gtrbx.gtrbxcounter034.databinding.SmallImageLayoutBinding
import com.gtrbx.gtrbxcounter034.workData.SpinRowDataHolder

class ViewAdapter(
    val clickEvent: ClickEvent
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val listOfViewMain: ArrayList<SpinRowDataHolder.ListModel> = arrayListOf()

    fun viewAdd(listOfView: ArrayList<SpinRowDataHolder.ListModel>) {
        listOfViewMain.clear()
        listOfViewMain.addAll(listOfView)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        return listOfViewMain[position].viewType
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return if (viewType == 2) {
            TipsTricksViewHolder(
                ItemTipsAndTricksBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else if (viewType == 1) {
            SmallViewHolder(
                SmallImageLayoutBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        } else {
            MainViewHolder(
                ItemMemesBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int
    ) {
        if (holder is SmallViewHolder) {
            holder.bind(listOfViewMain[position], position)
        } else if (holder is TipsTricksViewHolder) {
            holder.bind(listOfViewMain[position], position)
        } else if (holder is MainViewHolder) {
            holder.bind(listOfViewMain[position], position)
        }
    }

    override fun getItemCount(): Int = listOfViewMain.size

    inner class SmallViewHolder(val binding: SmallImageLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SpinRowDataHolder.ListModel, position: Int) {
            binding.apply {

                Glide.with(binding.imageProfile).load(model.bigLayout?.spin_p)
                    .into(binding.imageProfile)

                textMain.text = model.bigLayout?.spin_main_text

                textSub.text = model.bigLayout?.spin_main_sub_text

                Glide.with(binding.imageMain).load(model.bigLayout?.spin_main_image)
                    .into(binding.imageMain)

                button.text = model.bigLayout?.spin_main_button

                binding.textSub.isSelected = true

                smallLayoutHolder.setOnClickListener {
                    clickEvent.smallViewClick(model)
                }

                imageMain.setOnClickListener {
                    clickEvent.smallViewClick(model)
                }

                button.setOnClickListener {
                    clickEvent.smallViewClick(model)
                }

            }
        }

    }

    inner class MainViewHolder(val binding: ItemMemesBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SpinRowDataHolder.ListModel, position: Int) {
            binding.apply {

                textTitle.text = model.titleHolder
                textSub.text = model.subTitleHolder

                itemView.setOnClickListener {
                    clickEvent.mainViewClick(model)
                }

                imageShare.setOnClickListener {
                    clickEvent.mainViewShareClick(model)
                }

            }
        }
    }

    inner class TipsTricksViewHolder(val binding: ItemTipsAndTricksBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: SpinRowDataHolder.ListModel, position: Int) {
            binding.apply {

                textTitle.text = model.titleHolder

                itemView.setOnClickListener {
                    clickEvent.mainViewClick(model)
                }

            }
        }
    }

    interface ClickEvent {
        fun smallViewClick(model: SpinRowDataHolder.ListModel)

        fun mainViewClick(model: SpinRowDataHolder.ListModel)

        fun mainViewShareClick(model: SpinRowDataHolder.ListModel)
    }
}