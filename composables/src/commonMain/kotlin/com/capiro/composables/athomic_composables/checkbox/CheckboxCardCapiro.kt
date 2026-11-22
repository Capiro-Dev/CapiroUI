package com.capiro.composables.athomic_composables.checkbox

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import getTypography

/**
 * A yes / no field with the look of
 * [com.capiro.composables.athomic_composables.textfield.InputCardCapiro] (white elevated 54dp card,
 * 16dp corners), so a flag can share a form row with typed and picked values.
 *
 * The whole card toggles [checked] (one touch target, `Role.Checkbox`); [label] sits at the start and
 * the checkbox at the end. [checkboxModifier] reaches the checkbox itself (e.g. a test tag).
 */
@Composable
fun CheckboxCardCapiro(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    checkboxModifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val typography = getTypography()
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(54.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.elevatedCardElevation(defaultElevation = 8.dp),
    ) {
        Row(
            modifier = Modifier
                .fillMaxHeight()
                .toggleable(
                    value = checked,
                    enabled = enabled,
                    role = Role.Checkbox,
                    onValueChange = onCheckedChange,
                )
                .padding(start = 16.dp, end = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(
                text = label,
                style = typography.bodyMedium,
                color = if (checked) GreenCapiro else GrayDarkCapiro,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier.weight(1f),
            )
            Checkbox(
                checked = checked,
                // The card handles the toggle, so the box is not a second touch target.
                onCheckedChange = null,
                enabled = enabled,
                colors = CheckboxDefaults.colors(
                    checkedColor = GreenCapiro,
                    uncheckedColor = GrayDarkCapiro,
                    checkmarkColor = WhiteCapiro,
                ),
                modifier = checkboxModifier,
            )
        }
    }
}
