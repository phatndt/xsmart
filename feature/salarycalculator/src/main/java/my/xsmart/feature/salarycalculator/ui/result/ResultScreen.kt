package my.xsmart.feature.salarycalculator.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.deferred.DeferredText
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.AllowanceType
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.DeductionEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxInfoEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.config.VnSalaryConfigMap
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.component.DetailedCalculation
import my.xsmart.feature.salarycalculator.component.InsuranceDetailRow
import my.xsmart.feature.salarycalculator.component.SalaryBreakdownItem
import my.xsmart.feature.salarycalculator.component.SalaryBreakdownItemDefault
import my.xsmart.feature.salarycalculator.component.drawLeftBorder
import my.xsmart.feature.salarycalculator.ui.input.ui.Indigo500
import my.xsmart.feature.salarycalculator.ui.input.ui.Primary
import my.xsmart.feature.salarycalculator.ui.input.ui.Rose600
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegmentType
import my.xsmart.feature.salarycalculator.ui.result.state.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiEffect
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiIntent
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiState
import my.xsmart.feature.salarycalculator.ui.result.state.SalaryBreakdownUiState
import my.xsmart.feature.salarycalculator.ui.result.state.TaxBracketModel
import my.xsmart.share.ui.component.progressbar.HorizontalProgressBar
import my.xsmart.share.ui.component.progressbar.ProgressBarSegment
import my.xsmart.share.ui.component.row.TextTitleValueRow
import my.xsmart.share.ui.extension.paddingHorizontalLarge
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.widget.XSmartButton
import org.koin.androidx.compose.koinViewModel
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale
import java.util.UUID
import kotlin.math.abs

// Extension function to convert KmmBigDecimal to Double for display
private fun KmmBigDecimal.toDisplayDouble(): Double = this.toString().toDoubleOrNull() ?: 0.0

@Composable
fun ResultRoute(
    viewModel: ResultViewModel = koinViewModel(),
    onNavigateBack: () -> Unit,
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.value.isLoading) {
        viewModel.loadData()
    }

    LaunchedEffect(key1 = UUID.randomUUID()) {
        viewModel.effect.collect { effect ->
            when (effect) {
                ResultUiEffect.NavigateBackToInput -> onNavigateBack()
                ResultUiEffect.ShowShareDialog -> {
                    // TODO: Implement share dialog
                }
                is ResultUiEffect.ShowError -> {
                    // TODO: Show error snackbar
                }
            }
        }
    }

    ResultScreen(
        uiState = uiState.value,
        onAction = viewModel::setIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ResultScreen(
    uiState: ResultUiState,
    onAction: (ResultUiIntent) -> Unit = {},
) {
    val calculationData = uiState.calculationData ?: return

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.title_calculation_result),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.Transparent,
                ),
                navigationIcon = {
                    IconButton(onClick = { onAction(ResultUiIntent.NavigateBack) }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { /* TODO */ }) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        bottomBar = {
            BottomActionButtons(onAction = onAction)
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(bottom = Spacing.large),
        ) {
            NetSalaryCard(calculationData = calculationData)
            Spacer(modifier = Modifier.height(Spacing.large))
            SalaryBreakdownSection(
                uiState.salaryBreakdownItems.map {
                    when (it.type) {
                        BreakdownSegmentType.NET_SALARY -> {
                            SalaryBreakdownUiState(
                                title = DeferredText.Resource(R.string.label_net_salary),
                                percent = it.percentage,
                                color = MaterialTheme.colorScheme.primary,
                                amount = DeferredText.Constant(AmountFormatter.toDisplayAmount(it.amount)),
                            )
                        }

                        BreakdownSegmentType.INSURANCE -> {
                            SalaryBreakdownUiState(
                                title = DeferredText.Resource(R.string.label_insurance),
                                percent = it.percentage,
                                color = MaterialTheme.colorScheme.secondary,
                                amount = DeferredText.Constant(AmountFormatter.toDisplayAmount(it.amount))
                            )
                        }

                        BreakdownSegmentType.TAX -> {
                            SalaryBreakdownUiState(
                                title = DeferredText.Resource(R.string.label_income_tax),
                                percent = it.percentage,
                                color = MaterialTheme.colorScheme.tertiary,
                                amount = DeferredText.Constant(AmountFormatter.toDisplayAmount(it.amount))
                            )
                        }
                    }
                },
                onAction = onAction,
            )
            Spacer(modifier = Modifier.height(Spacing.large))
            DetailedCalculation(
                uiState.detailedCalculationUiState,
                modifier = Modifier.paddingHorizontalLarge(),
            )
            Spacer(modifier = Modifier.height(Spacing.large))
            UsdEquivalent(netSalary = calculationData.netSalary.toDisplayDouble())
        }
    }
}

