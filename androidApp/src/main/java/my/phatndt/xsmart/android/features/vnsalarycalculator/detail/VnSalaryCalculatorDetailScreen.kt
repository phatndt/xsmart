package my.phatndt.xsmart.android.features.vnsalarycalculator.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.phatndt.xsmart.android.core.extension.paddingHorizontalXlLarge
import my.phatndt.xsmart.android.core.ui.theme.Spacing
import my.phatndt.xsmart.android.core.ui.theme.XSmartTheme
import my.phatndt.xsmart.android.features.vnsalarycalculator.detail.state.VnSalaryCalculatorDetailUiState
import org.koin.androidx.compose.koinViewModel

@Composable
fun VnSalaryCalculatorDetailRoute(
    viewmodel: VnSalaryCalculatorDetailViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val uiState = viewmodel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.value.isInitial) {
        viewmodel.loadData()
    }

    VnSalaryCalculatorDetailScreen(uiState.value) {
        onBack()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VnSalaryCalculatorDetailScreen(
    uiState: VnSalaryCalculatorDetailUiState,
    onBack: () -> Unit = {},
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .safeDrawingPadding(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "Your salary",
                    style = MaterialTheme.typography.titleMedium,
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.Transparent,
            ),
            navigationIcon = {
                IconButton(onClick = onBack) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                    )
                }
            },
        )
        Text(
            text = "Overview",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .paddingHorizontalXlLarge()
                .padding(vertical = Spacing.large),
        )
        LazyColumn {
            items(uiState.listOfOverviewSalaryData.size) {
                VnSalaryCalculatorItemField(
                    title = uiState.listOfOverviewSalaryData[it].title.asString(),
                    description = uiState.listOfOverviewSalaryData[it].description,
                )
            }
        }
        Text(
            text = "Detail",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier
                .paddingHorizontalXlLarge()
                .padding(vertical = Spacing.large),
        )
        LazyColumn {
            items(uiState.listOfDetailSalaryData.size) {
                VnSalaryCalculatorItemField(
                    title = uiState.listOfDetailSalaryData[it].title.asString(),
                    description = uiState.listOfDetailSalaryData[it].description,
                )
            }
        }
    }
}

@Composable
fun VnSalaryCalculatorItemField(
    title: String,
    description: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = Spacing.medium,
                horizontal = Spacing.large,
            ),
    ) {
        Text(text = title)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = description)
    }
}

@Preview
@Composable
fun VnSalaryCalculatorDetailScreenPreview() {
    XSmartTheme {
        VnSalaryCalculatorDetailScreen(VnSalaryCalculatorDetailUiState()) {
        }
    }
}
