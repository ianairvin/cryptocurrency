package com.irvin.cryptocurrency.presentation.ui.app_common_screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.SnackbarHost
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.irvin.cryptocurrency.presentation.ui.theme.ErrorSnackbarOverlayColor
import com.irvin.cryptocurrency.presentation.ui.theme.ErrorSnackbarTextStyle


@Composable
fun ErrorSnackbar(
    snackbarHostState: SnackbarHostState
) {
    SnackbarHost(hostState = snackbarHostState) {
        Box(Modifier.fillMaxSize()) {
            Surface(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.BottomCenter),
                shape = RoundedCornerShape(4.dp),
                color = ErrorSnackbarOverlayColor,
                elevation = 6.dp,
            ) {
                Text(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth(),
                    text = it.message,
                    style = ErrorSnackbarTextStyle
                )
            }
        }
    }
}