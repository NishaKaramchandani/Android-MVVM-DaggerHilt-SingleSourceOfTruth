package com.example.baseproject.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.R
import com.example.baseproject.databinding.RowCountryBinding
import com.example.baseproject.view.data.Country

class CountriesAdapter : RecyclerView.Adapter<CountriesAdapter.CountryViewHolder>() {

    inner class CountryViewHolder(private val binding: RowCountryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(country: Country) {
            var regionStr = ""
            if(country.region.isNotBlank())
                regionStr = ", " + country.region
            binding.nameTextview.text = country.name
            binding.capitalTextview.text = country.capital
            binding.regionTextview.text = regionStr
            binding.codeTextview.text = country.code
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<Country>() {
        override fun areItemsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem.code == newItem.code
        }

        override fun areContentsTheSame(oldItem: Country, newItem: Country): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = RowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val country = differ.currentList[position]
        holder.bind(country)
    }

    private var onItemClickListener: ((Country) -> Unit)? = null

    fun setOnItemClickListener(listener: (Country) -> Unit) {
        onItemClickListener = listener
    }
}
/*
class CountriesAdapter: RecyclerView.Adapter<CountryViewHolder>() {

    private var countries = ArrayList<Country>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = RowCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    fun setData(newCountries: List<Country>) {
        val callback = CountryDiffCallback(countries, newCountries)
        val result = DiffUtil.calculateDiff(callback)
        countries = newCountries as ArrayList<Country> // don't forget to update the backing list
        result.dispatchUpdatesTo(this)
    }

    override fun getItemCount(): Int {
        return countries.size
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        val item = countries[position]
        holder.bind(item)
    }
}

class CountryViewHolder(private val binding: RowCountryBinding): RecyclerView.ViewHolder(binding.root) {
    fun bind(country: Country) {
        var regionStr = ""
        if(country.region.isNotBlank())
            regionStr = ", " + country.region
        binding.nameTextview.text = country.name
        binding.capitalTextview.text = country.capital
        binding.regionTextview.text = regionStr
        binding.codeTextview.text = country.code
    }
}*/
