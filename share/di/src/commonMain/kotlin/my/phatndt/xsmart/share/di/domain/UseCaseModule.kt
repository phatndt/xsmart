package my.phatndt.xsmart.share.di.domain

import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetVnSalaryConfigModeUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetVnSalaryConfigUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveVnSalaryConfigModeUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveVnSalaryConfigUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.GetVnSalaryConfigModeUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.GetVnSalaryConfigUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigModeUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.api.SaveVnSalaryConfigUseCase
import org.koin.dsl.bind
import org.koin.dsl.module

val useCaseModule = module {
    single {
        CalculateVnSalaryUseCaseImpl()
    } bind CalculateVnSalaryUseCase::class

    single {
        GetCalculateVnSalaryResultUseCaseImpl(get())
    } bind GetCalculateVnSalaryResultUseCase::class

    single {
        SaveCalculateVnSalaryResultUseCaseImpl(get())
    } bind SaveCalculateVnSalaryResultUseCase::class

    single {
        GetVnSalaryConfigUseCaseImpl()
    } bind GetVnSalaryConfigUseCase::class

    single {
        GetVnSalaryConfigModeUseCaseImpl()
    } bind GetVnSalaryConfigModeUseCase::class

    single {
        SaveVnSalaryConfigUseCaseImpl()
    } bind SaveVnSalaryConfigUseCase::class

    single {
        SaveVnSalaryConfigModeUseCaseImpl()
    } bind SaveVnSalaryConfigModeUseCase::class
}


