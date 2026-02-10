package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxBracket
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryCalculatorConstant

data class VnSalaryCalculatorConfig(
    // Employee insurance rates
    override val socialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.SOCIAL_INSURANCE_RATE,
    override val healthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.HEALTH_INSURANCE_RATE,
    override val unemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.UNEMPLOYMENT_INSURANCE_RATE,

    // Employer insurance rates
    override val employerSocialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_SOCIAL_INSURANCE_RATE,
    override val employerHealthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_HEALTH_INSURANCE_RATE,
    override val employerUnemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE,

    // Tax deductions
    override val personalDeduction: KmmBigDecimal = VnSalaryCalculatorConstant.PERSONAL_DEDUCTION,
    override val dependentDeduction: KmmBigDecimal = VnSalaryCalculatorConstant.DEPENDENT_DEDUCTION,
    
    // Base salary & Regional Minimum Wage
    override val baseSalary: KmmBigDecimal = VnSalaryCalculatorConstant.BASE_SALARY,
    override val regionalMinimumWage: Map<Area, KmmBigDecimal> = mapOf(
        Area.I to VnSalaryCalculatorConstant.REGION_I,
        Area.II to VnSalaryCalculatorConstant.REGION_II,
        Area.III to VnSalaryCalculatorConstant.REGION_III,
        Area.IV to VnSalaryCalculatorConstant.REGION_IV,
    ),

    // Tax brackets (Progressive Tax Rates)
    override val taxBrackets: List<TaxBracket> = listOf(
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_1_LOWER,
            VnSalaryCalculatorConstant.BRACKET_1_UPPER,
            VnSalaryCalculatorConstant.RATE_1,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_2_LOWER,
            VnSalaryCalculatorConstant.BRACKET_2_UPPER,
            VnSalaryCalculatorConstant.RATE_2,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_3_LOWER,
            VnSalaryCalculatorConstant.BRACKET_3_UPPER,
            VnSalaryCalculatorConstant.RATE_3,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_4_LOWER,
            VnSalaryCalculatorConstant.BRACKET_4_UPPER,
            VnSalaryCalculatorConstant.RATE_4,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_5_LOWER,
            VnSalaryCalculatorConstant.BRACKET_5_UPPER,
            VnSalaryCalculatorConstant.RATE_5,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_6_LOWER,
            VnSalaryCalculatorConstant.BRACKET_6_UPPER,
            VnSalaryCalculatorConstant.RATE_6,
        ),
        TaxBracket(
            VnSalaryCalculatorConstant.BRACKET_7_LOWER,
            VnSalaryCalculatorConstant.BRACKET_7_UPPER,
            VnSalaryCalculatorConstant.RATE_7,
        ),
    ),

    override val unionFeeRate: Double = VnSalaryCalculatorConstant.UNION_RATE

): VietnamSalaryConfig
