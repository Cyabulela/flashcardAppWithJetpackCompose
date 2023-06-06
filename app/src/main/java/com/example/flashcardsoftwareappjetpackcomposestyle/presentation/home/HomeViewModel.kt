package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case.FlashcardUseCasesFactory
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.UiEvent
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.destinations.AddEditScreenDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
@RequiresApi(Build.VERSION_CODES.O)
class HomeViewModel @Inject constructor(
    private val useCasesFactory: FlashcardUseCasesFactory
) : ViewModel(){


    var state by mutableStateOf(HomeFields())
        private set

    private var recentDeletedFlashcard : Flashcard? = null
    private val _uiEvents = Channel<UiEvent>()
    val uiEvent = _uiEvents.receiveAsFlow()

    private var job : Job? = null

    init {
        getNotes()
    }
    fun onEvent(event: HomeEvents) {
        when(event){
            HomeEvents.AddFlashcard -> {
                viewModelScope.launch {
                    _uiEvents.send(UiEvent.Navigate(AddEditScreenDestination()))
                }
            }
            is HomeEvents.DeleteFlashcard -> {
                viewModelScope.launch {
                    useCasesFactory.deleteFlashcard(event.flashcard)
                    recentDeletedFlashcard = event.flashcard
                    _uiEvents.send(UiEvent.ShowSnackbar(message = "Deleted" , action = "Undo"))
                }
            }
            is HomeEvents.OnItemClicked -> {
                viewModelScope.launch {
                    _uiEvents.send(UiEvent.Navigate(AddEditScreenDestination(event.flashcard.id ?: -1)))
                }
            }
            is HomeEvents.OnSearchTextChange -> {
                state = state.copy(
                    searchText = event.text
                )
                job?.cancel()
                job = viewModelScope.launch {
                    delay(500L)
                    getNotes(state.searchText)
                }
            }
            HomeEvents.UndoDeleteFlashcard -> {
                viewModelScope.launch {
                    recentDeletedFlashcard?.run {
                        useCasesFactory.upsectFlashcard(this)
                        recentDeletedFlashcard = null
                    }
                }
            }

            HomeEvents.OnToggleChange -> {
                state = state.copy(isSearchActive = !state.isSearchActive , searchText = "")
                job?.cancel()
                getNotes()
            }
        }
    }

    private fun getNotes(query : String = "") {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            useCasesFactory.getFlashcards(query)
                .collect {
                    state = state.copy(flashcards = it , isLoading = false)
                }
        }
    }

    data class HomeFields(
        val flashcards : List<Flashcard> = emptyList(),
        val searchText : String = "",
        val isSearchActive : Boolean = false,
        val isLoading : Boolean = false
    )

}