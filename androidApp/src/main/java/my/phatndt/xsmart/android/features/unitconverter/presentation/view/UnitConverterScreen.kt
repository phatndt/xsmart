package com.phatndt.xsmart.features.unitconverter.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import my.phatndt.xsmart.android.features.currencyconverter.presentation.state.CurrencyUIState
import my.phatndt.xsmart.android.features.currencyconverter.presentation.view.CurrencyConverterScreen
import my.phatndt.xsmart.feature.currency.data.model.CurrencyRate

@Composable
fun UnitConverterScreen() {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "The feature is under development")
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Go back")
        }
    }
}

@Composable
@Preview
fun  UnitConverterScreenPreview() {
    Scaffold() {
        Box(modifier = Modifier.padding(it)) {
            UnitConverterScreen()
        }
    }
}