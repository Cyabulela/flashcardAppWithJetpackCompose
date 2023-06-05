package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.add_edit

import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest

@RequiresApi(Build.VERSION_CODES.O)
@Composable
@Destination
fun AddEditScreen(
        navigator: DestinationsNavigator,
        flashcardId: Int = -1,
        viewModel: AddEditViewModel = hiltViewModel()
) {

    val context = LocalContext.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collectLatest { event ->
            when(event) {
                is UiEvent.Navigate -> navigator.navigateUp()
                is UiEvent.ShowToastMessage -> Toast.makeText(context , event.message , Toast.LENGTH_LONG).show()
                else -> Unit
            }

        }
    }

    Scaffold(
            floatingActionButton = {
                FloatingActionButton(onClick = { viewModel.onEvent(AddEditEvents.SaveFlashcard)}) {
                    Icon(imageVector = Icons.Default.Save, contentDescription = "Save")
                }
            }
    ) { padding ->
        Column(
                modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp)
        ) {
            Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
            ) {
                Text(text = "FlashCard" , style = MaterialTheme.typography.headlineLarge)
            }

            Spacer(modifier = Modifier.height(10.dp))

            TransparentTextField(
                    text = viewModel.title.text,
                    hint = viewModel.title.hint,
                    isHintVisible = viewModel.title.isHintVisible,
                    onTextChange = { viewModel.onEvent(AddEditEvents.TitleOnChangeText(it))},
                    onFocusChange = { viewModel.onEvent(AddEditEvents.TitleOnFocusChange(it))},
                    style = MaterialTheme.typography.headlineSmall,
                    singleLine = true
            )

            Spacer(modifier = Modifier.height(10.dp))

            TransparentTextField(
                    text = viewModel.note.text,
                    hint = viewModel.note.hint,
                    isHintVisible = viewModel.note.isHintVisible,
                    onTextChange = { viewModel.onEvent(AddEditEvents.NoteOnChangeText(it))},
                    onFocusChange = { viewModel.onEvent(AddEditEvents.NoteOnFocusChange(it))}
            )

        }
    }

}