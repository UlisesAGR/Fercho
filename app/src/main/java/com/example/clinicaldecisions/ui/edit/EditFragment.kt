/*
 * EditFragment.kt
 * ClinicalDecisions App For Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.FragmentEditBinding
import com.example.clinicaldecisions.domain.model.EmptyStateModel
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.ui.dialog.CreateDialogFragment
import com.example.clinicaldecisions.ui.dialog.UpdateDialogFragment
import com.example.clinicaldecisions.ui.edit.adapter.EditAdapter
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.gone
import com.example.clinicaldecisions.utils.materialDialog
import com.example.clinicaldecisions.utils.show
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditFragment : Fragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentEditBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()
    private lateinit var editAdapter: EditAdapter

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
        floatingActionButton.setOnClickListener {
            openCreateDialog()
        }
    }

    private fun setAdapter() {
        editAdapter = EditAdapter(
            onItemSelected = { medicine ->
                openUpdateDialog(medicine)
            },
            onItemDeleted = { medicine ->
                openDeleteDialog(medicine)
            },
        )
    }

    private fun setRecycler() = with(binding) {
        recyclerView.apply {
            setHasFixedSize(true)
            adapter = editAdapter
        }
    }

    private fun setFlows() {
        collect(containerViewModel.containerState) { state ->
            editAdapter.setList(state.list)
            setEmptyState()
        }
    }

    private fun setEmptyState() = with(binding) {
        if (editAdapter.itemCount == 0)
            emptyState.apply {
                show(
                    EmptyStateModel(
                        image = R.drawable.il_science,
                        title = context.getString(R.string.empty_list),
                        subTitle = context.getString(R.string.here_you_can_add_your_medications),
                    )
                )
                show()
            }
        else emptyState.gone()
    }

    private fun openCreateDialog() {
        val dialog = CreateDialogFragment.newInstance(
            onClickListener = { medicine ->
                containerViewModel.createMedicine(medicine)
            }
        )
        dialog.show(childFragmentManager, CreateDialogFragment.CREATE_DIALOG_FRAGMENT_TAG)
    }

    private fun openUpdateDialog(medicine: MedicineModel) {
        val dialog = UpdateDialogFragment.newInstance(
            medicine,
            callback = object : UpdateDialogFragment.OnClickListener {
                override fun clickDialog(medicine: MedicineModel) {
                    containerViewModel.updateMedicine(medicine)
                }
            }
        )
        dialog.show(childFragmentManager, UpdateDialogFragment.UPDATE_DIALOG_FRAGMENT_TAG)
    }

    private fun openDeleteDialog(medicine: MedicineModel) {
        requireContext().materialDialog(
            title = getString(R.string.delete),
            message = getString(R.string.are_you_sure_to_delete),
        ) {
            containerViewModel.deleteMedicine(medicine)
        }
    }
}