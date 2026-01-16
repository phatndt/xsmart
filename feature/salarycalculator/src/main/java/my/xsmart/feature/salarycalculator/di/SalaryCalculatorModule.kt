package my.xsmart.feature.salarycalculator.di

import my.xsmart.feature.salarycalculator.ui.config.SalaryConfigViewModel
import my.xsmart.feature.salarycalculator.ui.input.InputSalaryViewModel
import my.xsmart.feature.salarycalculator.ui.result.ResultViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val salaryCalculatorModule = module {
    viewModel {
        InputSalaryViewModel(get(), get())
    }
    viewModel {
        ResultViewModel( get())
    }
    viewModel {
        SalaryConfigViewModel(
            get(),
            get(),
            get(),
            get(),
        )
    }
}
