package com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto.Flashcards
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
fun Flashcards.toFlashcard() : Flashcard {

    return Flashcard(
        title = title,
        notes = notes,
        lastUpdateDateTime = LocalDateTime.ofEpochSecond(timeStamp , 0 , ZoneOffset.UTC),
        id = id
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Flashcard.toFlashcards() : Flashcards {
    return Flashcards(
        title = title,
        notes = notes,
        timeStamp = lastUpdateDateTime.toEpochSecond(ZoneOffset.UTC),
        id = id
    )
}