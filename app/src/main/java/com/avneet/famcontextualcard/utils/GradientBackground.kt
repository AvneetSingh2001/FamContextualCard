package com.avneet.famcontextualcard.utils

import androidx.compose.foundation.background
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.geometry.Offset
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

fun Modifier.gradientBackground(colors: List<String>?, angle: Float?) = this.then(
    Modifier.drawBehind {
        val parsedColors = colors?.map { safeParseColor(it) } ?: emptyList()
        val angleRad = (angle ?: 45f) / 270f * PI.toFloat()
        val x = cos(angleRad)
        val y = sin(angleRad)

        val startX = if (x >= 0) 0f else size.width
        val startY = if (y >= 0) 0f else size.height
        val endX = if (x >= 0) size.width else 0f
        val endY = if (y >= 0) size.height else 0f

        drawRect(
            brush = Brush.linearGradient(
                colors = parsedColors,
                start = Offset(startX, startY),
                end = Offset(endX, endY)
            ),
            size = size
        )
    }
)


private fun safeParseColor(colorString: String): Color {
    return try {
        Color(android.graphics.Color.parseColor(colorString))
    } catch (e: IllegalArgumentException) {
        Color.Black // default color if parsing fails
    }
}
