package com.capiro.composables.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.R
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.util_composables.ToolbarMenuLayoutDialogCapiro
import getTypography


/**
 * A toolbar component that displays farm information, section title, and a user menu button.
 * The user menu button opens a dialog with options for backup, printers, and logout.
 *
 * @param user The username to be displayed in the user menu button.
 * @param farm The name of the farm to be displayed on the toolbar.
 * @param section The current section or area title to be displayed on the toolbar.
 * @param userAbbreviation The abbreviated representation of the user's name, shown on the user menu button.
 * @param onBackUpClick Optional lambda function to be invoked when the backup option is clicked in the user menu dialog.
 * @param onPrintersClick Optional lambda function to be invoked when the printers option is clicked in the user menu dialog.
 * @param onLogoutClick Optional lambda function to be invoked when the logout option is clicked in the user menu dialog.
 */
@Composable
fun ToolbarCapiro(
    user: String,
    farm: String,
    section: String,
    userAbbreviation: String,
    onBackUpClick: (() -> Unit)? = null,
    onPrintersClick: (() -> Unit)? = null,
    onLogoutClick: (() -> Unit)? = null
) {
    val isMenuOpen = remember { mutableStateOf(false) }

    ToolbarMenuLayoutDialogCapiro(
        user = user,
        onLogoutClick = onLogoutClick,
        onBackUpClick = onBackUpClick,
        onPrintersClick = onPrintersClick,
        isOpenDialog = isMenuOpen.value,
        onCloseDialog = { isMenuOpen.value = false }
    )

    val type = getTypography()
    Column(modifier = Modifier.background(BeigeCapiro), verticalArrangement = Arrangement.Center) {
        Divider(thickness = 1.dp, color = GrayDarkCapiro)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .height(56.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.farm), contentDescription = null)
                Text(
                    text = farm,
                    style = type.headlineMedium,
                    color = GreenCapiro,
                    fontWeight = FontWeight.Normal
                )
            }
            Text(
                text = section,
                style = type.headlineMedium,
                color = GreenCapiro,
                fontWeight = FontWeight.Bold
            )
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clickable { isMenuOpen.value = true },
                contentAlignment = Alignment.Center
            ) {
                val backgroundResource =
                    if (isMenuOpen.value) R.drawable.perfil_hexagono_filled_orange else R.drawable.perfil_hexagono_filled

                Image(
                    modifier = Modifier.size(40.dp),
                    painter = painterResource(id = backgroundResource),
                    contentDescription = null,
                    contentScale = ContentScale.Fit
                )
                Text(text = userAbbreviation, style = type.bodyMedium, color = Color.White)
            }
        }
        Divider(thickness = 1.dp, color = GrayDarkCapiro)
    }
}

@Preview
@Composable
private fun ToolbarPreview() {
    ToolbarCapiro(
        user = "John Doe",
        farm = "SS",
        section = "Section Title",
        userAbbreviation = "JD"
    )
}