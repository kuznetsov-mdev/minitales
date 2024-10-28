package com.example.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.PlatformTextStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import ru.sandbox.minitales.theme.R

private val poppins = FontFamily(
    Font(R.font.poppins_regular),
    Font(R.font.poppins_bold),
    Font(R.font.poppins_extra_bold),
    Font(R.font.poppins_medium),
    Font(R.font.poppins_thin),
    Font(R.font.poppins_italic),
    Font(R.font.poppins_light),
    Font(R.font.poppins_black),
)
val defaultTextStyLe = TextStyle(
    fontFamily = poppins,
    platformStyle = PlatformTextStyle(
        includeFontPadding = false,
    ),
    lineHeightStyle = LineHeightStyle(
        alignment = LineHeightStyle.Alignment.Center,
        trim = LineHeightStyle.Trim.None,
    ),
)
