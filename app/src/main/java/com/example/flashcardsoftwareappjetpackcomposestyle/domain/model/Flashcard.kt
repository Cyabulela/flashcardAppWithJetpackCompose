package com.example.flashcardsoftwareappjetpackcomposestyle.domain.model

import android.os.Build
import android.os.Parcelable
import androidx.annotation.RequiresApi
import kotlinx.parcelize.Parcelize
import java.time.LocalDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Parcelize
data class Flashcard (
    val title : String,
    val notes : String,
    val lastUpdateDateTime : LocalDateTime = LocalDateTime.now(),
    val id : Int? = null
) : Parcelable
