package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.add_edit

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case.FlashcardUseCasesFactory
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.UiEvent
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.destinations.FlashcardsMainScreenDestination
import com.example.flashcardsoftwareappjetpackcomposestyle.util.Resources
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
@HiltViewModel
class AddEditViewModel @Inject constructor(
    private val useCasesFactory: FlashcardUseCasesFactory,
    stateHandle: SavedStateHandle
) : ViewModel() {

    private var flashcardId: Int? = null

    init {
        flashcardId = stateHandle.get<Int>("flashcardId")?.also {
            viewModelScope.launch {
                useCasesFactory.getFlashcardById(it)?.also {
                    title = title.copy(text = it.title , isHintVisible = false)
                    note = note.copy(text = it.notes , isHintVisible = false)
                }
            }
        }
    }

    var title by mutableStateOf(TransparentTextField(hint = "Title..."))
        private set

    var note by mutableStateOf(TransparentTextField(hint = "Enter some content..."))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: AddEditEvents) {
        when (event) {
            is AddEditEvents.NoteOnChangeText -> note = note.copy(text = event.text)
            is AddEditEvents.NoteOnFocusChange -> note = note.copy(isHintVisible = !event.focus.isFocused && note.text.isEmpty())
            is AddEditEvents.TitleOnChangeText -> title = title.copy(text = event.text)
            is AddEditEvents.TitleOnFocusChange -> title = title.copy(isHintVisible = !event.focus.isFocused && title.text.isEmpty())
            is AddEditEvents.SaveFlashcard -> viewModelScope.launch {
                val result = useCasesFactory.upsectFlashcard(
                        flashcard = Flashcard(
                                title = title.text,
                                notes = note.text,
                                id = if (flashcardId == -1) null else flashcardId
                        )
                )

                when (result) {
                    is Resources.Failure -> _uiEvent.send(UiEvent.ShowToastMessage(result.message))
                    is Resources.Success -> _uiEvent.send(UiEvent.Navigate(FlashcardsMainScreenDestination))
                    else -> Unit
                }
            }
        }
    }


    data class TransparentTextField(
            val text: String = "",
            val hint: String = "",
            val isHintVisible: Boolean = true
    )
}