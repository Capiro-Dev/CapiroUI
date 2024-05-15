package com.capiro.composables.athomic_composables

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.GreenSecondCapiro
import com.capiro.composables.theme.RedCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.capiro.composables.EMPTY
import com.capiro.composables.R
import getTypography


//********************************************
//                  BUTTON
//********************************************
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ButtonCapiro(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    isEnabled: Boolean = true,

    // colors border
    border: Color = GreenCapiro,
    borderPressed: Color = GreenCapiro,
    borderIsNotEnabled: Color = GrayDarkCapiro,

    // colors button
    fontColor: Color = WhiteCapiro,
    fontColorPressed: Color = GreenCapiro,
    fontColorIsNotEnabled: Color = GrayDarkCapiro,

    // colors background
    background: Color = GreenCapiro,
    backgroundPressed: Color = WhiteCapiro,
    backgroundIsNotEnabled: Color = GrayClearCapiro,
) {

    // color state
    var fontColorState by remember { mutableStateOf(fontColor) }
    var backgroundState by remember { mutableStateOf(background) }
    var borderState by remember { mutableStateOf(border) }

    // disable colors
    if (isEnabled.not()) {
        fontColorState = fontColorIsNotEnabled
        backgroundState = backgroundIsNotEnabled
        borderState = borderIsNotEnabled
    }

    // typography
    val typography = getTypography()

    Button(
        modifier = modifier.pointerInteropFilter {

            when (it.action) {
                // when the button is pressed
                MotionEvent.ACTION_DOWN -> {
                    backgroundState = backgroundPressed
                    fontColorState = fontColorPressed
                    borderState = borderPressed
                }

                // when the button is released
                MotionEvent.ACTION_UP -> {
                    backgroundState = background
                    fontColorState = fontColor
                    borderState = border
                }
            }
            true
        },
        shape = RoundedCornerShape(30),
        colors = ButtonDefaults.buttonColors(containerColor = backgroundState),
        enabled = isEnabled,
        border = BorderStroke(2.dp, borderState),
        onClick = { onClick() }
    ) {

        // text
        Text(
            text = text,
            color = fontColorState,
            fontWeight = FontWeight.Bold,
            style = typography.bodyMedium,
        )

    }
}

@Preview
@Composable
private fun ButtonCapiroPreview() {
    Box (modifier=Modifier.padding(8.dp)){
        ButtonCapiro(text = "Button", onClick = { /*TODO*/ })
    }
}


//********************************************
//                 ICON BUTTON
//********************************************
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ButtonIconCapiro(
    modifier: Modifier = Modifier,
    image: ImageVector,
    onClick: () -> Unit,
    isRounded: Boolean = true,
    label: String? = null,

    // font color
    fontColor: Color = GreenCapiro,

    // icon colors
    iconColor: Color = WhiteCapiro,
    iconColorPressed: Color = GreenCapiro,

    // background colors
    backgroundColor: Color = GreenCapiro,
    backgroundColorPressed: Color = WhiteCapiro,

    // line border colors
    lineBorderColor: Color = GreenCapiro,
    lineBorderColorPressed: Color = GreenCapiro,
) {

    // color state
    var iconColorState by remember { mutableStateOf(iconColor) }
    var backgroundColorState by remember { mutableStateOf(backgroundColor) }
    var lineBorderColorState by remember { mutableStateOf(lineBorderColor) }

    // corner percentage
    val percentageCorner = if (isRounded) 50 else 20

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        IconButton(
            modifier = modifier
                .pointerInteropFilter {

                    when (it.action) {
                        // when the button is pressed
                        MotionEvent.ACTION_DOWN -> {
                            iconColorState = iconColorPressed
                            backgroundColorState = backgroundColorPressed
                            lineBorderColorState = lineBorderColorPressed
                        }

                        // when the button is released
                        MotionEvent.ACTION_UP -> {
                            iconColorState = iconColor
                            backgroundColorState = backgroundColor
                            lineBorderColorState = lineBorderColor
                        }
                    }
                    true
                }
                .clip(RoundedCornerShape(percentageCorner))
                .background(backgroundColorState)
                .border(2.dp, lineBorderColorState, RoundedCornerShape(percentageCorner)),
            onClick = { onClick() },
        ) {
            // icon
            Icon(
                modifier = modifier.scale(1.4f),
                imageVector = image,
                contentDescription = null,
                tint = iconColorState
            )
        }

        Spacer(modifier = Modifier.size(8.dp))

        // label
        label?.let {
            Text(
                text = it,
                style = getTypography().bodySmall,
                color = fontColor,
            )
        }
    }
}


@Preview
@Composable
private fun ButtonIconCustomPreview() {
    Box(modifier = Modifier.padding(16.dp)) {
        ButtonIconCapiro(
            image = Icons.Filled.AccountBox,
            onClick = { /*TODO*/ },
            isRounded = false,
            label = "Button"
        )
    }
}


