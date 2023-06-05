package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.home

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard

sealed interface HomeEvents {
    data class DeleteFlashcard(val flashcard : Flashcard) : HomeEvents
    data class OnItemClicked(val flashcard: Flashcard) : HomeEvents
    data class OnSearchTextChange(val text : String) : HomeEvents
    object OnToggleChange : HomeEvents
    object AddFlashcard : HomeEvents
    object UndoDeleteFlashcard : HomeEvents
}