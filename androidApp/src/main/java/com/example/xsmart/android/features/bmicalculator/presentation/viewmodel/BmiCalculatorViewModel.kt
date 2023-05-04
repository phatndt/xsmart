package com.example.xsmart.android.features.bmicalculator.presentation.viewmodel

import androidx.lifecycle.ViewModel

class BmiCalculatorViewModel: ViewModel() {
    fun calculateBmi(height: Float, weight: Float): Float {
        return weight / (height * height)
    }
}