/*
 * CreateDialogFragment.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.ui.dialog

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts.PickVisualMedia
import androidx.activity.result.contract.ActivityResultContracts.RequestPermission
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.FragmentCreateDialogBinding
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.utils.FormEvents
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.hideSoftKeyboard
import com.example.clinicaldecisions.utils.load
import com.example.clinicaldecisions.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreateDialogFragment : DialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentCreateDialogBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()
    private lateinit var onClickListener: (MedicineModel) -> Unit
    private var image: String = ""

    private val requestPermissionLauncher =
        registerForActivityResult(RequestPermission()) { isGranted ->
            if (!isGranted) requireContext().toast(getString(R.string.accept_the_permission))
        }

    private val pickMedia =
        registerForActivityResult(PickVisualMedia()) { uri ->
            if (uri != null) {
                binding.coverImage.load(
                    uri = uri,
                    loadImage = R.drawable.ic_blur,
                    errorImage = R.drawable.ic_error,
                )
                image = uri.toString()
            } else requireContext().toast(getString(R.string.not_select_image))
        }

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, android.R.style.Theme_Material_Light_NoActionBar_Fullscreen)
    }

    private fun initUi() {
        setListeners()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        createToolbar.setNavigationOnClickListener {
            dismiss()
        }
        coverImage.setOnClickListener {
            checkGalleryPermission()
        }
        addButton.setOnClickListener {
            hideSoftKeyboard()
            containerViewModel.validateFormCreate(
                nameTextField.text.toString(),
                descriptionTextField.text.toString(),
                image,
            )
        }
    }

    private fun checkGalleryPermission() {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                READ_EXTERNAL_STORAGE,
            ) -> pickMedia.launch(PickVisualMediaRequest(PickVisualMedia.ImageOnly))

            else -> requestPermissionLauncher.launch(READ_EXTERNAL_STORAGE)
        }
    }

    private fun setFlows() {
        collect(containerViewModel.containerState) { state ->
            setFormEvents(state.formEvent)
        }
    }

    private fun setFormEvents(state: FormEvents) = with(binding) {
        if (state == FormEvents.COMPLETE) {
            onClickListener.invoke(
                MedicineModel(
                    id = 0,
                    nameTextField.text.toString(),
                    descriptionTextField.text.toString(),
                    image,
                )
            )
            dismiss()
        }
    }

    companion object {
        const val CREATE_DIALOG_FRAGMENT_TAG = "CreateDialogFragment"
        fun newInstance(
            onClickListener: (MedicineModel) -> Unit,
        ): CreateDialogFragment {
            return CreateDialogFragment().apply {
                this.onClickListener = onClickListener
            }
        }
    }
}