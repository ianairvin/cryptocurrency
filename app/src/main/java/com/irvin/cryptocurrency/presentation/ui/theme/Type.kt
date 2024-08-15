package com.irvin.cryptocurrency.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.irvin.cryptocurrency.R

val Roboto = FontFamily(
    Font(R.font.roboto_regular, FontWeight.Normal, FontStyle.Normal),
    Font(R.font.roboto_medium, FontWeight.Medium, FontStyle.Normal)
)

val Typography = Typography(
    h6 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight.Medium,
        fontSize = 20.sp,
        lineHeight = 23.44.sp,
        letterSpacing = 0.15.sp,
        textAlign = TextAlign.Left
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 18.75.sp,
        letterSpacing = 0.15.sp,
        textAlign = TextAlign.Left
    ),
    body2 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight(400),
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp,
        textAlign = TextAlign.Center
    ),
    button = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight(500),
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.75.sp,
        textAlign = TextAlign.Center
    )
)
