package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetFlashcards (
    private val flashcardRepository: FlashcardRepository
) {
    operator fun invoke(query : String) : Flow<List<Flashcard>> {
        return flashcardRepository.getFlashcards()
            .map { list ->
                if (query.isNotBlank()) {
                    list.filter {
                        it.title.trim().contains(query.trim() , ignoreCase = true) or it.notes.trim().contains(query.trim() , ignoreCase = true)
                    }.sortedBy {
                        it.lastUpdateDateTime
                    }
                }
                else {
                    list.sortedBy {
                        it.lastUpdateDateTime
                    }
                }
            }
    }
}