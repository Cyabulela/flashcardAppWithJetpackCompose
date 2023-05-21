package com.example.flashcardsoftwareappjetpackcomposestyle.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FlashcardDao::class],
    version = 1
)
abstract class FlashcardDatabase : RoomDatabase(){
    abstract val flashcardDao : FlashcardDao
}