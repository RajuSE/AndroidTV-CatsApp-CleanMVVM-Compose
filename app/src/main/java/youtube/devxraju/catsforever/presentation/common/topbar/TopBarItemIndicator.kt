package youtube.devxraju.catsforever.presentation.common.topbar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.DpRect
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.height
import androidx.compose.ui.unit.width
import androidx.compose.ui.zIndex
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TopBarItemIndicator(
    currentTabPosition: DpRect,
    modifier: Modifier = Modifier,
    activeColor: Color = Color(0xFFE5E1E6),
    inactiveColor: Color = MaterialTheme.colorScheme.secondaryContainer.copy(alpha = 0.4f),
    anyTabFocused: Boolean,
    shape: Shape
) {
    val width by animateDpAsState(targetValue = currentTabPosition.width, label = "width")
    val height = currentTabPosition.height
    val leftOffset by animateDpAsState(targetValue = currentTabPosition.left, label = "leftOffset")
    val topOffset = currentTabPosition.top

    val pillColor by animateColorAsState(
        targetValue = if (anyTabFocused) activeColor else inactiveColor,
        label = "pillColor"
    )

    Box(
        modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.BottomStart)
            .offset(x = leftOffset, y = topOffset)
            .width(width)
            .height(height)
            .clip(RoundedCornerShape(18.dp))
            .background(color = pillColor, shape = shape)
            .zIndex(-1f)
    )
}
