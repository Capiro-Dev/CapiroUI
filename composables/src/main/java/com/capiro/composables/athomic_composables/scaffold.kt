package com.capiro.capiroui.athomic_composables


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Storage
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.R


@Composable
fun ScaffoldCapiro(
    user: String?,
    farm: String?,
    onLogOutClickEvent: (() -> Unit)?,
    onBackUpClickEvent: (() -> Unit)?,
    colorIcon1: Color = GreenCapiro,
    colorIcon2: Color = GreenCapiro,
    colorIcon3: Color = GreenCapiro,
    colorIcon4: Color = GreenCapiro
) {
    Column(
        Modifier
            .wrapContentHeight()
            .background(color = MaterialTheme.colorScheme.onPrimary)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 0.dp)
                .fillMaxWidth()
                .height(48.dp)
        ) {


            // FARM
            if (farm != null)
                IconWithTitleReusable(
                    iconImage = Icons.Filled.LocationOn,
                    title = farm,
                    color = colorIcon1
                )

            // USER
            if (user != null)
                IconWithTitleReusable(
                    iconImage = Icons.Filled.Person,
                    title = user,
                    color = colorIcon2
                )

            // BACK UP
            if (onBackUpClickEvent != null)
                IconButtonScaffold(
                    label = stringResource(R.string.scaffold_backup),
                    image = Icons.Filled.Storage,
                    onClickEvent = onBackUpClickEvent,
                    color = colorIcon3
                )
            // LOGOUT
            if (onLogOutClickEvent != null)
                IconButtonScaffold(
                    label = stringResource(R.string.scaffold_lb_logout),
                    image = Icons.Filled.Logout,
                    onClickEvent = onLogOutClickEvent,
                    color = colorIcon4
                )
        }
        //Menu divider
        Divider(color = GreenCapiro, thickness = 3.dp)
    }

}


@Composable
private fun IconButtonScaffold(
    label: String?,
    image: ImageVector,
    onClickEvent: () -> Unit,
    color: Color = GreenCapiro
) {
    // PDA DESIGN ************
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {

        // icon
        Icon(
            modifier = Modifier.clickable { onClickEvent() },
            imageVector = image,
            contentDescription = null,
            tint = color
        )


        Text(text = label!!, color = GrayDarkCapiro, style = MaterialTheme.typography.bodySmall)
    }

}


@Composable
private fun IconWithTitleReusable(
    iconImage: ImageVector,
    title: String,
    color: Color = GreenCapiro
) {


    // PDA DESIGN ************
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 4.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        // icon
        Icon(
            modifier = Modifier,
            imageVector = iconImage,
            contentDescription = null,
            tint = color
        )
        // title displayed aside the icon
        Text(text = title, color = GrayDarkCapiro, style = MaterialTheme.typography.bodySmall)

    }


}

@Preview
@Composable
private fun ScaffoldCapiroPreview() {
    ScaffoldCapiro(
        user = "User",
        farm = "Farm",
        onLogOutClickEvent = { },
        onBackUpClickEvent = { }
    )
}