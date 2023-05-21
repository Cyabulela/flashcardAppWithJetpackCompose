package com.example.flashcardsoftwareappjetpackcomposestyle.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.data.local.dto.FlashcardDto
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import java.time.Instant
import java.time.ZoneId
import java.time.ZoneOffset

@RequiresApi(Build.VERSION_CODES.O)
fun FlashcardDto.toFlashcard() : Flashcard {

    return Flashcard(
        title = title,
        notes = notes,
        lastUpdateDateTime = Instant.ofEpochMilli(timeStamp).atZone(ZoneId.systemDefault()).toLocalDateTime(),
        id = id
    )
}

@RequiresApi(Build.VERSION_CODES.O)
fun Flashcard.toFlashcardDto() : FlashcardDto {
    return FlashcardDto(
        title = title,
        notes = notes,
        timeStamp = lastUpdateDateTime.toInstant(ZoneOffset.MIN).toEpochMilli(),
        id = id
    )
}