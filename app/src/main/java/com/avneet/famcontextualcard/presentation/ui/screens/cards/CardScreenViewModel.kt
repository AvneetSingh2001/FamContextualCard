package com.avneet.famcontextualcard.presentation.ui.screens.cards

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.domain.repository.CardsRepository
import com.avneet.famcontextualcard.data.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : ViewModel() {


    private var _cardUiState: MutableStateFlow<CardScreenUiState> = MutableStateFlow(CardScreenUiState.Loading)
    val cardScreenUiState: StateFlow<CardScreenUiState> get() = _cardUiState

    private var _isRefreshing: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isRefreshing: StateFlow<Boolean> get() = _isRefreshing

    private val temporarilyHiddenCardIds = mutableSetOf<Int>()

    init {
        fetchCards()
    }

    fun refresh() {
        _isRefreshing.value = true
        fetchCards()
    }

    private fun fetchCards() {
        viewModelScope.launch {
            cardsRepository.fetchCards().collectLatest { result ->
                _cardUiState.value = when (result) {
                    is NetworkResult.Loading -> CardScreenUiState.Loading
                    is NetworkResult.Success -> {
                        val cardGroupResponse = result.data
                        val filteredCardGroups = cardGroupResponse?.cardGroup?.map { cardGroup ->
                            cardGroup.copy(cardList = cardGroup.cardList.filterNot { card ->
                                temporarilyHiddenCardIds.contains(card.id)
                            })
                        }
                        _isRefreshing.value = false
                        CardScreenUiState.Success(CardGroupResponse(filteredCardGroups))
                    }
                    is NetworkResult.Error -> CardScreenUiState.Error(result.error).also { _isRefreshing.value = false }
                }
            }
        }
    }

    fun hideCardPermanently(cardId: Int) {
        viewModelScope.launch {
            cardsRepository.hideCard(cardId)
        }
        fetchCards()
    }

    fun hideCardTemporarily(cardId: Int) {
        temporarilyHiddenCardIds.add(cardId)
        fetchCards()
    }
}

sealed class CardScreenUiState() {
    data class Success(val cardList: CardGroupResponse?) : CardScreenUiState()
    data object Loading : CardScreenUiState()
    data class Error(val message: String?) : CardScreenUiState()
}