/*
 * ResponseStatus.kt
 * ClinicalDecisions App Android
 * Copyright (c) 2023. All rights reserved.
 */
package com.example.clinicaldecisions.utils

sealed class ResponseStatus<T> {
    class Loading<T> : ResponseStatus<T>()
    data class Success<T>(val data: T?, val code: Int = 0) : ResponseStatus<T>()
    data class Error<T>(val message: String?, val code: Int = 0) : ResponseStatus<T>()
}