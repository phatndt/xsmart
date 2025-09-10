package my.phatndt.xsmart.share.di.data

import my.phatndt.xsmart.share.data.repo.VnSalaryCalculatorRepoImpl
import my.phatndt.xsmart.share.data.repo.bmi.BmiRepositoryImpl
import my.phatndt.xsmart.share.domain.repo.VnSalaryCalculatorRepo
import my.phatndt.xsmart.share.domain.repo.bmi.BmiRepository
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

val repoModule = module {
    single<BmiRepository> { BmiRepositoryImpl(get(qualifier = named("ioDispatcher"))) }
    single { VnSalaryCalculatorRepoImpl() } bind VnSalaryCalculatorRepo::class
}
