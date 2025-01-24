package com.alkamali.newsapp.presentation.widgets.common

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size


@Composable
fun LoadImageFromUrl(
    imageUrl: String,
    placeholder: Painter,
    errorImage: Painter,
    modifier:Modifier = Modifier.height(220.dp).fillMaxWidth()

) {
    //val imagePainter = rememberAsyncImagePainter(imageUrl)
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .crossfade(true) // تأثير الانتقال السلس
            .size(Size.ORIGINAL) // الحجم الأصلي للصورة
            .build(),
        contentScale = ContentScale.Inside,
        contentDescription = "Loaded Image",
        placeholder = placeholder, // الصورة المؤقتة أثناء التحميل
        error = errorImage,        // الصورة في حالة وجود خطأ
        modifier = modifier
    )
}
