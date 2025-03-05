import android.widget.Toast
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.capiro.capiroui.R
import com.capiro.composables.athomic_composables.spinners.SpinnerButtonCapiro
import com.capiro.composables.athomic_composables.spinners.SpinnerButtonUnderlined
import com.capiro.composables.athomic_composables.spinners.SpinnerDropdownCapiro
import com.capiro.composables.athomic_composables.spinners.SpinnerDropdownUnderlined
import com.capiro.composables.athomic_composables.textfield.TextFieldCapiro
import com.capiro.composables.dialogs.ListTextFieldDialogCapiro
import com.capiro.composables.theme.BeigeCapiro

@Preview(showBackground = true)
@Composable
fun TestSpinners() {
    val context = LocalContext.current

    val selectedItemUnderlined1 = remember { mutableStateOf("Selecciona ...") }
    val selectedItemUnderlined2 = remember { mutableStateOf("item 1") }
    val isDialogOpenState = remember { mutableStateOf(false) }
    val dataList = listOf(
        "10",
        "20",
        "30",
        "40",
        "10",
        "20",
        "30",
        "40",
        "10",
        "20",
        "30",
        "40",
        "10",
        "20",
        "30",
        "40"
    )
    val dialogText = remember { mutableStateOf("") }


    ListTextFieldDialogCapiro(
        modifier = Modifier.padding(vertical = 68.dp),
        titleIdRes = R.string.longitude,
        isDialogOpenState = isDialogOpenState.value,
        dataList = dataList,
        text = dialogText.value,
        onSelectionChanged = { dialogText.value = it },
        onClose = { isDialogOpenState.value = false },
        onAccept = {
            isDialogOpenState.value = false
            },
        units = "cm"
    )


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = BeigeCapiro)
            .padding(16.dp), verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SpinnerButtonCapiro(
            labelResourceId = R.string.block,
            itemSelected = "00 A",
            onClick = { isDialogOpenState.value = true
                Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )

        SpinnerButtonCapiro(
            labelResourceId = R.string.bed,
            itemSelected = "",
            onClick = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )

        SpinnerButtonCapiro(
            labelResourceId = R.string.variety,
            itemSelected = "Alma",
            onClick = { Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show() }
        )



        Spacer(modifier = Modifier.padding(8.dp))

        Row(modifier = Modifier.fillMaxWidth()) {

            Box(modifier = Modifier.weight(1f)) {
                SpinnerDropdownUnderlined(
                    items = listOf("21", "22", "13"),
                    labelResourceId = R.string.type,
                    selectedItem = selectedItemUnderlined1.value,
                    onItemSelectedChange = { selectedItemUnderlined1.value = it },
                    isErrorActive = true
                )

            }

            Spacer(modifier = Modifier.size(16.dp))

            Box(modifier = Modifier.weight(1f)) {
                val text = remember { mutableStateOf("Selecciona...") }
                    TextFieldCapiro(
                        textInput = text.value,
                        label = "Label",
                        onTextChangeEvent = { text.value = it },
                        isError = true
                    )
            }
        }

        Box(modifier = Modifier.fillMaxWidth()) {
            SpinnerDropdownCapiro(
                items = listOf("Alhambra - AF", "Bochica - BC", "Capri - CC", "San Sebastían - SS", "Bouquetera - BQ", "Valley VF", "Plantas Madres - PM"),
                selectedItem = selectedItemUnderlined2.value,
                onItemSelect = { selectedItemUnderlined2.value = it },
                imageResourceId = com.capiro.composables.R.drawable.farm_secondary

            )
        }

    }
}