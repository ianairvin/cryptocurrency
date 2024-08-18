package com.irvin.cryptocurrency.presentation.ui.info_screen

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
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState

@Composable
fun InfoCryptocurrency(uiState: InfoUiState.Info) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = uiState.info.image,
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
            text = uiState.info.description,
            style = Typography.body1
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.header_categories_cryptocurrency),
            style = Typography.h6
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = uiState.info.categories.joinToString(separator = ", "),
            style = Typography.body1
        )
    }
}