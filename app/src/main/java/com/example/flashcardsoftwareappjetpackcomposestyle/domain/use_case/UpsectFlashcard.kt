package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import com.example.flashcardsoftwareappjetpackcomposestyle.util.Resources

@RequiresApi(Build.VERSION_CODES.O)
class UpsertFlashcard(
    private val flashcardRepository: FlashcardRepository
) {

    suspend operator fun invoke(flashcard: Flashcard) : Resources<Unit> {
        if (flashcard.title.isBlank()) {
            return Resources.Failure(message = "Title can not be empty")
        } else if (flashcard.notes.isBlank()) {
            return Resources.Failure(message = "Notes can not be empty")
        }
        flashcardRepository.upsertFlashcard(flashcard)
        return Resources.Success(Unit)
    }
}