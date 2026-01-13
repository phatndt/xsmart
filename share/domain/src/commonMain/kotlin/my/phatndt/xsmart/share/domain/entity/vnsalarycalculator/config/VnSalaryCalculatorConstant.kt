package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

object VnSalaryCalculatorConstant {
    // Employee insurance rates
    // Employee rate for social insurance (8%)
    val SOCIAL_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.08)

    // Employee rate for health insurance (1.5%)
    val HEALTH_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.015)

    // Employee rate for unemployment insurance (1%)
    val UNEMPLOYMENT_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.01)

    // Employer insurance rates
    // Employer rate for social insurance (17.5%)
    val EMPLOYER_SOCIAL_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.175)

    // Employer rate for health insurance (3%)
    val EMPLOYER_HEALTH_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.03)

    // Employer rate for unemployment insurance (1%)
    val EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.01)

    val PERSONAL_DEDUCTION: KmmBigDecimal = KmmBigDecimal(11_000_000.0)
    val DEPENDENT_DEDUCTION: KmmBigDecimal = KmmBigDecimal(4_400_000.0)

    // Lower bounds for tax brackets
    val BRACKET_1_LOWER: KmmBigDecimal = KmmBigDecimal(0.0)
    val BRACKET_2_LOWER: KmmBigDecimal = KmmBigDecimal(5_000_000.0)
    val BRACKET_3_LOWER: KmmBigDecimal = KmmBigDecimal(10_000_000.0)
    val BRACKET_4_LOWER: KmmBigDecimal = KmmBigDecimal(18_000_000.0)
    val BRACKET_5_LOWER: KmmBigDecimal = KmmBigDecimal(32_000_000.0)
    val BRACKET_6_LOWER: KmmBigDecimal = KmmBigDecimal(52_000_000.0)
    val BRACKET_7_LOWER: KmmBigDecimal = KmmBigDecimal(80_000_000.0)

    // Upper bounds for tax brackets (null for the last bracket)
    val BRACKET_1_UPPER: KmmBigDecimal = KmmBigDecimal(5_000_000.0)
    val BRACKET_2_UPPER: KmmBigDecimal = KmmBigDecimal(10_000_000.0)
    val BRACKET_3_UPPER: KmmBigDecimal = KmmBigDecimal(18_000_000.0)
    val BRACKET_4_UPPER: KmmBigDecimal = KmmBigDecimal(32_000_000.0)
    val BRACKET_5_UPPER: KmmBigDecimal = KmmBigDecimal(52_000_000.0)
    val BRACKET_6_UPPER: KmmBigDecimal = KmmBigDecimal(80_000_000.0)
    val BRACKET_7_UPPER: KmmBigDecimal? = null // No upper limit

    // Tax rates for each bracket
    val RATE_1: Double = 0.05
    val RATE_2: Double = 0.10
    val RATE_3: Double = 0.15
    val RATE_4: Double = 0.20
    val RATE_5: Double = 0.25
    val RATE_6: Double = 0.30
    val RATE_7: Double = 0.35
}
