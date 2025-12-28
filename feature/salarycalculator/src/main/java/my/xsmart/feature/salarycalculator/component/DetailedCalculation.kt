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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.CalculatorMode
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorEntity
import my.phatndt.xsmart.share.domain.entity.vnsalarycalculator.VnSalaryCalculatorInsuranceEntity
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.ui.input.ui.*
import my.xsmart.share.ui.theme.Spacing
import java.text.NumberFormat
import java.util.Locale

data class TaxBracketUiState(
    val percent: Int,
    val range: String,
    val amount: KmmBigDecimal,
    val isActive: Boolean
)

data class DetailedCalculationUiState(
    val data: VnSalaryCalculatorEntity,
    val taxBrackets: List<TaxBracketUiState> = emptyList(),
    val dependents: Int = 0
)

@Composable
fun DetailedCalculation(
    uiState: DetailedCalculationUiState,
    modifier: Modifier = Modifier
) {
    val data = uiState.data
    val fmt = { value: KmmBigDecimal -> formatNumber(value) }
    val fmtMoney = { value: KmmBigDecimal -> "${formatNumber(value)} VND" }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(Spacing.medium)
        ) {
            Text(
                text = stringResource(R.string.title_detailed_calculation),
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = twSlate900
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
                    fontWeight = FontWeight.Bold,
                    color = twSlate900
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
                verticalArrangement = Arrangement.spacedBy(10.dp)
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
                        text = fmt(data.beforeTaxIncome),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                        color = twSlate900
                    )
                }
            }

            // Deductions
            Column(
                modifier = Modifier
                    .padding(start = 4.dp)
                    .drawLeftBorder(Indigo500.copy(alpha = 0.2f), 2.dp)
                    .padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                DeductionRow(
                    label = stringResource(R.string.label_personal_deduction),
                    amount = "-${fmt(data.personalDeduction)}"
                )
                DeductionRow(
                    label = stringResource(R.string.label_dependent_deduction) + " (${uiState.dependents})",
                    amount = if (data.dependentDeduction == KmmBigDecimal("0")) "0" else "-${fmt(data.dependentDeduction)}"
                )
                DeductionRow(
                    label = stringResource(R.string.label_allowances_exempt),
                    amount = "0",
                    isExempt = true
                )
            }

            // Tax Block
            TaxBlock(
                taxableIncome = data.taxableIncome,
                totalTax = data.tax,
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
                    color = Primary
                )
                Text(
                    text = fmtMoney(data.netSalary),
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Primary
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
                color = twSlate600
            )
            Text(
                text = percent,
                style = TextStyle(fontSize = 10.sp),
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
            color = twSlate600
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
    brackets: List<TaxBracketUiState>
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
            verticalArrangement = Arrangement.spacedBy(12.dp)
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
                        color = twSlate900
                    )
                }
                TextButton(
                    onClick = { /* Open tax structure */ },
                    contentPadding = PaddingValues(0.dp),
                    modifier = Modifier.height(24.dp)
                ) {
                    Text(
                        text = stringResource(R.string.label_tax_structure),
                        style = TextStyle(fontSize = 12.sp),
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
                    color = twSlate600
                )
                Text(
                    text = formatNumber(taxableIncome),
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Bold,
                    color = twSlate900
                )
            }

            DashedDivider(
                color = twSlate300,
                thickness = 1.dp
            )

            // Brackets
            Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
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
                                text = bracket.range,
                                style = MaterialTheme.typography.labelSmall,
                                color = twSlate600
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

// Helper colors not in SalaryCalculatorTheme but used in React
val twSlate700 = Color(0xFF334155)
val twSlate800 = Color(0xFF1E293B)
val twSlate500 = Color(0xFF64748B)
val twSlate300 = Color(0xFFCBD5E1)
val Rose100 = Color(0xFFFFE4E6)
val Rose700 = Color(0xFFBE123C)

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
                            unemploymentInsurance = KmmBigDecimal("300000")
                        ),
                        beforeTaxIncome = KmmBigDecimal("26850000"),
                        personalDeduction = KmmBigDecimal("11000000"),
                        dependentDeduction = KmmBigDecimal("0"),
                        taxableIncome = KmmBigDecimal("15850000"),
                        tax = KmmBigDecimal("1085000"),
                        calculatorMode = CalculatorMode.GROSS_TO_NET,
                        allowance = KmmBigDecimal("0"),
                        bonus = KmmBigDecimal("0")
                    ),
                    taxBrackets = listOf(
                        TaxBracketUiState(5, "0 - 5.000.000", KmmBigDecimal("250000"), true),
                        TaxBracketUiState(10, "5.000.000 - 10.000.000", KmmBigDecimal("500000"), true),
                        TaxBracketUiState(15, "10.000.000 - 18.000.000", KmmBigDecimal("335000"), true),
                        TaxBracketUiState(20, "18.000.000 - 32.000.000", KmmBigDecimal("0"), false)
                    ),
                    dependents = 0
                )
            )
        }
    }
}
