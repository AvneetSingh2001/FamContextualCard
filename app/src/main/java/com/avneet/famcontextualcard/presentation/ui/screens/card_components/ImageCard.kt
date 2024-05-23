package com.avneet.famcontextualcard.presentation.ui.screens.card_components

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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.avneet.famcontextualcard.data.models.Card
import com.avneet.famcontextualcard.data.models.CardGroup


@Composable
fun ImageCardGroup(
    cardGroup: CardGroup,
    processDeepLink: (String) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier.fillMaxWidth()) {
        if (cardGroup.cardList.size > 1 && cardGroup.isScrollable) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(paddingValues = PaddingValues(horizontal = 16.dp))
            ) {
                cardGroup.cardList.forEach { card ->
                    ImageCard(
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
                    ImageCard(
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
fun ImageCard(
    modifier: Modifier = Modifier,
    card: Card,
    processDeepLink: (String) -> Unit
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable {
                card.url?.let { processDeepLink(it) }
            },
        shape = RoundedCornerShape(10.dp),
    ) {
        AsyncImage(
            model = card.bgImage?.imageUrl,
            contentDescription = null,
            modifier = Modifier.aspectRatio(ratio = card.bgImage?.aspectRatio ?: 2f)
        )
    }
}

