package my.xsmart.feature.salarycalculator.di

import my.xsmart.feature.salarycalculator.ui.input.InputSalaryViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val salaryCalculatorModule = module {
    viewModel {
        InputSalaryViewModel(get(), get())
    }
}
