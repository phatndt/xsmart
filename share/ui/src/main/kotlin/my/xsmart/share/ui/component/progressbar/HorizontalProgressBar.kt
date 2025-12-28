package my.xsmart.share.ui.component.progressbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlin.collections.forEach

data class ProgressBarSegment(
    val label: String,
    val percentage: Float,
    val color: Color,
)

@Composable
fun HorizontalProgressBar(
    segments: List<ProgressBarSegment>,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(32.dp)
            .clip(MaterialTheme.shapes.medium)
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        segments.forEach { segment ->
            Box(
                modifier = Modifier
                    .weight(segment.percentage)
                    .fillMaxSize()
                    .background(segment.color),
                contentAlignment = Alignment.Center,
            ) {
                if (segment.percentage > 5f) { // Only show text if segment is large enough
                    Text(
                        text = "${segment.percentage}%",
                        color = MaterialTheme.colorScheme.onPrimary,
                        style = MaterialTheme.typography.labelSmall,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun SalaryBreakdownProgressBarPreview() {
    MaterialTheme() {
        HorizontalProgressBar(
            segments = listOf(
                ProgressBarSegment(
                    label = "Net Salary",
                    percentage = 85f,
                    color = MaterialTheme.colorScheme.primary,
                ),
                ProgressBarSegment(
                    label = "Insurance",
                    percentage = 10.5f,
                    color = MaterialTheme.colorScheme.secondary,
                ),
                ProgressBarSegment(
                    label = "Tax",
                    percentage = 4.5f,
                    color = MaterialTheme.colorScheme.error,
                ),
            ),
        )
    }
}

