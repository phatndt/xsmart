package my.phatndt.xsmart.android.features.vnsalarycalculator.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.phatndt.xsmart.android.R
import my.phatndt.xsmart.android.core.extension.paddingHorizontalXlLarge
import my.phatndt.xsmart.android.core.ui.theme.Spacing
import my.phatndt.xsmart.android.core.ui.theme.XSmartTheme
import my.phatndt.xsmart.android.core.ui.widget.XSmartButton
import my.phatndt.xsmart.android.core.ui.widget.XSmartTextField
import my.phatndt.xsmart.android.core.ui.widget.textfield.XSmartAmountTextField
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.model.InsuranceType
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiEffect
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiIntent
import my.phatndt.xsmart.android.features.vnsalarycalculator.main.state.VnSalaryCalculatorUiState
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun VnSalaryCalculatorRoute(
    viewModel: VnSalaryCalculatorViewModel = koinViewModel(),
    onNavigateToDetail: (my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity) -> Unit,
    onBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarState = remember {
        SnackbarHostState()
    }
    val context = LocalContext.current

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effects ->
            when (effects) {
                is VnSalaryCalculatorUiEffect.NavigateToDetailSalary -> onNavigateToDetail(effects.data)
                VnSalaryCalculatorUiEffect.ShowDialogCanNotCalculateSalary -> {
                    snackBarState.showSnackbar(context.getString(R.string.vn_salary_error_can_not_calculate_salary))
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
                modifier = Modifier.paddingHorizontalXlLarge(),
            )
            XSmartAmountTextField(
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
                modifier = Modifier
                    .padding(top = Spacing.medium)
                    .paddingHorizontalXlLarge(),
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
                modifier = Modifier
                    .padding(top = Spacing.large)
                    .paddingHorizontalXlLarge(),
            )
            XSmartTextField(
                value = uiState.numberOfDependents.orEmpty(),
                onValueChange = {
                    onAction(VnSalaryCalculatorUiIntent.NumberOfDependentsChangeIntent(it))
                },
                label = {
                    Text(text = stringResource(R.string.vn_salary_number_of_dependents_placeholder))
                },
                modifier = Modifier
                    .padding(top = Spacing.medium)
                    .paddingHorizontalXlLarge(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        localFocusManager.clearFocus()
                    },
                ),
                maxLines = 1,
            )
            InsuranceComposable(
                selectedInsurance = uiState.insuranceType,
                insuranceTypes = insuranceTypes,
                otherInsuranceAmount = uiState.insuranceSalary.orEmpty(),
                onAction = onAction,
            )
            AreaComposable(
                selectedItem = uiState.area,
                items = areaTypes,
                onAction = onAction,
            )
        }
        XSmartButton(
            onClick = {
                onAction(VnSalaryCalculatorUiIntent.CalculatorSalary)
            },
            content = stringResource(R.string.vn_salary_btn_text_gross_to_net),
            modifier = Modifier
                .paddingHorizontalXlLarge()
                .padding(bottom = Spacing.large),
        )
    }
}

@Composable
fun AreaComposable(
    selectedItem: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area,
    items: List<my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area>,
    onAction: (VnSalaryCalculatorUiIntent) -> Unit = {},
) {
    Text(
        text = stringResource(R.string.vn_salary_area_title),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .padding(top = Spacing.medium)
            .paddingHorizontalXlLarge(),
    ) {
        items.forEachIndexed { index, area ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = items.size,
                    baseShape = ShapeDefaults.Small,
                ),
                onClick = {
                    onAction(VnSalaryCalculatorUiIntent.AreaChangeIntent(area))
                },
                selected = area == selectedItem,
                label = { Text(getAreaDisplayType(area)) },
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
fun getAreaDisplayType(type: my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area): String = when (type) {
    my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.I -> stringResource(R.string.vn_salary_area_i)
    my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.II -> stringResource(R.string.vn_salary_area_iI)
    my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.III -> stringResource(R.string.vn_salary_area_iii)
    my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area.IV -> stringResource(R.string.vn_salary_area_iv)
}

@Composable
fun InsuranceComposable(
    selectedInsurance: InsuranceType,
    insuranceTypes: List<InsuranceType>,
    otherInsuranceAmount: String,
    onAction: (VnSalaryCalculatorUiIntent) -> Unit,
) {
    Text(
        text = stringResource(R.string.vn_salary_insurance_title),
        style = MaterialTheme.typography.titleMedium,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .padding(top = Spacing.medium)
            .paddingHorizontalXlLarge(),
    ) {
        insuranceTypes.forEachIndexed { index, label ->
            SegmentedButton(
                shape = SegmentedButtonDefaults.itemShape(
                    index = index,
                    count = insuranceTypes.size,
                    baseShape = ShapeDefaults.Small,
                ),
                onClick = {
                    onAction(VnSalaryCalculatorUiIntent.InsuranceTypeChangeIntent(label))
                },
                selected = label == selectedInsurance,
                label = { Text(getInsuranceDisplayText(label)) },
            )
        }
    }
    XSmartAmountTextField(
        value = otherInsuranceAmount,
        onValueChange = {
            onAction(VnSalaryCalculatorUiIntent.InsuranceSalaryChangeIntent(it))
        },
        modifier = Modifier
            .padding(top = Spacing.medium)
            .paddingHorizontalXlLarge(),
        label = {
            Text(text = stringResource(R.string.vn_salary_insurance_title))
        },
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
        ),
        maxLines = 1,
        enabled = selectedInsurance == InsuranceType.OTHER,
    )
}

@Preview(
    showSystemUi = true,
)
@Composable
fun VnSalaryCalculatorScreenPreview() {
    XSmartTheme {
        VnSalaryCalculatorScreen(
            VnSalaryCalculatorUiState(
                income = "2000000",
            ),
        )
    }
}
