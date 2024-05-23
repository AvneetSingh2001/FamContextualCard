package com.avneet.famcontextualcard.presentation.ui.components

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.core.graphics.toColorInt
import com.avneet.famcontextualcard.data.models.Entity


@Composable
fun FamFormattedText(
    entityList: List<Entity>?,
    simpleText: String?
): String {
    var plainText = simpleText
    var newReplacingString: String = ""
    entityList?.forEach { entity ->
        val placeholder = "{}"
        val replacement = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = entity.color?.let { Color(entity.color.toColorInt()) } ?: Color.Black,
                    fontStyle = when (entity.fontStyle) {
                        Entity.FontStyle.ITALIC -> FontStyle.Italic
                        else -> FontStyle.Normal
                    },
                    fontWeight = when (entity.fontStyle) {
                        Entity.FontStyle.BOLD -> FontWeight.Bold
                        else -> FontWeight.Normal
                    },
                    textDecoration = when (entity.fontStyle) {
                        Entity.FontStyle.UNDERLINE -> TextDecoration.Underline
                        else -> TextDecoration.None
                    }
                )
            ) { append(entity.text) }
        }
        newReplacingString += replacement.toString()
        plainText = plainText?.replaceFirst(placeholder, replacement.toString())
    }
    return if(plainText?.trim().isNullOrEmpty()) {
        newReplacingString + "3453"
    } else {
        plainText ?: ""
    }
}