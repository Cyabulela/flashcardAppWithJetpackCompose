package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.home

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.flashcardsoftwareappjetpackcomposestyle.presentation.UiEvent
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@Destination(start = true)
@Composable
fun FlashcardsMainScreen(
    navigator: DestinationsNavigator,
    viewModel : HomeViewModel = hiltViewModel()
) {
    val state = viewModel.state
    val snackbarHost = remember { SnackbarHostState()}
    val scope = rememberCoroutineScope()

    LaunchedEffect( true ) {
        viewModel.uiEvent.collectLatest { event ->
            when (event) {
                is UiEvent.Navigate -> {
                    navigator.navigate(event.route)
                }
                is UiEvent.ShowSnackbar -> {
                    scope.launch {
                        val result = snackbarHost.showSnackbar(
                            message = event.message,
                            actionLabel = event.action,
                            duration = SnackbarDuration.Short
                        )
                        if (result == SnackbarResult.ActionPerformed) {
                            viewModel.onEvent(HomeEvents.UndoDeleteFlashcard)
                        }
                    }
                }
                else -> Unit
            }
        }
    }

    Scaffold (
        floatingActionButton = {
            FloatingActionButton(onClick = { viewModel.onEvent(HomeEvents.AddFlashcard)}) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add flashcard"
                )
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHost)}
    ){

        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
        ) {
            Header(
                state = state,
                onToggleChange = { viewModel.onEvent(HomeEvents.OnToggleChange)},
                onSearch = { viewModel.onEvent(HomeEvents.OnSearchTextChange(it))},
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(5.dp))

            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {

                LazyColumn (
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    items(state.flashcards) { flashcard ->
                        FlashcardItem(
                            flashcard = flashcard,
                            onClick = { viewModel.onEvent(HomeEvents.OnItemClicked(it)) },
                            onDeleteClick = { viewModel.onEvent(HomeEvents.DeleteFlashcard(it)) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp)
                        )
                    }
                }

                if (state.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .align(Alignment.Center),
                        color = Color.Green
                    )
                }

            }
        }
    }

}