package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket

data class CustomSalaryCalculatorConfig(
    // Employee insurance rates (unchanged)
    override val socialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.SOCIAL_INSURANCE_RATE,
    override val healthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.HEALTH_INSURANCE_RATE,
    override val unemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.UNEMPLOYMENT_INSURANCE_RATE,

    // Employer insurance rates (unchanged)
    override val employerSocialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_SOCIAL_INSURANCE_RATE,
    override val employerHealthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_HEALTH_INSURANCE_RATE,
    override val employerUnemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE,

    override val personalDeduction: KmmBigDecimal,
    override val dependentDeduction: KmmBigDecimal,

    override val taxBrackets: List<TaxBracket>,
) : VietnamSalaryConfig
