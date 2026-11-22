package com.capiro.composables.athomic_composables.textfield

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography

/**
 * A one-line input with the look of [com.capiro.composables.athomic_composables.spinners.SpinnerButtonCapiro]
 * (white elevated 54dp card, 16dp corners), so typed values and picked values can share a form row.
 *
 * Empty, it shows [label] as the placeholder; with a value, [label] moves to the top, as the spinner
 * does. With [readOnly] it only displays [value] (e.g. a computed total) and takes no focus.
 *
 * @param keyboardType [KeyboardType.Number] for integers, [KeyboardType.Decimal] for decimals.
 * @param visualTransformation e.g. a thousands separator; [value] stays the raw text.
 * @param trailingContent optional content at the end of the card (e.g. a "back to the list" icon).
 * @param isError draws a red border (e.g. a required field left empty on save).
 */
@Composable
fun InputCardCapiro(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    inputModifier: Modifier = Modifier,
    keyboardType: KeyboardType = KeyboardType.Number,
    imeAction: ImeAction = ImeAction.Done,
    readOnly: Boolean = false,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    trailingContent: (@Composable () -> Unit)? = null,
    isError: Boolean = false,
) {
    var isFocused by remember { mutableStateOf(false) }
    val typography = getTypography()

    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
        border = if (isError) BorderStroke(2.dp, RedCapiro) else null,
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .padding(vertical = 4.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Column(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.Center,
            ) {
                if (value.isNotEmpty()) {
                    Text(
                        text = label,
                        style = typography.bodySmall,
                        color = GrayDarkCapiro,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }
                if (readOnly) {
                    Text(
                        text = visualTransformation.filter(
                            AnnotatedString(value.ifEmpty { label }),
                        ).text.text,
                        style = typography.bodyMedium,
                        color = if (value.isEmpty()) GrayDarkCapiro else GreenCapiro,
                        maxLines = 1,
                        modifier = inputModifier,
                    )
                } else {
                    BasicTextField(
                        value = value,
                        onValueChange = onValueChange,
                        singleLine = true,
                        textStyle = typography.bodyMedium.copy(color = GreenCapiro),
                        cursorBrush = SolidColor(GreenCapiro),
                        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
                        keyboardActions = KeyboardActions.Default,
                        visualTransformation = visualTransformation,
                        modifier = inputModifier
                            .fillMaxWidth()
                            .onFocusChanged { isFocused = it.isFocused },
                        decorationBox = { innerTextField ->
                            if (value.isEmpty()) {
                                Text(
                                    text = label,
                                    style = typography.bodyMedium,
                                    color = if (isFocused) GreenCapiro else GrayDarkCapiro,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                )
                            }
                            innerTextField()
                        },
                    )
                }
            }
            trailingContent?.invoke()
        }
    }
}
