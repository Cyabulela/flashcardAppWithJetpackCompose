package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository

class DeleteFlashcard (
    private val flashcardRepository: FlashcardRepository
) {
    suspend operator fun invoke(flashcard: Flashcard) {
        flashcardRepository.deleteFlashCard(flashcard)
    }
}