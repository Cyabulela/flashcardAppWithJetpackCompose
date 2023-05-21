package com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository

import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.util.Resources
import kotlinx.coroutines.flow.Flow

interface FlashcardRepository {

    suspend fun upsertFlashcard(flashcard: Flashcard)

    suspend fun deleteFlashCard(flashcard: Flashcard)

    fun getFlashcards(query : String) : Flow<Resources<List<Flashcard>>>
}