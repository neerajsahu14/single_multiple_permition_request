package com.example.permitionrequest

import android.os.Bundle
import android.Manifest
import android.R
import androidx.activity.compose.setContent
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.permitionrequest.ui.theme.SingleMultiplePermitionRequestTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            SingleMultiplePermitionRequestTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)
                        .background(color = Color.White)) {
                        WebBrowser()
                    }
                }

                Column(modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally)  {
                  //  RequestPermission(permission = Manifest.permission.READ_CONTACTS)
//                    RequestMultiplePermissions(
//                        permissions = listOf(
//                            Manifest.permission.READ_CONTACTS,
//                            Manifest.permission.CAMERA
//                        )
//                    )
//                    HyperlinkText(
//                        fullText = "Visit my github and linkedin",
//                        linkText = listOf( "github","linkedin"),
//                        hyperlinks = listOf("https://github.com/neerajsahu14","https://linkedin.com/in/neerajsahu14/"),
//                        linkTextColor = Color.Blue,
//                        linkTextFontWeight = FontWeight.Bold,
//                    )
                }
            }
        }
    }
}

@Composable
fun HyperlinkText(
    modifier: Modifier = Modifier,
    fullText: String,
    linkText: List<String>,
    linkTextColor: Color = Color.Blue,
    linkTextFontWeight: FontWeight = FontWeight.Medium,
    linkTextDecoration: TextDecoration = TextDecoration.Underline,
    hyperlinks: List<String> = listOf("https://stevdza-san.com"),
    fontSize: TextUnit = TextUnit.Unspecified
) {
    val annotatedString = buildAnnotatedString {
        append(fullText)
        linkText.forEachIndexed { index, link ->
            val startIndex = fullText.indexOf(link)
            val endIndex = startIndex + link.length
            addStyle(
                style = SpanStyle(
                    color = linkTextColor,
                    fontSize=fontSize,
                    fontWeight = linkTextFontWeight,
                    textDecoration = linkTextDecoration
                ),
                start = startIndex,
                end = endIndex
            )
            addStringAnnotation(
                tag = "URL",
                annotation = hyperlinks[index],
                start = startIndex,
                end = endIndex
            )

        }
        addStyle(
            style = SpanStyle(
                fontSize = fontSize
            ),
            start = 0,
            end = fullText.length
        )
    }
    val urlHandler = LocalUriHandler.current

    ClickableText(
        modifier = modifier,
        text = annotatedString,
        onClick = {
            annotatedString
                .getStringAnnotations("URL", it, it)
                .firstOrNull()?.let { stringAnnotation ->
                    urlHandler.openUri(stringAnnotation.item)
                }
        }
    )

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