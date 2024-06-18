package com.example.languagelocalizationproject

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.languagelocalizationproject.ui.TitleScreen
import com.example.languagelocalizationproject.ui.theme.LanguageLocalizationProjectTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LanguageLocalizationProjectTheme {
              TitleScreen()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TitleScreen() // You can also preview your TitleScreen directly in Android Studio
}