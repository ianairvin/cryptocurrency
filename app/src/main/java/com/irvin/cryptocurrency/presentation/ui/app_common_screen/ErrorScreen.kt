package com.irvin.cryptocurrency.presentation.ui.app_common_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.presentation.ui.theme.ButtonColor
import com.irvin.cryptocurrency.presentation.ui.theme.ButtonTextColor
import com.irvin.cryptocurrency.presentation.ui.theme.ErrorTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.Typography


@Composable
fun ErrorScreen(
    changeStateToLoading: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(120.dp),
            imageVector = ImageVector.vectorResource(id = R.drawable.error_icon),
            contentDescription = null
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