@Composable
fun NetSalaryCard(calculationData: VnSalaryCalculatorEntity) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .padding(Spacing.large),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(R.string.label_your_net_salary).uppercase(),
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontWeight = FontWeight.SemiBold,
            )
            Spacer(modifier = Modifier.height(Spacing.small))
            Row(
                verticalAlignment = Alignment.Bottom,
            ) {
                Text(
                    text = formatCurrency(calculationData.netSalary),
                    style = MaterialTheme.typography.displayLarge,
                    color = Primary,
                    fontWeight = FontWeight.ExtraBold,
                )
                Spacer(modifier = Modifier.width(Spacing.small))
                Text(
                    text = stringResource(R.string.label_currency_vnd),
                    style = MaterialTheme.typography.headlineMedium,
                    color = Primary,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 4.dp),
                )
            }
            Spacer(modifier = Modifier.height(Spacing.medium))
            Row(
                modifier = Modifier
                    .background(
                        color = MaterialTheme.colorScheme.surfaceVariant,
                        shape = MaterialTheme.shapes.extraLarge,
                    )
                    .padding(horizontal = Spacing.medium, vertical = Spacing.small),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Text(
                    text = stringResource(R.string.label_monthly_income),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Medium,
                )
            }
        }
    }
}

@Composable
fun SalaryBreakdownSection(
    items: List<SalaryBreakdownUiState>,
    onAction: (ResultUiIntent) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .padding(Spacing.large),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.title_salary_breakdown),
                style = MaterialTheme.typography.titleLarge,
            )
            TextButton(onClick = { onAction(ResultUiIntent.ViewChart) }) {
                Text(
                    text = stringResource(R.string.button_view_chart),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary,
                )
            }
        }

        HorizontalProgressBar(
            segments = items.map {
                ProgressBarSegment(
                    label = it.title.resolve(LocalContext.current),
                    percentage = it.percent,
                    color = it.color,
                )
            },
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        items.forEachIndexed { index, state ->
            SalaryBreakdownItem(
                title = state.title.resolve(LocalContext.current),
                value = state.amount.resolve(LocalContext.current),
                modifier = Modifier.padding(horizontal = Spacing.medium),
                valueAttribute = SalaryBreakdownItemDefault.valueAttribute(color = state.color),
            )
            if (index < items.lastIndex) {
                Spacer(modifier = Modifier.height(Spacing.large))
            }
        }
    }
}

@Composable
fun BottomActionButtons(onAction: (ResultUiIntent) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .windowInsetsPadding(WindowInsets.navigationBars)
            .padding(Spacing.large)
            .paddingHorizontalLarge(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.medium),
    ) {
        OutlinedButton(
            onClick = { onAction(ResultUiIntent.Recalculate) },
            modifier = Modifier.weight(1f),
        ) {
            Icon(
                imageVector = Icons.Default.Refresh,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(Spacing.small))
            Text(
                text = stringResource(R.string.button_recalculate),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
        }

        XSmartButton(
            onClick = { onAction(ResultUiIntent.SharePdf) },
            modifier = Modifier.weight(1.5f),
        ) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null,
                modifier = Modifier.size(20.dp),
            )
            Spacer(modifier = Modifier.width(Spacing.small))
            Text(
                text = stringResource(R.string.button_share_pdf),
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun UsdEquivalent(netSalary: Double) {
    val usdAmount = (netSalary / 25000).toInt() // Approximate conversion rate
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = Spacing.xLarge),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(R.string.label_usd_equivalent, NumberFormat.getNumberInstance(Locale.US).format(usdAmount)),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontWeight = FontWeight.Medium,
        )
    }
}

