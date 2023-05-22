package my.phatndt.xsmart.android.features.currencyconverter.presentation.view

//import androidx.compose.material3.Text
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.AlertDialog
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import my.phatndt.xsmart.android.core.ui.theme.XSmartColor
import my.phatndt.xsmart.android.core.ui.theme.XSmartColor.CurrencyConverterColor
import my.phatndt.xsmart.android.core.ui.theme.XSmartTextStyles
import my.phatndt.xsmart.android.core.ui.widget.XSmartButton
import my.phatndt.xsmart.android.features.currencyconverter.presentation.state.CurrencyUIState
import my.phatndt.xsmart.android.features.currencyconverter.presentation.viewmodel.CurrencyViewModel
import my.phatndt.xsmart.feature.currency.data.model.CurrencyRate
import org.koin.androidx.compose.koinViewModel
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Currency
import java.util.Locale

@Composable
fun CurrencyConverterRoute(currencyViewModel: CurrencyViewModel = koinViewModel()) {
    val state = currencyViewModel.uiState.collectAsState()
    CurrencyConverterScreen(
        state.value,
        { currencyViewModel.calculateExchangeRate(it) },
        { currencyViewModel.setCurrentCurrencyRate(it) }) {
        currencyViewModel.updateShowPickerCurrencyDialog(it)
    }
}

@Composable
fun CurrencyConverterScreen(
    state: CurrencyUIState,
    calculate: (Double) -> Unit,
    setCurrentCurrencyRate: (CurrencyRate?) -> Unit,
    changeShowPickerCurrencyDialog: (Boolean) -> Unit,
) {
    var exchangeText by remember {
        mutableStateOf("")
    }
    if (state.isShowDialog) {
        Dialog(
            onDismissRequest = { },
            DialogProperties(dismissOnBackPress = false, dismissOnClickOutside = false)
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(100.dp)
                    .background(XSmartColor.White, shape = RoundedCornerShape(8.dp))
            ) {
                CircularProgressIndicator(color = CurrencyConverterColor)
            }
        }
    }
    if (state.isShowPickerCurrencyDialog) {
        Dialog(onDismissRequest = { changeShowPickerCurrencyDialog(false) }, content = {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .height(256.dp)
                    .background(XSmartColor.White, shape = RoundedCornerShape(8.dp))
            ) {
                state.currencyRate?.let {
                    LazyVerticalGrid(columns = GridCells.Fixed(4), content = {
                        items(count = it.size) {
                            TextButton(onClick = {
                                changeShowPickerCurrencyDialog(false)
                                setCurrentCurrencyRate(state.currencyRate[it].third)
                            }) {
                                Text(
                                    state.currencyRate[it].third?.code ?: "Error",
                                    style = XSmartTextStyles.h4,
                                    fontWeight = FontWeight.Normal,
                                )
                            }
                        }
                    })
                }
            }
        })
    }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                horizontal = 16.dp, vertical = 16.dp,
            )
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = exchangeText,
                onValueChange = {
                    if (it.isEmpty()) {
                        exchangeText = ""
                        calculate(0.0)
                        return@OutlinedTextField
                    }
                    val formatter = DecimalFormat("#,###,###")
                    exchangeText = formatter.format(it.replace(",", "").replace(".", "").toDouble())
                    calculate(it.replace(",", "").replace(".", "").toDouble())
                },
                modifier = Modifier.weight(1f),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .weight(1f)
                    .background(CurrencyConverterColor, shape = RoundedCornerShape(4.dp))
                    .padding(horizontal = 12.dp)
                    .height(56.dp)
                    .clickable(onClick = { changeShowPickerCurrencyDialog(true) })
            ) {
                state.currentCurrencyRate?.code?.let {
                    Text(
                        it,
                        style = XSmartTextStyles.h3,
                        fontWeight = FontWeight.W500,
                        color = XSmartColor.White
                    )
                }
                Icon(
                    Icons.Default.ArrowDropDown, tint = XSmartColor.White, contentDescription = null
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        state.currencyRate?.let {
            Row() {
                LazyColumn(modifier = Modifier.weight(1f), content = {
                    items(count = it.size) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            if (state.currencyRate[it].first != null) {
                                Text(
                                    DecimalFormat("#,###,###").format(state.currencyRate[it].first),
                                    modifier = Modifier
                                        .weight(1f)
                                        .padding(12.dp)
                                )
                            } else {
                                Box(modifier = Modifier.weight(1f))
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            Text(
                                state.currencyRate[it].third?.code ?: "Error",
                                style = XSmartTextStyles.h4,
                                fontWeight = FontWeight.Normal,
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(12.dp)
                            )
                        }
                    }
                })
            }
        }

    }
}

@Composable
@Preview
fun CurrencyConverterScreenPreview() {
    Scaffold() {
        Box(modifier = Modifier.padding(it)) {
            CurrencyConverterScreen(CurrencyUIState(
                currencyRate = listOf(
                    Triple(
                        231.534, "", CurrencyRate("AED", 1.2)
                    )
                )
            ), {}, {}) {

            }
        }
    }
}