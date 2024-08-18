package com.irvin.cryptocurrency.presentation.ui.description_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.presentation.ui.theme.BackgroundColor
import com.irvin.cryptocurrency.presentation.ui.theme.IconBackColor
import com.irvin.cryptocurrency.presentation.ui.theme.Typography
import kotlinx.coroutines.flow.StateFlow

@Composable
fun DescriptionToolBar(
    modifier: Modifier,
    pickedCryptocurrency: StateFlow<String>,
    navController: NavHostController,
) {
    val interactionSource = remember { MutableInteractionSource() }
    TopAppBar(
        modifier = modifier.fillMaxWidth(),
        backgroundColor = BackgroundColor
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                imageVector = ImageVector.vectorResource(R.drawable.back_icon),
                modifier = Modifier
                    .size(24.dp)
                    .clickable(
                        interactionSource = interactionSource,
                        onClick = { navController.popBackStack() },
                        indication = null
                    ),
                colorFilter = ColorFilter.tint(IconBackColor),
                contentDescription = null
            )
            Text(
                text = pickedCryptocurrency.collectAsState().value,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 32.dp),
                style = Typography.h6
            )
        }
    }
}
