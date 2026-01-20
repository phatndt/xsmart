package my.phatndt.xsmart.android.features.dashboard

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material.icons.outlined.ScubaDiving
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import my.phatndt.xsmart.android.R
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.widget.XSmartSpacerXLarge
import my.phatndt.xsmart.android.core.utils.DeferredText
import my.phatndt.xsmart.android.features.dashboard.model.FeatureItem
import my.phatndt.xsmart.android.features.dashboard.model.FeatureType
import org.koin.androidx.compose.koinViewModel

@Composable
fun DashboardRoute(
    viewModel: DashboardViewModel = koinViewModel(),
    onAction: (FeatureType) -> Unit,
) {
    val uiState = viewModel.uiState.collectAsState()
    DashboardScreen(uiState.value.features) {
        onAction(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    features: List<FeatureItem>,
    onAction: (FeatureType) -> Unit,
) {
    Scaffold {
        Column(
            modifier = Modifier
                .padding(it)
                .safeDrawingPadding(),
        ) {
            Text(
                text = stringResource(R.string.app_name),
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(
                    start = Spacing.large,
                    top = Spacing.large,
                ),
            )
            XSmartSpacerXLarge()
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(features.size) {
                    Card(
                        modifier = Modifier.padding(Spacing.large),
                        onClick = {
                            onAction(features[it].type)
                        },
                    ) {
                        Column(
                            modifier = Modifier.padding(Spacing.large),
                        ) {
                            Text(text = features[it].name.asString())
                            Icon(
                                imageVector = features[it].icon,
                                contentDescription = null,
                                modifier = Modifier.padding(top = Spacing.medium),
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DashboardScreenPreview() {
    DashboardScreen(
        listOf(
            FeatureItem(
                DeferredText.Constant("Vietnam Salary Calculator"),
                Icons.Outlined.Money,
                FeatureType.VN_SALARY_CALCULATOR,
            ),
            FeatureItem(
                DeferredText.Constant("BMI Calculator"),
                Icons.Outlined.ScubaDiving,
                FeatureType.BMI_CALCULATOR,
            ),
        ),
    ) {
    }
}
