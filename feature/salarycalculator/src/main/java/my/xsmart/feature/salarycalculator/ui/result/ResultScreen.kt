package my.xsmart.feature.salarycalculator.ui.result

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.wrapContentHeight
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
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.common.deferred.DeferredText
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.DeductionEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxInfoEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.component.DetailedCalculation
import my.xsmart.feature.salarycalculator.component.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.component.InsuranceDetailRow
import my.xsmart.feature.salarycalculator.component.SalaryBreakdownItem
import my.xsmart.feature.salarycalculator.component.SalaryBreakdownItemDefault
import my.xsmart.feature.salarycalculator.component.TaxBracketUiState
import my.xsmart.feature.salarycalculator.component.drawLeftBorder
import my.xsmart.feature.salarycalculator.ui.input.ui.Indigo500
import my.xsmart.feature.salarycalculator.ui.input.ui.Primary
import my.xsmart.feature.salarycalculator.ui.input.ui.Rose500
import my.xsmart.feature.salarycalculator.ui.input.ui.Rose600
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.feature.salarycalculator.ui.input.ui.Teal500
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegment
import my.xsmart.feature.salarycalculator.ui.result.model.BreakdownSegmentType
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiEffect
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiIntent
import my.xsmart.feature.salarycalculator.ui.result.state.ResultUiState
import my.xsmart.feature.salarycalculator.ui.result.state.SalaryBreakdownUiState
import my.xsmart.share.ui.component.progressbar.HorizontalProgressBar
import my.xsmart.share.ui.component.progressbar.ProgressBarSegment
import my.xsmart.share.ui.component.row.TextTitleValueRow
import my.xsmart.share.ui.component.row.TextTitleValueRowDefault
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
            SalaryBreakdownSection(uiState.salaryBreakdownItems.map {
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
            }, onAction = onAction)
            Spacer(modifier = Modifier.height(Spacing.large))
            DetailedCalculation(
                uiState.detailedCalculationUiState,
                modifier = Modifier.paddingHorizontalLarge(),
            )
//            DetailedCalculationSection(calculationData = calculationData)
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
                    text = formatCurrency(calculationData.netSalary.toDisplayDouble()),
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
fun DetailedCalculationSection(calculationData: VnSalaryCalculatorEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paddingHorizontalLarge()
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = MaterialTheme.shapes.extraLarge,
            )
            .padding(Spacing.xLarge),
    ) {
        Text(
            text = stringResource(R.string.title_detailed_calculation),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
        )

        Spacer(modifier = Modifier.height(Spacing.large))

        // Gross Salary
        TextTitleValueRow(
            title = stringResource(R.string.label_gross_salary),
            value = AmountFormatter.toDisplayAmount(calculationData.grossSalary),
            percent = 0.5f,
            titleAttribute = SalaryBreakdownItemDefault.titleAttribute(
                style = MaterialTheme.typography.titleMedium,
            ),
            valueAttribute =  SalaryBreakdownItemDefault.valueAttribute(
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
            ),
            verticalAlignment = Alignment.CenterVertically,
        )

        Spacer(modifier = Modifier.height(Spacing.large))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(modifier = Modifier.height(Spacing.large))

        // Insurance breakdown
        Column(
            modifier = Modifier
                .drawLeftBorder(
                    MaterialTheme.colorScheme.secondary,
                    2.dp,
                )
                .padding(
                    start = Spacing.large,
                    top = Spacing.small,
                    bottom = Spacing.small,
                ),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium),
        ) {
            InsuranceDetailRow(
                label = stringResource(R.string.label_social_insurance),
                percentage = "8%",
                amount = AmountFormatter.toDisplayAmount(calculationData.insurance.socialInsurance),
            )
            InsuranceDetailRow(
                label = stringResource(R.string.label_health_insurance),
                percentage = "1.5%",
                amount = AmountFormatter.toDisplayAmount(calculationData.insurance.healthInsurance),
            )
            InsuranceDetailRow(
                label = stringResource(R.string.label_unemployment_insurance),
                percentage = "1%",
                amount = AmountFormatter.toDisplayAmount(calculationData.insurance.unemploymentInsurance),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        // Before Tax Income
        TextTitleValueRow(
            title = stringResource(R.string.label_before_tax_income),
            value = AmountFormatter.toDisplayAmount(calculationData.taxInfo.beforeTaxIncome),
            percent = 0.5f,
            titleAttribute = SalaryBreakdownItemDefault.titleAttribute(
                style = MaterialTheme.typography.titleMedium,
            ),
            valueAttribute =  SalaryBreakdownItemDefault.valueAttribute(
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.titleLarge,
            ),
            verticalAlignment = Alignment.CenterVertically,
        )

        Spacer(modifier = Modifier.height(Spacing.large))
        HorizontalDivider(color = MaterialTheme.colorScheme.outlineVariant)
        Spacer(modifier = Modifier.height(Spacing.large))

        // Deductions
        Column(
            modifier = Modifier
                .drawLeftBorder(
                    Indigo500,
                    2.dp,
                )
                .padding(
                    start = Spacing.large,
                    top = Spacing.small,
                    bottom = Spacing.small,
                ),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium),

            ) {
            InsuranceDetailRow(
                label = stringResource(R.string.label_personal_deduction),
                amount = AmountFormatter.toDisplayAmount(calculationData.deduction.personal),
            )
            InsuranceDetailRow(
                label = stringResource(R.string.label_dependent_deduction),
                amount = AmountFormatter.toDisplayAmount(calculationData.deduction.dependent),
            )
            InsuranceDetailRow(
                label = stringResource(R.string.label_allowances_exempt),
                amount = AmountFormatter.toDisplayAmount("0"),
            )
        }

        Spacer(modifier = Modifier.height(Spacing.large))

        // Tax Calculation Card
        TaxCalculationCard(calculationData = calculationData)

        Spacer(modifier = Modifier.height(Spacing.large))
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(Spacing.large))

        // Final Net Salary
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.label_net_salary),
                style = MaterialTheme.typography.titleMedium,
                color = Primary,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "${formatCurrency(calculationData.netSalary.toDisplayDouble())} VND",
                style = MaterialTheme.typography.titleLarge,
                color = Primary,
                fontWeight = FontWeight.Bold,
            )
        }
    }
}

