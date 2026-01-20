package my.phatndt.xsmart.android.features.bmicalculator.presentation.view

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import my.xsmart.share.ui.theme.BmiCalculatorColor
import my.xsmart.share.ui.theme.XSmartTextStyles
import my.xsmart.share.ui.theme.XSmartTheme
import my.xsmart.share.ui.widget.XSmartButton
import my.phatndt.xsmart.android.core.utils.getDateTimeFromString
import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiUIState
import my.phatndt.xsmart.android.features.bmicalculator.presentation.viewmodel.BmiViewModel
import my.phatndt.xsmart.share.domain.entity.bmi.BmiEntity
import org.koin.androidx.compose.koinViewModel

@Composable
fun BmiCalculatorHomeRoute(
    bmiViewModel: BmiViewModel = koinViewModel(),
    onNavigateToBmiCalculatorScreen: () -> Unit,
) {
    val state = bmiViewModel.uiState.collectAsState()
    BmiCalculatorHomeScreen(state.value) { onNavigateToBmiCalculatorScreen() }
}

@OptIn(ExperimentalMaterialApi::class)
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun BmiCalculatorHomeScreen(state: BmiUIState, onNavigateToBmiCalculatorScreen: () -> Unit) {
    val bmiLevel = setBmiLevel(25.0)
    var visible by remember {
        mutableStateOf(false)
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 16.dp,
            ).safeDrawingPadding(),
    ) {
        Card(
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            onClick = {
                visible = !visible
            },
        ) {
            if (!state.bmi.isNullOrEmpty()) {
                Row(
                    modifier = Modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                ) {
                    Text("Your BMI ", style = XSmartTextStyles.h4)
                    Text(
                        state.bmi.first().bmi.toString(),
                        modifier = Modifier.padding(horizontal = 12.dp),
                        style = XSmartTextStyles.h3,
                    )
                    Text(
                        setBmiLevel(state.bmi.first().bmi).type,
                        style = XSmartTextStyles.h4,
                        color = setBmiLevel(state.bmi.last().bmi).color,
                    )
                }
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        if (state.bmi != null) "Calculate your Bmi now!" else "Nothing to show",
                        style = XSmartTextStyles.h4,
                    )
                }
            }
        }
        AnimatedVisibility(visible = visible) {
            Card(
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            ) {
                Column() {
                    BmiLevel.values().forEach {
                        Row(
                            modifier = Modifier
                                .height(48.dp)
                                .fillMaxWidth()
                                .padding(16.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                        ) {
                            Text(
                                it.description,
                                style = XSmartTextStyles.h5,
                                color = Color.Gray,
                                fontWeight = FontWeight.W500,
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
        if (!state.bmi.isNullOrEmpty()) {
            Text(
                "Recently calculated Bmi",
                style = XSmartTextStyles.h4,
            )
            LazyColumn {
                items(count = state.bmi.size) {
                    Card(
                        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 1.dp),
                        shape = RoundedCornerShape(4.dp),
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                        ) {
                            Column(horizontalAlignment = Alignment.Start) {
                                Text(
                                    getDateTimeFromString(state.bmi[it].time),
                                    style = XSmartTextStyles.h5,
                                )
                                Text(
                                    setBmiLevel(state.bmi[it].bmi).type,
                                    style = XSmartTextStyles.h4,
                                    color = setBmiLevel(state.bmi[it].bmi).color,
                                )
                            }
                            Text(
                                state.bmi[it].bmi.toString(),
                                style = XSmartTextStyles.h4,
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        XSmartButton(
            onClick = onNavigateToBmiCalculatorScreen,
            content = "Calculate BMI now!",
            colors = ButtonDefaults.buttonColors().copy(
                containerColor = BmiCalculatorColor,
                contentColor = Color.White,
            ),
        )
    }
}

enum class BmiLevel(val type: String, val description: String, val color: Color) {
    UNDERWEIGHT("Underweight", "Less than 18.5", Color(0xFF72D5FF)),
    NORMAL(
        "Normal",
        "18.5 to 24.99",
        Color(0xFF82D266),
    ),
    OVERWEIGHT("Overweight", "25 to 29.99", Color(0xFFFFEA7C)),
    OBESE_I(
        "Obese level 1",
        "30 to 34.99",
        Color(0xFFFFBA6A),
    ),
    OBESE_II("Obese level 2", "35 to 39.99", Color(0xFFFF9960)),
    OBESE_III(
        "Obese level 3",
        "Above 40",
        Color(0xFFFF7764),
    ),
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
        BmiCalculatorHomeScreen(BmiUIState(bmi = listOf(
            BmiEntity(
                1,
                17.24,
                "1683340656634"
            )
        ))) {}
    }
}
