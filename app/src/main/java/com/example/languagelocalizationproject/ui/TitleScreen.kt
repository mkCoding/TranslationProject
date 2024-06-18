package com.example.languagelocalizationproject.ui

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.languagelocalizationproject.R
import java.util.Locale

@Composable
fun TitleScreen (){
    val currentLocale = remember { Locale.getDefault() }
    var selectedLocale by remember { mutableStateOf(currentLocale) }
    val context = LocalContext.current


    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.translate),
            style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.SansSerif),
            modifier = Modifier
                    .padding(bottom = 10.dp)
        )
        Text(
            text = stringResource(R.string.paragraph),
                    modifier = Modifier.padding(10.dp) // Adjust padding as needed
        )
//        Text(
//            text = stringResource(R.string.select_locale),
//            style = TextStyle(fontSize = 30.sp, fontFamily = FontFamily.SansSerif)
//        )
// Include the LanguageSelector here
        DropDownLanguageSelector(
            currentLocale = selectedLocale,
            onLocaleSelected = { newLocale ->
                selectedLocale = newLocale
                setLocale(context, newLocale)
            }
        )


    }
}

val supportedLanguages = listOf(
    Language("English", Locale.ENGLISH),
    Language("French", Locale.FRENCH),
    Language("Spanish", Locale("es"))
)


@Composable
fun DropDownLanguageSelector(
    currentLocale: Locale,
    onLocaleSelected: (Locale) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(modifier = Modifier.padding(16.dp)) {
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Select Language",
            modifier = Modifier
                .padding(8.dp)
                .clickable { expanded = true }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            supportedLanguages.forEach { language ->
                DropdownMenuItem(
                    text ={ Text(text = language.displayName)},
                    onClick = {
                        onLocaleSelected(language.locale)
                        setLocale(context, language.locale)
                        expanded = false
                    }
                )

            }
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = "Current Language: ${currentLocale.displayName}"
        )
    }
}


fun setLocale(context: Context, locale: Locale) {
    val resources = context.resources
    val configuration = resources.configuration
    configuration.setLocale(locale)
    resources.updateConfiguration(configuration, resources.displayMetrics)
}


@Preview( showBackground = true)
@Composable
fun TitleScreenPreview(){
    TitleScreen()
}
