package my.phatndt.xsmart.android.features.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Money
import my.phatndt.xsmart.android.core.base.BaseViewModel
import my.phatndt.xsmart.android.core.base.UiIntent
import my.phatndt.xsmart.android.core.base.UiSideEffects
import my.phatndt.xsmart.android.features.dashboard.model.FeatureItem
import my.phatndt.xsmart.android.features.dashboard.model.FeatureType
import my.phatndt.xsmart.android.features.dashboard.state.DashboardUiState

class DashboardViewModel : BaseViewModel<DashboardUiState, UiIntent, UiSideEffects>() {
    init {
        setUiState {
            copy(
                features = listOf(
                    FeatureItem(
                        "Vietnam Salary Calculator",
                        Icons.Outlined.Money,
                        FeatureType.VN_SALARY_CALCULATOR,
                    ),
                    FeatureItem(
                        "Vietnam Salary Calculator",
                        Icons.Outlined.Money,
                        FeatureType.BMI_CALCULATOR,
                    ),
                ),
            )
        }
    }

    override fun createInitialState(): DashboardUiState = DashboardUiState()
    override fun handleIntent(intent: UiIntent) = Unit
}
