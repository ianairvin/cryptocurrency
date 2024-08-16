package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.CurrenciesTitleTopBarTextColor
import com.irvin.cryptocurrency.presentation.ui.theme.PickedChipsTopBarOverlayColor
import com.irvin.cryptocurrency.presentation.ui.theme.PickedChipsTopBarTextColor
import com.irvin.cryptocurrency.presentation.ui.theme.Typography
import com.irvin.cryptocurrency.presentation.ui.theme.UnpickedChipsTopBarOverlayColor
import com.irvin.cryptocurrency.presentation.ui.theme.UnpickedChipsTopBarTextColor
import com.irvin.cryptocurrency.presentation.viewmodels.CryptocurrenciesVM

@Composable
fun CryptocurrenciesScreen(
    modifier: Modifier,
    cryptocurrenciesViewModel: CryptocurrenciesVM
) {
    Column(modifier = modifier) {
        ToolBar(
            Modifier.weight(1f),
            cryptocurrenciesViewModel.pickedCurrency,
            cryptocurrenciesViewModel::changeStateToLoading
        )
        CryptocurrenciesContent(
            Modifier.weight(5f),
            cryptocurrenciesViewModel.uiState,
            cryptocurrenciesViewModel::changeStateToLoading
        )
    }
}

@Composable
fun ToolBar(
    modifier: Modifier,
    pickedCurrency: MutableState<String>,
    changeStateToLoading: () -> Unit
) {
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = BackgroundColor
    ) {
        Column {
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(start = 16.dp),
                    color = CurrenciesTitleTopBarTextColor,
                    style = Typography.h6,
                    text = stringResource(R.string.currencies_title_top_bar)
                )
            }
            Row(
                Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChipItem(pickedCurrency, Currency.USD.title, 16.dp, changeStateToLoading)
                ChipItem(pickedCurrency, Currency.RUB.title, 8.dp, changeStateToLoading)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipItem(
    pickedCurrency: MutableState<String>,
    currency: String,
    paddingStart: Dp,
    changeStateToLoading: () -> Unit
) {
    Chip(
        modifier = Modifier
            .padding(start = paddingStart)
            .height(32.dp)
            .width(89.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = if (pickedCurrency.value == currency) {
                PickedChipsTopBarOverlayColor
            } else {
                UnpickedChipsTopBarOverlayColor
            },
            contentColor = if (pickedCurrency.value == currency) {
                PickedChipsTopBarTextColor
            } else {
                UnpickedChipsTopBarTextColor
            },
        ),
        onClick = {
            pickedCurrency.value = currency
            changeStateToLoading()
        }
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            style = Typography.body2,
            textAlign = TextAlign.Center,
            text = if (currency == Currency.USD.title) stringResource(R.string.currency_usd) else stringResource(
                R.string.currency_rub
            )
        )
    }
}