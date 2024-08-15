package youtube.devxraju.catsforever.presentation.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.size.Scale
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import kotlinx.coroutines.delay
import youtube.devxraju.catsforever.R
import youtube.devxraju.catsforever.data.remote.dto.CatBreedsResponseItem
import youtube.devxraju.catsforever.presentation.details.components.CommonTopBar
import youtube.devxraju.catsforever.theme.CatsAppTheme
import youtube.devxraju.catsforever.util.DataState

@Composable
fun DetailsScreen(
    cat: CatBreedsResponseItem,
    event: (DetailsEvent) -> Unit,
    favUnfav: DataState?,
    isFavoritedAlready: Boolean,
    navigateUp: () -> Unit,
    openWebView:(url:String) -> Unit,
) {
    val context = LocalContext.current

    println("DetailsScreen fun")

    val isFav by remember(favUnfav) {
        mutableStateOf(
            favUnfav?.let {
                if (it is DataState.FavoriteUnfav) it.isFavorited else isFavoritedAlready
            } ?: isFavoritedAlready)
    }

    val isFavClickedNow by remember(favUnfav){
        mutableStateOf(
            favUnfav?.let {
                if (it is DataState.FavoriteUnfav) it.isFavorited else false
            } ?: false)
    }

    val lsState = rememberLazyListState()

    var isClicked by remember { mutableStateOf(false) }

    val alphaAnimation by animateFloatAsState(
        targetValue = if (!isClicked) 0.3f else 1f,
        animationSpec = tween(durationMillis = 1000)
    )

    LaunchedEffect(key1 = true) {
        isClicked=true
        delay(1000)
        isClicked = false
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
    ) {


        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            contentPadding = PaddingValues(),
            reverseLayout = false,
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start,
            userScrollEnabled = false
        ) {
            item {
                Box {
                    AsyncImage(
                        model = ImageRequest.Builder(context = context)
                            .data("https://cdn2.thecatapi.com/images/${cat.reference_image_id}.jpg")
                            .scale(Scale.FILL)
                            .build(),
                        contentDescription = null,
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillParentMaxHeight()
                            .clip(MaterialTheme.shapes.medium)
                            .alpha(alphaAnimation),
//                            .background(Color.Yellow),
                        contentScale = ContentScale.Crop
                    )

                    AnimatedVisibility(
                        visible = !isClicked,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .fillParentMaxHeight()
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = listOf(
                                            Color.Black.copy(alpha = 0.8f),
                                            Color.Transparent
                                        )
                                    )
                                ),
                            contentAlignment = Alignment.BottomStart
                        ) {
                            LazyColumn(
                                state = lsState,
                                modifier = Modifier
                                    .padding(16.dp)
                                    .width(470.dp)
                                    .padding(16.dp)
                                    .fillMaxHeight(),
                                userScrollEnabled = true
                            ) {
                                item {
                                    Spacer(modifier = Modifier.height(50.dp))
                                    Text(
                                        text = cat.name,
                                        style = MaterialTheme.typography.labelLarge,
                                        fontSize = 38.sp, color = Color.White
                                    )
                                    Spacer(modifier = Modifier.height(16.dp))
                                    Text(
                                        text = cat.description,
                                        style = MaterialTheme.typography.bodyMedium,
                                        fontSize = 21.sp,
                                        maxLines = 10,
                                        overflow = TextOverflow.Ellipsis,
                                        color = Color.White,
                                    )
                                    Spacer(modifier = Modifier.height(20.dp))

                                    Row(horizontalArrangement = Arrangement.SpaceAround) {
                                        ColorChipsRounded(
                                            cat.origin,
                                            backgroundColor = colorResource(id = R.color.onPrimary)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        ColorChipsRounded(
                                            cat.temperament,
                                            backgroundColor = colorResource(id = R.color.onTertiary)
                                        )
                                    }
                                    Spacer(modifier = Modifier.height(12.dp))

                                    Row(horizontalArrangement = Arrangement.SpaceAround) {

                                        ColorChipsRounded(
                                            cat.life_span ?: cat.alt_names ?: "",
                                            backgroundColor = colorResource(id = R.color.errorContainer)
                                        )
                                        Spacer(modifier = Modifier.width(10.dp))
                                        ColorChipsRounded(
                                            "Short Legs: ${if (cat.short_legs == 1) "Yes" else "No"} ",
                                            backgroundColor = Color.Green,
                                            textColor = Color.Black
                                        )
                                    }


                                }

                            }
                        }
                    }
                }
            }
        }

        CommonTopBar(
            isFav = isFav,
            isFavClickedNow=isFavClickedNow,
            onBrowsingClick = {
                isClicked = !isClicked
            },
            onShareClick = {
                openWebView.invoke(cat.wikipedia_url)
            },
            onFavouriteClick = {

                event(DetailsEvent.UpsertDeleteCat(cat))
            },
            onBackClick = navigateUp
        )



    }
}
@Composable
fun HeartLottie( isPlaying:Boolean) {
    val composition by rememberLottieComposition(spec = LottieCompositionSpec.RawRes(R.raw.heart_lottie2))

    LottieAnimation(
        composition = composition,
        isPlaying = isPlaying,
        iterations = 1,
    )
}

@Composable
fun ColorChipsRounded(
    text: String,
    backgroundColor: Color,
    textColor: Color = Color.White,
    cornerRadius: Dp = 28.dp,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .background(backgroundColor, RoundedCornerShape(cornerRadius))
            .padding(16.dp)
    ) {
        Text(
            text = text,
            color = textColor,
            maxLines = 1,
            style = MaterialTheme.typography.bodyMedium,

            )
    }
}

@Preview(showBackground = true, device = Devices.TV_1080p)
@Composable
fun DetailsScreenPreview() {
    CatsAppTheme(dynamicColor = false) {
        DetailsScreen(
            cat = CatBreedsResponseItem(
                adaptability = 6625,
                affection_level = 7976,
                alt_names = null,
                cfa_url = null,
                child_friendly = 4734,
                country_code = "New Zealand",
                country_codes = "Algeria",
                description = "This is description to test the ui with multiple lines to see how it appears devxraju",
                dog_friendly = 9450,
                energy_level = 5296,
                experimental = 3199,
                grooming = 8102,
                hairless = 2415,
                health_issues = 9693,
                hypoallergenic = 5754,
                id = "dicant",
                indoor = 6308,
                intelligence = 3328,
                lap = 6785,
                life_span = null,
                name = "Cornish Rexx",
                natural = 9979,
                origin = "sagittis",
                rare = 2512,
                reference_image_id = "0XYvRd7oD",
                rex = 9258,
                shedding_level = 1900,
                short_legs = 2328,
                social_needs = 1863,
                stranger_friendly = 1502,
                suppressed_tail = 2258,
                temperament = "erat",
                vcahospitals_url = null,
                vetstreet_url = null,
                vocalisation = 7496,
                wikipedia_url = "http://www.bing.com/search?q=altera"
            ),
            isFavoritedAlready = false,
            event = {},
            favUnfav = null,
            navigateUp = {},
            openWebView = {}
        )
    }
}