private fun formatCurrency(amount: BigDecimal): String {
    return AmountFormatter.toDisplayAmount(amount)
}

@Preview(showSystemUi = true)
@Composable
fun ResultScreenPreview() {
    SalaryCalculatorTheme {
        ResultScreen(
            uiState = ResultUiState(
                calculationData = VnSalaryCalculatorEntity(
                    grossSalary = KmmBigDecimal("30000000"),
                    netSalary = KmmBigDecimal("25765000"),
                    insurance = VnSalaryCalculatorInsuranceEntity(
                        socialInsurance = KmmBigDecimal("2400000"),
                        healthInsurance = KmmBigDecimal("450000"),
                        unemploymentInsurance = KmmBigDecimal("300000"),
                    ),
                    taxInfo = TaxInfoEntity(
                        beforeTaxIncome = KmmBigDecimal("26850000"),
                        taxableIncome = KmmBigDecimal("15850000"),
                        totalTax = KmmBigDecimal("1085000"),
                        taxBrackets = emptyList()
                    ),
                    deduction = DeductionEntity(
                        personal = KmmBigDecimal("11000000"),
                        dependent = KmmBigDecimal("0"),
                    ),
                    calculatorMode = CalculatorMode.GROSS_TO_NET,
                    allowance = AllowanceEntity(
                        allowance = KmmBigDecimal("0"),
                        allowanceType = AllowanceType.INCLUDED,
                    ),
                    config = VnSalaryConfigMap.newConfig,
                    ),
                salaryBreakdownItems = listOf(
                    BreakdownSegment(
                        type = BreakdownSegmentType.NET_SALARY,
                        amount = BigDecimal("25765000"),
                        percentage = 85.0,
                    ),
                    BreakdownSegment(
                        type = BreakdownSegmentType.INSURANCE,
                        amount = BigDecimal("2400000"),
                        percentage = 85.0,
                    ),
                    BreakdownSegment(
                        type = BreakdownSegmentType.TAX,
                        amount = BigDecimal("1085000"),
                        percentage = 5.0,
                    ),
                ),
                detailedCalculationUiState = DetailedCalculationUiState(
                    data = VnSalaryCalculatorEntity(
                        grossSalary = KmmBigDecimal("30000000"),
                        netSalary = KmmBigDecimal("25765000"),
                        insurance = VnSalaryCalculatorInsuranceEntity(
                            socialInsurance = KmmBigDecimal("2400000"),
                            healthInsurance = KmmBigDecimal("450000"),
                            unemploymentInsurance = KmmBigDecimal("300000"),
                        ),
                        taxInfo = TaxInfoEntity(
                            beforeTaxIncome = KmmBigDecimal("26850000"),
                            taxableIncome = KmmBigDecimal("15850000"),
                            totalTax = KmmBigDecimal("1085000"),
                            taxBrackets = emptyList()
                        ),
                        deduction = DeductionEntity(
                            personal = KmmBigDecimal("11000000"),
                            dependent = KmmBigDecimal("0"),
                        ),
                        calculatorMode = CalculatorMode.GROSS_TO_NET,
                        allowance = AllowanceEntity(
                            allowance = KmmBigDecimal("0"),
                            allowanceType = AllowanceType.INCLUDED,
                        ),
                        config = VnSalaryConfigMap.newConfig,
                    ),
                    taxBrackets = listOf(
                        TaxBracketModel(
                            percent = 5,
                            min = BigDecimal(0), max = BigDecimal(5000000),
                            amount = KmmBigDecimal("250000"),
                            isActive = true
                        ),
                        TaxBracketModel(
                            percent = 10,
                            min = BigDecimal(0), max = BigDecimal(5000000),
                            amount = KmmBigDecimal("500000"),
                            isActive = true
                        ),
                        TaxBracketModel(
                            percent = 15,
                            min = BigDecimal(0), max = BigDecimal(5000000),
                            amount = KmmBigDecimal("335000"),
                            isActive = true
                        ),
                        TaxBracketModel(
                            percent = 20,
                            min = BigDecimal(0), max = BigDecimal(5000000),
                            amount = KmmBigDecimal("0"),
                            isActive = false
                        ),
                    ),
                ),
            ),
        )
    }
}
