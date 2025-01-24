package com.alkamali.newsapp.presentation.widgets.components


import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.graphics.vector.PathParser
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alkamali.newsapp.R
import com.alkamali.newsapp.ui.theme.NewsAppTheme

const val  FILLED_STARS_KEY = "filledStars"
const val  EMPTY_STARS_KEY = "emptyStars"
const val  HALF_STARS_KEY = "halfStars"

@Composable
fun RatingWidget(
    rating: Double,
    modifier: Modifier = Modifier
) {
    val result = calculateStar(rating)
    val starPathString = stringResource(R.string.star_path)
    val starPath = remember {
        PathParser().parsePathString(pathData = starPathString).toPath()
    }
    val starPathBounds = remember {
        starPath.getBounds()
    }


        Row(
            modifier = modifier,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            result[FILLED_STARS_KEY]?.let {
                repeat(it) {
                    FillStar(starPath, starPathBounds)
                }
            }
            result[HALF_STARS_KEY]?.let {
                repeat(it) {
                    HalfStar(starPath, starPathBounds)
                }
            }
            result[EMPTY_STARS_KEY]?.let {
                repeat(it) {
                    EmptyStar(starPath, starPathBounds)
                }
            }
        }
}

@Composable
fun FillStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 4f
) {
    val colorStar = MaterialTheme.colorScheme.tertiary
    Canvas(
        modifier = Modifier.size(24.dp),
        onDraw = {
            //val canvasSize = this.size
            scale(scale = scaleFactor) {
                val pathHeight = starPathBounds.height
                val pathWidth = starPathBounds.width
                val left = size.width.div(2) - pathWidth.div(1.3f)
                val top = size.height.div(2) - pathHeight.div(1.3f)
                translate(
                    left = left,
                    top = top
                ) {
                    drawPath(
                        path = starPath,
                        color = colorStar
                    )
                }
            }
        }
    )
}

@Composable
fun HalfStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 4f
) {
    val colorStar = MaterialTheme.colorScheme.tertiary
    val colorLStar = MaterialTheme.colorScheme.surfaceVariant
    val textMeasurer = rememberTextMeasurer()

    Canvas(
        modifier = Modifier.size(24.dp),
        onDraw = {
            val canvasSize = this.size

            scale(scale = scaleFactor) {
                val pathHeight = starPathBounds.height
                val pathWidth = starPathBounds.width
                val left = size.width.div(2) - pathWidth.div(1.3f)
                val top = size.height.div(2) - pathHeight.div(1.3f)

                translate(left = left, top = top) {
                    drawPath(path = starPath, color = colorLStar)
                    clipPath(path = starPath) {
                        drawRect(
                            color = colorStar,
                            size = Size(
                                width = starPathBounds.maxDimension.div(1.25f),
                                height = starPathBounds.height.times(scaleFactor)
                            )
                        )
                    }

                }

            }

        }
    )

}

@Composable
fun EmptyStar(
    starPath: Path,
    starPathBounds: Rect,
    scaleFactor: Float = 4f
) {

    val colorLStar = MaterialTheme.colorScheme.surfaceVariant

    Canvas(
        modifier = Modifier.size(24.dp),
        onDraw = {
            val canvasSize = this.size

            scale(scale = scaleFactor) {
                val pathHeight = starPathBounds.height
                val pathWidth = starPathBounds.width
                val left = size.width.div(2) - pathWidth.div(1.3f)
                val top = size.height.div(2) - pathHeight.div(1.3f)

                translate(left = left, top = top) {
                    drawPath(path = starPath, color = colorLStar)
                }
            }
        }
    )
}

@Composable
fun calculateStar(rating: Double): Map<String, Int> {
    val maxStars by remember { mutableIntStateOf(5) }
    var filledStars by remember { mutableIntStateOf(0) }
    var halfStars by remember { mutableIntStateOf(0) }
    var emptyStars by remember { mutableIntStateOf(0) }

    LaunchedEffect(key1 = rating) {
        val (first, last) = rating.toString()
            .split(".").map { it.toInt() }
        when {
            first in 0..maxStars && last in 0..9 -> {
                filledStars = first
                if (last in 1..5) {
                    halfStars = halfStars.plus(1)
                }
                if (last in 6..9) {
                    filledStars = filledStars.plus(1)
                }
                if (first == 5 && last > 0) {
                    emptyStars = 5
                    halfStars = 0
                    filledStars = 0
                }
            }

            else -> {
                Log.e("RatingWidget", "Invalid rating Number")
            }
        }
    }
    emptyStars = maxStars - (filledStars + halfStars)

    return mapOf(
        FILLED_STARS_KEY to filledStars,
        HALF_STARS_KEY to halfStars,
        EMPTY_STARS_KEY to emptyStars,
    )
}


@Preview(showBackground = true)
@Composable
fun RatingWidgetPreview() {
    NewsAppTheme {
        RatingWidget(rating = 4.0)
    }
}