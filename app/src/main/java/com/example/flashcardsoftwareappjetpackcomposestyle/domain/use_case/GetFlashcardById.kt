package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository

class GetFlashcardById(
    private val repository : FlashcardRepository
) {

    suspend operator fun invoke(id : Int) : Flashcard? {
        return repository.getFlashcardById(id)
    }
}