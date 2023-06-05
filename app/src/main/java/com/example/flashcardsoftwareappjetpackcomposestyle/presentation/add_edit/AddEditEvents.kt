package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.add_edit

import androidx.compose.ui.focus.FocusState

sealed interface AddEditEvents {
    data class TitleOnChangeText(val text : String) : AddEditEvents
    data class TitleOnFocusChange (val focus : FocusState) : AddEditEvents
    data class NoteOnChangeText(val text : String) : AddEditEvents
    data class NoteOnFocusChange (val focus : FocusState) : AddEditEvents
    object SaveFlashcard : AddEditEvents
}