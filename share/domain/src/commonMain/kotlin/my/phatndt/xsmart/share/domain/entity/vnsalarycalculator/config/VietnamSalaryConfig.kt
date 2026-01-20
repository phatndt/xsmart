package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket

sealed interface VietnamSalaryConfig {
    val socialInsuranceRate: KmmBigDecimal
    val healthInsuranceRate: KmmBigDecimal
    val unemploymentInsuranceRate: KmmBigDecimal
    val employerSocialInsuranceRate: KmmBigDecimal
    val employerHealthInsuranceRate: KmmBigDecimal
    val employerUnemploymentInsuranceRate: KmmBigDecimal

    val personalDeduction: KmmBigDecimal
    val dependentDeduction: KmmBigDecimal

    val baseSalary: KmmBigDecimal
    val regionalMinimumWage: Map<Area, KmmBigDecimal>

    val taxBrackets: List<TaxBracket>
}
