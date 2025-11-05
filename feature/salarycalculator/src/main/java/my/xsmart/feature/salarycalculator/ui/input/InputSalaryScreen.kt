package my.xsmart.feature.salarycalculator.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiState
import my.xsmart.feature.salarycalculator.R

@Composable
fun VnSalaryCalculatorRoute(
    onBack: () -> Unit,
) {
//    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
//    val snackBarState = remember {
//        SnackbarHostState()
//    }
//    val context = LocalContext.current
//
//    LaunchedEffect(key1 = UUID.randomUUID()) {
//        viewModel.effect.collect { effects ->
//            when (effects) {
//                is VnSalaryCalculatorUiEffect.NavigateToDetailSalary -> onNavigateToDetail(effects.data)
//                VnSalaryCalculatorUiEffect.ShowDialogCanNotCalculateSalary -> {
//                    snackBarState.showSnackbar(context.getString(R.string.vn_salary_error_can_not_calculate_salary))
//                }
//            }
//        }
//    }

//    VnSalaryCalculatorScreen(
//        uiState = uiState.value,
//        onAction = {
//            viewModel.setIntent(it)
//        },
//        onBack = onBack,
//    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VnSalaryCalculatorScreen(
    uiState: VnSalaryCalculatorUiState,
    onAction: (VnSalaryCalculatorUiIntent) -> Unit = {},
    onBack: () -> Unit = {},
) {
    // <editor-fold desc="Insurance">
    val insuranceTypes = remember {
        InsuranceType.entries.toList()
    }

    val areaTypes = remember {
        my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.entries.toList()
    }
    // </editor-fold>

    val localFocusManager = LocalFocusManager.current

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .safeDrawingPadding()
            .imePadding(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(R.string.vn_salary_title_screen),
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
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(state = rememberScrollState()),
        ) {
            Text(
                text = stringResource(R.string.vn_salary_placeholder_income),
                style = MaterialTheme.typography.titleMedium,
            )
            TextField(
                value = uiState.income.orEmpty(),
                onValueChange = {
                    onAction(VnSalaryCalculatorUiIntent.IncomeChangeIntent(it))
                },
                label = {
                    Text(
                        text = stringResource(R.string.vn_salary_placeholder_income),
                        modifier = Modifier.background(color = Color.Transparent)
                    )
                },
                modifier = Modifier,
                maxLines = 1,
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        if (uiState.numberOfDependents.isNullOrEmpty()) {
                            localFocusManager.moveFocus(FocusDirection.Down)
                        }
                    })
            )
            Text(
                text = stringResource(R.string.vn_salary_number_of_dependents_placeholder),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier,
            )
        }
//        XSmartButton(
//            onClick = {
//                onAction(VnSalaryCalculatorUiIntent.CalculatorSalary)
//            },
//            content = stringResource(R.string.vn_salary_btn_text_gross_to_net),
//            modifier = Modifier
//                .paddingHorizontalXlLarge()
//                .padding(bottom = Spacing.large),
//        )
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun VnSalaryCalculatorScreenPreview() {
    MaterialTheme {
        VnSalaryCalculatorScreen(
            VnSalaryCalculatorUiState(
                income = "2000000",
            ),
        )
    }
}
