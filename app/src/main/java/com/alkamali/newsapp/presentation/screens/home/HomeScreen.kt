package com.alkamali.newsapp.presentation.screens.home


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.compose.collectAsLazyPagingItems
import com.alkamali.newsapp.presentation.widgets.common.MenuContent
import com.alkamali.newsapp.presentation.widgets.components.RatingWidget


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val getAllHeroes = viewModel.getAllHeroes.collectAsLazyPagingItems()

    Scaffold(
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface),
        topBar = {
            HomeAppbar(onSearchClick = {})
        },
        content = {
            Column (modifier = Modifier.padding(it)){
                Text(text = "size: ${getAllHeroes.itemCount}")
                MenuContent(
                    heroes = getAllHeroes,
                    navHostController = navHostController
                )
            }

        }
    )

}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    val context = LocalContext.current
    HomeScreen(navHostController = NavHostController(context))
}
