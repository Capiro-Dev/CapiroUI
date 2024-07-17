package com.capiro.composables.athomic_composables

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.WhiteCapiro

@Composable
fun CardCapiro(
    innerComposable: @Composable () -> (Unit),
    modifier: Modifier = Modifier,
    paddingInner: Dp = 16.dp) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = Modifier
    )  {
        //Compose to be placed inside the card
        Box (modifier = Modifier.padding(paddingInner)){
            innerComposable()
        }

    }
}

