package my.phatndt.xsmart.android.features.bmicalculator.calculator

import my.phatndt.xsmart.model.entity.bmi.BmiEntity

data class BmiUIState(
    val bmi: List<BmiEntity>? = null,
    val errorMessage: String? = null,
    val calculatedBmi: Double? = null,
    val isShowDiaLog: Boolean = false,
)
