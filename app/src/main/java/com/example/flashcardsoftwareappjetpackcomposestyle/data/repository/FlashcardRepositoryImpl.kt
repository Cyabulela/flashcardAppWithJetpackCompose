package com.example.flashcardsoftwareappjetpackcomposestyle.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.FlashcardDatabase
import com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper.toFlashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper.toFlashcards
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class FlashcardRepositoryImpl @Inject constructor(
    flashcardDatabase: FlashcardDatabase
) : FlashcardRepository {

    private val dao = flashcardDatabase.flashcardDao

    override suspend fun upsertFlashcard(flashcard: Flashcard) {
        dao.upsertFlashcard(flashcard.toFlashcards())
    }

    override suspend fun deleteFlashCard(flashcard: Flashcard) {
        dao.deleteFlashcard(flashcard.toFlashcards())
    }

    override suspend fun getFlashcardById(id: Int): Flashcard? {
        return dao.getFlashcardsById(id)?.toFlashcard()
    }

    override fun getFlashcards(): Flow<List<Flashcard>> {
        return dao.getFlashcards().map { list ->
            list.map {
                it.toFlashcard()
            }
        }
    }
}