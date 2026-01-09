package my.xsmart.feature.salarycalculator.component

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.OpenInNew
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.DeductionEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.TaxInfoEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.ui.input.ui.*
import my.xsmart.feature.salarycalculator.ui.result.state.DetailedCalculationUiState
import my.xsmart.feature.salarycalculator.ui.result.state.TaxBracketModel
import my.xsmart.share.ui.theme.spacing
import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale


@Composable
fun DetailedCalculation(
    uiState: DetailedCalculationUiState,
    modifier: Modifier = Modifier
) {
    uiState.data ?: return
    val data = uiState.data
    val fmt = { value: KmmBigDecimal -> AmountFormatter.toDisplayAmount(value) }
    val fmtMoney = { value: KmmBigDecimal -> "${AmountFormatter.toDisplayAmount(value)} VND" }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
        ) {
            Text(
                text = stringResource(R.string.title_detailed_calculation),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            )

            // Gross Salary
            Row(
                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.label_gross_salary),
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold,
                    color = twSlate700
                )
                Text(
                    text = fmt(data.grossSalary),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 4.dp),
                color = twSlate100
            )

            // Insurance
            Column(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .drawLeftBorder(Teal500.copy(alpha = 0.2f), 2.dp)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smPlus)
            ) {
                InsuranceRow(
                    label = stringResource(R.string.label_social_insurance),
                    percent = "8%",
                    amount = "-${fmt(data.insurance.socialInsurance)}"
                )
                InsuranceRow(
                    label = stringResource(R.string.label_health_insurance),
                    percent = "1.5%",
                    amount = "-${fmt(data.insurance.healthInsurance)}"
                )
                InsuranceRow(
                    label = stringResource(R.string.label_unemployment_insurance),
                    percent = "1%",
                    amount = "-${fmt(data.insurance.unemploymentInsurance)}"
                )
            }

            // Before Tax
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                color = twSlate100.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = stringResource(R.string.label_before_tax_income),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.SemiBold,
                        color = twSlate700
                    )
                    Text(
                        text = fmt(data.taxInfo.beforeTaxIncome),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }

            // Deductions
            Column(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .drawLeftBorder(Indigo500.copy(alpha = 0.2f), 2.dp)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smPlus)
            ) {
                DeductionRow(
                    label = stringResource(R.string.label_personal_deduction),
                    amount = "-${fmt(data.deduction.personal)}"
                )
                DeductionRow(
                    label = stringResource(R.string.label_dependent_deduction) + " (${uiState.data.dependents})",
                    amount = if (data.deduction.dependent == KmmBigDecimal("0")) "0" else "-${fmt(data.deduction.dependent)}"
                )
                DeductionRow(
                    label = stringResource(R.string.label_allowances_exempt),
                    amount = "0",
                    isExempt = true
                )
            }

            // Tax Block
            TaxBlock(
                taxableIncome = data.taxInfo.taxableIncome,
                totalTax = data.taxInfo.totalTax,
                brackets = uiState.taxBrackets
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 16.dp),
                color = twSlate100
            )

            // Final Net
            Row(
                modifier = Modifier.fillMaxWidth().padding(top = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.label_net_salary),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )
                Text(
                    text = fmtMoney(data.netSalary),
                    style = MaterialTheme.typography.titleLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Composable
private fun InsuranceRow(label: String, percent: String, amount: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Text(
                text = percent,
                style = MaterialTheme.typography.labelSmall,
                fontWeight = FontWeight.Bold,
                color = twSlate400,
                modifier = Modifier
                    .background(twSlate100, RoundedCornerShape(4.dp))
                    .padding(horizontal = 6.dp, vertical = 2.dp)
            )
        }
        Text(
            text = amount,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = twSlate800
        )
    }
}

@Composable
private fun DeductionRow(label: String, amount: String, isExempt: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text = amount,
            style = MaterialTheme.typography.bodySmall,
            fontWeight = FontWeight.Medium,
            color = if (isExempt || amount == "0") twSlate400 else twSlate800
        )
    }
}

