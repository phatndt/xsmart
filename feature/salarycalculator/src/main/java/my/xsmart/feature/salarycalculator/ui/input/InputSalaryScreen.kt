package my.xsmart.feature.salarycalculator.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiState
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
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
        Area.entries.toList()
    }
    // </editor-fold>

    val localFocusManager = LocalFocusManager.current

    Scaffold(
        topBar = {
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
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(state = rememberScrollState()),
        ) {
            Icon(
                Icons.Default.Calculate,
                modifier = Modifier
                    .size(64.dp)
                    .align(Alignment.CenterHorizontally),
                contentDescription = null,
            )
            Text(
                text = "Calculate your net salary after taxes and insurance",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(horizontal = 16.dp),
                textAlign = TextAlign.Center,
            )
            Card(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
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
                        modifier = Modifier.fillMaxSize(),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (uiState.numberOfDependents.isNullOrEmpty()) {
                                    localFocusManager.moveFocus(FocusDirection.Down)
                                }
                            },
                        ),
                    )
                    Text(
                        text = stringResource(R.string.vn_salary_number_of_dependents_placeholder),
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier,
                    )
                }
            }
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
