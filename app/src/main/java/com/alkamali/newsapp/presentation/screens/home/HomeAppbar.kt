package com.alkamali.newsapp.presentation.screens.home


import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.alkamali.newsapp.R
import com.alkamali.newsapp.ui.theme.surfaceContainer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeAppbar(onSearchClick: ()-> Unit) {
     TopAppBar(
         title = {
             Text(
                 text = stringResource(R.string.title_app_bar),
                 style = MaterialTheme.typography.headlineSmall
             )
         },
         colors = TopAppBarDefaults.largeTopAppBarColors(
             containerColor = MaterialTheme.colorScheme.surfaceContainer
         ),
         actions = {
             IconButton(onClick = onSearchClick){
                 Icon(
                     imageVector = Icons.Default.Search,
                     contentDescription = stringResource(R.string.search_icon)
                 )
             }
         }
     )
 }