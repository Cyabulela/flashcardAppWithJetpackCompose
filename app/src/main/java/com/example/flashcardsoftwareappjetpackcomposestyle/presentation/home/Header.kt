package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun Header(
        state: HomeViewModel.HomeFields,
        onToggleChange: () -> Unit,
        onSearch: (String) -> Unit,
        modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(5.dp)
    ) {

        AnimatedVisibility(
            visible = !state.isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                            .weight(1f),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Flashcards",
                        style = MaterialTheme.typography.headlineLarge,
                        color = Color.Green
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                IconButton(onClick = onToggleChange) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "Search")
                }
            }
        }

        AnimatedVisibility(
            visible = state.isSearchActive,
            enter = fadeIn(),
            exit = fadeOut()
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    value = state.searchText,
                    onValueChange = onSearch,
                    placeholder = {
                        Text(
                            text = "Search",
                            style = MaterialTheme.typography.bodyMedium,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    },
                    modifier = Modifier
                        .padding(5.dp)
                        .weight(1f),
                    shape = RoundedCornerShape(10.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                IconButton( onClick = onToggleChange) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close search",
                        modifier = Modifier.size(25.dp)
                    )
                }
            }
        }
    }
}