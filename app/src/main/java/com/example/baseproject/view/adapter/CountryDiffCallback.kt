package com.example.baseproject.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.baseproject.model.remote.data.CountryResponse
import com.example.baseproject.view.data.Country


class CountryDiffCallback : DiffUtil.ItemCallback<Country>() {
    override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem.code == newItem.code
    }

    override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
        return oldItem == newItem
    }
}
