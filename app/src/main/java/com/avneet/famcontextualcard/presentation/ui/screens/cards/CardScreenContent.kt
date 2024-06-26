package com.avneet.famcontextualcard.presentation.ui.screens.cards

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.avneet.famcontextualcard.data.models.CardGroup
import com.avneet.famcontextualcard.presentation.ui.components.PullToRefreshLazyColumn
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.BigDisplayCardGroup
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.DynamicWidthCardGroup
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.ImageCardGroup
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.SmallDisplayCardGroup
import com.avneet.famcontextualcard.presentation.ui.screens.card_components.SmallDisplayCardWithArrowGroup
import com.avneet.famcontextualcard.ui.theme.FamContextualCardTheme


@Composable
fun CardScreenContent(
    modifier: Modifier = Modifier,
    cardList: List<CardGroup>,
    dismiss: (Int) -> Unit,
    remind: (Int) -> Unit,
    onRefresh: () -> Unit,
    isRefreshing: Boolean
) {
    val context = LocalContext.current
    Column(modifier = modifier) {
        PullToRefreshLazyColumn(
            isRefreshing = isRefreshing,
            onRefresh = { onRefresh() },
            items = cardList,
            content = { cardGroup ->
                Spacer(modifier = Modifier.height(20.dp))
                when (cardGroup.designType) {
                    CardGroup.DesignType.SMALL_DISPLAY_CARD -> {
                        SmallDisplayCardGroup(
                            cardGroup = cardGroup,
                            processDeepUrl = { url ->
                                processDeepLink(context = context, deepLinkUrl = url)
                            }
                        )
                    }

                    CardGroup.DesignType.BIG_DISPLAY_CARD -> {
                        BigDisplayCardGroup(
                            cardGroup = cardGroup,
                            processDeepUrl = { url ->
                                processDeepLink(context = context, deepLinkUrl = url)
                            },
                            dismiss = dismiss,
                            remind = remind
                        )
                    }

                    CardGroup.DesignType.IMAGE_CARD -> {
                        ImageCardGroup(
                            cardGroup = cardGroup,
                            processDeepLink = { url ->
                                processDeepLink(context = context, deepLinkUrl = url)
                            }
                        )
                    }

                    CardGroup.DesignType.SMALL_CARD_WITH_ARROW -> {
                        SmallDisplayCardWithArrowGroup(
                            cardGroup = cardGroup,
                            processDeepLink = { url ->
                                processDeepLink(context = context, deepLinkUrl = url)
                            }
                        )
                    }

                    CardGroup.DesignType.DYNAMIC_WIDTH_CARD -> {
                        DynamicWidthCardGroup(
                            cardGroup = cardGroup,
                            processDeepUrl = { url ->
                                processDeepLink(context = context, deepLinkUrl = url)
                            }
                        )
                    }
                }
            }
        )
    }
}

fun processDeepLink(context: Context, deepLinkUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(deepLinkUrl)
        flags = Intent.FLAG_ACTIVITY_NEW_TASK
    }
    context.startActivity(intent)
}

