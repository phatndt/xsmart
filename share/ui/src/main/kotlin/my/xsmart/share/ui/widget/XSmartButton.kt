package my.xsmart.share.ui.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.share.ui.theme.XSmartTextStyles
import my.xsmart.share.ui.theme.XSmartTheme

@SuppressLint("ModifierParameter")
@Composable
fun XSmartButton(
    onClick: () -> Unit,
    isFinish: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    border: BorderStroke? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    content: String,
    modifier: Modifier = Modifier,
) {
    val isPressed = interactionSource.collectIsPressedAsState()
    Button(
        onClick = onClick,
        enabled = isFinish,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .defaultMinSize(
                minHeight = 48.dp,
            ),
        interactionSource = interactionSource,
        border = border,
        colors = colors,
    ) {
        if (isFinish) {
            Text(text = content, style = XSmartTextStyles.button)
        } else {
            CircularProgressIndicator(
                color = Color.White,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PrimaryButtonPreview() {
    XSmartTheme() {
        XSmartButton(onClick = {}, content = "Login")
    }
}
