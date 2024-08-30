package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.clickable
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
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.pullrefresh.PullRefreshIndicator
import androidx.compose.material.pullrefresh.pullRefresh
import androidx.compose.material.pullrefresh.rememberPullRefreshState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.domain.entities.Cryptocurrency
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.app_common_screen.ErrorSnackbar
import com.irvin.cryptocurrency.presentation.ui.formattingCryptoPrice
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoDecreasePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoFullNameTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoIncreasePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoNeutralChangePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoPriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoShortNameTextStyle
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Cryptocurrencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import java.text.DecimalFormat
import java.text.DecimalFormatSymbols
import kotlin.math.abs

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CryptocurrenciesList(
    uiState: Cryptocurrencies,
    pickedCurrency: StateFlow<Currency>,
    updateListFromPullToRefresh: suspend () -> Boolean,
    navController: NavHostController,
) {
    val isErrorLoading = remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val textError = stringResource(R.string.snackbar_text)
    LaunchedEffect(key1 = isErrorLoading.value)
    {
        if (isErrorLoading.value) {
            snackbarHostState.showSnackbar(textError)
            isErrorLoading.value = false
        }
    }

    var isRefreshing by remember { mutableStateOf(false) }
    val pullRefreshState =
        rememberPullRefreshState(
            refreshing = isRefreshing,
            onRefresh = {
                isRefreshing = true
            })
    LaunchedEffect(key1 = isRefreshing) {
        if (isRefreshing) {
            isErrorLoading.value = updateListFromPullToRefresh()
            withContext(Dispatchers.Main) { isRefreshing = false }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .pullRefresh(pullRefreshState)
    ) {
        PullRefreshIndicator(
            refreshing = isRefreshing,
            state = pullRefreshState,
            modifier = Modifier.align(Alignment.TopCenter)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            itemsIndexed(uiState.list) { index, it ->
                when (index) {
                    0 -> {
                        Spacer(Modifier.height(8.dp))
                        ItemCryptocurrency(
                            it,
                            pickedCurrency,
                            navController
                        )
                    }

                    uiState.list.size - 1 -> {
                        ItemCryptocurrency(
                            it,
                            pickedCurrency,
                            navController
                        )
                        Spacer(Modifier.height(8.dp))
                    }

                    else -> ItemCryptocurrency(
                        it,
                        pickedCurrency,
                        navController
                    )
                }
            }
        }
        ErrorSnackbar(snackbarHostState)
    }
}

@Composable
fun ItemCryptocurrency(
    item: Cryptocurrency,
    pickedCurrency: StateFlow<Currency>,
    navController: NavHostController
) {
    val infoRoute = stringResource(id = R.string.info_cryptocurrency_route)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .clickable {
                navController.navigate("${infoRoute}/${item.name}/${item.id}") {
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
                    text = formattingCryptoPrice(
                        pickedCurrency.collectAsState().value,
                        item.price,
                        symbols
                    ),
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
                TextPercentages(Modifier.weight(1f), item.priceChangePercentage, symbols)
            }
        }
    }
}

@Composable
fun TextPercentages(
    modifier: Modifier,
    priceChangePercentage: Double,
    symbols: DecimalFormatSymbols
) {
    if ((priceChangePercentage / 0.01).toInt() == 0
        && (priceChangePercentage / 0.0001).toInt() != 0
    ) {
        if (priceChangePercentage > 0.0) {
            Text(
                modifier = modifier,
                text = DecimalFormat("+ 0.0000", symbols)
                    .format(priceChangePercentage) + "%",
                style = CryptoIncreasePriceTextStyle
            )
        } else {
            Text(
                modifier = modifier,
                text = DecimalFormat("- 0.0000", symbols)
                    .format(abs(priceChangePercentage)) + "%",
                style = CryptoDecreasePriceTextStyle
            )
        }
    } else if ((priceChangePercentage / 0.0001) == 0.0) {
        Text(
            modifier = modifier,
            text = DecimalFormat("0.00", symbols)
                .format(priceChangePercentage) + "%",
            style = CryptoNeutralChangePriceTextStyle
        )
    } else if (priceChangePercentage > 0.0) {
        Text(
            modifier = modifier,
            text = DecimalFormat("+ 0.00", symbols)
                .format(priceChangePercentage) + "%",
            style = CryptoIncreasePriceTextStyle
        )
    } else {
        Text(
            modifier = modifier,
            text = DecimalFormat("- 0.00", symbols)
                .format(abs(priceChangePercentage)) + "%",
            style = CryptoDecreasePriceTextStyle
        )
    }
}
