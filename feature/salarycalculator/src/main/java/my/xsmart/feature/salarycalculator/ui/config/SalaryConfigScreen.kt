package my.xsmart.feature.salarycalculator.ui.config

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.component.DeductionInput
import my.xsmart.feature.salarycalculator.component.TaxBracketTable
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMode
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiEffect
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiIntent
import my.xsmart.feature.salarycalculator.ui.config.state.SalaryConfigUiState
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.share.ui.extension.paddingHorizontalLarge
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.theme.spacing
import my.xsmart.share.ui.widget.XSmartButton
import org.koin.androidx.compose.koinViewModel
import java.util.UUID

@Composable
fun SalaryConfigRoute(
    viewModel: SalaryConfigViewModel = koinViewModel(),
    onBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()
    val snackBarState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effect ->
            when (effect) {
                SalaryConfigUiEffect.ConfigSaved -> {
                    snackBarState.showSnackbar("Configuration saved successfully")
                }

                SalaryConfigUiEffect.NavigateBack -> onBack()
                SalaryConfigUiEffect.ShowSaveConfigFail -> snackBarState.showSnackbar("Configuration saved failed")
            }
        }
    }

    SalaryConfigScreen(
        uiState = uiState.value,
        onAction = viewModel::setIntent,
        onBack = onBack,
        snackBarHostState = snackBarState,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SalaryConfigScreen(
    uiState: SalaryConfigUiState,
    onAction: (SalaryConfigUiIntent) -> Unit,
    onBack: () -> Unit,
    snackBarHostState: SnackbarHostState = remember { SnackbarHostState() },
) {
    val configModes = remember {
        listOf(
            VnSalaryConfigMode.BEFORE_2026,
            VnSalaryConfigMode.AFTER_2026,
            VnSalaryConfigMode.CUSTOM,
        )
    }

    Scaffold(
        modifier = Modifier.imePadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_salary_config),
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
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackBarHostState)
        },
        bottomBar = {
            XSmartButton(
                onClick = {
                    onAction(SalaryConfigUiIntent.SaveConfig)
                },
                content = stringResource(R.string.action_save_config),
                modifier = Modifier
                    .windowInsetsPadding(WindowInsets.navigationBars)
                    .padding(top = Spacing.large, bottom = Spacing.large)
                    .paddingHorizontalLarge(),
            )
        },
    ) { paddingValues ->
        if (uiState.isLoading && uiState.currentConfigModel == null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                CircularProgressIndicator()
            }
        } else {
            Column(
                Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
                    .verticalScroll(state = rememberScrollState()),
            ) {
                // Tab Selector
                TabRow(
                    selectedTabIndex = configModes.indexOf(uiState.currentMode),
                    containerColor = MaterialTheme.colorScheme.surface,
                    modifier = Modifier
                        .padding(top = Spacing.medium)
                        .paddingHorizontalLarge()
                        .clip(MaterialTheme.shapes.small),
                    indicator = {},
                    divider = {},
                ) {
                    configModes.forEach { mode ->
                        val selected = uiState.currentMode == mode
                        Tab(
                            modifier = Modifier
                                .padding(4.dp)
                                .clip(MaterialTheme.shapes.extraSmall)
                                .background(
                                    if (selected) {
                                        MaterialTheme.colorScheme.background
                                    } else {
                                        Color.Unspecified
                                    },
                                    MaterialTheme.shapes.extraSmall,
                                ),
                            selected = selected,
                            onClick = {
                                onAction(SalaryConfigUiIntent.ChangeMode(mode))
                            },
                            text = {
                                Text(
                                    getConfigModeLabel(mode),
                                    style = MaterialTheme.typography.titleSmall,
                                )
                            },
                        )
                    }
                }

                Spacer(modifier = Modifier.padding(top = Spacing.large))

                // Deduction Inputs
                uiState.currentConfigModel?.let { config ->

                    Text(
                        text = stringResource(R.string.title_deductions),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                start = MaterialTheme.spacing.lg,
                                bottom = MaterialTheme.spacing.md,
                            ),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface,
                    )

                    DeductionInput(
                        label = stringResource(R.string.label_personal_deduction),
                        value = config.personalDeduction,
                        onChange = { newValue ->
                            onAction(SalaryConfigUiIntent.UpdatePersonalDeduction(newValue))
                        },
                        disabled = !config.isEditable,
                        modifier = Modifier
                            .fillMaxWidth()
                            .paddingHorizontalLarge(),
                    )

                    Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.lg))

                    DeductionInput(
                        label = stringResource(R.string.label_dependent_deduction),
                        value = config.dependentDeduction,
                        onChange = { newValue ->
                            onAction(SalaryConfigUiIntent.UpdateDependentDeduction(newValue))
                        },
                        disabled = !config.isEditable,
                        modifier = Modifier
                            .fillMaxWidth()
                            .paddingHorizontalLarge(),
                    )

                    Spacer(modifier = Modifier.padding(top = MaterialTheme.spacing.lg))

                    // Tax Bracket Table
                    TaxBracketTable(
                        brackets = config.brackets,
                        modifier = Modifier.paddingHorizontalLarge(),
                    )

                    Spacer(modifier = Modifier.padding(top = Spacing.xLarge))
                }
            }
        }
    }
}

@Composable
private fun getConfigModeLabel(mode: VnSalaryConfigMode): String = when (mode) {
    VnSalaryConfigMode.BEFORE_2026 -> stringResource(R.string.tab_before_2026)
    VnSalaryConfigMode.AFTER_2026 -> stringResource(R.string.tab_after_2026)
    VnSalaryConfigMode.CUSTOM -> stringResource(R.string.tab_custom)
}

@Preview(showSystemUi = true)
@Composable
fun SalaryConfigScreenPreview() {
    SalaryCalculatorTheme {
        SalaryConfigScreen(
            uiState = SalaryConfigUiState(
                currentMode = VnSalaryConfigMode.BEFORE_2026,
                currentConfigModel = ConfigConstants.configsModel[VnSalaryConfigMode.BEFORE_2026],
                isLoading = false,
            ),
            onAction = {},
            onBack = {},
        )
    }
}
