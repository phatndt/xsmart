package my.xsmart.feature.salarycalculator.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.xsmart.feature.salarycalculator.ui.input.model.InsuranceType
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiIntent
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiState
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.Area
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.xsmart.feature.salarycalculator.R
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.xsmart.feature.salarycalculator.ui.input.model.AreaModel
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiEffect
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTextFieldDefault
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.share.ui.component.XSmartOutlineTextField
import my.xsmart.share.ui.component.XSmartTextFieldTransformation
import my.xsmart.share.ui.extension.paddingHorizontalLarge
import my.xsmart.share.ui.extension.paddingHorizontalXlLarge
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.widget.RadioOptionCard
import my.xsmart.share.ui.widget.XSmartButton
import my.xsmart.share.ui.widget.XSmartTextField
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun InputSalaryRoute(
    viewModel: InputSalaryViewModel = koinViewModel(),
    onNavigateToDetail: (VnSalaryCalculatorEntity) -> Unit,
    onBack: () -> Unit,
    onChangeConfig: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarState = remember {
        SnackbarHostState()
    }
    val context = LocalContext.current

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effects ->
            when (effects) {
                is InputSalaryUiEffect.NavigateToDetailSalary -> onNavigateToDetail(effects.data)
                InputSalaryUiEffect.ShowDialogCanNotCalculateSalary -> {
                    snackBarState.showSnackbar(context.getString(R.string.vn_salary_error_can_not_calculate_salary))
                }
            }
        }
    }

    InputSalaryScreen(
        uiState = uiState.value,
        onAction = viewModel::setIntent,
        onBack = onBack,
        onChangeConfig = onChangeConfig,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSalaryScreen(
    uiState: InputSalaryUiState,
    onAction: (InputSalaryUiIntent) -> Unit = {},
    onBack: () -> Unit = {},
    onChangeConfig: () -> Unit = {},
) {
    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_salary_calculator),
                        style = MaterialTheme.typography.titleLarge,
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
                actions = {
                    IconButton(onClick = onChangeConfig) {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        bottomBar = {
            XSmartButton(
                onClick = {
                    onAction(InputSalaryUiIntent.CalculatorSalary)
                },
                content = stringResource(R.string.vn_salary_btn_text_gross_to_net),
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(top = Spacing.large, bottom = Spacing.large)
                    .paddingHorizontalLarge()
            )
        },
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize(),
        ) {
            SalarySection(
                salary = uiState.income.orEmpty(),
                onAction = onAction,
            )

            DependentSection(
                dependents = uiState.numberOfDependents,
                onAction = onAction,
            )

            InsuranceSection(
                selectedItem = uiState.area,
                selectedInsurance = uiState.insuranceType,
                otherInsuranceAmount = uiState.insuranceSalary.orEmpty(),
                onAction = onAction,
            )

            AllowancesSection(
                uiState.allowance.orEmpty(),
                uiState.allowanceType,
                onAction,
            )
        }
    }
}

