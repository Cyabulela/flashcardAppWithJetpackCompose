package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFlashcards (
    private val flashcardRepository: FlashcardRepository
) {
    operator fun invoke(query : String) : Flow<List<Flashcard>> {
        return flashcardRepository.getFlashcards(query)
            .map { list ->
                list.sortedBy { it.lastUpdateDateTime }
            }
    }
}