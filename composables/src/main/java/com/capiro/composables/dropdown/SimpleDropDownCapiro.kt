package com.capiro.composables.dropdown

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.theme.BlackCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography
import java.nio.file.WatchEvent

/**
 * A composable function that displays a spinner dropdown with a list of items.
 *
 * @param modifier Modifier for styling and layout adjustments.
 * @param imageResourceId Resource ID for the drawable to be displayed alongside the spinner.
 * @param items List of items to display in the dropdown menu.
 * @param selectedItem The currently selected item in the dropdown.
 * @param onItemSelect Callback to be invoked when an item is selected.
 * @param isEnabled Boolean to control if the dropdown is enabled or not.
 */
@Composable
fun SimpleDropdownTextCapiro(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String,
    onItemSelect: (String) -> Unit,
    isEnabled: Boolean = true,
    labelResourceId: Int,
    isErrorActive: Boolean = false,
    action: ()->Unit = {}
) {
    val isExpanded = remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        SimpleDropDownUnderlined(
            labelResourceId = labelResourceId,
            selectedItem = selectedItem,
            onClick = {
                if (isEnabled) {
                    action()
                    isExpanded.value = !isExpanded.value
                }
            },
            isErrorActive = isErrorActive
        )
        Spacer(modifier = Modifier.size(4.dp))
        SimpleDropdownTextMenu(
            isExpanded = isExpanded,
            items = items,
            onItemSelectedChange = onItemSelect
        )
    }
}

/**
 * A composable function for displaying the dropdown menu items.
 *
 * @param isExpanded State to control the visibility of the dropdown menu.
 * @param items List of items to display in the menu.
 * @param onItemSelectedChange Callback to be invoked when an item is selected.
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
private fun SimpleDropdownTextMenu(
    isExpanded: MutableState<Boolean>,
    items: List<String>,
    onItemSelectedChange: (String) -> Unit,
) {
    var dropDownWidth by remember { mutableIntStateOf(0) }
    var otherText by remember { mutableStateOf("")}

    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .padding(horizontal = 8.dp)
            .fillMaxWidth()
            .padding(top = 16.dp)
            .onSizeChanged { dropDownWidth = it.width }
    ) {
        MaterialTheme(shapes = MaterialTheme.shapes.copy(extraSmall = RoundedCornerShape(16.dp))) {
            DropdownMenu(
                modifier = Modifier
                    .height(300.dp)
                    .background(Color.White)
                    .width(with(LocalDensity.current) { dropDownWidth.toDp() }),
                expanded = isExpanded.value,
                onDismissRequest = { isExpanded.value = false }
            ) {
                Spacer(modifier = Modifier.height(2.dp))
                items.forEach { label ->
                    if (label == "otro") {
                        DropdownMenuItem(
                            text = {
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    verticalAlignment = Alignment.CenterVertically // Centra los elementos en el eje vertical
                                ) {
                                    Box(modifier = Modifier.weight(1f)) { // Ocupa el máximo espacio posible)
                                        TextFieldSolidBackground(
                                            text = otherText,
                                            onTextChanged = { otherText = it },
                                            keyboardType = KeyboardType.Number
                                            )
                                    }

                                    Icon(
                                        modifier = Modifier
                                            .size(32.dp)
                                            .align(Alignment.CenterVertically) // Asegura que el ícono esté centrado verticalmente
                                            .clickable {
                                                isExpanded.value = false
                                                onItemSelectedChange(otherText)
                                            },
                                        painter = painterResource(R.drawable.done),
                                        contentDescription = null,
                                        tint = GreenSecondCapiro
                                    )
                                }
                            },
                            onClick = {
                                isExpanded.value = false
                                onItemSelectedChange(label)
                            }
                        )
                    } else {
                        DropdownMenuItem(
                            text = {
                                Column(modifier = Modifier.fillMaxWidth()) {
                                    Text(
                                        text = label,
                                        style = TypographyProvider.typography.bodyMedium,
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
}

/**
 * A composable function that displays the layout for the spinner dropdown.
 *
 * @param itemSelected The currently selected item in the spinner.
 * @param onClick Callback to be invoked when the spinner layout is clicked.
 * @param imageResourceId Resource ID for the drawable to be displayed alongside the spinner.
 * @param isEnabled Boolean to control if the dropdown is enabled or not.
 */
@Composable
fun SimpleDropDownUnderlined(
    @StringRes labelResourceId: Int,
    selectedItem: String,
    onClick: () -> Unit,
    isErrorActive: Boolean = false
) {
    val labelColor = if (isErrorActive) RedCapiro else BlackCapiro

    Column(
        modifier = Modifier
            .height(54.dp)
            .fillMaxWidth()
            .clickable { onClick() }
    ) {
        // Display the label
        Text(
            modifier = Modifier.padding(horizontal = 8.dp),
            text = stringResource(id = labelResourceId),
            style = getTypography().bodyMedium,
            color = labelColor,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Display the selected item
            Text(
                modifier = Modifier.weight(1f),
                text = selectedItem,
                style = getTypography().bodyMedium,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = GreenCapiro,
                fontWeight = FontWeight.Bold
            )

            // Display the arrow icon
            Image(
                painter = painterResource(id = R.drawable.down_arrow),
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
        }

        // Divider line
        Divider(modifier = Modifier.padding(top = 4.dp),thickness = 1.dp, color = GrayDarkCapiro)
    }
}


@Preview
@Composable
fun SimpleDropdownTextPreview() {
    Row() {
        val itemSelected = remember {  mutableStateOf("Selecciona..")}
        Box(modifier = Modifier.weight(1f)) {
            SimpleDropdownTextCapiro(
                items = listOf("otro", "Item 2", "Item 3"),
                selectedItem = itemSelected.value,
                onItemSelect = { itemSelected.value = it},
                labelResourceId = R.string.cappiro_name
            )
        }
        Box(modifier = Modifier.weight(1f)) {
            SimpleDropdownTextCapiro(
                items = listOf("Item 1", "Item 2", "Item 3"),
                selectedItem = "Item 1",
                onItemSelect = { },
                labelResourceId = R.string.general_bt_more,
                isErrorActive = true
            )
        }
    }
}