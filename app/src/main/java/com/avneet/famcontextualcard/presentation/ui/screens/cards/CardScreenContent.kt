package com.avneet.famcontextualcard.presentation.ui.screens.cards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.avneet.famcontextualcard.data.models.Card
import com.avneet.famcontextualcard.data.models.CardGroup
import com.avneet.famcontextualcard.data.models.CardImage
import com.avneet.famcontextualcard.data.models.Entity
import com.avneet.famcontextualcard.data.models.FormattedText
import com.avneet.famcontextualcard.presentation.ui.components.FamFormattedText
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.SmallDisplayCardGroup
import com.avneet.famcontextualcard.ui.theme.FamContextualCardTheme


@Composable
fun CardScreenContent(
    modifier: Modifier = Modifier,
    cardList: List<CardGroup>
) {
    Column(modifier = modifier) {
        LazyColumn {
            items(items = cardList) { cardGroup ->
                when (cardGroup.designType) {
                    CardGroup.DesignType.SMALL_DISPLAY_CARD -> {
                        SmallDisplayCardGroup(cardGroup = cardGroup)
                    }

                    CardGroup.DesignType.BIG_DISPLAY_CARD -> {

                    }

                    CardGroup.DesignType.IMAGE_CARD -> {

                    }

                    CardGroup.DesignType.SMALL_CARD_WITH_ARROW -> {

                    }

                    CardGroup.DesignType.DYNAMIC_WIDTH_CARD -> {

                    }
                }
            }
        }
    }
}

