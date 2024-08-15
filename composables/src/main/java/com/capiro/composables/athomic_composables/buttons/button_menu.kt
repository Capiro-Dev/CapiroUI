package com.capiro.composables.athomic_composables.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Block
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.textfield.TextFieldCapiro
import com.capiro.composables.model.MenuItemCapiro
import com.capiro.composables.theme.BeigeCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.RedCapiro

@Composable
fun ButtonMenu(
    menuItem: MenuItemCapiro,
    size: Dp,
    isEnabled: Boolean = true,
    borderColor: Color = Transparent

) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(vertical = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box {


            Image(
                modifier = Modifier
                    .shadow(4.dp, shape = RoundedCornerShape(20))
                    .background(color = menuItem.colorBack, shape = RoundedCornerShape(20))
                    .border( BorderStroke(2.dp, borderColor))
                    .size(size)
                    .padding(8.dp)
                    .clickable {
                        menuItem.execute()
                    },
                painter = painterResource(id = menuItem.image),
                contentDescription = null,
            )


            if (!isEnabled)
                Row(
                    modifier = Modifier
                        .size(size)
                        .background(color = GrayDarkCapiro, RoundedCornerShape(20)),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.Top
                ) {
                    Icon(
                        imageVector = Icons.Filled.Block,
                        contentDescription = null,
                        modifier = Modifier.size(32.dp),
                        tint = RedCapiro
                    )
                }
        }


        Text(
            text = menuItem.title,
            style = TypographyProvider.typography.bodySmall,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
        )
    }
}


@Preview
@Composable
private fun ButtonMenuPreview() {
    Box(modifier = Modifier.padding(8.dp)) {
        ButtonMenu(
            menuItem = MenuItemCapiro(
                image = R.drawable.flower,
                title = "Button",
                borderColor = GreenCapiro,
                colorBack = BeigeCapiro,
                execute = {  },
            ),
            size = 64.dp,
            isEnabled = false
        )
    }
}
