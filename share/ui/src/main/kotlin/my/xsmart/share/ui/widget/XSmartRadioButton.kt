package my.xsmart.share.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalViewConfiguration
import androidx.compose.ui.tooling.preview.Preview
import my.xsmart.share.ui.theme.Spacing
import my.xsmart.share.ui.theme.XSmartTheme

@Composable
fun XSmartRadioButton(
    selected: Boolean,
    modifier: Modifier = Modifier,
    onCheckedChange: (() -> Unit)? = null,
    text: String? = null,
) {
    Row(
        modifier = modifier.clickable(
            interactionSource = remember {
                MutableInteractionSource()
            },
            indication = null,
        ) {
            onCheckedChange?.invoke()
        },
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = onCheckedChange,
            modifier = Modifier.size(LocalViewConfiguration.current.minimumTouchTargetSize),
        )
        if (text != null) {
            Text(
                text = text,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(
                    start = Spacing.medium,
                    end = Spacing.xLarge,
                ),
            )
        }
    }
}

@Preview
@Composable
fun XSmartRadioButtonPreview() {
    XSmartTheme {
        Column {
            XSmartRadioButton(true)
            XSmartRadioButton(true, text = "false")
        }
    }
}
