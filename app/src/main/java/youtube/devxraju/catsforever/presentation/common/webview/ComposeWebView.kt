package youtube.devxraju.catsforever.presentation.common.webview

import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import youtube.devxraju.catsforever.R
import youtube.devxraju.catsforever.presentation.details.components.CommonTopBar
import youtube.devxraju.catsforever.theme.CatsAppTheme

@Composable
fun WebView(url: String, navigateUp: ()->Unit) {


        Box {

            AndroidView(factory = { context ->
                android.webkit.WebView(context).apply {
                    webViewClient = WebViewClient()
                    loadUrl(url)
                }
            })

            Row(modifier = Modifier.wrapContentSize()
                .padding(horizontal = 24.dp, vertical = 24.dp)
                .background(color = Color.DarkGray, shape = CircleShape)) {
                IconButton(onClick = navigateUp, modifier = Modifier.size(42.dp)) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back_arrow_black),
                        contentDescription = null,
                    )
                }
            }
        }

}

@Preview(device = Devices.TV_1080p)
@Composable
fun prevw(){
    CatsAppTheme {
        WebView(url = "", navigateUp = {})
    }
}

