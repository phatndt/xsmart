package my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config

import my.phatndt.xsmart.share.common.amount.KmmBigDecimal

object VnSalaryCalculatorConstant2026 {
    // Employee insurance rates (unchanged)
    val SOCIAL_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.08)
    val HEALTH_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.015)
    val UNEMPLOYMENT_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.01)

    // Employer insurance rates (unchanged)
    val EMPLOYER_SOCIAL_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.175)
    val EMPLOYER_HEALTH_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.03)
    val EMPLOYER_UNEMPLOYMENT_INSURANCE_RATE: KmmBigDecimal = KmmBigDecimal(0.01)

    // Personal and dependent deductions (increased)
    val PERSONAL_DEDUCTION: KmmBigDecimal = KmmBigDecimal(15_500_000.0) // 15.5M/month
    val DEPENDENT_DEDUCTION: KmmBigDecimal = KmmBigDecimal(6_200_000.0) // 6.2M/month

    // Lower bounds for 2026 tax brackets
    val BRACKET_1_LOWER: KmmBigDecimal = KmmBigDecimal(0.0)
    val BRACKET_2_LOWER: KmmBigDecimal = KmmBigDecimal(10_000_000.0)  // >10M
    val BRACKET_3_LOWER: KmmBigDecimal = KmmBigDecimal(30_000_000.0)  // >30M
    val BRACKET_4_LOWER: KmmBigDecimal = KmmBigDecimal(60_000_000.0)  // >60M
    val BRACKET_5_LOWER: KmmBigDecimal = KmmBigDecimal(100_000_000.0) // >100M

    // Upper bounds for brackets (null for the last)
    val BRACKET_1_UPPER: KmmBigDecimal = KmmBigDecimal(10_000_000.0)
    val BRACKET_2_UPPER: KmmBigDecimal = KmmBigDecimal(30_000_000.0)
    val BRACKET_3_UPPER: KmmBigDecimal = KmmBigDecimal(60_000_000.0)
    val BRACKET_4_UPPER: KmmBigDecimal = KmmBigDecimal(100_000_000.0)
    val BRACKET_5_UPPER: KmmBigDecimal? = null // No upper limit

    // Updated 2026 progressive tax rates
    val RATE_1: Double = 0.05  // 5%
    val RATE_2: Double = 0.10  // 10%
    val RATE_3: Double = 0.20  // 20%
    val RATE_4: Double = 0.30  // 30%
    val RATE_5: Double = 0.35  // 35%
}
