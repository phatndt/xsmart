package my.phatndt.xsmart.android.features.currencyconverter.presentation.state

import my.phatndt.xsmart.feature.currency.data.model.CurrencyRate
import java.security.KeyStore.Builder

data class CurrencyUIState(
    val currencyRate: List<Triple<Double?, String?, CurrencyRate?>>? = null,
    val exchangeRate: List<Double?>? = null,
    val currentCurrencyRate: CurrencyRate? = null,
    val errorMessage: String? = null,
    val isShowDialog: Boolean = false,
    val isShowPickerCurrencyDialog: Boolean = false,
)