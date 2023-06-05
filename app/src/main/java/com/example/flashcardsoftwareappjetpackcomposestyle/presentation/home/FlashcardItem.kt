package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.home

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.flashcardsoftwareappjetpackcomposestyle.domain.model.Flashcard

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun FlashcardItem(
    flashcard: Flashcard,
    onClick: (Flashcard) -> Unit,
    onDeleteClick : (Flashcard) -> Unit,
    modifier: Modifier = Modifier
) {

    Column (
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.tertiaryContainer , shape = RoundedCornerShape(5.dp))
            .padding(10.dp)
            .clickable { onClick(flashcard) }
    ) {

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = flashcard.title,
                style = MaterialTheme.typography.headlineSmall,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                modifier = Modifier
                    .weight(1f)
            )

            Spacer(modifier = Modifier.width(4.dp))

            IconButton(onClick = { onDeleteClick(flashcard) }) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    contentDescription = "Delete flashcard"
                )
            }
        }

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = buildString {
                val day = flashcard.lastUpdateDateTime.dayOfMonth
                val month = flashcard.lastUpdateDateTime.month.name.take(3)
                val year = flashcard.lastUpdateDateTime.year
                val hour = flashcard.lastUpdateDateTime.hour
                val minute = flashcard.lastUpdateDateTime.minute
                val second = flashcard.lastUpdateDateTime.second

                append((if (day < 10) "0$day" else day))
                append(" $month ")
                append(year)
                append(" ")
                append((if (hour < 10) "0$day" else hour))
                append(":")
                append((if (minute < 10) "0$day" else minute))
                append(":")
                append((if (second < 10) "0$day" else second))
            },
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = flashcard.notes,
            style = MaterialTheme.typography.bodySmall,
            maxLines = 5,
            overflow = TextOverflow.Ellipsis
        )
    }
}