@Composable
fun TaxBlock(
    taxableIncome: KmmBigDecimal,
    totalTax: KmmBigDecimal,
    brackets: List<TaxBracketModel>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
            .background(twSlate100.copy(alpha = 0.4f), RoundedCornerShape(16.dp))
            .border(1.dp, twSlate100, RoundedCornerShape(16.dp))
            .clip(RoundedCornerShape(16.dp))
    ) {
        // Blob decoration
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .offset(x = 24.dp, y = (-24).dp)
                .size(96.dp)
                .background(Rose500.copy(alpha = 0.05f), CircleShape)
                .blur(40.dp)
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.md)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .size(28.dp)
                            .background(Rose100, RoundedCornerShape(8.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.AccountBalance,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            tint = Rose600
                        )
                    }
                    Text(
                        text = stringResource(R.string.label_personal_income_tax),
                        style = MaterialTheme.typography.bodySmall,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
                TextButton(
                    onClick = { /* Open tax structure */ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.height(24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_tax_structure),
                        style = MaterialTheme.typography.labelMedium,
                        color = twSlate500
                    )
                    Spacer(Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.OpenInNew,
                        contentDescription = null,
                        modifier = Modifier.size(14.dp),
                        tint = twSlate500
                    )
                }
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = stringResource(R.string.label_taxable_income),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = formatNumber(taxableIncome),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            }

            DashedDivider(
                color = twSlate300,
                thickness = 1.dp
            )

            // Brackets
            Column(verticalArrangement = Arrangement.spacedBy(MaterialTheme.spacing.smPlus)) {
                brackets.forEach { bracket ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(if (bracket.isActive) 1f else 0.4f),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Text(
                                text = "${bracket.percent}%",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                color = twSlate500,
                                modifier = Modifier.width(40.dp)
                            )
                            Text(
                                text = bracket.min.toString(),
                                style = MaterialTheme.typography.labelSmall,
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        }
                        Text(
                            text = formatNumber(bracket.amount),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.SemiBold,
                            color = twSlate800
                        )
                    }
                }
            }

            HorizontalDivider(color = twSlate200)

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.label_tax_amount),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.SemiBold,
                    color = Rose700
                )
                Text(
                    text = "-${formatNumber(totalTax)}",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                    color = Rose600
                )
            }
        }
    }
}

@Composable
fun DashedDivider(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.outlineVariant,
    thickness: Dp = 1.dp,
    dashWidth: Dp = 4.dp,
    gapWidth: Dp = 4.dp
) {
    Canvas(modifier = modifier.fillMaxWidth().height(thickness)) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f),
            pathEffect = PathEffect.dashPathEffect(floatArrayOf(dashWidth.toPx(), gapWidth.toPx()), 0f),
            strokeWidth = thickness.toPx()
        )
    }
}

private fun formatNumber(value: KmmBigDecimal): String {
    val formatter = NumberFormat.getInstance(Locale("vi", "VN"))
    return formatter.format(value.toString().toDoubleOrNull() ?: 0.0)
}

fun Modifier.drawLeftBorder(color: Color, width: Dp): Modifier = drawBehind {
    val strokeWidth = width.toPx()
    drawLine(
        color = color,
        start = Offset(strokeWidth / 2, 0f),
        end = Offset(strokeWidth / 2, size.height),
        strokeWidth = strokeWidth
    )
}



@Preview(showBackground = true)
@Composable
fun DetailedCalculationPreview() {
    SalaryCalculatorTheme {
        Box(modifier = Modifier.padding(16.dp).background(twBackgroundLight)) {
            DetailedCalculation(
                uiState = DetailedCalculationUiState(
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
                        TaxBracketModel(5, min = BigDecimal(0), max = BigDecimal(5000000), KmmBigDecimal("250000"), true),
                        TaxBracketModel(10, min = BigDecimal(0), max = BigDecimal(5000000), KmmBigDecimal("500000"), true),
                        TaxBracketModel(15, min = BigDecimal(0), max = BigDecimal(5000000), KmmBigDecimal("335000"), true),
                        TaxBracketModel(20, min = BigDecimal(0), max = BigDecimal(5000000), KmmBigDecimal("0"), false)
                    ),
                )
            )
        }
    }
}
