package my.xsmart.feature.salarycalculator.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.feature.salarycalculator.R
import my.xsmart.feature.salarycalculator.ui.config.data.ConfigConstants
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracket
import my.xsmart.feature.salarycalculator.ui.config.model.TaxBracketColorTheme
import my.xsmart.feature.salarycalculator.ui.input.ui.Indigo500
import my.xsmart.feature.salarycalculator.ui.input.ui.Orange500
import my.xsmart.feature.salarycalculator.ui.input.ui.Purple500
import my.xsmart.feature.salarycalculator.ui.input.ui.Rose500
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.feature.salarycalculator.ui.input.ui.Teal500
import my.xsmart.share.ui.theme.spacing

@Composable
fun  TaxBracketTable(
    brackets: List<TaxBracket>,
    modifier: Modifier = Modifier,
) {
        Column(modifier = modifier.fillMaxWidth()) {
            // Header
            Text(
                text = stringResource(R.string.title_progressive_tax_scale),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = MaterialTheme.spacing.md),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onSurface,
            )

            // Table
            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.surfaceVariant,
                        MaterialTheme.shapes.large,
                    ),
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colorScheme.surface,
            ) {
                Column {
                    // Table Header
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(MaterialTheme.colorScheme.surfaceVariant)
                            .padding(horizontal = MaterialTheme.spacing.md, vertical = MaterialTheme.spacing.md),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = stringResource(R.string.label_level),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.weight(0.1f),
                            textAlign = TextAlign.Center,
                        )
                        Spacer(Modifier.width(MaterialTheme.spacing.sm))

                        Text(
                            text = stringResource(R.string.label_taxable_income),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.7f),
                        )
                        Text(
                            text = stringResource(R.string.label_rate),
                            style = MaterialTheme.typography.labelSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(0.2f),
                            textAlign = TextAlign.End,
                        )
                    }

                    brackets.forEach { bracket ->
                        Column {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(
                                        horizontal = MaterialTheme.spacing.md,
                                        vertical = MaterialTheme.spacing.md,
                                    ),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                // Level indicator
                                Box(
                                    modifier = Modifier
                                        .weight(0.1f)
                                        .size(24.dp),
                                    contentAlignment = Alignment.Center,
                                ) {
                                    Text(
                                        text = bracket.id.toString(),
                                        modifier = Modifier
                                            .size(24.dp)
                                            .clip(CircleShape)
                                            .background(MaterialTheme.colorScheme.surfaceVariant)
                                            .wrapContentHeight(),
                                        style = MaterialTheme.typography.labelSmall,
                                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                    )
                                }

                                Spacer(Modifier.width(MaterialTheme.spacing.sm))

                                // Income range
                                Text(
                                    text = bracket.label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurface,
                                    fontWeight = FontWeight.Medium,
                                    modifier = Modifier.weight(0.7f),
                                )

                                Spacer(Modifier.width(MaterialTheme.spacing.sm))

                                // Rate badge
                                Box(modifier = Modifier.weight(0.2f)) {
                                    Surface(
                                        modifier = Modifier.align(Alignment.CenterEnd),
                                        shape = MaterialTheme.shapes.small,
                                        color = MaterialTheme.colorScheme.background,
                                        border = BorderStroke(1.dp, getTaxBracketColor(bracket.colorTheme))
                                    ) {
                                        Text(
                                            text = "${bracket.rate}%",
                                            style = MaterialTheme.typography.labelMedium,
                                            color = getTaxBracketColor(bracket.colorTheme),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.padding(
                                                horizontal = MaterialTheme.spacing.sm,
                                                vertical = MaterialTheme.spacing.xxs,
                                            ),
                                        )
                                    }
                                }
                            }

                            if (bracket != brackets.last()) {
                                HorizontalDivider(
                                    color = MaterialTheme.colorScheme.surfaceVariant,
                                    thickness = 1.dp,
                                )
                            }
                        }
                    }
                }
            }
        }
}

@Composable
private fun getTaxBracketColor(theme: TaxBracketColorTheme) = when (theme) {
    TaxBracketColorTheme.GREEN -> Teal500
    TaxBracketColorTheme.BLUE -> MaterialTheme.colorScheme.primary
    TaxBracketColorTheme.INDIGO -> Indigo500
    TaxBracketColorTheme.PURPLE -> Purple500
    TaxBracketColorTheme.PINK -> Rose500
    TaxBracketColorTheme.ORANGE -> Orange500
    TaxBracketColorTheme.RED -> MaterialTheme.colorScheme.error
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F7F8)
@Composable
fun TaxBracketTableBefore2026Preview() {
    SalaryCalculatorTheme {
        TaxBracketTable(
            brackets = ConfigConstants.BRACKETS_BEFORE_2026,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F7F8)
@Composable
fun TaxBracketTableAfter2026Preview() {
    SalaryCalculatorTheme {
        TaxBracketTable(
            brackets = ConfigConstants.BRACKETS_AFTER_2026,
            modifier = Modifier.padding(16.dp),
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFF5F7F8)
@Composable
fun TaxBracketTableEditablePreview() {
    SalaryCalculatorTheme {
        TaxBracketTable(
            brackets = ConfigConstants.BRACKETS_AFTER_2026,
            modifier = Modifier.padding(16.dp),
        )
    }
}
