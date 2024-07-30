package com.capiro.composables.buttons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.capiro.composables.athomic_composables.ButtonCapiro
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro

@Composable
fun SwitchButtonCapiro(tabLabel0: String, tabLabel1: String,selectedTab : MutableState<Int> = remember { mutableStateOf(0) }){

    var zIndex0=1f
    var zIndex1=0f
    var tabBackgroundColor0=GreenCapiro
    var tabFontColor0=  WhiteCapiro

    var tabBackgroundColor1= GrayCapiro
    var tabFontColor1= GrayDarkCapiro


    if(selectedTab.value== 1){
        zIndex0=0f
        zIndex1=1f
        tabBackgroundColor0= GrayCapiro
        tabFontColor0=  GrayDarkCapiro

        tabBackgroundColor1= GreenCapiro
        tabFontColor1= WhiteCapiro
    }

    Row(modifier = Modifier.padding(4.dp).shadow(6.dp, shape = RoundedCornerShape(30)), horizontalArrangement = Arrangement.spacedBy((-20).dp)) {
            ButtonCapiro(text = tabLabel0, onClick = { selectedTab.value = 0 }, background  =tabBackgroundColor0, fontColor = tabFontColor0, modifier = Modifier.zIndex(zIndex0).wrapContentWidth())

            ButtonCapiro(text = tabLabel1, onClick = { selectedTab.value = 1 }, background =tabBackgroundColor1, fontColor = tabFontColor1, modifier = Modifier.zIndex(zIndex1).padding(start = 10.dp).wrapContentWidth())

    }
}