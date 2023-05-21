package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import com.example.flashcardsoftwareappjetpackcomposestyle.util.Resources
import kotlinx.coroutines.flow.Flow

class GetFlashcards (
    private val flashcardRepository: FlashcardRepository
) {

    operator fun invoke(query : String = "") : Flow<Resources<List<Flashcard>>> {
        return flashcardRepository.getFlashcards(query)
    }
}