package com.irvin.cryptocurrency.presentation.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
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
        textAlign = TextAlign.Left,
        color = H6TextColor
    ),
    body1 = TextStyle(
        fontFamily = Roboto,
        fontWeight = FontWeight(400),
        fontSize = 16.sp,
        lineHeight = 18.75.sp,
        letterSpacing = 0.15.sp,
        textAlign = TextAlign.Left,
        color = Body1TextColor
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

val ToolBarTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 20.sp,
    lineHeight = 23.44.sp,
    letterSpacing = 0.15.sp,
    textAlign = TextAlign.Left,
    color = TitleTopBarTextColor
)

val CryptoFullNameTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Medium,
    fontSize = 16.sp,
    lineHeight = 18.75.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.Start,
    color = CryptoFullNameTextColor,
)

val CryptoShortNameTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.41.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.Start,
    color = CryptoShortNameTextColor,
)

val CryptoPriceTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight(600),
    fontSize = 16.sp,
    lineHeight = 18.75.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.End,
    color = CryptoPriceTextColor,
)

val CryptoIncreasePriceTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.41.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.End,
    color = CryptoIncreasePriceTextColor
)

val CryptoDecreasePriceTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.41.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.End,
    color = CryptoDecreasePriceTextColor
)

val CryptoNeutralChangePriceTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 14.sp,
    lineHeight = 16.41.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.End,
    color = CryptoNeutralChangePriceTextColor
)

val ErrorTextStyle = TextStyle(
    fontFamily = Roboto,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp,
    lineHeight = 18.75.sp,
    letterSpacing = 0.sp,
    textAlign = TextAlign.Center,
    color = ErrorTextColor
)

val LinkTextStyle = SpanStyle(
    color = Color.Blue
)