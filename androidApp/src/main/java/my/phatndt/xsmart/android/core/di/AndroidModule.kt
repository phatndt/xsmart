package my.phatndt.xsmart.android.core.di

import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiViewModel
import my.phatndt.xsmart.android.features.currencyconverter.presentation.viewmodel.CurrencyViewModel
import my.phatndt.xsmart.android.features.dashboard.DashboardViewModel
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.VnSalaryCalculatorDetailViewModel
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.VnSalaryCalculatorViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val androidModule = module {
    viewModel { BmiViewModel(get()) }
    viewModel { CurrencyViewModel(get()) }
    viewModel { DashboardViewModel() }
    viewModel { VnSalaryCalculatorViewModel(get(), get()) }
    viewModel { VnSalaryCalculatorDetailViewModel(get()) }
}
