package youtube.devxraju.catsforever.theme

import androidx.tv.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import youtube.devxraju.catsforever.R

val Ubuntu = FontFamily(
    fonts = listOf(
        Font(R.font.ubuntu_regular, FontWeight.Normal),
        Font(R.font.ubuntu_bold, FontWeight.Bold),
        Font(R.font.ubuntu_medium, FontWeight.Medium),
    )
)

val Typography = Typography(
    displaySmall = TextStyle(
        fontSize = 24.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        lineHeight = 36.sp,
    ),
    displayMedium = TextStyle(
        fontSize = 32.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        lineHeight = 48.sp,
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        lineHeight = 21.sp,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp,
    ),
    labelSmall = TextStyle(
        fontSize = 13.sp,
        fontFamily = Ubuntu,
        fontWeight = FontWeight.Normal,
        lineHeight = 19.sp,
    ),

)