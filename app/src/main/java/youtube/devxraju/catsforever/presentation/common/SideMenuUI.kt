package youtube.devxraju.catsforever.presentation.common

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Text
import youtube.devxraju.catsforever.R


@Composable
fun SideMenu(
    items: List<MenuItem>,
    selectedItemIndex: Int,
    onItemClick: (Int) -> Unit
) {

    var isFocused = remember {
        mutableStateOf(true)
    }
    TvLazyColumn(
        modifier = Modifier
            .background(color = Color.Black)
            .fillMaxHeight()
            .focusable(),
        horizontalAlignment = Alignment.CenterHorizontally,
        contentPadding = PaddingValues(all = 12.dp)// Make the entire SideMenu focusable
    ) {
        items(items.size) { index ->
            SideMenuItem(
                item = items[index],
                isSelected = index == selectedItemIndex,
                onClick = { onItemClick(index) },
                modifier = Modifier.focusable()
//                    .onFocusChanged { focusState ->
//                        isFocused.value = focusState.isFocused
//                    }.scale(if(isFocused.value) 1.15f else 1f)
            )
        }
    }
}

@Composable
fun SideMenuItem(
    item: MenuItem,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier
) {
    Column(modifier = Modifier.wrapContentSize(Alignment.Center)) {
        Spacer(modifier = Modifier.height(20.dp))
        Box(
            modifier = modifier
                .clip(RoundedCornerShape(12.dp))
                .background(if (isSelected) colorResource(id = R.color.black_bckg) else Color.Transparent)
        ) {
            Text(
                text = item.title,
                modifier = modifier
                    .clickable { onClick() }
                    .padding(16.dp)
                    .focusable(), // Highlight selected item
                color = if (isSelected) Color.White else Color.White // Change text color for selected item
            )
        }
    }

}

data class MenuItem(val title: String)