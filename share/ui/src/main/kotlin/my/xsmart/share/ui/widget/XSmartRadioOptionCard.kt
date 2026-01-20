package my.xsmart.share.ui.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.share.ui.theme.Spacing

@Composable
fun RadioOptionCard(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    selected: Boolean,
    shape: Shape = XSmartRadioOptionCardDefault.shape,
    color: XSmartRadioOptionColor = XSmartRadioOptionCardDefault.color(),
    onClick: () -> Unit,
    content: @Composable () -> Unit  = {},
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(XSmartRadioOptionCardDefault.shape)
            .background(
                color.background,
                shape,
            )
            .border(
                width = 1.dp,
                color = if (selected) color.focusedBorderColor else color.unfocusedBorderColor,
                shape = XSmartRadioOptionCardDefault.shape,
            )
            .clickable(onClick = onClick)
            .padding(Spacing.large),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(
                    title,
                    style = MaterialTheme.typography.titleMedium,
                )
                Text(
                    subtitle,
                    style = MaterialTheme.typography.labelMedium,
                )
            }
            RadioButton(
                selected = selected,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(selectedColor = color.selectedRadioButtonColor),
            )
        }
        content()
    }
}

data class XSmartRadioOptionColor(
    val background: Color = Color.Unspecified,
    val unfocusedBorderColor: Color = Color.Unspecified,
    val focusedBorderColor: Color = Color.Unspecified,
    val selectedRadioButtonColor: Color = Color.Unspecified
)

object XSmartRadioOptionCardDefault {
    val shape: Shape
        @Composable get() = MaterialTheme.shapes.medium

    @Composable
    fun color() = XSmartRadioOptionColor(
        focusedBorderColor = MaterialTheme.colorScheme.primary,
        unfocusedBorderColor = Color.Unspecified,
        background = MaterialTheme.colorScheme.surface,
        selectedRadioButtonColor = MaterialTheme.colorScheme.primary,
    )
}

@Preview
@Composable
fun XSmartRadioOptionCardPreview() {
    MaterialTheme {
        Column(modifier = Modifier.padding(16.dp)) {
            RadioOptionCard(
                Modifier,
                "Title",
                "Subtitle",
                false,
               onClick =  {},
                ) { }
            Spacer(Modifier.height(16.dp))
            RadioOptionCard(
                Modifier,
                "Title",
                "Subtitle",
                true,
                onClick = {}) { }
        }
    }
}
