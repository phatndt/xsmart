package my.phatndt.xsmart.android.features.vnsalarycalculator.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.phatndt.xsmart.android.R
import my.phatndt.xsmart.android.core.extension.paddingHorizontalXlLarge
import my.phatndt.xsmart.android.core.ui.theme.Spacing
import my.phatndt.xsmart.android.core.ui.theme.XSmartTheme
import my.phatndt.xsmart.android.core.ui.widget.XSmartButton
import my.phatndt.xsmart.android.core.ui.widget.XSmartRadioButton
import my.phatndt.xsmart.android.core.ui.widget.XSmartSpacerLarge
import my.phatndt.xsmart.android.core.ui.widget.XSmartSpacerMedium
import my.phatndt.xsmart.android.core.ui.widget.XSmartSpacerXLarge
import my.phatndt.xsmart.android.core.ui.widget.XSmartTextField
import my.phatndt.xsmart.android.core.ui.widget.textfield.XSmartAmountTextField
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.model.InsuranceType
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiEffect
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiState
import my.phatndt.xsmart.model.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.model.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun VnSalaryCalculatorRoute(
    viewModel: VnSalaryCalculatorViewModel = koinViewModel(),
    onNavigateToDetail: (VnSalaryCalculatorEntity) -> Unit,
    onBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effects ->
            when (effects) {
                is VnSalaryCalculatorUiEffect.NavigateToDetailSalary -> onNavigateToDetail(effects.data)
                VnSalaryCalculatorUiEffect.ShowDialogCanNotCalculateSalary -> {
                    snackBarState.showSnackbar("Can not calculate salary, please try later!")
                }
            }
        }
    }

    VnSalaryCalculatorScreen(
        uiState = uiState.value,
        onAction = {
            viewModel.setIntent(it)
        },
        onBack = onBack,
    )
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

    var isVisibleInsuranceManualInput by remember {
        mutableStateOf(false)
    }
    // </editor-fold>

    Column(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
            .imePadding(),
    ) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = "VN salary calculator",
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
            XSmartSpacerXLarge()
            XSmartAmountTextField(
                value = uiState.income.orEmpty(),
                onValueChange = {
                    onAction(VnSalaryCalculatorUiIntent.IncomeChangeIntent(it))
                },
                label = {
                    Text(text = "Income")
                },
                modifier = Modifier.paddingHorizontalXlLarge(),
            )
            XSmartSpacerLarge()
            XSmartTextField(
                value = uiState.numberOfDependents.orEmpty(),
                onValueChange = {
                    onAction(VnSalaryCalculatorUiIntent.NumberOfDependentsChangeIntent(it))
                },
                label = {
                    Text(text = "Number of dependents")
                },
                modifier = Modifier.paddingHorizontalXlLarge(),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
            )
            XSmartSpacerLarge()
            Text(
                text = "Insurance Salary",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.paddingHorizontalXlLarge(),
            )
            XSmartSpacerMedium()
            LazyRow(modifier = Modifier.paddingHorizontalXlLarge()) {
                items(
                    insuranceTypes.size,
                    key = { index ->
                        insuranceTypes[index]
                    },
                ) { index ->
                    XSmartRadioButton(
                        selected = insuranceTypes[index] == uiState.insuranceType,
                        text = getInsuranceDisplayText(insuranceTypes[index]),
                        onCheckedChange = {
                            onAction(
                                VnSalaryCalculatorUiIntent.InsuranceTypeChangeIntent(
                                    insuranceTypes[index],
                                ),
                            )
                            isVisibleInsuranceManualInput = insuranceTypes[index] == InsuranceType.OTHER
                        },
                    )
                }
            }
            AnimatedVisibility(visible = isVisibleInsuranceManualInput) {
                XSmartAmountTextField(
                    value = uiState.insuranceSalary.orEmpty(),
                    onValueChange = {
                        onAction(VnSalaryCalculatorUiIntent.NumberOfDependentsChangeIntent(it))
                    },
                    modifier = Modifier
                        .padding(top = Spacing.medium)
                        .paddingHorizontalXlLarge(),
                    label = {
                        Text(text = "Insurance salary")
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number,
                    ),
                )
            }
            AreaComposable(
                selectedArea = uiState.area,
                listOfArea = areaTypes,
                onAction = onAction,
            )
        }
        XSmartButton(
            onClick = {
                onAction(VnSalaryCalculatorUiIntent.CalculatorSalary)
            },
            content = "Gross to Net",
            modifier = Modifier
                .paddingHorizontalXlLarge()
                .padding(bottom = Spacing.large),
        )
    }
}

@Composable
fun AreaComposable(
    selectedArea: Area,
    listOfArea: List<Area>,
    onAction: (VnSalaryCalculatorUiIntent) -> Unit = {},
) {
    Text(
        text = "Area",
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )
    XSmartSpacerMedium()
    LazyRow(modifier = Modifier.paddingHorizontalXlLarge()) {
        items(
            listOfArea.size,
            key = { index ->
                listOfArea[index]
            },
        ) { index ->
            XSmartRadioButton(
                selected = listOfArea[index] == selectedArea,
                text = getAreaDisplayType(listOfArea[index]),
                onCheckedChange = {
                    onAction(VnSalaryCalculatorUiIntent.AreaChangeIntent(listOfArea[index]))
                },
            )
        }
    }
}

@Composable
fun getInsuranceDisplayText(type: InsuranceType): String = when (type) {
    InsuranceType.FULL -> LocalContext.current.getString(R.string.full)
    InsuranceType.OTHER -> LocalContext.current.getString(R.string.other)
}

@Composable
fun getAreaDisplayType(type: Area): String = when (type) {
    Area.I -> "I"
    Area.II -> "II"
    Area.III -> "III"
    Area.IV -> "IV"
}

@Preview(
    showSystemUi = true,
)
@Composable
fun VnSalaryCalculatorScreenPreview() {
    XSmartTheme() {
        VnSalaryCalculatorScreen(
            VnSalaryCalculatorUiState(
                income = "2000000",
            ),
        )
    }
}
