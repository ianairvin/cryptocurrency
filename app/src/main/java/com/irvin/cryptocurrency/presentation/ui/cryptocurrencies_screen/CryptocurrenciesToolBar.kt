package com.irvin.cryptocurrency.presentation.ui.cryptocurrencies_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.domain.entities.Currency
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.PickedChipsTopBarOverlayColor
import com.irvin.cryptocurrency.presentation.ui.theme.PickedChipsTopBarTextColor
import com.irvin.cryptocurrency.presentation.ui.theme.ToolBarTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.Typography
import com.irvin.cryptocurrency.presentation.ui.theme.UnpickedChipsTopBarOverlayColor
import com.irvin.cryptocurrency.presentation.ui.theme.UnpickedChipsTopBarTextColor
import kotlinx.coroutines.flow.StateFlow

@Composable
fun CryptocurrenciesToolBar(
    changePickedCurrency: () -> Unit,
    pickedCurrency: StateFlow<Currency>,
) {
    TopAppBar(
        modifier = Modifier.height(117.dp).fillMaxWidth(),
        backgroundColor = BackgroundColor
    ) {
        Column {
            Row(
                modifier = Modifier.wrapContentHeight().fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.padding(16.dp),
                    style = ToolBarTextStyle,
                    text = stringResource(R.string.currencies_title_top_bar)
                )
            }
            Row(
                Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(start = 16.dp, bottom = 12.dp, top = 12.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ChipItem(pickedCurrency, Currency.USD, changePickedCurrency)
                Spacer(Modifier.width(8.dp))
                ChipItem(pickedCurrency, Currency.RUB, changePickedCurrency)
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ChipItem(
    pickedCurrency: StateFlow<Currency>,
    currency: Currency,
    changePickedCurrency: () -> Unit
) {
    Chip(
        modifier = Modifier.height(32.dp).width(89.dp),
        colors = ChipDefaults.chipColors(
            backgroundColor = if (pickedCurrency.collectAsState().value == currency) {
                PickedChipsTopBarOverlayColor
            } else {
                UnpickedChipsTopBarOverlayColor
            },
            contentColor = if (pickedCurrency.collectAsState().value == currency) {
                PickedChipsTopBarTextColor
            } else {
                UnpickedChipsTopBarTextColor
            },
        ),
        onClick = {
            changePickedCurrency()
        }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                style = Typography.body2,
                text = if (currency == Currency.USD) stringResource(R.string.currency_usd) else stringResource(
                    R.string.currency_rub
                )
            )
        }
    }
}