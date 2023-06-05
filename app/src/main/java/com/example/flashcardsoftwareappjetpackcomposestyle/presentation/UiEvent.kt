package com.example.flashcardsoftwareappjetpackcomposestyle.presentation

import com.ramcosta.composedestinations.spec.Direction

sealed interface UiEvent {
    data class ShowSnackbar(val message: String, val action : String? = null) : UiEvent
    data class Navigate(val route : Direction) : UiEvent
    data class ShowToastMessage(val message : String) : UiEvent
}