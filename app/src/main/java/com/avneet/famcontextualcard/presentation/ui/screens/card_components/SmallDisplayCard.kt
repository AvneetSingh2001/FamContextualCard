package com.avneet.famcontextualcard.presentation.ui.screens.card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.avneet.famcontextualcard.data.models.CardImage
import com.avneet.famcontextualcard.presentation.ui.components.FamFormattedText

@Composable
fun SmallDisplayCardGroup(cardGroup: CardGroup) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)) {
        if (cardGroup.cardList.size > 1 && cardGroup.isScrollable) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(paddingValues = PaddingValues(horizontal = 16.dp))
            ) {
                cardGroup.cardList.forEach { card ->
                    SmallDisplayCard(
                        modifier = Modifier,
                        card = card
                    )

                    if (card != cardGroup.cardList.last()) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        } else {
            Row(
                modifier = Modifier
                    .padding(paddingValues = PaddingValues(horizontal = 16.dp))
            ){
                cardGroup.cardList.forEach { card ->
                    SmallDisplayCard(
                        modifier = Modifier.weight(1f),
                        card = card
                    )

                    if (card != cardGroup.cardList.last()) {
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                }
            }
        }
    }
}


@Composable
fun SmallDisplayCard(
    modifier: Modifier = Modifier,
    card: Card
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color(card.bgColor.toColorInt()))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (card.icon != null) {
            when (card.icon.imageType) {
                CardImage.ImageType.EXTERNAL -> {
                    AsyncImage(
                        model = card.icon.imageUrl,
                        contentDescription = null,
                        modifier = Modifier.aspectRatio(card.icon.aspectRatio ?: 0.5f)
                    )
                }

                CardImage.ImageType.ASSET -> {
                    AsyncImage(
                        model = card.icon.assetType,
                        contentDescription = null,
                        modifier = Modifier.aspectRatio(card.icon.aspectRatio ?: 0.5f)
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))
        }

        Column(modifier = Modifier.fillMaxHeight(), verticalArrangement = Arrangement.Center) {
            if (card.formattedTitle != null) {
                Text(
                    text = FamFormattedText(
                        entityList = card.formattedTitle.entityList,
                        simpleText = card.formattedTitle.text
                    ),
                )
            }

            if (card.formattedDescription != null) {
                Text(
                    text = FamFormattedText(
                        entityList = card.formattedDescription.entityList,
                        simpleText = card.formattedDescription.text
                    )
                )
            }
        }
    }
}
