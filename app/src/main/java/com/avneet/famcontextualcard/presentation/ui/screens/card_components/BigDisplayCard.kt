package com.avneet.famcontextualcard.presentation.ui.screens.card_components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import coil.compose.AsyncImage
import com.avneet.famcontextualcard.R
import com.avneet.famcontextualcard.data.models.Card
import com.avneet.famcontextualcard.data.models.CardGroup
import com.avneet.famcontextualcard.data.models.CardImage
import com.avneet.famcontextualcard.presentation.ui.components.FamActionButton
import com.avneet.famcontextualcard.presentation.ui.components.FamFormattedText
import kotlin.math.roundToInt


@Composable
fun BigDisplayCardGroup(
    cardGroup: CardGroup,
    processDeepUrl: (String) -> Unit,
    dismiss: (Int) -> Unit,
    remind: (Int) -> Unit
) {
    val scrollState = rememberScrollState()
    Row(modifier = Modifier) {
        if (cardGroup.cardList.size > 1 && cardGroup.isScrollable) {
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(paddingValues = PaddingValues(horizontal = 16.dp))
            ) {
                cardGroup.cardList.forEach { card ->
                    BigDisplayCard(
                        modifier = Modifier,
                        card = card,
                        processDeepUrl = processDeepUrl,
                        dismiss = { dismiss(card.id) },
                        remind = { remind(card.id) }
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
                    BigDisplayCard(
                        modifier = Modifier.weight(1f),
                        card = card,
                        processDeepUrl = processDeepUrl,
                        dismiss = { dismiss(card.id) },
                        remind = { remind(card.id) }
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
fun BigDisplayCard(
    modifier: Modifier = Modifier,
    card: Card,
    processDeepUrl: (String) -> Unit,
    dismiss: () -> Unit,
    remind: () -> Unit
) {
    var isSwiped by remember { mutableStateOf(false) }
    val offsetX = remember { Animatable(0f) }
    val density = LocalDensity.current

    LaunchedEffect(isSwiped) {
        val targetOffset = with(density) { 120.dp.toPx() }
        offsetX.animateTo(
            targetValue = if (isSwiped) targetOffset else 0f,
            animationSpec = tween(durationMillis = 300)
        )
    }

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box {

            BigDisplayCardActionButton(
                dismiss = dismiss,
                remind = remind
            )

            BigDisplayCardFront(
                modifier = Modifier
                    .pointerInput(Unit) {
                        detectTapGestures(
                            onLongPress = {
                                isSwiped = !isSwiped
                            }
                        )
                    }
                    .offset { IntOffset(offsetX.value.roundToInt(), 0) },
                card = card,
                processDeepUrl = processDeepUrl
            )
        }
    }
}

@Composable
fun BigDisplayCardFront(
    modifier: Modifier = Modifier,
    card: Card,
    processDeepUrl: (String) -> Unit
) {
    Card(
        modifier = modifier.fillMaxSize(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (card.bgColor != null) Color(card.bgColor.toColorInt())
            else Color.LightGray
        ),
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            if (card.bgImage != null) {
                when (card.bgImage.imageType) {
                    CardImage.ImageType.EXTERNAL -> {
                        AsyncImage(
                            model = card.bgImage.imageUrl,
                            contentDescription = null,
                        )
                    }

                    CardImage.ImageType.ASSET -> {
                        AsyncImage(
                            model = card.bgImage.assetType,
                            contentDescription = null,
                        )
                    }
                }
                Spacer(modifier = Modifier.height(30.dp))
            }

            if (card.formattedTitle != null) {
                Text(
                    text = FamFormattedText(
                        entityList = card.formattedTitle.entityList,
                        simpleText = card.formattedTitle.text
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (card.formattedDescription != null) {
                Text(
                    text = FamFormattedText(
                        entityList = card.formattedDescription.entityList,
                        simpleText = card.formattedDescription.text
                    ),
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            if (!card.ctaList.isNullOrEmpty()) {
                Row {
                    card.ctaList.forEach { cta ->
                        TextButton(
                            onClick = {
                                card.url?.let { processDeepUrl(it) }
                            },
                            modifier = Modifier,
                            shape = RoundedCornerShape(10.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = cta.bgColor?.toColorInt()?.let { Color(it) }
                                    ?: Color.Black
                            )
                        ) {
                            Text(
                                text = cta.text ?: "",
                                color = Color.White,
                                modifier = Modifier.padding(horizontal = 24.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BigDisplayCardActionButton(
    modifier: Modifier = Modifier,
    dismiss: () -> Unit,
    remind: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        FamActionButton(
            modifier = Modifier.size(80.dp),
            onClick = { remind() },
            iconId = R.drawable.ic_bell,
            text = stringResource(R.string.remind_later)
        )

        Spacer(modifier = Modifier.height(20.dp))

        FamActionButton(
            modifier = Modifier.size(80.dp),
            onClick = { dismiss() },
            iconId = R.drawable.ic_dismiss,
            text = stringResource(R.string.dismiss_now)
        )
    }
}
