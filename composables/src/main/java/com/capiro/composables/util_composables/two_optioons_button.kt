package com.capiro.composables.util_composables

import TypographyProvider
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro

@Composable
fun TwoOptionsButton(
    text1: String,
    text2: String,
    onClick1: () -> Unit,
    onClick2: () -> Unit,
    width: Dp = 200.dp
) {

    val fontProvider = TypographyProvider.typography.bodyMedium
    var isFirstSelected by remember { mutableStateOf(true) }
    val background1 = if (isFirstSelected) GreenCapiro else GrayCapiro
    val background2 = if (isFirstSelected) GrayCapiro else GreenCapiro
    val selectedTextStyle = fontProvider.merge(
        color = WhiteCapiro,
        fontWeight = FontWeight.Bold
    )
    val unselectedTextStyle = fontProvider.merge(
        color = GrayDarkCapiro,
        fontWeight = FontWeight.Normal
    )

    val textStyle1 = if (isFirstSelected) selectedTextStyle else unselectedTextStyle
    val textStyle2 = if (isFirstSelected ) unselectedTextStyle else selectedTextStyle

    val zIndex1 = if (isFirstSelected) 1f else 0f
    val zIndex2 = if (isFirstSelected) 0f else 1f


    Row(horizontalArrangement =  Arrangement.spacedBy((-20).dp)) {
        ButtonCapiro(
            modifier = Modifier
                .width(width)
                .zIndex(zIndex1),
            border = background1,
            text = text1,
            onClick ={
                isFirstSelected = true
                onClick1()
            } ,
            background = background1,
            textStyle = textStyle1
        )
        ButtonCapiro(
            border = background2,
            modifier = Modifier
                .width(width)
                .zIndex(zIndex2),
            text = text2,
            onClick = {
                isFirstSelected = false
                onClick2()
            },
            background = background2,
            textStyle = textStyle2
        )
    }

}

@Composable
@Preview
fun TwoOptionsButtonPreview() {


    Row(modifier = Modifier.fillMaxSize().padding(16.dp), horizontalArrangement = Arrangement.Center){
        TwoOptionsButton(
            text1 = "Option 1",
            text2 = "Option 2",
            onClick1 = {},
            onClick2 = {},
            width=140.dp
        )

    }

}