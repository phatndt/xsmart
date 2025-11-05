package my.phatndt.xsmart.android.features.vnsalarycalculator.main.state

import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode

data class VnSalaryCalculatorUiState(
    val income: String? = null,
    val numberOfDependents: String? = null,
    val insuranceSalary: String? = null,
    val insuranceType: InsuranceType = InsuranceType.FULL,
    val area: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area = my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.I,
    val calculatorMode: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode = my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode.GROSS_TO_NET,
)
