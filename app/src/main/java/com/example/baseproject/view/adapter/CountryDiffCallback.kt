package com.example.baseproject.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.baseproject.model.remote.data.CountryResponse
import com.example.baseproject.view.data.Country


class CountryDiffCallback(
    private val oldList: List<Country>,
    private val newList: List<Country>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].code == newList[newItemPosition].code
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
