package com.irvin.cryptocurrency.presentation.ui.description_screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.presentation.ui.theme.Typography

@Composable
fun DescriptionCryptocurrency() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.wrapContentHeight().fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = "https://coin-images.coingecko.com/coins/images/1/large/bitcoin.png?1696501400",
                contentDescription = null
            )
        }
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.header_description_cryptocurrency),
                style = Typography.h6
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Bitcoin is a decentralized cryptocurrency originally described in a 2008 " +
                        "whitepaper by a person, or group of people, using the alias Satoshi " +
                        "Nakamoto. It was launched soon after, in January 2009.",
                style = Typography.body1
            )
            Spacer(Modifier.height(16.dp))
            Text(
                text = stringResource(R.string.header_categories_cryptocurrency),
                style = Typography.h6
            )
            Spacer(Modifier.height(8.dp))
            Text(
                text = "Smart Contract Platform, Ethereum Ecosystems",
                style = Typography.body1
            )

    }
}