package com.alkamali.newsapp.presentation.widgets.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alkamali.newsapp.ui.theme.*


@Composable
fun ShimmerEffect() {
    LazyColumn(
        contentPadding = PaddingValues(PADDING_S.dp),
        verticalArrangement = Arrangement.spacedBy(PADDING_S.dp),
        //state = rememberLazyListState()
    ) {
        items(count = 3){
            AnimateShimmerItem()
        }
    }
}

@Composable
fun AnimateShimmerItem(){
    val trans = rememberInfiniteTransition(label = "")
    val alphaAnimation = trans.animateFloat(
        animationSpec = InfiniteRepeatableSpec(
            animation = tween(
                durationMillis = 800,
                easing = FastOutLinearInEasing
            )
        ),
        initialValue = 1.0F,
        targetValue = 0.0F, label = "",
    )
    ShimmerItem(alpha = alphaAnimation.value)
}

@Composable
fun ShimmerItem(
    alpha: Float = 0f
) {

    Surface(
        color = MaterialTheme.colorScheme.surfaceContainerHighL,
        shape = RoundedCornerShape(corner = CornerSize(CORNER_M.dp)),
        modifier = Modifier
            .height(DEFAULT_HIGH_ITEM.dp)
            //.padding(PADDING_XM.dp)
            .fillMaxSize()

    ) {
        Column( //modifier = Modifier.padding(PADDING_XM.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

           Column( modifier = Modifier
               .background(
                   color = MaterialTheme.colorScheme.surfaceContainerLowest,
                   shape = ShapeDefaults.Large.copy(topStart = CornerSize(CORNER_XXS.dp))
               ).padding(PADDING_XM.dp)
           ) {
               Surface(
                   color = MaterialTheme.colorScheme.surfaceContainerHighL,
                   shape = RoundedCornerShape(corner = CornerSize(CORNER_M.dp)),
                   modifier = Modifier
                       .height(20.dp)
                       .fillMaxSize(.6F)
                       .alpha(alpha)
               ) {}
               Spacer(modifier = Modifier.height(PADDING_M.dp))
               repeat(3){
                   Surface(
                       color = MaterialTheme.colorScheme.surfaceContainerHighL,
                       shape = RoundedCornerShape(corner = CornerSize(CORNER_M.dp)),
                       modifier = Modifier
                           .height(15.dp)
                           .fillMaxSize(.9F)
                           .alpha(alpha)
                   ) {}
                   Spacer(modifier = Modifier.height(PADDING_S.dp))
               }
               Spacer(modifier = Modifier.height(PADDING_M.dp))
               Row (
                   modifier = Modifier
                       .fillMaxWidth()
               ){
                   repeat(5){
                       Surface(
                           color = MaterialTheme.colorScheme.surfaceContainerHighL,
                           shape = RoundedCornerShape(corner = CornerSize(CORNER_XS.dp)),
                           modifier = Modifier
                               .size(30.dp)
                               .alpha(alpha)
                       ) {}
                       Spacer(modifier = Modifier.width(PADDING_S.dp))
                   }

               }
           }

        }
    }

}




@Preview(showBackground = true)
@Composable
fun ShimmerEffectPreview() {
    NewsAppTheme {
        ShimmerEffect()
    }

}