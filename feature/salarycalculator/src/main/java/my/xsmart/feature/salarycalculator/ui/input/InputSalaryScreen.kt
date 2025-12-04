package my.xsmart.feature.salarycalculator.ui.input

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MenuAnchorType
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
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
import my.xsmart.feature.salarycalculator.ui.input.model.AreaModel
import my.xsmart.feature.salarycalculator.ui.input.state.InputSalaryUiEffect
import my.xsmart.feature.salarycalculator.ui.input.ui.AppTheme
import my.xsmart.share.ui.component.XSmartTextFieldTransformation
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.widget.XSmartButton
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun InputSalaryRoute(
    viewModel: InputSalaryViewModel = koinViewModel(),
    onNavigateToDetail: (VnSalaryCalculatorEntity) -> Unit,
    onBack: () -> Unit,
) {
    InputSalaryScreen(InputSalaryUiState(), {}, {})
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
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSalaryScreen(
    uiState: InputSalaryUiState,
    onAction: (InputSalaryUiIntent) -> Unit = {},
    onBack: () -> Unit = {},
) {
    // <editor-fold desc="Insurance">
    val insuranceTypes = remember {
        InsuranceType.entries.toList()
    }

    val areaTypes = remember {
        Area.entries.map { area ->
            AreaModel(
                text = when (area) {
                    Area.I -> "Region I - Urban areas (Hanoi, HCMC, etc.)"
                    Area.II -> "Region II - Provincial cities"
                    Area.III -> "Region III - Other cities & districts"
                    Area.IV -> "Region IV - Rural areas"
                },
                area = area,
            )
        }
    }

    val selectedArea = remember(uiState.area) {
        areaTypes.find { it.area == uiState.area }
    }

    var expanded by remember { mutableStateOf(false) }
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
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(state = rememberScrollState())
                .fillMaxSize(),
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
                modifier = Modifier.padding(
                    start = 16.dp,
                    end = 16.dp,
                    top = 16.dp,
                ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    OutlinedTextField(
                        value = uiState.income.orEmpty(),
                        onValueChange = {
                            onAction(InputSalaryUiIntent.IncomeChangeIntent(it))
                        },
                        label = {
                            Text(
                                text = "Gross Salary (VND/month)",
                                modifier = Modifier.background(color = Color.Transparent)
                            )
                        },
                        modifier = Modifier.fillMaxWidth(),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Decimal,
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (uiState.numberOfDependents.isNullOrEmpty()) {
                                    localFocusManager.moveFocus(FocusDirection.Next)
                                }
                            },
                        ),
                        visualTransformation = XSmartTextFieldTransformation()
                    )
                    Text(
                        text = "Enter your monthly gross salary",
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 4.dp),
                    )

                    OutlinedTextField(
                        value = uiState.numberOfDependents.orEmpty(),
                        onValueChange = {
                            onAction(InputSalaryUiIntent.NumberOfDependentsChangeIntent(it))
                        },
                        label = {
                            Text(
                                text = stringResource(R.string.vn_salary_number_of_dependents_placeholder),
                                modifier = Modifier.background(color = Color.Transparent)
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 16.dp),
                        maxLines = 1,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = ImeAction.Next,
                        ),
                        keyboardActions = KeyboardActions(
                            onNext = {
                                if (uiState.numberOfDependents.isNullOrEmpty()) {
                                    localFocusManager.moveFocus(FocusDirection.Next)
                                }
                            },
                        ),
                    )
                    Text(
                        text = stringResource(R.string.vn_salary_number_of_dependents_placeholder_description),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 4.dp),
                    )

                    ExposedDropdownMenuBox(
                        expanded = expanded,
                        onExpandedChange = {
                            expanded = it
                        },
                    ) {
                        OutlinedTextField(
                            // The `menuAnchor` modifier must be passed to the text field to handle
                            // expanding/collapsing the menu on click. A read-only text field has
                            // the anchor type `PrimaryNotEditable`.
                            readOnly = true,
                            singleLine = true,
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(expanded)
                            },
                            value = selectedArea?.text.orEmpty(),
                            onValueChange = {},
                            label = {
                                Text(
                                    text = stringResource(R.string.vn_salary_area_title),
                                    modifier = Modifier.background(color = Color.Transparent)
                                )
                            },
                            modifier = Modifier
                                .menuAnchor(MenuAnchorType.PrimaryNotEditable)
                                .fillMaxWidth()
                                .padding(top = 16.dp),
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
                        ExposedDropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                        ) {
                            areaTypes.forEach { option ->
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            option.text,
                                            style = MaterialTheme.typography.bodyLarge,
                                        )
                                    },
                                    onClick = {
                                        onAction(InputSalaryUiIntent.AreaChangeIntent(option.area))
                                        expanded = false
                                    },
                                    contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                                )
                            }
                        }
                    }
                    Text(
                        text = stringResource(R.string.vn_salary_area_description),
                        style = MaterialTheme.typography.labelLarge,
                        modifier = Modifier.padding(top = 4.dp),
                    )
                    XSmartButton(
                        onClick = {
                            onAction(InputSalaryUiIntent.CalculatorSalary)
                        },
                        content = stringResource(R.string.vn_salary_btn_text_gross_to_net),
                        modifier = Modifier.padding(top = Spacing.large),
                    )
                }
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(
                        top = 16.dp,
                        start = 16.dp,
                        end = 16.dp,
                    ),
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                ) {
                    Text(
                        text = "Information",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    LazyColumn {
                        items(10) { index ->
                            Text(
                                text = "Social Insurance: 8% (employee contribution)",
                                style = MaterialTheme.typography.titleMedium,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(
    showSystemUi = true,
)
@Composable
fun InputSalaryScreenPreview() {
    AppTheme {
        InputSalaryScreen(
            InputSalaryUiState(
                income = "",
            ),
        )
    }
}
