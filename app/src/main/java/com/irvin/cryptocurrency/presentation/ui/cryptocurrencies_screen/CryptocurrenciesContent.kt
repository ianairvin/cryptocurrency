package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.ErrorScreen
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoDecreasePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoFullNameTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoIncreasePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoNeutralChangePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoPriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoShortNameTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.ProgressBarColor
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Cryptocurrencies
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Error
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Initial
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Loading
import kotlinx.coroutines.flow.StateFlow
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.abs

@Composable
fun CryptocurrenciesContent(
    modifier: Modifier,
    uiState: StateFlow<CryptocurrenciesUiState>,
    changeStateToLoading: () -> Unit,
    pickedCurrency: StateFlow<Currency>,
    navController: NavHostController
) {
    Box(modifier.fillMaxSize()) {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingCryptocurrencies()
            is Error -> ErrorScreen(changeStateToLoading)
            is Cryptocurrencies -> CryptocurrenciesList(
                uiState.collectAsState().value as Cryptocurrencies,
                pickedCurrency,
                navController
            )
        }
    }
}

@Composable
fun LoadingCryptocurrencies() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularProgressIndicator(
            color = ProgressBarColor
        )
    }
}

@Composable
fun CryptocurrenciesList(
    uiState: Cryptocurrencies,
    pickedCurrency: StateFlow<Currency>,
    navController: NavHostController,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        itemsIndexed(uiState.list) { index, it ->
            when (index) {
                0 -> {
                    Spacer(Modifier.height(8.dp))
                    ItemCryptoCurrency(it, pickedCurrency, navController)
                }

                uiState.list.size - 1 -> {
                    ItemCryptoCurrency(it, pickedCurrency, navController)
                    Spacer(Modifier.height(12.dp))
                }

                else -> ItemCryptoCurrency(it, pickedCurrency, navController)
            }
        }
    }
}

@Composable
fun ItemCryptoCurrency(
    item: Cryptocurrency,
    pickedCurrency: StateFlow<Currency>,
    navController: NavHostController
) {
    val descriptionRoute = stringResource(id = R.string.description_currency_route)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable {
                navController.navigate("${descriptionRoute}/${item.name}") {
                    popUpTo(navController.graph.startDestinationId) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }
    ) {
        AsyncImage(
            modifier = Modifier
                .padding(start = 16.dp, top = 8.dp)
                .size(40.dp),
            model = item.image,
            contentDescription = null
        )
        Column(Modifier.fillMaxSize()) {
            val symbols = remember { DecimalFormatSymbols() }
            symbols.setDecimalSeparator('.')
            symbols.setGroupingSeparator(',')
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, top = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.name,
                    style = CryptoFullNameTextStyle
                )
                Text(
                    modifier = Modifier.weight(1f),
                    text = (if (pickedCurrency.collectAsState().value == Currency.USD) "$ "
                    else "₽ ")
                            + DecimalFormat("#,##0.00", symbols).format(item.price),
                    style = CryptoPriceTextStyle
                )
            }
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 16.dp, bottom = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = item.shortName.uppercase(),
                    style = CryptoShortNameTextStyle
                )
                if (item.priceChangePercentage > 0.0) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = DecimalFormat("+ 0.00", symbols)
                            .format(item.priceChangePercentage) + "%",
                        style = CryptoIncreasePriceTextStyle
                    )
                } else if (item.priceChangePercentage == 0.0) {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = DecimalFormat("0.00", symbols)
                            .format(item.priceChangePercentage) + "%",
                        style = CryptoNeutralChangePriceTextStyle
                    )
                } else {
                    Text(
                        modifier = Modifier.weight(1f),
                        text = DecimalFormat("- 0.00", symbols)
                            .format(abs(item.priceChangePercentage)) + "%",
                        style = CryptoDecreasePriceTextStyle
                    )
                }
            }
        }
    }
}