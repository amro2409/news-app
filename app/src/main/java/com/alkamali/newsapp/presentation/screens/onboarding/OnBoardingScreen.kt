package com.alkamali.newsapp.presentation.screens.onboarding

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.util.Pair
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.alkamali.newsapp.R
import com.alkamali.newsapp.domain.model.OnBoardingPage
import com.alkamali.newsapp.navigation.Destinations
import com.alkamali.newsapp.ui.theme.NewsAppTheme
import com.alkamali.newsapp.ui.theme.PADDING_S
import com.alkamali.newsapp.util.Constants.PAGE_COUNT_ONBOARD


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnBoardingScreen(
    navController: NavHostController,
    onBoardingViewModel: OnBoardingViewModel = hiltViewModel()
) {
    val pages = listOf(
        OnBoardingPage.First,
        OnBoardingPage.Second,
        OnBoardingPage.Three
    )
    val pagerState = rememberPagerState(
        initialPage = 0,
        initialPageOffsetFraction = 0f
    ) { PAGE_COUNT_ONBOARD }

    val lastOnBoardingPage = rememberSaveable { mutableStateOf(false) }
    lastOnBoardingPage.value = pagerState.currentPage == PAGE_COUNT_ONBOARD - 1

    NewsAppTheme {
        Surface(
            color = MaterialTheme.colorScheme.surface,
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                HorizontalPager(
                    state = pagerState,
                    verticalAlignment = Alignment.Top
                ) { PagerContent(pages[it]) }

                HorizontalPageIndicator(pagerState.currentPage)

                AnimatedVisibility(visible = lastOnBoardingPage.value) {
                    ButtonNav {
                        navController.popBackStack()
                        navController.navigate(Destinations.Home.route)
                        onBoardingViewModel.saveOnBoardingState(true)
                    }
                }

                Text(
                    text = "Version-1.0.0",
                    style = MaterialTheme.typography.labelLarge.copy(
                        color = MaterialTheme.colorScheme.onSurface,
                        fontFamily = FontFamily.Cursive
                    )
                )

            }
        }
    }

}

@Composable
fun PagerContent(onBoardingPage: OnBoardingPage) {
    Column(
        modifier = Modifier
            .fillMaxHeight(0.75F)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(onBoardingPage.imageSource ?: R.drawable.placeholder),
            contentDescription = "Image Top"
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "${onBoardingPage.title}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.headlineSmall.copy(
                    //fontSize = 24.sp,
                    //fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface
                )
            )
            Text(
                text = "${onBoardingPage.description}",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium.copy(
                    //fontSize = 16.sp,
                    //fontWeight = FontWeight.Normal,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                ),
                modifier = Modifier.fillMaxWidth(0.75F)
                    .padding(top = PADDING_S.dp)
            )
        }

    }
}

@Composable
fun ButtonNav(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        shape = MaterialTheme.shapes.small,
        modifier = Modifier.fillMaxWidth(0.75F)

    ) {
        Text(
            text = "Get Started.",
            style = MaterialTheme.typography.labelLarge
        )
    }
}

@Composable
fun HorizontalPageIndicator(currentPage: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth().height(48.dp)
    ) {
        repeat(PAGE_COUNT_ONBOARD) {
            val pair = if (it == currentPage)
                Pair(MaterialTheme.colorScheme.primary, 16.dp)
            else Pair(MaterialTheme.colorScheme.outline, 12.dp)

            Box(
                modifier =
                Modifier
                    .padding(PADDING_S.dp)
                    .size(pair.second)
                    .background(
                        color = pair.first,
                        shape = CircleShape
                    )
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun OnBoardingScreenPreview() {
    val navController = rememberNavController()
    OnBoardingScreen(navController)
}