@Composable
fun SalarySection(
    salary: String,
    onAction: (InputSalaryUiIntent) -> Unit = {},
) {
    Text(
        text = stringResource(R.string.title_monthly_gross_salary),
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )

    XSmartTextField(
        value = salary,
        onValueChange = {
            onAction(InputSalaryUiIntent.IncomeChangeIntent(it))
        },
        textStyle = MaterialTheme.typography.displayMedium,
        placeholder = {
            Text(
                text = stringResource(R.string.placeholder_salary_amount),
                style = MaterialTheme.typography.displayMedium
            )
        },
        supportingText = {
            Text(
                text = stringResource(R.string.helper_salary_input),
                style = MaterialTheme.typography.labelMedium,
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge()
            .padding(top = Spacing.medium),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = XSmartTextFieldTransformation(),
        shape = MaterialTheme.shapes.small,
        colors = SalaryCalculatorTextFieldDefault.color(),
    )
}

@Composable
fun DependentSection(
    dependents: Int,
    onAction: (InputSalaryUiIntent) -> Unit,
) {
//    Text(
//        stringResource(R.string.title_personal_family),
//        style = MaterialTheme.typography.titleLarge,
//        modifier = Modifier
//            .padding(top = Spacing.large)
//            .paddingHorizontalXlLarge(),
//    )

    Row(
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge()
            .fillMaxWidth()
            .background(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.large,
            )
            .padding(Spacing.large),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                stringResource(R.string.label_dependents),
                style = MaterialTheme.typography.titleMedium,
            )
            Text(
                stringResource(R.string.helper_dependents),
                style = MaterialTheme.typography.labelMedium,
            )
        }

        Spacer(modifier = Modifier.width(Spacing.medium))

        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(MaterialTheme.colorScheme.background)
                .padding(4.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            StepperButton(Icons.Default.Remove) {
                if (dependents > 0) {
                    onAction(InputSalaryUiIntent.NumberOfDependentsChangeIntent(dependents - 1))
                }
            }
            Text(
                text = dependents.toString(),
                modifier = Modifier.width(32.dp),
                textAlign = TextAlign.Center,
                style =  MaterialTheme.typography.titleLarge,
            )
            StepperButton(Icons.Default.Add) {
                onAction(InputSalaryUiIntent.NumberOfDependentsChangeIntent(dependents + 1))
            }
        }
    }
}

@Composable
fun AreaSection(
    selectedItem: Area,
    items: List<Area>,
    onAction: (InputSalaryUiIntent) -> Unit = {},
) {
    Text(
        text = stringResource(R.string.vn_salary_area_title),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.paddingHorizontalXlLarge(),
    )
    SingleChoiceSegmentedButtonRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Spacing.small)
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
                    onAction(InputSalaryUiIntent.AreaChangeIntent(area))
                },
                selected = area == selectedItem,
                label = {
                    Text(
                        getAreaDisplayType(area),
                        style = MaterialTheme.typography.titleMedium,
                    )
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
    Area.I -> stringResource(R.string.vn_salary_area_i)
    Area.II -> stringResource(R.string.vn_salary_area_iI)
    Area.III -> stringResource(R.string.vn_salary_area_iii)
    Area.IV -> stringResource(R.string.vn_salary_area_iv)
}

@Composable
fun StepperButton(icon: ImageVector, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(RoundedCornerShape(6.dp))
            .background(Color.White)
            .clickable(onClick = onClick), contentAlignment = Alignment.Center
    ) {
        Icon(icon, contentDescription = null, modifier = Modifier.size(18.dp))
    }
}

@Composable
fun InsuranceSection(
    selectedItem: Area,
    selectedInsurance: InsuranceType,
    otherInsuranceAmount: String,
    onAction: (InputSalaryUiIntent) -> Unit = {},
) {
//    Text(
//        stringResource(R.string.title_insurance_settings),
//        style = MaterialTheme.typography.headlineSmall,
//        modifier = Modifier
//            .padding(top = Spacing.large)
//            .paddingHorizontalXlLarge(),
//    )

    Spacer(Modifier.height(Spacing.large))

    InsurancePaymentOn(
        selectedInsurance = selectedInsurance,
        insuranceTypes = InsuranceType.entries,
        otherInsuranceAmount = otherInsuranceAmount,
        onAction = onAction,
    )

    Spacer(Modifier.height(Spacing.large))

    AreaSection(
        selectedItem = selectedItem,
        items = Area.entries,
        onAction = onAction,
    )
}