//********************************************
//               BUTTON SPINNER
//********************************************
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ButtonSpinnerCapiro(
    textInput: String,
    label: String,
    onClickEvent: () -> Unit,
    isEnabled: Boolean = true,
) {

    val interactionSource = remember { MutableInteractionSource() }
    val typography = getTypography()



    Box(modifier = Modifier.clickable { if (isEnabled) onClickEvent() }) {

        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = textInput,
            enabled = false,
            textStyle = typography.bodyMedium.copy(textAlign = TextAlign.Center),
            singleLine = true,
            interactionSource = interactionSource,
            cursorBrush = SolidColor(Color.White),
            onValueChange = { }
        ) { innerTextField ->
            OutlinedTextFieldDefaults.DecorationBox(
                value = textInput,
                innerTextField = innerTextField,
                enabled = false,
                singleLine = true,
                interactionSource = interactionSource,
                visualTransformation = VisualTransformation.None,
                trailingIcon = {
                    // arrow icon
                    if (isEnabled) {
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = null,
                            tint = GreenCapiro,
                            modifier = Modifier.scale(1.5f)
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Filled.Lock,
                            contentDescription = null,
                            tint = RedCapiro,
                            modifier = Modifier.scale(1.2f)
                        )
                    }
                },
                label = {
                    Text(
                        text = label,
                        color = if (textInput.isEmpty() || textInput == EMPTY || !isEnabled) GrayDarkCapiro else GreenSecondCapiro,
                        style = typography.labelSmall,
                    )
                },
                container = {
                    OutlinedTextFieldDefaults.ContainerBox(
                        enabled = false,
                        isError = false,
                        interactionSource = interactionSource,
                        colors = OutlinedTextFieldDefaults.colors(
                            disabledTextColor = if (isEnabled) GreenCapiro else GrayDarkCapiro,
                            disabledContainerColor = if (isEnabled) WhiteCapiro else GrayClearCapiro,
                            disabledBorderColor = if (isEnabled) GreenCapiro else GrayDarkCapiro,
                        ),
                        focusedBorderThickness = 3.dp,
                        unfocusedBorderThickness = 3.dp
                    )
                }
            )
        }

    }

}

@Preview
@Composable
private fun ButtonSpinnerCapiroPreview() {
    Box(Modifier.padding(16.dp)) {
        ButtonSpinnerCapiro(
            textInput = "Text",
            label = "Label",
            onClickEvent = { /*TODO*/ },
            isEnabled = false
        )
    }
}


//********************************************
//               BUTTON IMAGE
//********************************************
@Composable
fun ButtonImageCapiro(
    @DrawableRes imageRes: Int,
    onClick: () -> Unit,
    size: Dp,
    modifier: Modifier = Modifier,
    label: String? = null,
    color: Color = WhiteCapiro,
    borderColor: Color = GreenCapiro,
    isEnabled: Boolean = true
) {

    val borderColor = if (isEnabled) borderColor else GrayDarkCapiro
    val textColor = if (isEnabled) GreenCapiro else GrayDarkCapiro
    val cardElevation = 8.dp

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Card(
            modifier = Modifier.size(size),
            border = BorderStroke(2.dp, borderColor),
            elevation = CardDefaults.cardElevation(cardElevation)
        ) {

            Box(modifier = Modifier.size(size), contentAlignment = Alignment.TopEnd) {

                IconButton(
                    onClick = { if (isEnabled) onClick() },
                    enabled = isEnabled,
                    modifier = modifier.size(size)
                ) {
                    Image(
                        painter = painterResource(imageRes),
                        contentDescription = null,
                        modifier = modifier
                            .fillMaxSize()
                            .border(width = 2.dp, shape = RoundedCornerShape(20), color = color)
                            .clip(RoundedCornerShape(20))
                            .scale(0.8F)
                    )
                }


                if (isEnabled.not()) {
                    Icon(
                        imageVector = Icons.Filled.Lock,
                        contentDescription = null,
                        tint = RedCapiro,
                        modifier = Modifier.scale(0.8f)
                    )
                    Spacer(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(color = GrayDarkCapiro.copy(alpha = 0.5F))
                    )

                }
            }

        }

        if (label != null) {
            Text(
                text = label,
                color = textColor,
                style = getTypography().bodySmall,
            )
        }
    }

}

@Preview
@Composable
private fun ButtonImageCapiroPreview() {
    Box(Modifier.padding(16.dp)) {
        ButtonImageCapiro(
            imageRes = R.drawable.icon,
            label = "Button",
            onClick = { /*TODO*/ },
            size = 50.dp,
            isEnabled = true
        )
    }
}



