package my.xsmart.feature.dashboard.di

import my.xsmart.feature.dashboard.ui.main.DashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val dashboardModule = module {
    viewModel { DashboardViewModel() }
}
