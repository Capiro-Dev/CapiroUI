package com.capiro.composables.util_composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capiro.composables.R
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import getTypography

@Composable
fun CurrentAccountMenu(
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
                    onPrintersClick= onPrintersClick
                )

            }

            Spacer(modifier = Modifier.size(8.dp))


        }
    }
}


@Composable
private fun IconUser(user: String) {
    val user = remember { user.substring(0, 2).uppercase() }
    val typo = getTypography()

    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.perfil_hexagono),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )

        Text(text = user, color = GreenCapiro, style = typo.titleLarge, fontSize = 30.sp)
    }
}

@Composable
private fun CurrentAccountMenuOptions(
    user: String,
    onBackUpClick: (() -> Unit)? = null,
    onPrintersClick: (() -> Unit)? = null,

    ) {

    val typo = getTypography()

    Column(modifier=Modifier.heightIn(min = 60.dp), verticalArrangement = Arrangement.SpaceEvenly) {

        // user
        Text(
            text = user,
            style = typo.bodySmall,
            color = GreenCapiro,
            fontWeight = FontWeight.Bold,
            overflow = TextOverflow.Ellipsis,
            maxLines = 1

        )

        // backup
        if (onBackUpClick != null)
            Text(
                modifier = Modifier.clickable { onBackUpClick?.invoke() },
                text = stringResource(id = R.string.general_bt_backUp),
                style = typo.bodySmall,
                color = GreenSecondCapiro,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )

        if (onPrintersClick != null)
            Text(
                modifier = Modifier.clickable { onPrintersClick?.invoke() },
                text = stringResource(id = R.string.general_bt_printers),
                style = typo.bodySmall,
                color = GreenSecondCapiro,
                textDecoration = TextDecoration.Underline,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1

            )
    }
}


@Composable
fun CurrentAccountMenuTopBar(
    onCloseSession: () -> Unit
) {

    val typography = getTypography()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 0.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            // close session
            Text(
                modifier = Modifier.weight(1.15f),
                text = stringResource(R.string.cappiro_name),
                style = typography.bodySmall,
                color = GreenCapiro,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1
            )


            // company name
            Text(
                modifier = Modifier.clickable { onCloseSession() }.weight(0.95f),
                text = stringResource(R.string.general_bt_closeSesion),
                style = typography.bodySmall,
                color = GreenCapiro,
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                textAlign = TextAlign.End
            )


        }

        Divider(modifier = Modifier.fillMaxWidth(), color = Color.DarkGray, thickness = 1.dp)

    }


}
