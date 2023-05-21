package com.example.flashcardsoftwareappjetpackcomposestyle.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.FlashcardDatabase
import com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper.toFlashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper.toFlashcardDto
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import com.example.flashcardsoftwareappjetpackcomposestyle.util.Resources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class FlashcardRepositoryImpl @Inject constructor(
    flashcardDatabase: FlashcardDatabase
) : FlashcardRepository {

    private val dao = flashcardDatabase.flashcardDao


    override suspend fun upsertFlashcard(flashcard: Flashcard) {
        dao.upsertFlashcard(flashcard.toFlashcardDto())
    }

    override suspend fun deleteFlashCard(flashcard: Flashcard) {
        dao.deleteFlashcard(flashcard.toFlashcardDto())
    }

    override fun getFlashcards(query: String): Flow<Resources<List<Flashcard>>> {
        return flow {
            emit(Resources.Loading())
            val result = try {
                val result = dao.getFlashcards(query)
                Resources.Success(
                    data = result.map {
                        it.toFlashcard()
                    }
                )
            } catch (e : Exception ) {
                e.printStackTrace()
                Resources.Failure(message = e.localizedMessage ?: "Unknown error occurred")
            }

            emit(result)
            emit(Resources.Loading(isLoading = false))
        }
    }
}