package com.avneet.famcontextualcard.presentation.ui.screens.card_components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.avneet.famcontextualcard.data.models.Card
import com.avneet.famcontextualcard.data.models.CardGroup
import com.avneet.famcontextualcard.utils.gradientBackground


@Composable
fun DynamicWidthCardGroup(cardGroup: CardGroup) {
    Row(
        modifier = Modifier
            .padding(16.dp)
            .height(cardGroup.height?.dp ?: 0.dp)
    ) {
        cardGroup.cardList.forEach { card ->
            DynamicWidthCard(
                modifier = Modifier.fillMaxHeight(),
                card = card
            )

            if (card != cardGroup.cardList.last()) {
                Spacer(modifier = Modifier.width(8.dp))
            }
        }
    }
}


@Composable
fun DynamicWidthCard(
    modifier: Modifier = Modifier,
    card: Card
) {
    Card(
        modifier = modifier
            .aspectRatio(card.bgImage?.aspectRatio ?: 0.5f),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (card.bgColor != null) Color(card.bgColor.toColorInt())
            else Color.Transparent
        ),
    ) {
        Box(modifier = Modifier.fillMaxSize().gradientBackground(
            angle = card.bgGradient?.angle,
            colors = card.bgGradient?.colorList
        ))
    }
}