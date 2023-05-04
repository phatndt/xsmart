package com.example.xsmart.android.features.bmicalculator.presentation.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Female
import androidx.compose.material.icons.filled.Male
import androidx.compose.material.icons.filled.Man
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.xsmart.android.ui.theme.BmiCalculatorColor
import com.example.xsmart.android.ui.theme.XSmartTextStyles
import com.example.xsmart.android.ui.theme.XSmartTheme
import com.example.xsmart.android.ui.widget.XSmartButton

@Composable
fun BmiCalculatorRoute() {
    BmiCalculatorScreen()
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BmiCalculatorScreen() {
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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp, vertical = 16.dp,
            )
    ) {
        Text(text = "Gender", style = XSmartTextStyles.h4)
        Row() {
            gender.forEach {
                Button(
                    onClick = { onSelectionChange(it) },
                    colors = ButtonDefaults.buttonColors(backgroundColor = if (it == selectedOption) BmiCalculatorColor else Color.White),
                    modifier = Modifier.padding(end = 8.dp),
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Icon(
                        imageVector = if (it == gender.first()) Icons.Filled.Male else Icons.Filled.Female,
                        tint = if (it == selectedOption) Color.White else BmiCalculatorColor,
                        contentDescription = null
                    )
                    Text(
                        text = it,
                        style = XSmartTextStyles.button,
                        color = if (it == selectedOption) Color.White else BmiCalculatorColor
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("Age (years)", style = XSmartTextStyles.h4)
        Text(age.toInt().toString(), style = XSmartTextStyles.h2)
        Slider(
            modifier = Modifier.padding(0.dp),
            value = age,
            onValueChange = {
                age = it
            },
            valueRange = 0f..100f,
            colors = SliderDefaults.colors(
                activeTickColor = BmiCalculatorColor,
                thumbColor = BmiCalculatorColor,
                activeTrackColor = BmiCalculatorColor,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Height (cm)", style = XSmartTextStyles.h4)
        Text(height.toInt().toString(), style = XSmartTextStyles.h2)
        Slider(
            modifier = Modifier.padding(0.dp),
            value = height,
            onValueChange = {
                height = it
            },
            valueRange = 0f..300f,
            colors = SliderDefaults.colors(
                activeTickColor = BmiCalculatorColor,
                thumbColor = BmiCalculatorColor,
                activeTrackColor = BmiCalculatorColor,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text("Weight (kg)", style = XSmartTextStyles.h4)
        Text(weight.toInt().toString(), style = XSmartTextStyles.h2)
        Slider(
            modifier = Modifier.padding(0.dp),
            value = weight,
            onValueChange = {
                weight = it
            },
            valueRange = 0f..300f,
            colors = SliderDefaults.colors(
                activeTickColor = BmiCalculatorColor,
                thumbColor = BmiCalculatorColor,
                activeTrackColor = BmiCalculatorColor,
            )
        )
        Spacer(modifier = Modifier.weight(1f))
        XSmartButton(
            onClick = { /*TODO*/ },
            content = "Calculate",
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BmiCalculatorColor,
                contentColor = Color.White
            )
        )
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun BmiCalculatorScreenPreview() {
    XSmartTheme() {
        BmiCalculatorScreen()
    }
}