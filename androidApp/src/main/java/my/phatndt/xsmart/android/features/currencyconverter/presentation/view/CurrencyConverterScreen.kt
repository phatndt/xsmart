package my.phatndt.xsmart.android.features.currencyconverter.presentation.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
//import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import my.phatndt.xsmart.android.features.currencyconverter.presentation.viewmodel.CurrencyViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun CurrencyConverterScreen(currencyViewModel: CurrencyViewModel = koinViewModel()) {
    Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "${currencyViewModel.countries.size}")
    }
}