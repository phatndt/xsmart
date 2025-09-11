package my.phatndt.xsmart.share.di.domain

import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.CalculateVnSalaryUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.GetCalculateVnSalaryResultUseCaseImpl
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCase
import my.phatndt.xsmart.share.domain.usecase.vnsalarycalculator.SaveCalculateVnSalaryResultUseCaseImpl
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
}

