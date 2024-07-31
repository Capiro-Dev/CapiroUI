package com.capiro.composables.athomic_composables.card

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * A composable function that provides a customizable card layout with specified content.
 *
 * @param innerComposable A composable lambda function that defines the content to be displayed inside the card.
 * This allows you to pass any composable content, such as text, images, or other UI elements, to be rendered within the card.
 *
 * @param modifier An optional [Modifier] that can be used to customize the card's layout behavior or appearance.
 * By default, it is set to [Modifier] which means no additional modifiers are applied unless specified.
 *
 * @param paddingInner The padding applied inside the card around the content. Default is 16.dp.
 * This padding ensures that the content does not touch the edges of the card and has some spacing around it.
 * The card has rounded corners, an elevation effect, and a white background color.
 */
@Composable
fun CardCapiro(
    innerComposable: @Composable () -> (Unit),
    modifier: Modifier = Modifier,
    paddingInner: Dp = 16.dp) {

    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.elevatedCardElevation(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
    )  {
        //Compose to be placed inside the card
        Box (modifier = Modifier.padding(paddingInner)){
            innerComposable()
        }

    }
}

