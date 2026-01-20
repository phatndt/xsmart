package my.xsmart.share.ui.component.row

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TextTitleValueRow(
    title: String,
    value: String,
    percent: Float,
    titleAttribute: TextAttribute = TextTitleValueRowDefault.titleAttribute(),
    valueAttribute: TextAttribute = TextTitleValueRowDefault.valueAttribute(),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    TitleValueRow(
        title = {
            Text(
                title,
                modifier = Modifier.weight(percent),
                style = titleAttribute.style,
                color = titleAttribute.color,
            )
        },
        value = {
            Text(
                value,
                modifier = Modifier.weight(1 - percent),
                style = valueAttribute.style,
                color = valueAttribute.color,
                textAlign = valueAttribute.textAlign,
                fontWeight = valueAttribute.fontWeight,
            )
        },
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    )
}

object TextTitleValueRowDefault {
    @Composable
    fun titleAttribute() = TextAttribute(
        style = MaterialTheme.typography.bodyMedium,
    )

    @Composable
    fun valueAttribute() = TextAttribute(
        style = MaterialTheme.typography.bodyMedium,
    )

    @Composable
    fun titleAttribute(
        color: Color,
        style: TextStyle,
    ) = TextAttribute(
        color = color,
        style = style,
    )

    @Composable
    fun valueAttribute(
        color: Color,
        style: TextStyle,
        textAlign: TextAlign,
    ) = TextAttribute(
        color = color,
        style = style,
        textAlign = textAlign,
    )
}


data class TextAttribute(
    val color: Color = Color.Unspecified,
    val style: TextStyle = TextStyle.Default,
    val textAlign: TextAlign = TextAlign.Unspecified,
    val fontWeight: FontWeight? = null,
)

@Composable
fun TitleValueRow(
    title: @Composable RowScope.() -> Unit,
    value: @Composable RowScope.() -> Unit,
    modifier: Modifier = Modifier,
    horizontalArrangement: Arrangement.Horizontal = Arrangement.Start,
    verticalAlignment: Alignment.Vertical = Alignment.Top,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
    ) {
        title()
        value()
    }
}


@Preview
@Composable
fun TitleValueRowPreview() {
    MaterialTheme() {
        TitleValueRow(
            title = {},
            value = {},
        )
    }
}
