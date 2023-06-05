package com.example.flashcardsoftwareappjetpackcomposestyle.domain.use_case

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.repository.FlashcardRepository
import javax.inject.Inject

@RequiresApi(Build.VERSION_CODES.O)
class FlashcardUseCasesFactory @Inject constructor(
    private val repository: FlashcardRepository
) {
    val upsectFlashcard  = UpsertFlashcard(repository)
    val deleteFlashcard = DeleteFlashcard(repository)
    val getFlashcards = GetFlashcards(repository)
    val getFlashcardById = GetFlashcardById(repository)
}