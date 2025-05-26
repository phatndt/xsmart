package my.phatndt.xsmart.android.core.ui.widget.textfield

import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.phatndt.xsmart.android.core.ui.theme.colorTextField
import my.phatndt.xsmart.android.core.ui.widget.XSmartTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XSmartAmountTextField(
    value: TextFieldValue,
    onValueChange: (TextFieldValue) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    supportingText: @Composable() (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = 17,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = XSmartTextFieldToken.ContainerShape,
    colors: TextFieldColors = TextFieldDefaults.colorTextField(),
) {
    XSmartTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = VisualTransformation.None,
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Number,
        ),
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        maxLength = maxLength,
        interactionSource = interactionSource,
        colors = colors,
        shape = shape,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun XSmartAmountTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    readOnly: Boolean = false,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable() (() -> Unit)? = null,
    leadingIcon: @Composable() (() -> Unit)? = null,
    trailingIcon: @Composable() (() -> Unit)? = null,
    supportingText: @Composable() (() -> Unit)? = null,
    isError: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    maxLength: Int = 17,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    shape: Shape = XSmartTextFieldToken.ContainerShape,
    colors: TextFieldColors = TextFieldDefaults.colorTextField(),
) {
    XSmartTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier),
        enabled = enabled,
        readOnly = readOnly,
        textStyle = textStyle,
        label = label,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        supportingText = supportingText,
        isError = isError,
        visualTransformation = AmountVisualTransformation(),
        keyboardOptions = keyboardOptions.copy(
            keyboardType = KeyboardType.Number,
        ),
        keyboardActions = keyboardActions,
        singleLine = singleLine,
        maxLines = maxLines,
        maxLength = maxLength,
        interactionSource = interactionSource,
        colors = colors,
        shape = shape,
    )
}

@Preview
@Composable
fun XSmartAmountTextFieldPreview() {
    XSmartAmountTextField(
        value = "Hello",
        onValueChange = {},
        label = {
            Text(text = "Label")
        },
    )
}
