package com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Flashcards(
    val title : String,
    val timeStamp : Long,
    val notes : String = "",
    @PrimaryKey(autoGenerate = true) val id : Int? = null
)
