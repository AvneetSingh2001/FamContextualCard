package com.avneet.famcontextualcard.presentation.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.core.graphics.toColorInt
import com.avneet.famcontextualcard.data.models.Entity


@Composable
fun FamFormattedText(
    entityList: List<Entity>?,
    simpleText: String?
): AnnotatedString {
    val builder = AnnotatedString.Builder()

    var plainText = simpleText ?: ""

    entityList?.forEach { entity ->
        val placeholderIndex = plainText.indexOf("{}")
        if (placeholderIndex != -1) {

            builder.append(plainText.substring(0, placeholderIndex))

            builder.withStyle(
                style = SpanStyle(
                    color = entity.color?.let { Color(it.toColorInt()) } ?: Color.Black,
                    fontStyle = FontStyle.Italic,
                    fontWeight = when (entity.fontStyle) {
                        Entity.FontStyle.BOLD -> FontWeight.Bold
                        else -> FontWeight.Normal
                    },
                    textDecoration = when (entity.fontStyle) {
                        Entity.FontStyle.UNDERLINE -> TextDecoration.Underline
                        else -> TextDecoration.None
                    },
                    fontSize = when (entity.fontSize) {
                        null -> TextUnit.Unspecified
                        else -> entity.fontSize.sp
                    }
                )
            ) { append(entity.text) }

            plainText = plainText.substring(placeholderIndex + 2)
        }
    }

    builder.append(plainText)

    return builder.toAnnotatedString()
}