package com.example.flashcardsoftwareappjetpackcomposestyle.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto.FlashcardDto
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {

    @Upsert
    suspend fun upsertFlashcard(flashcardDto: FlashcardDto)

    @Delete
    suspend fun deleteFlashcard(flashcardDto: FlashcardDto)

    @Query("""
        SELECT * FROM flashcarddto 
        WHERE LOWER(title) 
        LIKE '%' || LOWER(:query) || '%'
        OR 
        LOWER(notes) LIKE '%' || LOWER(:query) || '%'
    """)
    fun getFlashcards(query : String) : List<FlashcardDto>

}