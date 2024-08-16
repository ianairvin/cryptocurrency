package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.theme.ButtonColor
import com.irvin.cryptocurrency.presentation.ui.theme.ButtonTextColor
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoFullNameTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoIncreasePriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoPriceTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.CryptoShortNameTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.ErrorTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.ProgressBarColor
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Cryptocurrencies
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Error
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Initial
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesUiState.Loading
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CryptocurrenciesContent(
    modifier: Modifier,
    uiState: StateFlow<CryptocurrenciesUiState>,
    changeStateToLoading: () -> Unit,
    pickedCurrency: StateFlow<Currency>
) {
    Box(modifier.fillMaxSize()) {
        when (uiState.collectAsState().value) {
            is Initial -> Unit
            is Loading -> LoadingCryptocurrencies()
            is Cryptocurrencies -> CryptocurrenciesList(
                uiState.collectAsState().value as Cryptocurrencies,
                pickedCurrency
            )

            is Error -> CryptocurrenciesError(changeStateToLoading)
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
fun CryptocurrenciesError(
    changeStateToLoading: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.btc),
            contentDescription = ""
        )
        Spacer(Modifier.padding(8.dp))
        Text(
            text = stringResource(R.string.error),
            style = ErrorTextStyle
        )
        Spacer(Modifier.padding(16.dp))
        Button(
            modifier = Modifier
                .height(36.dp)
                .width(175.dp),
            onClick = { changeStateToLoading() },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = ButtonColor,
                contentColor = ButtonTextColor
            )
        ) {
            Text(
                text = stringResource(R.string.error_button),
                style = typography.button
            )
        }
    }
}

@Composable
fun CryptocurrenciesList(
    uiState: Cryptocurrencies,
    pickedCurrency: StateFlow<Currency>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp, bottom = 8.dp)
    ) {
        itemsIndexed(uiState.list) { _, it ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
            ) {
                Image(
                    modifier = Modifier.padding(start = 16.dp, top = 8.dp),
                    imageVector = ImageVector.vectorResource(id = R.drawable.btc),
                    contentDescription = ""
                )
                Column(Modifier.fillMaxSize()) {
                    Row(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxWidth()
                            .padding(start = 8.dp, end = 16.dp, top = 8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            modifier = Modifier.weight(1f),
                            text = it.name,
                            style = CryptoFullNameTextStyle
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = (if (pickedCurrency.collectAsState().value == Currency.USD) "$"
                                    else "₽") + it.priceChange.toString(),
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
                            text = it.shortName.uppercase(),
                            style = CryptoShortNameTextStyle
                        )
                        Text(
                            modifier = Modifier.weight(1f),
                            text = "${it.priceChangePercentage} %",
                            style = CryptoIncreasePriceTextStyle
                        )
                    }
                }
            }
        }
    }
}