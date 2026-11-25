package com.capiro.composables.athomic_composables.radio

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro
import getTypography

/**
 * One choice among [options], laid out in a row of equal-width radio buttons with their labels, no
 * card around them (e.g. "Pico" | "Regular").
 *
 * [selectedIndex] is the picked option, null while none is. Each option (button + label) is one touch
 * target, at least 48dp high, `Role.RadioButton`; picking it calls [onSelect] with its index.
 * [isError] paints the unselected buttons and the labels red (a required choice still empty), like
 * the error border of the form cards. [optionModifier] reaches each option's row (e.g. a test tag).
 */
@Composable
fun RadioGroupCapiro(
    options: List<String>,
    selectedIndex: Int?,
    onSelect: (Int) -> Unit,
    modifier: Modifier = Modifier,
    optionModifier: (index: Int) -> Modifier = { Modifier },
    isError: Boolean = false,
    enabled: Boolean = true,
) {
    val typography = getTypography()
    Row(
        modifier = modifier.selectableGroup(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        options.forEachIndexed { index, option ->
            val selected = index == selectedIndex
            Row(
                modifier = optionModifier(index)
                    .weight(1f)
                    .defaultMinSize(minHeight = 48.dp)
                    .selectable(
                        selected = selected,
                        enabled = enabled,
                        role = Role.RadioButton,
                        onClick = { onSelect(index) },
                    )
                    .padding(end = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
            ) {
                RadioButton(
                    selected = selected,
                    // The option's row handles the pick, so the button is not a second touch target.
                    onClick = null,
                    enabled = enabled,
                    colors = RadioButtonDefaults.colors(
                        selectedColor = GreenCapiro,
                        unselectedColor = if (isError) RedCapiro else GrayDarkCapiro,
                    ),
                    modifier = Modifier.padding(start = 12.dp),
                )
                Text(
                    text = option,
                    style = typography.bodyMedium,
                    color = when {
                        selected -> GreenCapiro
                        isError -> RedCapiro
                        else -> GrayDarkCapiro
                    },
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
    }
}
