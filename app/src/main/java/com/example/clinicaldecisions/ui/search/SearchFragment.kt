/*
 * SearchFragment.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.clinicaldecisions.databinding.FragmentSearchBinding
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.ui.search.adapter.SearchAdapter
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.setEmptyState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentSearchBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()
    private lateinit var searchAdapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        setListeners()
        setAdapter()
        setRecycler()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        nameTextField.addTextChangedListener { text ->
            lifecycleScope.launch {
                searchAdapter.filterListByName(text.toString())
            }
        }
    }

    private fun setAdapter() {
        searchAdapter = SearchAdapter(
            onItemSelected = { medicine ->
                findNavController().navigate(
                    SearchFragmentDirections.actionSearchFragmentToDetailActivity(medicine.name)
                )
            },
        )
    }

    private fun setRecycler() = with(binding) {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = searchAdapter
        }
    }

    private fun setFlows() {
        collect(containerViewModel.containerState) { state ->
            searchAdapter.addList(state.list)
            binding.emptyState.setEmptyState(searchAdapter.itemCount)
        }
    }

    private fun resetView() =
        binding.nameTextField.setText("")

    override fun onPause() {
        super.onPause()
        resetView()
    }
}