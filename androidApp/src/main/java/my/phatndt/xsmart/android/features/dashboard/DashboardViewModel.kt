package my.phatndt.xsmart.android.features.dashboard

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Money
import my.phatndt.xsmart.android.R
import my.xsmart.share.android.base.BaseViewModel
import my.xsmart.share.android.base.UiIntent
import my.xsmart.share.android.base.UiSideEffects
import my.phatndt.xsmart.android.core.utils.DeferredText
import my.phatndt.xsmart.android.features.dashboard.model.FeatureItem
import my.phatndt.xsmart.android.features.dashboard.model.FeatureType
import my.phatndt.xsmart.android.features.dashboard.state.DashboardUiState

class DashboardViewModel : BaseViewModel<DashboardUiState, UiIntent, UiSideEffects>() {
    init {
        setUiState {
            copy(
                features = listOf(
                    FeatureItem(
                        DeferredText.StringResource(R.string.feature_name_vietnam_salary_calculator),
                        Icons.Outlined.Money,
                        FeatureType.VN_SALARY_CALCULATOR,
                    ),
                    FeatureItem(
                        DeferredText.StringResource(R.string.feature_name_bmi_calculator),
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
