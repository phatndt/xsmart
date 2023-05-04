package com.example.xsmart.android.features.bmicalculator.presentation.view

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Space
import androidx.annotation.Px
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.xsmart.android.ui.theme.BmiCalculatorColor
import com.example.xsmart.android.ui.theme.XSmartColor
import com.example.xsmart.android.ui.theme.XSmartTextStyles
import com.example.xsmart.android.ui.theme.XSmartTheme
import com.example.xsmart.android.ui.widget.XSmartButton
import com.github.mikephil.charting.charts.PieChart

@Composable
fun BmiCalculatorHomeRoute(onNavigateToBmiCalculatorScreen: () -> Unit) {
    BmiCalculatorHomeScreen() { onNavigateToBmiCalculatorScreen() }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BmiCalculatorHomeScreen(onNavigateToBmiCalculatorScreen: () -> Unit) {
    val bmiLevel = setBmiLevel(25.0)
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp, vertical = 16.dp,
            )
    ) {
        Card(elevation = 4.dp,
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = {
                visible = !visible
            }) {
            Row(
                modifier = Modifier.padding(24.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center,
            ) {
                Text("Your BMI ", style = XSmartTextStyles.h4)
                Text(
                    "25",
                    modifier = Modifier.padding(horizontal = 12.dp),
                    style = XSmartTextStyles.h3
                )
                Text(bmiLevel.type, style = XSmartTextStyles.h4, color = bmiLevel.color)
            }
        }
        AnimatedVisibility(visible = visible) {
            Card(
                elevation = 4.dp,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth(),
            ) {
                Column() {
                    BmiLevel.values().forEach {
                        Row(
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                it.description,
                                style = XSmartTextStyles.h5,
                                color = Color.Gray,
                                fontWeight = FontWeight.W500
                            )
                            Text(it.type, style = XSmartTextStyles.h5, color = it.color)
                        }
                        if (it != BmiLevel.OBESE_III) {
                            Divider(
                                modifier = Modifier.padding(horizontal = 16.dp),
                                thickness = 1.dp,
                                color = Color.Gray,
                            )
                        }
                    }
                }
            }
        }
        XSmartButton(
            onClick = onNavigateToBmiCalculatorScreen,
            content = "Calculate BMI now!",
            colors = ButtonDefaults.buttonColors(
                backgroundColor = BmiCalculatorColor, contentColor = Color.White
            )
        )

    }
}

enum class BmiLevel(val type: String, val description: String, val color: Color) {
    UNDERWEIGHT("Underweight", "Less than 18.5", Color(0xFF72D5FF)),
    NORMAL(
        "Normal", "18.5 to 24.99", Color(0xFF82D266)
    ),
    OVERWEIGHT("Overweight", "25 to 29.99", Color(0xFFFFEA7C)),
    OBESE_I(
        "Obese level 1", "30 to 34.99", Color(0xFFFFBA6A)
    ),
    OBESE_II("Obese level 2", "35 to 39.99", Color(0xFFFF9960)),
    OBESE_III(
        "Obese level 3", "Above 40", Color(0xFFFF7764)
    )
}

fun setBmiLevel(value: Double): BmiLevel {
    return when (value) {
        in 0.0..18.5 -> BmiLevel.UNDERWEIGHT
        in 18.5..24.99 -> BmiLevel.NORMAL
        in 25.0..29.99 -> BmiLevel.OVERWEIGHT
        in 30.0..34.99 -> BmiLevel.OBESE_I
        in 35.0..39.99 -> BmiLevel.OBESE_II
        else -> BmiLevel.OBESE_III
    }
}

@Preview(showBackground = true, device = "spec:width=360dp,height=640dp")
@Composable
fun BmiCalculatorHomeScreenPreview() {
    XSmartTheme() {
        BmiCalculatorHomeScreen() {}
    }
}
