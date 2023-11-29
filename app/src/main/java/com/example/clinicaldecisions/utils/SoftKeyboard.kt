/*
 * hideSoftKeyboard.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.utils

import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

fun View.hideSoftKeyboard() {
    if (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) is InputMethodManager) {
        val imm =
            context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }
}

fun View.showSoftKeyboard() {
    if (context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) is InputMethodManager) {
        val imm =
            context.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(this, 0)
    }
}

fun Fragment.hideSoftKeyboard() {
    if (context != null && context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) is InputMethodManager) {
        val imm =
            context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}

fun Fragment.forceHideKeyboard(focusView: View) {
    val inputMethodManager =
        context?.getSystemService(AppCompatActivity.INPUT_METHOD_SERVICE) as? InputMethodManager
    inputMethodManager?.hideSoftInputFromWindow(focusView.windowToken, 0)
}