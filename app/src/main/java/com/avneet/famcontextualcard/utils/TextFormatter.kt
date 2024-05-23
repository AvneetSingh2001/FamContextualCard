package com.avneet.famcontextualcard.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import com.avneet.famcontextualcard.data.models.FormattedText

object TextFormatter {
    fun String.formatWithEntities(formattedText: FormattedText): String {
        val formattedString = StringBuilder(this)
        formattedText.entityList.forEach { entity ->
            val placeholder = "{}"
            val index = formattedString.indexOf(placeholder)
            if (index != -1) {
                formattedString.replace(index, index + placeholder.length, entity.text)
            }
        }
        return formattedString.toString()

    }
}

val annotatedLinkString = buildAnnotatedString {
    val str = "Click this link to go to web site"
    val startIndex = str.indexOf("link")
    val endIndex = startIndex + 4
    append(str)
    addStyle(
        style = SpanStyle(
            color = Color(0xff64B5F6),
            textDecoration = TextDecoration.Underline
        ), start = startIndex, end = endIndex
    )
}