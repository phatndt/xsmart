package my.xsmart.feature.salarycalculator.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import my.xsmart.share.ui.component.row.TitleValueRow
import my.xsmart.share.ui.theme.Spacing
import kotlin.math.abs

@Composable
fun InsuranceDetailRow(
    label: String,
    amount: String,
    percentage: String? = null,
) {
    TitleValueRow(
        title = {
            Row(
                verticalAlignment = Alignment.Companion.CenterVertically,
            ) {
                Text(
                    text = label,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                percentage?.let {
                    Spacer(modifier = Modifier.Companion.width(Spacing.small))
                    Text(
                        text = percentage,
                        style = MaterialTheme.typography.labelSmall,
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.Companion
                            .background(
                                color = MaterialTheme.colorScheme.surfaceVariant,
                                shape = MaterialTheme.shapes.extraSmall,
                            )
                            .padding(horizontal = 6.dp, vertical = 2.dp),
                        fontWeight = FontWeight.Companion.SemiBold,
                    )
                }
            }
        },
        value =  {
            Text(
                text = amount,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        },
        modifier = Modifier.Companion.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Companion.CenterVertically,
    )
}
