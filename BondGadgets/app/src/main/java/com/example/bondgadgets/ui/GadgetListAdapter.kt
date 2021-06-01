package com.example.bondgadgets.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bondgadgets.Gadget
import com.example.bondgadgets.GadgetNFC
import com.example.bondgadgets.GadgetQRCode
import com.example.bondgadgets.R
import com.example.bondgadgets.common.toFormattedString
import com.example.bondgadgets.databinding.ItemGadgetBinding

class GadgetListAdapter(val clickListener: GadgetClickListener) : ListAdapter<Gadget, GadgetListAdapter.ViewHolder>(GadgetDiffCallback()){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder = ViewHolder.from(parent)

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentGadget = getItem(position)
        holder.bind(currentGadget)
        holder.binding.root.setOnClickListener {
            clickListener.onGadgetClicked(currentGadget)
        }
    }

    class ViewHolder(val binding : ItemGadgetBinding) : RecyclerView.ViewHolder(binding.root){

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemGadgetBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }

        fun bind(currentGadget : Gadget) {
            binding.itemDate.text = currentGadget.dateCreated.toFormattedString()

            when(currentGadget){
                is GadgetQRCode -> {
                    binding.itemUrl.text = currentGadget.url
                    binding.itemGadgetIcon.setImageResource(R.drawable.ic_qrcode)
                }
                is GadgetNFC -> {
                    binding.itemUrl.text = currentGadget.url
                    binding.itemGadgetIcon.setImageResource(R.drawable.ic_nfc)
                }
            }
        }
    }

    interface GadgetClickListener {
        fun onGadgetClicked(currentGadget: Gadget)
    }

    private class GadgetDiffCallback : DiffUtil.ItemCallback<Gadget>() {

        override fun areItemsTheSame(oldItem: Gadget, newItem: Gadget): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Gadget, newItem: Gadget): Boolean {
            return oldItem == newItem
        }

    }
}