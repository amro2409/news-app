package com.alkamali.newsapp.presentation.screens

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.alkamali.newsapp.R
import com.alkamali.newsapp.ui.theme.NewsAppTheme
import com.bumptech.glide.load.resource.bitmap.Rotate


@Composable
fun SplashScreen(navController: NavHostController) {
    val rotate = remember { androidx.compose.animation.core.Animatable(0f) }
    LaunchedEffect(key1 = true){
       rotate.animateTo(
           targetValue = 360f,
           animationSpec = tween(
               durationMillis = 1000,
               delayMillis = 200
           )
       )
    }
  Splash(rotate.value)
}


@Composable
fun Splash(rotate: Float) {
    NewsAppTheme {
        Box(modifier = Modifier
                .background(
                    brush =
                    Brush.verticalGradient(
                        listOf(
                            MaterialTheme.colorScheme.primary,
                            MaterialTheme.colorScheme.primary
                        )
                    )
                ).fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                modifier = Modifier.rotate(rotate),
                painter = painterResource(R.drawable.logo),
                contentDescription = stringResource(R.string.image_logo)
            )
        }
    }
}

@Preview(
    showBackground = true,
    uiMode = UI_MODE_NIGHT_NO
)
@Composable
 fun SplashScreenPreview(){
   Splash(0f)
 }