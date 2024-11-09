package com.example.permitionrequest

import android.annotation.SuppressLint
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.google.accompanist.web.*
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun WebBrowser(modifier: Modifier= Modifier) {
    var url by rememberSaveable { mutableStateOf("https://www.google.com") }
    val state = rememberWebViewState(url = url)
    val navigator = rememberWebViewNavigator()
    var textFieldValue by remember(state.content.getCurrentUrl()) {
        mutableStateOf(state.content.getCurrentUrl() ?: "")
    }

    Column {
        TopAppBar {
            IconButton(onClick = { navigator.navigateBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            IconButton(onClick = { navigator.navigateForward() }) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Forward"
                )
            }
            Text(
                text = "Web Browser", style = TextStyle(
                    color = Color.White,
                    fontSize = MaterialTheme.typography.h6.fontSize,
                    fontWeight = MaterialTheme.typography.h6.fontWeight
                )
            )
            Row(
                modifier = Modifier.weight(1f),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = { navigator.reload() }) {
                    Icon(
                        imageVector = Icons.Default.Refresh,
                        contentDescription = "Refresh"
                    )
                }
                IconButton(onClick = { url = textFieldValue }) {
                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = "Go"
                    )
                }
            }
        }

        Row(modifier = Modifier.padding(all = 12.dp)
            .height(30.dp)
            .border(
                width = 1.dp,
                color = Color.Black,
                shape = MaterialTheme.shapes.medium,
            ),
            verticalAlignment = CenterVertically
        ) {
            BasicTextField(
                modifier = Modifier.weight(9f)
                    .padding(start = 10.dp),
                value = textFieldValue,
                onValueChange = { textFieldValue = it },
                maxLines = 1
            )
            if (state.errorsForCurrentRequest.isNotEmpty()) {
                Icon(
                    modifier = Modifier.weight(1f),
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Error",
                    tint = Color.Red
                )
            }
        }

        val loadingState = state.loadingState
        if (loadingState is LoadingState.Loading) {
            LinearProgressIndicator(
                progress = loadingState.progress,
                modifier = Modifier.fillMaxWidth()
            )
        }

        WebView(
            state = state,
            modifier = Modifier.weight(1f),
            navigator = navigator,
            onCreated = { webView ->
                webView.settings.javaScriptEnabled = true
            },
            client = CustomWebViewClient()
        )
    }
}