package my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel

import my.phatndt.xsmart.share.domain.entity.bmi.BmiEntity

data class BmiUIState(val bmi: List<my.phatndt.xsmart.share.domain.entity.bmi.BmiEntity>? = null, val errorMessage: String? = null, val calculatedBmi: Double? = null, val isShowDiaLog: Boolean = false)
