package com.example.permitionrequest

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.google.accompanist.permissions.*
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.PermissionStatus
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun RequestPermission(
    permission: String,
    deniedMessage: String = "Give this app a permission to proceed. If it doesn't work, then you'll have to do it manually from the settings.",
    rationaleMessage: String = "To use this app's functionalities, you need to give us the permission.",
) {
    val permission = rememberPermissionState(permission)

    HandleRequest(
        permissionState =permission,
        deniedContent = {
            shouldShowRationale ->
            PermissionDeniedContent(
                deniedMessage = deniedMessage,
                shouldShowRationale = shouldShowRationale,
                rationaleMessage = rationaleMessage,
                onRequestPermission = { permission.launchPermissionRequest() }
            )
        },
        content = {
            Content(
                text = "PERMISSION GRANTED!",
                showButton = false
            ) {}
        }
    )
}
@OptIn(ExperimentalPermissionsApi::class)
@Composable
private fun HandleRequest(
    permissionState: PermissionState,
    deniedContent: @Composable (Boolean) -> Unit,
    content: @Composable () -> Unit
) {
    when (permissionState.status) {
        is PermissionStatus.Granted -> {
            content()
        }
        is PermissionStatus.Denied -> {
            deniedContent(permissionState.status.shouldShowRationale)
        }
    }
}

@Composable
fun Content(
    text : String,
    showButton : Boolean=true,
    onClick : () -> Unit
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(16.dp))
        if(showButton){
            Button(onClick = onClick) {
                Text(text = "Request")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PermissionDeniedContent(
    deniedMessage : String,
    rationaleMessage : String,
    shouldShowRationale : Boolean,
    onRequestPermission : () -> Unit
){
    if(shouldShowRationale){
        AlertDialog(
        onDismissRequest = {  },
            title = {
                Text(
                    text = "Permission Request",
                    style = TextStyle(
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold
                )
                )
            },
            text = {
                Text(text = rationaleMessage)
            },
                confirmButton = {
                Button(onClick = onRequestPermission) {
                    Text(text = "Give Permission")
                }
            }
        )
    }
    else{
        Content(
            text = deniedMessage,
            onClick = onRequestPermission
        )
    }
}
