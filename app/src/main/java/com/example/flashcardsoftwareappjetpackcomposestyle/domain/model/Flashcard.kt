package com.example.flashcardsoftwareappjetpackcomposestyle.domain.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
data class Flashcard (
    val title : String,
    val notes : String,
    val lastUpdateDateTime : LocalDateTime = LocalDateTime.now(),
    val id : Int? = null
)
