package my.phatndt.xsmart.android.core.ui.widget

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import my.phatndt.xsmart.android.core.ui.theme.Spacing
import my.phatndt.xsmart.android.core.ui.theme.XSmartTheme

data class XSmartRadioButtonGroupModel(
    val id: Int,
    val displayText: String,
    val modifier: Modifier = Modifier,
)

@Composable
fun XSmartRadioButtonGroup(
    options: List<XSmartRadioButtonGroupModel>,
    modifier: Modifier = Modifier,
    orientation: Orientation = Orientation.Horizontal,
) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(options[1]) }
    if (orientation == Orientation.Horizontal) {
        Row(modifier = modifier) {
            options.forEach { option ->
                XSmartRadioButton(
                    selected = true,
                    modifier = option.modifier,
                    text = option.displayText,
                )
            }
        }
    } else {
        Column(modifier = modifier) {
            options.forEach { option ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (option == selectedOption),
                            onClick = {
                                onOptionSelected(option)
                            },
                        ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = (option == selectedOption),
                        onClick = { onOptionSelected(option) },
                    )
                    Text(
                        text = option.displayText,
                        style = MaterialTheme.typography.bodyMedium,
                        modifier = Modifier.padding(start = Spacing.medium),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun XSmartRadioButtonGroupPreview() {
    XSmartTheme {
        XSmartRadioButtonGroup(
            listOf(
                XSmartRadioButtonGroupModel(1, "A"),
                XSmartRadioButtonGroupModel(1, "A"),
            ),
            modifier = Modifier.fillMaxWidth(),
        )
    }
}
