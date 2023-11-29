/*
 * UpdateDialogFragment.kt
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
import androidx.core.net.toUri
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.clinicaldecisions.R
import com.example.clinicaldecisions.databinding.FragmentUpdateDialogBinding
import com.example.clinicaldecisions.domain.model.MedicineModel
import com.example.clinicaldecisions.ui.container.viewModel.ContainerViewModel
import com.example.clinicaldecisions.utils.FormEvents
import com.example.clinicaldecisions.utils.collect
import com.example.clinicaldecisions.utils.hideSoftKeyboard
import com.example.clinicaldecisions.utils.load
import com.example.clinicaldecisions.utils.toast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpdateDialogFragment : DialogFragment() {

    private val binding by lazy(LazyThreadSafetyMode.NONE) {
        FragmentUpdateDialogBinding.inflate(layoutInflater)
    }

    private val containerViewModel: ContainerViewModel by activityViewModels()
    private lateinit var onClickListener: OnClickListener
    private lateinit var medicine: MedicineModel
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.FullScreenDialog)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
    }

    private fun initUi() {
        image = medicine.image
        setListeners()
        setViewData()
        setFlows()
    }

    private fun setListeners() = with(binding) {
        updateToolbar.setNavigationOnClickListener {
            dismiss()
        }
        coverImage.setOnClickListener {
            checkGalleryPermission()
        }
        acceptButton.setOnClickListener {
            hideSoftKeyboard()
            containerViewModel.validateFormUpdate(
                nameTextField.text.toString(),
                descriptionTextField.text.toString(),
                image,
            )
        }
    }

    private fun setViewData() = with(binding) {
        coverImage.load(
            uri = medicine.image.toUri(),
            loadImage = R.drawable.ic_blur,
            errorImage = R.drawable.ic_error,
        )
        medicine.apply {
            nameTextField.setText(name)
            descriptionTextField.setText(description)
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
            onClickListener.clickDialog(
                MedicineModel(
                    medicine.id,
                    nameTextField.text.toString(),
                    descriptionTextField.text.toString(),
                    image,
                )
            )
            dismiss()
        }
    }

    fun setOnClickListener(listener: OnClickListener) {
        onClickListener = listener
    }

    interface OnClickListener {
        fun clickDialog(medicine: MedicineModel)
    }

    companion object {
        const val UPDATE_DIALOG_FRAGMENT_TAG = "UpdateDialogFragment"
        fun newInstance(
            medicine: MedicineModel,
            callback: OnClickListener,
        ): UpdateDialogFragment {
            return UpdateDialogFragment().apply {
                this.medicine = medicine
                setOnClickListener(callback)
            }
        }
    }
}