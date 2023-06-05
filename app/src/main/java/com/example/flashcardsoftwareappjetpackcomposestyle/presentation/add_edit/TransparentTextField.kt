package com.example.flashcardsoftwareappjetpackcomposestyle.presentation.add_edit

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp

@Composable
fun TransparentTextField(
    text : String,
    hint : String,
    isHintVisible : Boolean,
    onTextChange : (String) -> Unit,
    onFocusChange : (FocusState) -> Unit,
    modifier : Modifier = Modifier,
    singleLine : Boolean = false,
    style : TextStyle = MaterialTheme.typography.bodyMedium
) {

    Box(
        modifier = modifier,
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = text,
            onValueChange = onTextChange,
            textStyle = style.copy(color = if (isSystemInDarkTheme()) Color.White else Color.Black),
            singleLine = singleLine,
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .onFocusChanged {
                    onFocusChange(it)
                },
            cursorBrush = SolidColor(MaterialTheme.colorScheme.onPrimary),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
        )

        if (isHintVisible) {
            Text(text = hint , color = Color.Gray , style = style , modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp))
        }
    }

}