package my.phatndt.xsmart.android.features.bmicalculator.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.phatndt.xsmart.android.core.ui.theme.BmiCalculatorColor
import my.phatndt.xsmart.android.core.ui.theme.XSmartTextStyles
import my.phatndt.xsmart.android.core.ui.theme.XSmartTheme
import my.phatndt.xsmart.android.core.ui.widget.XSmartButton
import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiUIState
import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun BmiCalculatorRoute(
    bmiViewModel: BmiViewModel = koinViewModel(),
    onNavigateToBmiCalculatorHomeScreen: () -> Unit,
) {
    val state = bmiViewModel.uiState.collectAsState()
    BmiCalculatorScreen(
        state.value,
        {
            bmiViewModel.dismissDialog()
            onNavigateToBmiCalculatorHomeScreen()
        },
    ) { height, weight ->
        bmiViewModel.calculateBmi(height, weight)
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BmiCalculatorScreen(
    state: BmiUIState,
    dismissDiaLog: () -> Unit,
    calculateBmi: (Double, Double) -> Unit,
) {
    val gender = listOf("Male", "Female")
    var selectedOption by remember {
        mutableStateOf("Male")
    }
    val onSelectionChange = { text: String ->
        selectedOption = text
    }
    var age by remember {
        mutableStateOf(0f)
    }
    var height by remember {
        mutableStateOf(0f)
    }
    var weight by remember {
        mutableStateOf(0f)
    }
    if (state.isShowDiaLog) {
        AlertDialog(
            onDismissRequest = {
            },
            title = {
                Text(text = "Title")
            },
            text = {
                Column() {
                    Text("Your Bmi is: ${state.calculatedBmi}")
                }
            },
            buttons = {
                Row(
                    modifier = Modifier.padding(all = 8.dp),
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Button(
                        modifier = Modifier.fillMaxWidth(),
                        onClick = dismissDiaLog,
                    ) {
                        Text("OK")
                    }
                }
            },
        )
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            )
            .safeDrawingPadding(),
    ) {
        Text("Height (cm)", style = XSmartTextStyles.h4)
        Text(
            height
                .toInt()
                .toString(),
            style = XSmartTextStyles.h2,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            MinusIconButton {
                height -= 1
            }
            Slider(
                modifier = Modifier.weight(1f),
                value = height,
                onValueChange = {
                    height = it
                },
                valueRange = 0f..300f,
                colors = SliderDefaults.colors(
                    activeTickColor = BmiCalculatorColor,
                    thumbColor = BmiCalculatorColor,
                    activeTrackColor = BmiCalculatorColor,
                ),
            )
            PlusIconButton {
                height += 1
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Weight (kg)", style = XSmartTextStyles.h4)
        Text(
            weight
                .toInt()
                .toString(),
            style = XSmartTextStyles.h2,
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            MinusIconButton {
                weight -= 1
            }
            Slider(
                modifier = Modifier.weight(1f),
                value = weight,
                onValueChange = {
                    weight = it
                },
                valueRange = 0f..300f,
                colors = SliderDefaults.colors(
                    activeTickColor = BmiCalculatorColor,
                    thumbColor = BmiCalculatorColor,
                    activeTrackColor = BmiCalculatorColor,
                ),
            )
            PlusIconButton {
                weight += 1
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        XSmartButton(
            onClick = {
                calculateBmi(height.toDouble(), weight.toDouble())
            },
            content = "Calculate",
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BmiCalculatorColor,
                contentColor = Color.White,
            ),
        )
    }
}

@Composable
fun MinusIconButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = BmiCalculatorColor.copy(alpha = 0.25f),
                shape = RoundedCornerShape(4.dp),
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Remove,
            tint = BmiCalculatorColor,
            contentDescription = null,
        )
    }
}

@Composable
fun PlusIconButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(RoundedCornerShape(4.dp))
            .background(
                color = BmiCalculatorColor.copy(alpha = 0.25f),
                shape = RoundedCornerShape(4.dp),
            )
            .clickable { onClick() },
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            tint = BmiCalculatorColor,
            contentDescription = null,
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun BmiCalculatorScreenPreview() {
    XSmartTheme() {
        BmiCalculatorScreen(BmiUIState(), {}) { _, _ ->
        }
    }
}
