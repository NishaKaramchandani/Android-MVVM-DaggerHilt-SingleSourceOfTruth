package com.example.baseproject.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.baseproject.databinding.RowCountryBinding
import com.example.baseproject.view.data.Country

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
}