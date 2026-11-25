package com.capiro.composables.athomic_composables.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography

/**
 * A plain yes / no field: the Capiro-green checkbox and its [label] after it, with no card around
 * them (see [CheckboxCardCapiro] for the carded one that lines up with the form cards).
 *
 * The whole row toggles [checked] (one touch target, at least 48dp high, `Role.Checkbox`).
 * [checkboxModifier] reaches the checkbox itself (e.g. a test tag).
 */
@Composable
fun CheckboxCapiro(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    checkboxModifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val typography = getTypography()
    Row(
        modifier = modifier
            .defaultMinSize(minHeight = 48.dp)
            .toggleable(
                value = checked,
                enabled = enabled,
                role = Role.Checkbox,
                onValueChange = onCheckedChange,
            )
            .padding(end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        Checkbox(
            checked = checked,
            // The row handles the toggle, so the box is not a second touch target.
            onCheckedChange = null,
            enabled = enabled,
            colors = CheckboxDefaults.colors(
                checkedColor = GreenCapiro,
                uncheckedColor = GrayDarkCapiro,
                checkmarkColor = WhiteCapiro,
            ),
            modifier = checkboxModifier.padding(start = 12.dp),
        )
        Text(
            text = label,
            style = typography.bodyMedium,
            color = if (checked) GreenCapiro else GrayDarkCapiro,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}
