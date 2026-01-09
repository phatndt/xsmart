package my.xsmart.feature.salarycalculator.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import my.xsmart.feature.salarycalculator.ui.input.ui.SalaryCalculatorTextFieldDefault
import my.xsmart.share.ui.component.XSmartOutlineTextField
import my.xsmart.share.ui.component.XSmartTextFieldTransformation
import my.xsmart.share.ui.theme.spacing
import my.xsmart.share.ui.widget.XSmartTextField

@Composable
fun DeductionInput(
    label: String,
    value: String,
    onChange: (String) -> Unit,
    disabled: Boolean,
    modifier: Modifier = Modifier,
) {
    var textValue by rememberSaveable(key=value) {
        mutableStateOf(value)
    }

    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            text = label,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )

        Spacer(modifier = Modifier.height( MaterialTheme.spacing.xs))

        XSmartTextField(
            value = textValue,
            onValueChange = { newValue ->
                textValue = newValue
                onChange(newValue)
            },
            placeholder = {
                Text(
                    text = label,
                    style = MaterialTheme.typography.titleSmall,
                )
            },
            enabled = !disabled,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = null,
                    tint = if (disabled) {
                        MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                )
            },
            trailingIcon = {
                Icon(
                    imageVector = if (disabled) Icons.Default.Lock else Icons.Default.Edit,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(
                        alpha = if (disabled) 0.5f else 0.7f,
                    ),
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            ),
            visualTransformation = XSmartTextFieldTransformation(),
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.small,
            colors = SalaryCalculatorTextFieldDefault.color(),
        )
    }
}
