package my.xsmart.feature.salarycalculator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.phatndt.xsmart.share.common.amount.AmountFormatter
import my.phatndt.xsmart.share.common.amount.KmmBigDecimal
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTheme
import my.xsmart.share.ui.component.row.TextAttribute
import my.xsmart.share.ui.component.row.TextTitleValueRowDefault
import my.xsmart.share.ui.component.row.TitleValueRow
import my.xsmart.share.ui.theme.Spacing
import java.math.BigDecimal

@Composable
fun SalaryBreakdownItem(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    percent: Float = 0.5f,
    titleAttribute: TextAttribute = SalaryBreakdownItemDefault.titleAttribute(),
    valueAttribute: TextAttribute = SalaryBreakdownItemDefault.valueAttribute(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    TitleValueRow(
        title = {
            Row(
                modifier = Modifier.weight(percent),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(
                            valueAttribute.color.copy(alpha = 0.5f),
                            CircleShape,
                        )
                        .padding(2.dp)
                        .background(valueAttribute.color, CircleShape),
                )
                Spacer(modifier = Modifier.width(Spacing.medium))
                Text(
                    text = title,
                    style = titleAttribute.style,
                    color = titleAttribute.color,
                    fontWeight = titleAttribute.fontWeight,
                )
            }
        },
        value = {
            Text(
                text = value,
                modifier = Modifier.weight(1 - percent),
                style = valueAttribute.style,
                color = valueAttribute.color,
                textAlign = valueAttribute.textAlign,
                fontWeight = valueAttribute.fontWeight,
            )
        },
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    )
}

object SalaryBreakdownItemDefault {

    @Composable
    fun titleAttribute() = TextAttribute(
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
        fontWeight = FontWeight.Medium
    )

    @Composable
    fun titleAttribute(
        color: Color = MaterialTheme.colorScheme.onSurfaceVariant,
        style: TextStyle = MaterialTheme.typography.bodyMedium,
        textAlign: TextAlign = TextAlign.Start,
        fontWeight: FontWeight = FontWeight.Medium,
    ) = TextAttribute(
        color = color,
        style = style,
        textAlign = textAlign,
        fontWeight = fontWeight,
    )

    @Composable
    fun valueAttribute(
        color: Color = MaterialTheme.colorScheme.primary,
    ) = TextAttribute(
        color = color,
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.End,
        fontWeight = FontWeight.Bold,
    )

    @Composable
    fun valueAttribute(
        color: Color = MaterialTheme.colorScheme.primary,
        style: TextStyle = MaterialTheme.typography.bodyMedium,
        textAlign: TextAlign = TextAlign.End,
        fontWeight: FontWeight = FontWeight.Bold,
    ) = TextAttribute(
        color = color,
        style = style,
        textAlign = textAlign,
        fontWeight = fontWeight,
    )
}


@Composable
@Preview
fun SalaryBreakdownItemPreview() {
    SalaryCalculatorTheme {
        Scaffold() {
            Column(modifier = Modifier.padding(it)) {
                SalaryBreakdownItem(
                    "Title",
                    "Value",
                    modifier = Modifier.fillMaxWidth(),
                    valueAttribute = TextAttribute().copy(
                        color = MaterialTheme.colorScheme.primary,
                        textAlign = TextAlign.End,
                    )
                )
            }
        }
    }
}
