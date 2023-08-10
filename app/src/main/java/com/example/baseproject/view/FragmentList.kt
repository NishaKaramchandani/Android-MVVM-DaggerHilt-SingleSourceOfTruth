package com.example.baseproject.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.baseproject.R
import com.example.baseproject.view.adapter.CountriesAdapter
import com.example.baseproject.databinding.FragmentListBinding
import com.example.baseproject.viewmodel.CountryListUiState
import com.example.baseproject.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class FragmentList : Fragment() {

    private lateinit var binding: FragmentListBinding

    private lateinit var countriesAdapter: CountriesAdapter

    companion object {
        private const val TAG = "FragmentList"
    }

    private val viewModel: ListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentListBinding.bind(view)
        countriesAdapter = CountriesAdapter()
        binding.recyclerView.apply {
            adapter = countriesAdapter
            layoutManager = LinearLayoutManager(context)
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.countriesUiState.collect {
                    when(it) {
                        is CountryListUiState.Loading -> showLoadingView()
                        is CountryListUiState.Success -> {
                            showListView()
                            countriesAdapter.differ.submitList(it.countryList)
                        }
                        is CountryListUiState.Error -> {
                            showErrorView(it.errorMessageId.toString())
                        }
                    }
                }
            }
        }
    }

    private fun showLoadingView() {
        binding.loadingProgressbar.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.messageTextView.visibility = View.GONE
    }

    private fun showListView() {
        binding.messageTextView.visibility = View.GONE
        binding.recyclerView.visibility = View.VISIBLE
        binding.loadingProgressbar.visibility = View.GONE
    }

    private fun showEmptyView() {
        binding.messageTextView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.loadingProgressbar.visibility = View.GONE
        binding.messageTextView.text = resources.getText(R.string.unknown_error)
    }

    private fun showErrorView(message: String) {
        binding.messageTextView.visibility = View.VISIBLE
        binding.recyclerView.visibility = View.GONE
        binding.loadingProgressbar.visibility = View.GONE
        binding.messageTextView.text = message
    }
}