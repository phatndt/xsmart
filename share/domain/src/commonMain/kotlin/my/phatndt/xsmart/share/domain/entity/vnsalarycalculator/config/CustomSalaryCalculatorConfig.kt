package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
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

    override val baseSalary: KmmBigDecimal = VnSalaryCalculatorConstant2026.BASE_SALARY,
    override val regionalMinimumWage: Map<Area, KmmBigDecimal> = mapOf(
        Area.I to VnSalaryCalculatorConstant2026.REGION_I,
        Area.II to VnSalaryCalculatorConstant2026.REGION_II,
        Area.III to VnSalaryCalculatorConstant2026.REGION_III,
        Area.IV to VnSalaryCalculatorConstant2026.REGION_IV,
    ),

    override val taxBrackets: List<TaxBracket>,

    override val unionFeeRate: Double = VnSalaryCalculatorConstant.UNION_RATE

) : VietnamSalaryConfig
