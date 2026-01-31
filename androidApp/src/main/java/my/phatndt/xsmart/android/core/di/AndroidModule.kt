package my.phatndt.xsmart.android.core.di

import my.xsmart.feature.dashboard.di.dashboardModule
import my.xsmart.feature.salarycalculator.di.salaryCalculatorModule
import org.koin.dsl.module

val androidModule = module {
    includes(dashboardModule)
    includes(salaryCalculatorModule)
}
