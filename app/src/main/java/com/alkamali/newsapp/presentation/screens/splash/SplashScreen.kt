package com.alkamali.newsapp.presentation.screens.splash

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import androidx.compose.animation.core.*
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.alkamali.newsapp.R
import com.alkamali.newsapp.navigation.Destinations
import com.alkamali.newsapp.ui.theme.NewsAppTheme


@Composable
fun SplashScreen(
    navController: NavHostController,
    splashViewModel: SplashViewModel = hiltViewModel()
) {
    val onBoardingCompleted by splashViewModel.onBoardingCompleted.collectAsState()

    val rotate = remember { Animatable(0f) }

    LaunchedEffect(key1 = true) {
        rotate.animateTo(
            targetValue = 360f,
            animationSpec = tween(
                durationMillis = 1000,
                delayMillis = 200
            )
        )
        navController.popBackStack()
        if (onBoardingCompleted) {
            navController.navigate(Destinations.Home.route)
        } else {
            navController.navigate(Destinations.Welcome.route)
        }
    }

    Splash(rotate.value)
}


@Composable
fun Splash(degrees: Float) {
    NewsAppTheme {
        Box(
            modifier = Modifier.background(brush = brush())
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) { SplashLogo(degrees) }
    }
}

@Composable
private fun brush() = Brush.verticalGradient(
    listOf(
        MaterialTheme.colorScheme.primary,
        MaterialTheme.colorScheme.primary
    )
)

@Composable
fun SplashLogo(degrees: Float) {
    Image(
        modifier = Modifier.rotate(degrees),
        painter = painterResource(R.drawable.logo),
        contentDescription = stringResource(R.string.image_logo)
    )
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
fun SplashScreenPreview() {
    Splash(0f)
}