@Composable
fun DetailRow(
    label: String,
    amount: Double,
    isBold: Boolean = false,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = if (isBold) FontWeight.Bold else FontWeight.SemiBold,
        )
        Text(
            text = formatCurrency(abs(amount)),
            style = if (isBold) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun TaxCalculationCard(calculationData: VnSalaryCalculatorEntity) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.4f),
                shape = MaterialTheme.shapes.large,
            )
            .padding(Spacing.large),
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = stringResource(R.string.label_personal_income_tax),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
            TextButton(onClick = { /* TODO */ }) {
                Text(
                    text = stringResource(R.string.label_tax_structure),
                    style = MaterialTheme.typography.labelSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }

        Spacer(modifier = Modifier.height(Spacing.medium))

        // Taxable Income
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.label_taxable_income),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(
                text = formatCurrency(calculationData.taxInfo.taxableIncome.toDisplayDouble()),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold,
            )
        }

        Spacer(modifier = Modifier.height(Spacing.medium))
        HorizontalDivider(color = MaterialTheme.colorScheme.outline)
        Spacer(modifier = Modifier.height(Spacing.medium))

        // Total Tax
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = stringResource(R.string.label_tax_amount),
                style = MaterialTheme.typography.bodyMedium,
                color = Rose600,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                text = "-${formatCurrency(calculationData.taxInfo.totalTax.toDisplayDouble())}",
                style = MaterialTheme.typography.titleMedium,
                color = Rose600,
                fontWeight = FontWeight.Bold,
            )
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

private fun formatCurrency(amount: Double): String {
    return NumberFormat.getNumberInstance(Locale.US).format(amount.toLong())
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
                    allowance = KmmBigDecimal("0"),
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
                        allowance = KmmBigDecimal("0"),
                    ),
                    taxBrackets = listOf(
                        TaxBracketUiState(
                            percent = 5,
                            range = "0 - 5.000.000",
                            amount = KmmBigDecimal("250000"),
                            isActive = true
                        ),
                        TaxBracketUiState(
                            percent = 10,
                            range = "5.000.000 - 10.000.000",
                            amount = KmmBigDecimal("500000"),
                            isActive = true
                        ),
                        TaxBracketUiState(
                            percent = 15,
                            range = "10.000.000 - 18.000.000",
                            amount = KmmBigDecimal("335000"),
                            isActive = true
                        ),
                        TaxBracketUiState(
                            percent = 20,
                            range = "18.000.000 - 32.000.000",
                            amount = KmmBigDecimal("0"),
                            isActive = false
                        ),
                    ),
                ),
            ),
        )
    }
}
