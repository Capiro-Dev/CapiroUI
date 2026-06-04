package com.capiro.composables.util_composables

import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capiro.composables.R
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography


/**
 * Displays a dialog with a toolbar menu layout.
 *
 * @param user The user name to display in the toolbar.
 * @param onLogoutClick A lambda function to be invoked when the logout action is triggered. If `null`, the logout option will not be displayed.
 * @param onBackUpClick A lambda function to be invoked when the backup action is triggered. If `null`, the backup option will not be displayed.
 * @param onPrintersClick A lambda function to be invoked when the printers action is triggered. If `null`, the printers option will not be displayed.
 * @param isOpenDialog A boolean indicating whether the dialog should be open or closed.
 * @param onCloseDialog A lambda function to be invoked when the dialog is closed.
 */
@Composable
fun ToolbarMenuLayoutDialogCapiro(
    user: String,
    onLogoutClick: (() -> Unit)? = null,
    onBackUpClick: (() -> Unit)? = null,
    onPrintersClick: (() -> Unit)? = null,
    isOpenDialog: Boolean,
    onCloseDialog: () -> Unit = {}
) {
    if (isOpenDialog) {
        Dialog(
            onDismissRequest = onCloseDialog,
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable {
                        onCloseDialog()
                        Log.wtf("ToolbarMenuLayoutDialogCapiro", "clickable")
                    }
                    .padding(top = 42.dp)
                    .background(Color.Transparent),
                contentAlignment = Alignment.TopEnd
            ) {
                ToolbarMenuLayout(
                    user = user,
                    onLogoutClick = onLogoutClick,
                    onBackUpClick = onBackUpClick,
                    onPrintersClick = onPrintersClick
                )
            }
        }
    }
}

/**
 * Displays the layout for the toolbar menu within the dialog.
 *
 * @param user The user name to display.
 * @param onLogoutClick A lambda function to be invoked when the logout action is triggered. If `null`, the logout option will not be displayed.
 * @param onBackUpClick A lambda function to be invoked when the backup action is triggered. If `null`, the backup option will not be displayed.
 * @param onPrintersClick A lambda function to be invoked when the printers action is triggered. If `null`, the printers option will not be displayed.
 */
@Composable
private fun ToolbarMenuLayout(
    user: String,
    onLogoutClick: (() -> Unit)? = null,
    onBackUpClick: (() -> Unit)? = null,
    onPrintersClick: (() -> Unit)? = null
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
            .padding(16.dp)
            .width(260.dp)
    ) {
        Column {
            CurrentAccountMenuTopBar { onLogoutClick?.invoke() }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                IconUser(user = user)

                Spacer(modifier = Modifier.size(16.dp))

                CurrentAccountMenuOptions(
                    user = user,
                    onBackUpClick = onBackUpClick,
                    onPrintersClick = onPrintersClick
                )
            }

            Spacer(modifier = Modifier.size(8.dp))
        }
    }
}

/**
 * Displays a user icon with initials.
 *
 * @param user The user name to generate initials for and display.
 */
@Composable
private fun IconUser(user: String) {
    val userInitials = remember { user.take(2).uppercase() }
    val typo = getTypography()

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.perfil_hexagono),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Text(text = userInitials, color = GreenCapiro, style = typo.titleLarge, fontSize = 30.sp)
    }
}

/**
 * Displays menu options for the current account.
 *
 * @param user The user name to display.
 * @param onBackUpClick A lambda function to be invoked when the backup action is triggered. If `null`, the backup option will not be displayed.
 * @param onPrintersClick A lambda function to be invoked when the printers action is triggered. If `null`, the printers option will not be displayed.
 */
@Composable
private fun CurrentAccountMenuOptions(
    user: String,
    onBackUpClick: (() -> Unit)? = null,
    onPrintersClick: (() -> Unit)? = null
) {
    val typo = getTypography()

    Column(
        modifier = Modifier.heightIn(min = 60.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = user,
            style = typo.bodySmall,
            color = GreenCapiro,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1
        )

        if (onBackUpClick != null) {
            Text(
                modifier = Modifier.clickable { onBackUpClick() },
                text = stringResource(id = R.string.general_bt_backUp),
                style = typo.bodySmall,
                color = GreenSecondCapiro,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }

        if (onPrintersClick != null) {
            Text(
                modifier = Modifier.clickable { onPrintersClick() },
                text = stringResource(id = R.string.general_bt_printers),
                style = typo.bodySmall,
                color = GreenSecondCapiro,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )
        }
    }
}

/**
 * Displays the top bar of the current account menu.
 *
 * @param onCloseSession A lambda function to be invoked when the close session action is triggered.
 */
@Composable
fun CurrentAccountMenuTopBar(onCloseSession: () -> Unit) {
    val typography = getTypography()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                modifier = Modifier.weight(1.15f),
                text = stringResource(R.string.cappiro_name),
                style = typography.bodySmall,
                color = GreenCapiro,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

            Text(
                modifier = Modifier
                    .clickable { onCloseSession() }
                    .weight(0.95f),
                text = stringResource(R.string.general_bt_closeSesion),
                style = typography.bodySmall,
                color = GreenCapiro,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.End
            )
        }

        Divider(modifier = Modifier.fillMaxWidth(), color = Color.DarkGray, thickness = 0.5.dp)
    }
}

@Preview
@Composable
private fun ToolbarMenuLayoutDialogCapiroPreview() {
    ToolbarMenuLayoutDialogCapiro(
        user = "User Name",
        isOpenDialog = true,
        onCloseDialog = {}
    )
}

@Preview
@Composable
private fun ToolbarMenuLayoutPreview() {
    ToolbarMenuLayout(
        user = "User Name",
        onLogoutClick = {},
        onBackUpClick = {},
        onPrintersClick = {}
    )
}

@Preview
@Composable
private fun IconUserPreview() {
    IconUser(user = "User Name")
}

@Preview
@Composable
private fun CurrentAccountMenuOptionsPreview() {
    CurrentAccountMenuOptions(
        user = "User Name",
        onBackUpClick = {},
        onPrintersClick = {}
    )
}

@Preview
@Composable
private fun CurrentAccountMenuTopBarPreview() {
    CurrentAccountMenuTopBar(onCloseSession = {})
}