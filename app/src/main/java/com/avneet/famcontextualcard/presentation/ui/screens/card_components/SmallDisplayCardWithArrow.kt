package com.avneet.famcontextualcard.presentation.ui.screens.card_components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
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
fun SmallDisplayCardWithArrowGroup(
    cardGroup: CardGroup,
    processDeepLink: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier.height(intrinsicSize = IntrinsicSize.Max)) {
        if (cardGroup.cardList.size > 1 && cardGroup.isScrollable) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(paddingValues = PaddingValues(horizontal = 16.dp))
            ) {
                cardGroup.cardList.forEach { card ->
                    SmallDisplayCardWithArrow(
                        modifier = Modifier,
                        card = card,
                        processDeepLink = processDeepLink
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
            ) {
                cardGroup.cardList.forEach { card ->
                    SmallDisplayCardWithArrow(
                        modifier = Modifier.weight(1f),
                        card = card,
                        processDeepLink = processDeepLink
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
fun SmallDisplayCardWithArrow(
    modifier: Modifier = Modifier,
    card: Card,
    processDeepLink: (String) -> Unit
) {
    Row(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .background(
                color = if (card.bgColor != null) Color(card.bgColor.toColorInt())
                else Color.Transparent
            )
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(modifier = Modifier.weight(1f)) {
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


        Icon(
            imageVector = Icons.Filled.KeyboardArrowRight,
            contentDescription = null,
            modifier = Modifier.clickable {
                card.url?.let { processDeepLink(it) }
            }
        )
    }
}
