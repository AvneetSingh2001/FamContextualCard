package com.avneet.famcontextualcard.presentation.ui.screens.cards

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.avneet.famcontextualcard.data.models.CardGroupResponse
import com.avneet.famcontextualcard.domain.repository.CardsRepository
import com.avneet.famcontextualcard.data.utils.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardScreenViewModel @Inject constructor(
    private val cardsRepository: CardsRepository
) : ViewModel() {


    private var _cardUiState: MutableStateFlow<CardScreenUiState> = MutableStateFlow(CardScreenUiState.Loading)
    val cardScreenUiState: StateFlow<CardScreenUiState> get() = _cardUiState

    init {
        fetchCards()
    }

    private fun fetchCards() {
        viewModelScope.launch {
            cardsRepository.fetchCards().collectLatest { result ->
                _cardUiState.value = when (result) {
                    is NetworkResult.Loading -> CardScreenUiState.Loading
                    is NetworkResult.Success -> CardScreenUiState.Success(result.data)
                    is NetworkResult.Error -> CardScreenUiState.Error(result.error)
                }
            }
        }
    }

}

sealed class CardScreenUiState() {
    data class Success(val cardList: CardGroupResponse?) : CardScreenUiState()
    data object Loading : CardScreenUiState()
    data class Error(val message: String?) : CardScreenUiState()
}