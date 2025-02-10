package my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import my.phatndt.xsmart.domain.repo.bmi.BmiRepository
import java.text.DecimalFormat

class BmiViewModel(private val bmiRepository: BmiRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(BmiUIState())
    val uiState = _uiState.asStateFlow()

    fun calculateBmi(height: Double, weight: Double) {
        val bmi = (weight / (height * height)) * 10000
        val decimalFormat = DecimalFormat("##.##")
        val bmiAfterFormat = decimalFormat
            .format(bmi)
            .replace(",", ".")
        _uiState.update {
            it.copy(calculatedBmi = bmiAfterFormat.toDouble(), isShowDiaLog = true)
        }
        insertBmi(
            bmiAfterFormat.toDouble(),
            System
                .currentTimeMillis()
                .toString()
        )
    }

    init {
        getBmi()
    }

    private fun getBmi() {
        viewModelScope.launch {
//            bmiRepository.getListBmi().collect {
//                when (it) {
//                    NetworkResponse.Loading -> {
//
//                    }
//
//                    is NetworkResponse.Success -> {
//                        _uiState.update { state ->
//                            state.copy(bmi = it.data.map { bmi ->
//                                BmiEntity(
//                                    bmi.id, bmi.bmi, bmi.time
//                                )
//                            }.sortedByDescending { bmi -> bmi.time })
//                        }
//                    }
//
//                    is NetworkResponse.Error -> {
//                        _uiState.update { state ->
//                            state.copy(errorMessage = it.exception.message)
//                        }
//                    }
//                }
//            }
        }
    }

    private fun insertBmi(bmi: Double, time: String) {
        bmiRepository.insertBmi(bmi, time)
    }

    fun dismissDialog() {
        _uiState.update { it.copy(isShowDiaLog = false) }
    }
}
