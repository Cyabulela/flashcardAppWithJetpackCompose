package com.example.flashcardsoftwareappjetpackcomposestyle.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto.Flashcards

@Database(
    entities = [Flashcards::class],
    version = 1
)
abstract class FlashcardDatabase : RoomDatabase(){
    abstract val flashcardDao : FlashcardDao
}