@Composable
fun InsurancePaymentOn(
    selectedInsurance: InsuranceType,
    insuranceTypes: List<InsuranceType>,
    otherInsuranceAmount: String,
    onAction: (InputSalaryUiIntent) -> Unit,
) {
    Text(
        stringResource(R.string.label_insurance_payment_on),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.paddingHorizontalXlLarge(),
    )

    insuranceTypes.forEach { type ->
        RadioOptionCard(
            modifier = Modifier
                .padding(top = Spacing.medium)
                .paddingHorizontalXlLarge(),
            title = when (type) {
                InsuranceType.FULL -> stringResource(R.string.title_insurance_full_salary)
                InsuranceType.OTHER -> stringResource(R.string.helper_insurance_full_salary)
            },
            subtitle = when (type) {
                InsuranceType.FULL -> stringResource(R.string.title_insurance_contract_salary)
                InsuranceType.OTHER -> stringResource(R.string.helper_insurance_contract_salary)
            },
            selected = selectedInsurance == type,
            onClick = {
                onAction(InputSalaryUiIntent.InsuranceTypeChangeIntent(type))
            },
        ) {
            if (type == InsuranceType.OTHER && selectedInsurance == InsuranceType.OTHER) {
                XSmartTextField(
                    value = otherInsuranceAmount,
                    onValueChange = {
                        onAction(InputSalaryUiIntent.InsuranceSalaryChangeIntent(it))
                    },
                    placeholder = {
                        Text(text = stringResource(R.string.vn_salary_insurance_title))
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = Spacing.large),
                    maxLines = 1,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Decimal,
                        imeAction = ImeAction.Done,
                    ),
                    visualTransformation = XSmartTextFieldTransformation(),
                    colors = SalaryCalculatorTextFieldDefault.color(
                        focusedContainerColor = MaterialTheme.colorScheme.background,
                        unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    ),
                )
            }
        }
    }
}

@Composable
fun AllowancesSection(
    allowances: String,
    selectedAllowanceType: AllowanceType,
    onAction: (InputSalaryUiIntent) -> Unit,
) {
    val allowanceTypes = remember { AllowanceType.entries }
    Text(
        stringResource(R.string.title_allowances),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )

    TabRow(
        selectedTabIndex = allowanceTypes.indexOf(selectedAllowanceType),
        containerColor = MaterialTheme.colorScheme.surface,
        modifier = Modifier
            .padding(top = Spacing.medium)
            .paddingHorizontalLarge()
            .clip(MaterialTheme.shapes.medium),
        indicator = {},
        divider = {},
    ) {
        allowanceTypes.forEach { type ->
            val selected = selectedAllowanceType == type
            Tab(
                modifier = Modifier
                    .padding(4.dp)
                    .clip(MaterialTheme.shapes.medium)
                    .background(
                        if (selected) {
                            MaterialTheme.colorScheme.background
                        } else {
                            Color.Unspecified
                        },
                        MaterialTheme.shapes.medium,
                    ),
                selected = selected,
                onClick = {
                    onAction(InputSalaryUiIntent.ChangeAllowanceType(type))
                },
                text = {
                    Text(
                        getAllowanceTypeDisplay(type),
                        style = MaterialTheme.typography.titleMedium,
                    )
                },
            )
        }
    }

    XSmartTextField(
        value = allowances,
        onValueChange = {
            onAction(InputSalaryUiIntent.ChangeAllowanceMoney(it))
        },
        placeholder = {
            Text(
                stringResource(R.string.placeholder_lunch_allowance),
            )
        },
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge()
            .padding(top = Spacing.medium),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Decimal,
            imeAction = ImeAction.Done,
        ),
        visualTransformation = XSmartTextFieldTransformation(),
        shape = MaterialTheme.shapes.small,
        colors = SalaryCalculatorTextFieldDefault.color(),
    )
}

@Composable
fun getAllowanceTypeDisplay(allowanceType: AllowanceType) = when (allowanceType) {
    AllowanceType.SEPARATED -> stringResource(R.string.title_allowances_separate_salary)
    AllowanceType.INCLUDED -> stringResource(R.string.label_allowances_include_salary)
}

@Composable
fun Union(
    allowances: String,
    selectedAllowanceType: AllowanceType,
    onAction: (InputSalaryUiIntent) -> Unit,
) {
    val allowanceTypes = remember { AllowanceType.entries }
    Text(
        stringResource(R.string.title_allowances),
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier
            .padding(top = Spacing.large)
            .paddingHorizontalXlLarge(),
    )

}

@Preview(
    showSystemUi = true,
)
@Composable
fun InputSalaryScreenPreview() {
    SalaryCalculatorTheme {
        InputSalaryScreen(
            InputSalaryUiState(
                income = "",
                insuranceType = InsuranceType.OTHER,
            ),
        )
    }
}
