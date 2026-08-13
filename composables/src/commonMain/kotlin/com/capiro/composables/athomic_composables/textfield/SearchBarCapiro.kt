package com.capiro.composables.athomic_composables.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import getTypography

/**
 * A pill-shaped search field with a leading search icon and a clear ("x") icon that
 * appears once there is text to clear.
 *
 * @param searchText The current search query.
 * @param onSearchTextChange Callback triggered when the search query changes.
 * @param placeholder The text shown when [searchText] is empty.
 * @param typography The typography style to use for the query text and placeholder.
 * @param onSearch Callback triggered when the user presses the keyboard's search action.
 * @param modifier The [Modifier] applied to the search bar's container.
 * @param containerColor The background color of the search bar.
 * @param shape The shape of the search bar's container.
 * @param elevation The shadow elevation drawn behind the search bar, following [shape].
 * @param iconTint The tint applied to the search icon while focused and to the clear icon.
 */
@Composable
fun SearchBarCapiro(
    searchText: String,
    onSearchTextChange: (String) -> Unit,
    placeholder: String = "Buscar",
    typography: Typography = getTypography(),
    onSearch: () -> Unit = {},
    modifier: Modifier = Modifier,
    containerColor: Color = GrayClearCapiro,
    shape: Shape = RoundedCornerShape(24.dp),
    elevation: Dp = 0.dp,
    iconTint: Color = GreenCapiro,
) {
    var isFocused by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .shadow(elevation, shape)
            .background(containerColor, shape)
            .padding(horizontal = 16.dp, vertical = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = null,
            tint = if (isFocused) iconTint else GrayDarkCapiro,
        )

        BasicTextField(
            value = searchText,
            onValueChange = onSearchTextChange,
            textStyle = typography.bodyMedium,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            modifier = Modifier
                .weight(1f)
                .onFocusChanged { isFocused = it.isFocused },
            decorationBox = { innerTextField ->
                if (searchText.isEmpty()) {
                    Text(
                        text = placeholder,
                        color = GrayDarkCapiro,
                        style = typography.bodyMedium,
                    )
                } else {
                    innerTextField()
                }
            }
        )

        if (searchText.isNotEmpty()) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                tint = GrayDarkCapiro,
                modifier = Modifier.clickable { onSearchTextChange("") },
            )
        }
    }
}

@Preview
@Composable
private fun PreviewSearchBarCapiro() {
    var text by remember { mutableStateOf("") }
    SearchBarCapiro(
        searchText = text,
        onSearchTextChange = { text = it },
        placeholder = "Buscar finca",
    )
}
