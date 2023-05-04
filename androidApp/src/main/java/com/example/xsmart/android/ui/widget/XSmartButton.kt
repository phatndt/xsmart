package com.example.xsmart.android.ui.widget

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xsmart.android.ui.theme.XSmartTextStyles
import com.example.xsmart.android.ui.theme.XSmartTheme

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
                minHeight = 48.dp
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