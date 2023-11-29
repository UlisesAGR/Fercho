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
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.FragmentSearchBinding
import com.example.clinicaldecisions.domain.model.EmptyStateModel
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.ui.search.adapter.SearchAdapter
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.gone
import com.example.clinicaldecisions.utils.show
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
            setEmptyState()
        }
    }

    private fun setEmptyState() = with(binding) {
        if (searchAdapter.itemCount == 0) {
            emptyState.apply {
                show(
                    EmptyStateModel(
                        image = R.drawable.il_medical,
                        title = context.getString(R.string.empty_list),
                        subTitle = context.getString(R.string.here_you_will_see_your_list_of_medications),
                    )
                )
                show()
            }
            mainLayout.gone()
        } else {
            emptyState.gone()
            mainLayout.show()
        }
    }

    private fun resetView() {
        binding.nameTextField.setText("")
    }

    override fun onPause() {
        super.onPause()
        resetView()
    }
}