package com.capiro.composables.dialogs


import TypographyProvider
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Sync
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.capiro.composables.R
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.model.SyncItem
import com.capiro.composables.model.SyncStatus
import com.capiro.composables.theme.GrayCapiro
import com.capiro.composables.theme.GrayDarkCapiro
import com.capiro.composables.theme.GreenCapiro


@Composable
fun SyncDialogCapiro(
    isTheDialogOpen: Boolean,
    syncItems: Array<SyncItem>,
    onCloseDialogClickEvent: () -> Unit,
    amountItemsSyncProcessFinish: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    // SYNC DIALOG
    if (isTheDialogOpen) {
        Dialog(
            properties = DialogProperties(usePlatformDefaultWidth= false),
            onDismissRequest = { onCloseDialogClickEvent()},
            content = {
                SyncDialogLayout(
                    modifier = modifier,
                    syncItems = syncItems,
                    onCloseDialogClickEvent = onCloseDialogClickEvent,
                    amountItemsSyncProcessFinish = amountItemsSyncProcessFinish
                )
            }
        )
    }

}

@Composable
private fun SyncDialogLayout(
    syncItems: Array<SyncItem>,
    onCloseDialogClickEvent: () -> Unit,
    amountItemsSyncProcessFinish: MutableState<Int>,
    modifier: Modifier
) {

    Column(
        modifier
            .fillMaxSize()
            .background(color = White, RoundedCornerShape(5)),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // title
        Text(
            text = stringResource(id = R.string.general_lb_sync),
            style = TypographyProvider.typography.bodyMedium,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            color = GreenCapiro
        )


        // sync list fields
        Column(
            modifier = Modifier
                .padding(top = 24.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            SyncDataList(syncItems = syncItems)
        }

        // close button
        CloseButton(
            onCloseDialogClickEvent = onCloseDialogClickEvent,
            syncItems = syncItems,
            amountItemsSyncProcessFinish = amountItemsSyncProcessFinish
        )
    }
}


@Composable
private fun SyncDataList(syncItems: Array<SyncItem>) {

    // items list
    for (item in syncItems.indices)
        UtilRow(
            label = stringResource(syncItems[item].messageDisplayed.value),
            currentState = syncItems[item].syncState
        )

}


@Composable
private fun CloseButton(
    onCloseDialogClickEvent: () -> Unit,
    syncItems: Array<SyncItem>,
    amountItemsSyncProcessFinish: MutableState<Int>
) {

    val syncFinish = amountItemsSyncProcessFinish.value >= syncItems.size - 1

    Row(
        modifier = Modifier
            .padding(vertical = 16.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(bottom = 16.dp)
            .padding(horizontal = 16.dp),
        horizontalArrangement = Arrangement.End,
    ) {

        // button
        ButtonCapiro(
            text = stringResource(id = R.string.dialog_sync_bt_close),
            onClick = { onCloseDialogClickEvent() },
            isEnabled = syncFinish,
            background = if (syncFinish) GreenCapiro else GrayCapiro
        )
    }

}


@Composable
private fun UtilRow(label: String, currentState: State<SyncStatus>) {

    Column {


        Row(modifier = Modifier.fillMaxWidth()) {

            Spacer(modifier = Modifier.size(32.dp))

            // sync field title
            Row(
                modifier = Modifier
                    .weight(0.8f)
                    .align(Alignment.CenterVertically),
                horizontalArrangement = Arrangement.Center
            ) {

                Text(
                    text = label,
                    color = GreenCapiro,
                    maxLines = 2,
                    style = TypographyProvider.typography.bodyMedium,
                    fontWeight = FontWeight.Normal
                )
            }


            // sync field icon status
            Row(
                modifier = Modifier
                    .padding(end = 8.dp)
                    .weight(0.2f),
                horizontalArrangement = Arrangement.End,
                verticalAlignment = Alignment.CenterVertically
            ) {

                when (currentState.value) {

                    // error
                    is SyncStatus.Error -> ImageCapiro(
                        imageSourceId = R.drawable.error,
                        modifier = Modifier.size(32.dp)
                    )

                    // done
                    is SyncStatus.IsDone -> ImageCapiro(
                        imageSourceId = R.drawable.done,
                        modifier = Modifier.size(32.dp)
                    )

                    // loading
                    is SyncStatus.IsLoading -> CircularProgressIndicator(
                        color = GreenCapiro,
                        modifier = Modifier
                            .size(32.dp)
                    )

                }
            }
        }

    }
}


@Preview
@Composable
private fun SyncDialogPreview() {

    val message1 = remember { mutableIntStateOf(R.string.dialog_sync_bt_done) }
    val message2 = remember { mutableIntStateOf(R.string.dialog_sync_bt_processing) }
    val message3 = remember { mutableIntStateOf(R.string.dialog_sync_error) }

    var syncItems = arrayOf(
        SyncItem(
            messageDisplayed = message1,
            syncState = remember { mutableStateOf(SyncStatus.IsLoading())},
        ),
        SyncItem(
            messageDisplayed = message2,
            syncState = remember { mutableStateOf(SyncStatus.Error())},
        ),
        SyncItem(
            messageDisplayed = message3,
            syncState = remember { mutableStateOf(SyncStatus.IsDone())},
        )
    )

    SyncDialogCapiro(
        modifier = Modifier.padding(40.dp),
        isTheDialogOpen = true,
        syncItems = syncItems,
        onCloseDialogClickEvent = {},
        amountItemsSyncProcessFinish = remember { mutableStateOf(3)}
    )
}