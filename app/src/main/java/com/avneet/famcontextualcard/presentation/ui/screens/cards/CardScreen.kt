package com.avneet.famcontextualcard.presentation.ui.screens.cards

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.avneet.famcontextualcard.R
import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.presentation.ui.components.EmptyContentScreen
import com.avneet.famcontextualcard.presentation.ui.components.FamLoadingWheel
import com.avneet.famcontextualcard.ui.theme.FamContextualCardTheme


@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    viewModel: CardScreenViewModel = hiltViewModel()
) {
    val uiState = viewModel.cardScreenUiState.collectAsStateWithLifecycle()

    CardScreen(
        modifier = modifier,
        uiState = uiState.value
    )
}

@Composable
fun CardScreen(
    modifier: Modifier = Modifier,
    uiState: CardScreenUiState
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        when (uiState) {
            is CardScreenUiState.Success -> {
                if(uiState.cardList != null && uiState.cardList.cardGroup.isEmpty()) {
                    EmptyContentScreen(
                        title = stringResource(id = R.string.empty_list),
                        imageContent = { Icon(imageVector = Icons.Default.Info, contentDescription = null) }
                    )
                } else {
                    CardScreenContent(
                        modifier = Modifier.fillMaxSize(),
                        cardList = uiState.cardList!!.cardGroup
                    )
                }
            }

            is CardScreenUiState.Error -> {
                EmptyContentScreen(
                    title = stringResource(id = R.string.Error),
                    subTitle = uiState.message ?: "",
                    imageContent = { Icon(imageVector = Icons.Default.Warning, contentDescription = null) }
                )
            }

            is CardScreenUiState.Loading -> {
                FamLoadingWheel(
                    contentDesc = stringResource(id = R.string.loading)
                )
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun CardScreenPreview() {
    FamContextualCardTheme {
        CardScreen(
            uiState = CardScreenUiState.Success(CardGroupResponse(cardGroup = listOf()))
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun CardScreenLoadingPreview() {
    FamContextualCardTheme {
        CardScreen(
            uiState = CardScreenUiState.Loading
        )
    }
}