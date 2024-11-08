package com.example.permitionrequest

import android.os.Bundle
import android.Manifest
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.permitionrequest.ui.theme.SingleMultiplePermitionRequestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SingleMultiplePermitionRequestTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)  {
                  //  RequestPermission(permission = Manifest.permission.READ_CONTACTS)
                    RequestMultiplePermissions(
                        permissions = listOf(
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.CAMERA
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SingleMultiplePermitionRequestTheme {
        Greeting("Android")
    }
}