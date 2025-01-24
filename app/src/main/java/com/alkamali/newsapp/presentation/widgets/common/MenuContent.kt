package com.alkamali.newsapp.presentation.widgets.common

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.alkamali.newsapp.R
import com.alkamali.newsapp.domain.model.ItemEntity
import com.alkamali.newsapp.navigation.Destinations
import com.alkamali.newsapp.presentation.widgets.components.RatingWidget
import com.alkamali.newsapp.presentation.widgets.components.ShimmerEffect
import com.alkamali.newsapp.ui.theme.CORNER_S
import com.alkamali.newsapp.ui.theme.NewsAppTheme
import com.alkamali.newsapp.ui.theme.PADDING_M
import com.alkamali.newsapp.ui.theme.PADDING_S
import com.alkamali.newsapp.util.Constants.BASE_URL


@Composable
fun MenuContent(
    heroes: LazyPagingItems<ItemEntity>,
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {

    Log.d("MenuContent", "------->loadState: ${heroes.loadState} ")
    if (handlePagingResult(heroes)) {
        LazyColumn(
            contentPadding = PaddingValues(PADDING_S.dp),
            verticalArrangement = Arrangement.spacedBy(PADDING_S.dp),
            //state = rememberLazyListState()
        ) {

            items(
                count = heroes.itemCount,
                key = { item -> item },
                itemContent = { index ->
                    heroes[index]?.let {
                        MenuContentItem(
                            itemEntity = it,
                            navHostController = navHostController
                        )
                    }
                }
            )
        }
    }

}
@Composable
fun handlePagingResult(
    lazyPagingItems: LazyPagingItems<ItemEntity>
): Boolean {
    lazyPagingItems.apply {
        val error = when {
            loadState.refresh is LoadState.Error -> loadState.refresh as LoadState.Error
            loadState.append is LoadState.Error -> loadState.append as LoadState.Error
            loadState.prepend is LoadState.Error -> loadState.prepend as LoadState.Error
            else -> null
        }
        return when {
            loadState.refresh is LoadState.Loading -> {
                ShimmerEffect()
                false
            }

            error != null -> {
                false
            }

            else -> {
                true
            }
        }
    }

}

@Composable
fun MenuContentItem(
    itemEntity: ItemEntity,
    navHostController: NavHostController
) {
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current)
            .data(data = "${BASE_URL}/${itemEntity.image}")
            .apply(block = fun ImageRequest.Builder.() {

                placeholder(R.drawable.placeholder)
                error(R.drawable.no_data_svg)
            }).build()
    )


    Box(
        modifier = Modifier
            .height(320.dp)
            .clickable {
                navHostController.navigate(Destinations.Details.passItemId(itemEntity.id))
            },
        contentAlignment = Alignment.BottomCenter
    ) {
        Surface(shape = MaterialTheme.shapes.large) {
            Image(
                painter = painter,
                contentScale = ContentScale.Crop,
                contentDescription = "image hero",
                modifier = Modifier.fillMaxSize()
            )
        }
        /* LoadImageFromUrl(
             imageUrl = "${BASE_URL}/${hero.image}",
             placeholder = painterResource(id = R.drawable.network_error),
             errorImage = painterResource(id = R.drawable.network_error)
         )*/
        Surface(
            color = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5F),
            shape = RoundedCornerShape(
                bottomStartPercent = CORNER_S,
                bottomEndPercent = CORNER_S
            )
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(PADDING_S.dp),
                modifier = Modifier.fillMaxWidth()
                    //.align(Alignment.BottomCenter)
                    .padding(PADDING_M.dp)
            ) {
                Text(
                    text = "${itemEntity.name}", style =
                    MaterialTheme.typography.headlineSmall.copy(
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = "${itemEntity.about}",
                    style = MaterialTheme.typography.bodyMedium.copy(
                        color = MaterialTheme.colorScheme.inverseOnSurface
                    ),
                    modifier = Modifier.fillMaxWidth(.75F)
                )

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RatingWidget(rating = itemEntity.rating)
                    Text(
                        text = " (${itemEntity.rating})",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.inverseOnSurface
                        ),
                    )
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun MenuItemPreview() {
    val context = LocalContext.current
    NewsAppTheme {
        MenuContentItem(
            itemEntity = ItemEntity(
                id = 1,
                name = "sankera",
                image = "",
                about = "So this is how that item will look like on a light and dark theme as well.",
                rating = 3.7,
                power = 44,
                month = "July",
                day = "12",
                family = emptyList(),
                abilities = emptyList(),
                natureTypes = emptyList()
            ),
            navHostController = NavHostController(context)
        )
    }

}