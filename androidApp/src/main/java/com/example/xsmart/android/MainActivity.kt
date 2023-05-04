package com.example.xsmart.android

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.xsmart.android.features.bmicalculator.presentation.view.BmiCalculatorRoute
import com.example.xsmart.android.features.bmicalculator.presentation.view.BmiCalculatorHomeRoute
import com.example.xsmart.android.features.currencyconverter.presentation.view.CompassScreen
import com.example.xsmart.android.features.currencyconverter.presentation.view.CurrencyConverterScreen
import com.example.xsmart.android.features.unitconverter.presentation.view.UnitConverterScreen
import com.example.xsmart.android.ui.theme.BmiCalculatorColor
import com.example.xsmart.android.ui.theme.CompassColor
import com.example.xsmart.android.ui.theme.CurrencyConverterColor
import com.example.xsmart.android.ui.theme.UnitConverterColor
import com.example.xsmart.android.ui.theme.XSmartTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            XSmartTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    XSmart()
                }
            }
        }
    }
}

@Composable
fun XSmart() {
    XSmartNavHost()
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun XSmartNavHost(navHostController: NavHostController = rememberNavController()) {
    val items = listOf(TabItem.UnitConverter, TabItem.CurrencyConverter, TabItem.BmiCalculator , TabItem.Compass)
    var converterType by remember { mutableStateOf(items.first()) }
    var expanded by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors =
                TopAppBarDefaults.centerAlignedTopAppBarColors(containerColor = setTopBarColor(converterType)),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            if (converterType != items.first()) {
                                converterType =
                                    items.first()
                                navHostController.navigate(Destinations.UnitConverter.route)
                            }
                        }) {
                        Icon(Icons.Rounded.Home, tint = Color.White, contentDescription = null)
                    }
                },
                title = {
                    Box(modifier = Modifier.fillMaxHeight(), contentAlignment = Alignment.Center) {
                        Row(
                            modifier = Modifier
                                .clickable(
                                    indication = null,
                                    interactionSource = MutableInteractionSource(),
                                ) {
                                    expanded = !expanded
                                }
                                .width(200.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = converterType.tabName,
                                textAlign = TextAlign.Center,
                                color = Color.White
                            )
                            Icon(
                                Icons.Filled.ArrowDropDown,
                                tint = Color.White,
                                contentDescription = ""
                            )
                            DropdownMenu(
                                expanded = expanded,
                                offset = DpOffset((12).dp, 0.dp),
                                onDismissRequest = { expanded = false }) {
                                items.forEachIndexed { index, itemValue ->
                                    DropdownMenuItem(
                                        onClick = {
                                            expanded = false
                                            converterType = itemValue
                                            if (index == 0) {
                                                navHostController.navigate(Destinations.UnitConverter.route)
                                            } else if (index == 1) {
                                                navHostController.navigate(Destinations.CurrencyConverter.route)
                                            } else if (index == 2) {
                                                navHostController.navigate(Destinations.BmiCalculatorHome.route)
                                            } else if (index == 3) {
                                                navHostController.navigate(Destinations.Compass.route)
                                            }
                                        },
                                        enabled = (itemValue != converterType),
                                        interactionSource = MutableInteractionSource(),
                                    ) {
                                        Text(itemValue.tabName)
                                    }
                                }
                            }
                        }
                    }

                },
            )
        },
    ) {
        Box(modifier = Modifier.padding(it)) {
            NavHost(
                navController = navHostController,
                startDestination = Destinations.UnitConverter.route,
                route = "/"
            ) {
                composable(Destinations.UnitConverter.route) {
                    UnitConverterScreen()
                }
                composable(Destinations.BmiCalculatorHome.route) {
                    BmiCalculatorHomeRoute() {
                        navHostController.navigate(Destinations.BmiCalculator.route)
                    }
                }
                composable(Destinations.CurrencyConverter.route) {
                    CurrencyConverterScreen()
                }
                composable(Destinations.Compass.route) {
                    CompassScreen()
                }
                composable(Destinations.BmiCalculator.route) {
                    BmiCalculatorRoute()
                }
            }
        }
    }
}

fun setTopBarColor(tabItem: TabItem): Color {
    return when (tabItem) {
        TabItem.UnitConverter -> {
            UnitConverterColor
        }
        TabItem.CurrencyConverter -> {
            CurrencyConverterColor
        }
        TabItem.BmiCalculator -> {
            BmiCalculatorColor
        }
        TabItem.Compass -> {
            CompassColor
        }
    }
}

enum class TabItem(val tabName: String) {
    UnitConverter("Unit Converter"),
    CurrencyConverter("Currency Converter"),
    BmiCalculator("Bmi Calculator"),
    Compass("Compass")
}

sealed class Destinations(val route: String) {
    object UnitConverter : Destinations("unitConverter")
    object BmiCalculatorHome : Destinations("bmCalculatorHome")
    object BmiCalculator : Destinations("bmCalculator")
    object CurrencyConverter : Destinations("currencyConverter")
    object Compass : Destinations("compass")
}

@Preview
@Composable
fun DefaultPreview() {
    XSmartTheme {
        XSmart()
    }
}
