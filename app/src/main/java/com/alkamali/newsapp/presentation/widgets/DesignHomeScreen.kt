package com.alkamali.newsapp.presentation.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.material.icons.filled.*
import androidx.compose.ui.draw.shadow
import com.alkamali.newsapp.R
import com.alkamali.newsapp.ui.theme.CORNER_L
import com.alkamali.newsapp.ui.theme.NewsAppTheme
import com.alkamali.newsapp.ui.theme.PADDING_M
import com.alkamali.newsapp.ui.theme.surfaceContainerLowest


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(
        bottomBar = { BottomNavigationBar() }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(color = MaterialTheme.colorScheme.surface)
        ) {
            // Top Bar
            TopAppBarSection()

            // User Account Info
            AccountSection()

            // Tasks section
            TasksSection()


            // Action Buttons
            ActionButtons()
        }
    }
}
@Composable
fun TopAppBarSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, end = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Info, contentDescription = "info", tint = Color.Gray)
        }

        Text(
            text = "الصفحة الرئيسية",
            style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold),
        )

        IconButton(onClick = { /*TODO*/ }) {
            Icon(Icons.Filled.Notifications, contentDescription = "Notifications", tint = Color.Gray)
        }
    }
}

@Composable
fun AccountSection() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor =MaterialTheme.colorScheme.primary
        )
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.urashiki), // Replace with your actual user profile image
                    contentDescription = "User Avatar",
                    modifier = Modifier
                        .size(50.dp)
                        .clip(CircleShape),
                    contentScale = ContentScale.Crop
                )

                Spacer(modifier = Modifier.weight(1f))

                Text(
                    text = "Demo Account",
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold, color = Color.White),
                )

                Spacer(modifier = Modifier.weight(1f))

            }
            Spacer(modifier = Modifier.height(4.dp))

            Text(
                text = "اليوم",
                style = TextStyle(fontSize = 14.sp, color = Color.White),
                modifier = Modifier.align(Alignment.Start)
            )
            Text(
                text = "Tue, 07 Jan 2025",
                style = TextStyle(fontSize = 16.sp, color = Color.White),
                modifier = Modifier.align(Alignment.Start)
            )

            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { /*TODO*/ },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
                ,
                contentPadding = PaddingValues(8.dp)
            ) {
                Text(text = "تسجيل الدخول", color = Color.Black)
                Icon(Icons.Filled.ArrowForward, contentDescription = "Go to Login", tint = Color.Black)
            }
        }
    }
}
@Composable
fun TasksSection() {
    Column(modifier = Modifier.padding(horizontal = 16.dp)){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "عرض الكل",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Gray,
            )

            Text(
                text = "مهمتك",
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
        ) {
            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .background(
                                color = MaterialTheme.colorScheme.inversePrimary,
                                shape = RoundedCornerShape(6.dp)
                            )
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "1 Completed", color = Color.Black)
                    }

                    Box(
                        modifier = Modifier
                            .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text(text = "Medium", color = Color.Black)
                    }
                }

                Text(
                    text = "TASK-2025-00002",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
                Text(
                    text = "0.0",
                    style = MaterialTheme.typography.titleLarge,
                    color = Color.White,
                )

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Filled.Notifications, contentDescription = "date", tint = Color.White)
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "2025-01-07",
                        style = MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                    )
                }
            }
        }
    }
}


@Composable
fun ActionButtons() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "الاحراات المتوفرة",
            style = TextStyle(fontSize = 18.sp, textAlign = TextAlign.Center),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(PADDING_M.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(PADDING_M.dp),
            ) {
                ActionButton(icon = Icons.Filled.Home, text = "عطلة", onClick = { /*TODO*/ },modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
                ActionButton(icon = Icons.Default.ShoppingCart, text = "الرواتب", onClick = { /*TODO*/ },  modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
                ActionButton(icon = Icons.Filled.Check, text = "إجازة", onClick = { /*TODO*/ },  modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
            }
            Row(
                horizontalArrangement = Arrangement.spacedBy(PADDING_M.dp),
            ) {
                ActionButton(icon = Icons.Filled.Home, text = "مهام", onClick = { /*TODO*/ },  modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
                ActionButton(icon = Icons.Default.ShoppingCart, text = "الحضور", onClick = { /*TODO*/ },  modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
                ActionButton(icon = Icons.Filled.Check, text = "إجازة", onClick = { /*TODO*/ },  modifier =  Modifier.height(84.dp)
                    .weight(1.0F))
            }
        }


        Spacer(modifier = Modifier.height(16.dp))


        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.size(60.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primary,
                contentColor = MaterialTheme.colorScheme.onPrimary,
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Add New Task", tint = Color.White, modifier = Modifier.size(30.dp))
        }
    }
}

@Composable
fun ActionButton(icon: ImageVector, text: String, onClick: () -> Unit,  modifier:Modifier =  Modifier) {
    val color = MaterialTheme.colorScheme.primary
    Button(
        onClick = onClick,
        elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(CORNER_L.dp),
        colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.surfaceContainerLowest),
        modifier = modifier

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(8.dp)
        ) {
            Icon(icon, contentDescription = text, tint = color, modifier = Modifier.size(24.dp))
            Text(text = text, style = MaterialTheme.typography.bodyMedium,
                color = color)
        }
    }
}

@Composable
private fun BottomNavigationBar() {
    NavigationBar(
        containerColor = Color.White,
        modifier = Modifier.height(70.dp)
    ) {
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Person, contentDescription = "Home") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.List, contentDescription = "Favorites") },
            selected = false,
            onClick = { /* TODO */ }
        )
        NavigationBarItem(
            icon = { Icon(Icons.Filled.Home, contentDescription = "Profile") },
            selected = true,
            onClick = { /* TODO */ }
        )
    }
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    NewsAppTheme {
        HomeScreen()
    }
}