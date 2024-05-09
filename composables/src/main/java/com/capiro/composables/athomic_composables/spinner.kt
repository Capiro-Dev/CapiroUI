package com.capiro.composables.athomic_composables

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.capiroui.athomic_composables.TextFieldOutlinedCapiro
import com.capiro.composables.EMPTY
import com.capiro.capiroui.theme.GrayClearCapiro
import com.capiro.capiroui.theme.GrayDarkCapiro
import com.capiro.capiroui.theme.GreenCapiro
import com.capiro.capiroui.theme.GreenSecondCapiro
import com.capiro.capiroui.theme.WhiteCapiro
import getTypography

@Composable
fun SpinnerSimpleCapiro(
    modifier: Modifier = Modifier,
    items: List<String>,
    selectedItem: String,
    onItemSelect: (String) -> Unit,
    isEnabled: Boolean = true,

    backgroundColor: Color = GreenCapiro,
    backgroundColorPressed: Color = WhiteCapiro,
    backgroundColorDisabled: Color = GrayClearCapiro,
    textColor: Color = WhiteCapiro,
    textColorPressed: Color = GreenCapiro,
    textColorDisabled: Color = GrayDarkCapiro,
    borderColor: Color = GreenSecondCapiro,
    borderColorPressed: Color = GreenCapiro,
    borderColorDisable: Color = GrayDarkCapiro


) {
    var expanded by remember { mutableStateOf(false) }
    val typography = getTypography()

    // colors
    val fontColorSelected =
        if (!isEnabled) textColorDisabled else if (expanded) textColorPressed else textColor
    val backgroundColorSelected =
        if (!isEnabled) backgroundColorDisabled else if (expanded) backgroundColorPressed else backgroundColor
    val borderColorSelected =
        if (!isEnabled) borderColorDisable else if (expanded) borderColorPressed else borderColor




    Column {

        Button(
            modifier = modifier
                .clip(RoundedCornerShape(20))
                .border(2.dp, borderColorSelected, RoundedCornerShape(20))
                .background(backgroundColorSelected)
            ,
            shape = RoundedCornerShape(20),

            colors = ButtonDefaults.buttonColors(
                containerColor = backgroundColorSelected
            ),
            onClick = { expanded = !expanded },
            enabled = isEnabled
        ) {
            Row (Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically){
                Text(
                    selectedItem,
                    style = typography.bodyMedium,
                    color = fontColorSelected,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 1
                )
                Icon(
                    imageVector = Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = fontColorSelected
                )

            }

        }
        DropdownMenu(
            expanded = expanded,
            modifier = Modifier.background(WhiteCapiro),
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { label ->
                DropdownMenuItem(onClick = {
                    expanded = false
                    onItemSelect(label)
                }, text = {
                    Text(
                        text = label,
                        style = typography.bodyMedium,
                        color = GrayDarkCapiro,
                    )
                })
            }
        }
    }
}

@Preview
@Composable
private fun SpinnerSimpleCapiroPreview() {
    val suggestions = listOf("Item1", "Item2", "Item3")
    var selectedItem by remember { mutableStateOf("Item1") }
    Box(
        modifier = Modifier
            .width(180.dp)
            .padding(16.dp)
    ) {
        SpinnerSimpleCapiro(
            items = suggestions,
            selectedItem = selectedItem,
            onItemSelect = { selectedItem = it }
        )
    }
}





@Composable
fun CustomDropdown(
    text:String,
    selectedItem: String,
    items: List<String>,
    onItemSelectedChange: (String) -> Unit,
    isActivated: Boolean = true
) {
    var expanded by remember { mutableStateOf(false) }
    val icon : ImageVector? = if (isActivated) Icons.Filled.Lock else null


    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Box (modifier = Modifier.clickable { if (isActivated) expanded = !expanded }) {
            TextFieldOutlinedCapiro(
                textInput = selectedItem,
                label =text,
                onTextChangeEvent = { },
                isEnabled = false,
                iconDisable = icon,
                backgroundColorDisabled = WhiteCapiro,
                borderColorDisabled = if(selectedItem.isNotEmpty() || selectedItem != EMPTY ) GreenCapiro else GrayDarkCapiro,
                fontColorDisabled =  GreenCapiro
            )
        }


        DropdownMenu(
            modifier = Modifier.background(Color.White),
            expanded = expanded,
            onDismissRequest = { expanded = false },
        ) {
            items.forEach { label ->
                DropdownMenuItem(text = {
                    Column {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyMedium,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            color = Color.Black
                        )
                       // Divider(thickness = 2.dp, color = GrayCake)
                    }
                }, onClick = {
                    expanded = false
                    onItemSelectedChange(label)
                })
            }
        }
    }

}

@Preview
@Composable
private fun CustomDropdownPreview() {
    val suggestions = listOf("Item1", "Item2", "Item3")
    var selectedItem by remember { mutableStateOf(EMPTY) }
    Box(
        modifier = Modifier
            .width(180.dp)
            .padding(16.dp)
    ) {
        Row (modifier= Modifier.fillMaxWidth()){
            CustomDropdown(
                text = "itetem",
                selectedItem = selectedItem,
                items = suggestions,
                onItemSelectedChange = { selectedItem = it }
            )
        }

    }
}


