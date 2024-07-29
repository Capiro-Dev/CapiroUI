package com.capiro.composables.athomic_composables.spinners

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.PopupProperties
import com.capiro.composables.EMPTY
import com.capiro.composables.R
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography

@Composable
fun SpinnerDropdownCapiro(
    modifier: Modifier = Modifier,
    @DrawableRes imageResourceId: Int,
    items: List<String>,
    selectedItem: String,
    onItemSelect: (String) -> Unit,
    isEnabled: Boolean = true,
) {

    val isExpanded = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        SpinnerDropdownLayout(
            itemSelected = selectedItem,
            isEnabled = isEnabled,
            onClick = {
                if (isEnabled) {
                    isExpanded.value = !isExpanded.value
                }
            },
            imageResourceId = imageResourceId
        )


        Spacer(modifier = Modifier.size(4.dp))

        SpinnerDropdownMenu(
            isExpanded = isExpanded,
            items = items,
            onItemSelectedChange = onItemSelect
        )
    }

}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SpinnerDropdownMenu(
    isExpanded: MutableState<Boolean>,
    items: List<String>,
    onItemSelectedChange: (String) -> Unit,
) {

    var dropDownWidth by remember { mutableStateOf(0) }


    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
            .padding(top = 16.dp)
            .onSizeChanged { dropDownWidth = it.width }
    ) {
        MaterialTheme(    shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))){

        DropdownMenu(
                modifier = Modifier.height(300.dp)
                    .background(Color.White)
                    .width(with(LocalDensity.current) { dropDownWidth.toDp() }),
                // Set the height as needed
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                items.forEach { label ->
                    DropdownMenuItem(
                        text = {
                            Column(modifier = Modifier.fillMaxWidth()) {
                                Text(
                                    text = label,
                                    style = getTypography().bodyMedium,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    color = GreenCapiro
                                )
                            }
                        },
                        onClick = {
                            isExpanded.value = false
                            onItemSelectedChange(label)
                        }
                    )
                }
            }

        }
    }
}


@Composable
private fun SpinnerDropdownLayout(
    itemSelected: String,
    onClick: () -> Unit,
    @DrawableRes imageResourceId: Int?,
    isEnabled: Boolean
) {
    val textColor = GreenCapiro
    val colorBackground = if (isEnabled) Color.White else GrayDarkCapiro

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(54.dp)
                .clickable(onClick = onClick),
            shape = RoundedCornerShape(16.dp),
            colors = CardDefaults.cardColors(containerColor = colorBackground),
            elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 4.dp, horizontal = 16.dp),
                verticalArrangement = Arrangement.Center
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    if (imageResourceId != null) {
                        Image(
                            painter = painterResource(id = imageResourceId),
                            contentDescription = null
                        )
                    }
                    // Display Text
                    Text(
                        text = itemSelected,
                        style = getTypography().bodyMedium,
                        color = textColor
                    )

                    // Arrow Icon
                    if (isEnabled) {
                        Image(
                            painter = painterResource(id = R.drawable.down_arrow),
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                    } else {
                        //lock icon
                        Image(
                            imageVector = Icons.Default.Lock,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp),
                            colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(RedCapiro)
                        )
                    }

                }
            }
        }
    }
}
