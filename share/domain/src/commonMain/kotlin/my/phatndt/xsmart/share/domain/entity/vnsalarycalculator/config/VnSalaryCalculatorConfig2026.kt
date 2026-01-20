package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket

data class VnSalaryCalculatorConfig2026(
    // Employee insurance rates (unchanged)
    override val socialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.SOCIAL_INSURANCE_RATE,
    override val healthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.HEALTH_INSURANCE_RATE,
    override val unemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.UNEMPLOYMENT_INSURANCE_RATE,

    // Employer insurance rates (unchanged)
    override val employerSocialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_SOCIAL_INSURANCE_RATE,
    override val employerHealthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_HEALTH_INSURANCE_RATE,
    override val employerUnemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant2026.EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE,

    // Tax deductions (NEW 2026)
    override val personalDeduction: KmmBigDecimal = VnSalaryCalculatorConstant2026.PERSONAL_DEDUCTION,

    override val dependentDeduction: KmmBigDecimal = VnSalaryCalculatorConstant2026.DEPENDENT_DEDUCTION,

    // Base salary & Regional Minimum Wage
    override val baseSalary: KmmBigDecimal = VnSalaryCalculatorConstant2026.BASE_SALARY,
    override val regionalMinimumWage: Map<Area, KmmBigDecimal> = mapOf(
        Area.I to VnSalaryCalculatorConstant2026.REGION_I,
        Area.II to VnSalaryCalculatorConstant2026.REGION_II,
        Area.III to VnSalaryCalculatorConstant2026.REGION_III,
        Area.IV to VnSalaryCalculatorConstant2026.REGION_IV,
    ),

    // Progressive tax brackets (5 levels – 2026)
    override val taxBrackets: List<TaxBracket> = listOf(
        TaxBracket(
            lowerBound = VnSalaryCalculatorConstant2026.BRACKET_1_LOWER,
            upperBound = VnSalaryCalculatorConstant2026.BRACKET_1_UPPER,
            rate = VnSalaryCalculatorConstant2026.RATE_1,
        ),
        TaxBracket(
            lowerBound = VnSalaryCalculatorConstant2026.BRACKET_2_LOWER,
            upperBound = VnSalaryCalculatorConstant2026.BRACKET_2_UPPER,
            rate = VnSalaryCalculatorConstant2026.RATE_2,
        ),
        TaxBracket(
            lowerBound = VnSalaryCalculatorConstant2026.BRACKET_3_LOWER,
            upperBound = VnSalaryCalculatorConstant2026.BRACKET_3_UPPER,
            rate = VnSalaryCalculatorConstant2026.RATE_3,
        ),
        TaxBracket(
            lowerBound = VnSalaryCalculatorConstant2026.BRACKET_4_LOWER,
            upperBound = VnSalaryCalculatorConstant2026.BRACKET_4_UPPER,
            rate = VnSalaryCalculatorConstant2026.RATE_4,
        ),
        TaxBracket(
            lowerBound = VnSalaryCalculatorConstant2026.BRACKET_5_LOWER,
            upperBound = VnSalaryCalculatorConstant2026.BRACKET_5_UPPER, // null
            rate = VnSalaryCalculatorConstant2026.RATE_5,
        ),
    ),
) : VietnamSalaryConfig

