package com.irvin.cryptocurrency.presentation.ui.info_screen

пimport android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.text.Html
import android.text.style.URLSpan
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.irvin.cryptocurrency.R
import com.irvin.cryptocurrency.presentation.ui.theme.LinkTextStyle
import com.irvin.cryptocurrency.presentation.ui.theme.Typography
import com.irvin.cryptocurrency.presentation.viewmodels.InfoUiState

@Composable
fun InfoCryptocurrency(uiState: InfoUiState.Info) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(Modifier.height(16.dp))
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            AsyncImage(
                modifier = Modifier.size(90.dp),
                model = uiState.info.image,
                contentDescription = null
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.header_description_cryptocurrency),
            style = Typography.h6
        )
        Spacer(Modifier.height(8.dp))
        if (uiState.info.description != "") {
            TextWithLinks(uiState.info.description)
        } else {
            Text(
                text = stringResource(id = R.string.no_information_about_cryptocurrency),
                style = Typography.body1
            )
        }
        Spacer(Modifier.height(16.dp))
        Text(
            text = stringResource(R.string.header_categories_cryptocurrency),
            style = Typography.h6
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text = uiState.info.categories.joinToString(separator = ", "),
            style = Typography.body1
        )
        Spacer(Modifier.height(16.dp))
    }
}

@Composable
fun TextWithLinks(htmlContent: String) {
    val formattedHtmlContent = htmlContent.replace("\r\n\r\n", "<br><br>")
    val spannedText = remember(formattedHtmlContent) {
        Html.fromHtml(formattedHtmlContent, Html.FROM_HTML_MODE_LEGACY)
    }
    val annotatedString = remember(spannedText) {
        buildAnnotatedString {
            val spanTextString = spannedText.toString()
            append(spanTextString)
            spannedText.getSpans(0, spannedText.length, URLSpan::class.java).forEach { urlSpan ->
                val start = spannedText.getSpanStart(urlSpan)
                val end = spannedText.getSpanEnd(urlSpan)
                val url = urlSpan.url
                addStyle(
                    style = LinkTextStyle,
                    start = start,
                    end = end
                )
                addStringAnnotation(
                    tag = "URL",
                    annotation = url,
                    start = start,
                    end = end
                )
            }
        }
    }

    val context = LocalContext.current
    val toastText = stringResource(R.string.toast_text)
    ClickableText(
        text = annotatedString,
        style = Typography.body1,
        onClick = { offset ->
            annotatedString.getStringAnnotations("URL", offset, offset).firstOrNull()
                ?.let { annotation ->
                    val url = annotation.item
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
                        addCategory(Intent.CATEGORY_BROWSABLE)
                        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                    }
                    try {
                        context.startActivity(intent)
                    } catch(e: ActivityNotFoundException) {
                        Toast.makeText(context, toastText, Toast.LENGTH_LONG).show()
                    }
                }
        }
    )
}