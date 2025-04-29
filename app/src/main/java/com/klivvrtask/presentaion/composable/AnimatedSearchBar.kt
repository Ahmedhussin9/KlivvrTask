package com.klivvrtask.presentaion.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay

@Composable
fun AnimatedSearchBar(
    query: String,
    onQueryChanged: (String) -> Unit
) {
    var showInitialPlaceholder by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        delay(1000)
        showInitialPlaceholder = false
    }

    TextField(
        value = query,
        onValueChange = onQueryChanged,
        placeholder = {
            AnimatedContent(targetState = showInitialPlaceholder) { isInitial ->
                Text(if (isInitial) "Search for cities..." else "Type to search")
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        singleLine = true
    )
}