package com.alkamali.newsapp.domain.model

import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import com.alkamali.newsapp.R

sealed class OnBoardingPage(
    val imageSource: Int?,
    val title: String?,
    val description: String?
) {
    data object First : OnBoardingPage(
        imageSource = (R.drawable.greetings),
        title = "Header One",
        description = "body content description One body content description One"
    )

    data object Second : OnBoardingPage(
        imageSource = R.drawable.boruto,
        title = "Header Second",
        description = "body content description Second body content description Second\""
    )

    data object Three : OnBoardingPage(
        imageSource = (R.drawable.power),
        title = "Header Three",
        description = "body content description Three body content description Second\""
    )

}