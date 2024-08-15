package youtube.devxraju.catsforever.presentation.details.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import youtube.devxraju.catsforever.R
import youtube.devxraju.catsforever.presentation.details.HeartLottie
import youtube.devxraju.catsforever.theme.CatsAppTheme

@OptIn(ExperimentalTvMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
    isFav:Boolean = false,
    isFavClickedNow:Boolean = false,
    isBackTopBar:Boolean = false,
    onBrowsingClick: () -> Unit =  {},
    onShareClick: () -> Unit =  {},
    onFavouriteClick: () -> Unit =  {},
    onBackClick: () -> Unit ,
) {

    TopAppBar(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 12.dp),
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = Color.Transparent,
            actionIconContentColor = colorResource(id = R.color.body),
            navigationIconContentColor = colorResource(id = R.color.body),
        ),
        title = {},
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_arrow),
                    contentDescription = null,
                )
            }
        },
        actions = {
            if(isBackTopBar){
                return@TopAppBar
            }


            IconButton(onClick = onFavouriteClick) {
               if(isFavClickedNow){
                   HeartLottie(isPlaying = true, )
               }else
                   if(isFav)
                Icon(
                    painter = painterResource(id = R.drawable.img_favourited_red ),
                    contentDescription = null,
                    tint =  Color.Red
                )
                else Icon(
                       painter = painterResource(id = R.drawable.img_heart_black),
                       contentDescription = null,
                   )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(onClick = onShareClick) {
                Icon(
                    painter = painterResource(id = R.drawable.img_browse),
                    contentDescription = null,
                )
            }
            Spacer(modifier = Modifier.width(40.dp))
            IconButton(onClick = onBrowsingClick) {
                Icon(
                    painter = painterResource(id = R.drawable.img_eye),
                    contentDescription = null
                )
            }
        },
    )
}



@Preview(showBackground = false, device = Devices.TV_1080p)
@Composable
fun DetailsTopBarPreview() {
    CatsAppTheme(dynamicColor = false) {
        CommonTopBar(
            false,
            onShareClick = { /*TODO*/ },
            onFavouriteClick = { /*TODO*/ },
            onBrowsingClick = {}) {

        }
    }
}