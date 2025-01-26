package com.alkamali.newsapp.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import androidx.compose.material.icons.filled.*
import com.alkamali.newsapp.R
import com.alkamali.newsapp.ui.theme.*


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen() {
    val showCareInstructions = remember { mutableStateOf(false) }
    val showFAQ = remember { mutableStateOf(false) }

    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = { BottomNavigationBar() },
        modifier = Modifier.fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                Image(
                    painter = painterResource(id = R.drawable.lemon_tree), // Replace with your actual image resource
                    contentDescription = "Product Image",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(350.dp),
                    contentScale = ContentScale.Fit
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, top = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(Icons.Filled.ArrowBack, contentDescription = "Back", tint = Color.Black)
                    }

                    Row(
                        modifier = Modifier
                            .padding(end = 8.dp)
                    ) {
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.Share, contentDescription = "Share", tint = Color.Black)
                        }
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorite", tint = Color.Black)
                        }
                    }

                }

                // Price Tag
                Box(
                    modifier = Modifier
                        .padding(16.dp)
                        .background(
                            color = MaterialTheme.colorScheme.surface,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(8.dp)
                        .align(Alignment.BottomStart)

                ) {
                    Text(
                        text = "$45",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }


            Column(modifier = Modifier.padding(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = "Agave",
                        style = MaterialTheme.typography.headlineSmall,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "In Stock",
                        tint = Color.Green,
                        modifier = Modifier.size(20.dp)
                    )
                    Text(
                        text = "In stock",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent sollicitudin nibh at libero fermentum rutrum. Integer ut massa mattis, tempus enim sed, commodo lorem. Mauris tincidunt mi sapien, eget volutpat elit consequat laoreet. Integer hendrerit. Integer hendrerit.",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    shape = RoundedCornerShape(CORNER_XXXL.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(text = "Add to cart")
                }

                Spacer(modifier = Modifier.height(16.dp))

                ExpandableSection(
                    title = "Care instructions",
                    isExpanded = showCareInstructions.value,
                    onToggle = { showCareInstructions.value = it }
                ) {
                    Text(
                        text = "Detailed care instructions for the Agave plant will be shown here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(PADDING_S.dp))

                ExpandableSection(
                    title = "FAQ",
                    isExpanded = showFAQ.value,
                    onToggle = { showFAQ.value = it }
                ) {
                    Text(
                        text = "Frequently asked questions about the Agave plant will be listed here.",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.Gray
                    )
                }

                Spacer(modifier = Modifier.height(PADDING_XM.dp))
            }
        }
    }
}


@Composable
fun ExpandableSection(
    title: String,
    isExpanded: Boolean,
    onToggle: (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerLow,
        shape = RoundedCornerShape(CORNER_XXL.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(PADDING_M.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = title, style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.weight(0.6F)
                        .padding(horizontal = PADDING_XS.dp),
                    )
                IconButton(onClick = { onToggle(!isExpanded) }) {
                    Icon(
                        imageVector = if (isExpanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Expand/Collapse",
                        tint = Color.Black
                    )
                }
            }
            if (isExpanded) {
                Spacer(modifier = Modifier.height(PADDING_S.dp))
                content()
            }
        }
    }
    //Divider()

}

@Composable
private fun BottomNavigationBar() {
    NavigationBar(
        // containerColor = Color(0xFFF8F0EC),
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Home") },
            label = { Text("Home") },
            selected = true,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.FavoriteBorder, contentDescription = "Favorites") },
            label = { Text("Favorites") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.ShoppingCart, contentDescription = "Cart") },
            label = { Text("Cart") },
            selected = false,
            onClick = { /* TODO */ }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ProductScreenPreview() {
    NewsAppTheme {
        ProductScreen()
    }
}