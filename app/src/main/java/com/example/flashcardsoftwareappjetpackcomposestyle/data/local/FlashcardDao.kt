package com.example.flashcardsoftwareappjetpackcomposestyle.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto.Flashcards
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {

    @Upsert
    suspend fun upsertFlashcard(flashcards: Flashcards)

    @Delete
    suspend fun deleteFlashcard(flashcards: Flashcards)

    @Query("SELECT * FROM flashcards WHERE id = :id")
    suspend fun getFlashcardsById(id : Int) : Flashcards?

    @Query("SELECT * FROM flashcards")
    fun getFlashcards() : Flow<List<Flashcards>>

}