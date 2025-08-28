package my.phatndt.xsmart.model.entity.vnsalarycalculator

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

data class VnSalaryCalculatorConfig(
    // Employee insurance rates
    val socialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.SOCIAL_INSURANCE_RATE,
    val healthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.HEALTH_INSURANCE_RATE,
    val unemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.UNEMPLOYMENT_INSURANCE_RATE,

    // Employer insurance rates
    val employerSocialInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_SOCIAL_INSURANCE_RATE,
    val employerHealthInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_HEALTH_INSURANCE_RATE,
    val employerUnemploymentInsuranceRate: KmmBigDecimal = VnSalaryCalculatorConstant.EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE,

    // Tax deductions
    val personalDeduction: KmmBigDecimal = VnSalaryCalculatorConstant.PERSONAL_DEDUCTION, // Deduction for the employee
    val dependentDeduction: KmmBigDecimal = VnSalaryCalculatorConstant.DEPENDENT_DEDUCTION, // Deduction per dependent

    // Tax brackets (Progressive Tax Rates)
    val taxBrackets: List<TaxBracket> = listOf(
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
)
