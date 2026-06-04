package com.capiro.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonFabCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonIconCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonImageCapiro
import com.capiro.composables.athomic_composables.buttons.ButtonMenu
import com.capiro.composables.athomic_composables.card.CardCapiro
import com.capiro.composables.athomic_composables.checkbox.CheckBoxAndTextCapiro
import com.capiro.composables.athomic_composables.checkbox.CheckBoxCapiro
import com.capiro.composables.athomic_composables.image.ImageCapiro
import com.capiro.composables.athomic_composables.photohexagon.PolygonCapiro
import com.capiro.composables.athomic_composables.radiobutton.RadioButtonCapiro
import com.capiro.composables.athomic_composables.radiobutton.RadioButtonTextCapiro
import com.capiro.composables.athomic_composables.spinners.SpinnerButtonCapiro
import com.capiro.composables.athomic_composables.spinners.SpinnerButtonUnderlined
import com.capiro.composables.athomic_composables.spinners.SpinnerDropdownCapiro
import com.capiro.composables.athomic_composables.spinners.SpinnerDropdownUnderlined
import com.capiro.composables.athomic_composables.switchcapiro.SwitchCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldAndTileRoundedBorderCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldOutlinedCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldRoundedBorderCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldSolidBackground
import com.capiro.composables.dialogs.CustomDatePicker
import com.capiro.composables.dialogs.DialogHistoryCapiro
import com.capiro.composables.dialogs.ListTextFieldDialogCapiro
import com.capiro.composables.dialogs.LoadingDialog
import com.capiro.composables.dialogs.OneOptionDialogCapiro
import com.capiro.composables.dialogs.RadioButtonDialogCapiro
import com.capiro.composables.dialogs.SearchListDialogCapiro
import com.capiro.composables.dialogs.SimpleDialogHistoryCapiro
import com.capiro.composables.dialogs.SimpleListDialogsCapiro
import com.capiro.composables.dialogs.SyncDialogCapiro
import com.capiro.composables.dialogs.TwoOptionsDialogCapiro
import com.capiro.composables.dropdown.SimpleDropDownUnderlined
import com.capiro.composables.dropdown.SimpleDropdownTextCapiro
import com.capiro.composables.model.MenuItemCapiro
import com.capiro.composables.model.SyncItem
import com.capiro.composables.model.SyncStatus
import com.capiro.composables.theme.GrayClearCapiro
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.WhiteCapiro
import com.capiro.composables.toolbar.ToolbarCapiro
import com.capiro.composables.util_composables.CalendarBarCapiro
import com.capiro.composables.util_composables.CurrentAccountMenuTopBar
import com.capiro.composables.util_composables.DialogTitle
import com.capiro.composables.util_composables.DialogTitle2
import com.capiro.composables.util_composables.ItemExpandableCapiro
import com.capiro.composables.util_composables.ItemManualCapiro
import com.capiro.composables.util_composables.ItemScannerCapiro
import com.capiro.composables.util_composables.LabelManualCapiro
import com.capiro.composables.util_composables.TwoOptionsButtonCapiro
import com.capiro.composables.util_composables.VarietyReport
import com.capiro.composables.util_composables.WeekFilterCapiro
import org.junit.Rule
import org.junit.Test

/**
 * Renders individual library composables to PNG snapshots used for the README documentation.
 * Run `./gradlew :composables:recordPaparazziDebug` to (re)generate the images.
 */
class ScreenshotTest {

    @get:Rule
    val paparazzi = Paparazzi(
        deviceConfig = DeviceConfig.PIXEL_5,
        renderingMode = RenderingMode.SHRINK,
        showSystemUi = false,
    )

    /** Wraps a composable on a neutral background. [width] = null lets the snapshot crop to content. */
    private fun snap(width: Int? = 320, content: @Composable () -> Unit) {
        paparazzi.snapshot {
            var m = Modifier.background(GrayClearCapiro)
            if (width != null) m = m.width(width.dp)
            Box(m.padding(16.dp)) { content() }
        }
    }

    // ---------- Buttons ----------

    @Test fun button_capiro() = snap(width = null) {
        ButtonCapiro(text = "Aceptar", onClick = {})
    }

    @Test fun button_capiro_disabled() = snap(width = null) {
        ButtonCapiro(text = "Aceptar", onClick = {}, isEnabled = false)
    }

    @Test fun button_icon_capiro() = snap(width = null) {
        ButtonIconCapiro(image = Icons.Filled.Search, onClick = {}, label = "Buscar")
    }

    @Test fun button_image_capiro() = snap(width = null) {
        ButtonImageCapiro(imageRes = R.drawable.farm, onClick = {}, size = 72.dp, label = "Finca")
    }

    @Test fun button_fab_capiro() = snap(width = null) {
        Box(Modifier.size(150.dp)) {
            ButtonFabCapiro(
                imageRes1 = R.drawable.fab,
                onClick1 = {},
                innerPadding = 0.dp,
                iconSize = 56.dp,
            )
        }
    }

    @Test fun button_menu() = snap(width = null) {
        ButtonMenu(
            menuItem = MenuItemCapiro(
                image = R.drawable.farm,
                title = "Fincas",
                borderColor = GreenCapiro,
                colorBack = WhiteCapiro,
                execute = {},
            ),
            size = 120.dp,
        )
    }

    // ---------- Text fields ----------

    @Test fun text_field_capiro() = snap {
        TextFieldCapiro(textInput = "123", label = "Cantidad", onTextChangeEvent = {})
    }

    @Test fun text_field_outlined_capiro() = snap {
        TextFieldOutlinedCapiro(textInput = "Capiro", label = "Usuario", onTextChangeEvent = {})
    }

    @Test fun text_field_rounded_border_capiro() = snap {
        TextFieldRoundedBorderCapiro(textInput = "Capiro", onTextChangeEvent = {}, label = "Usuario")
    }

    @Test fun text_field_and_tile_rounded_border_capiro() = snap {
        TextFieldAndTileRoundedBorderCapiro(
            textInput = "Capiro",
            onTextChangeEvent = {},
            label = "Usuario",
            title = "Usuario",
        )
    }

    @Test fun text_field_solid_background() = snap {
        TextFieldSolidBackground(text = "Buscar...", onTextChanged = {})
    }

    // ---------- Spinners & dropdowns ----------

    @Test fun spinner_button_capiro() = snap {
        SpinnerButtonCapiro(itemSelected = "Finca 1", labelResourceId = R.string.login_tt_user_label, onClick = {})
    }

    @Test fun spinner_button_underlined() = snap {
        SpinnerButtonUnderlined(labelResourceId = R.string.login_tt_user_label, selectedItem = "Finca 1", onClick = {})
    }

    @Test fun spinner_dropdown_capiro() = snap {
        SpinnerDropdownCapiro(
            imageResourceId = R.drawable.farm,
            items = listOf("Finca 1", "Finca 2"),
            selectedItem = "Finca 1",
            onItemSelect = {},
        )
    }

    @Test fun spinner_dropdown_underlined() = snap {
        SpinnerDropdownUnderlined(
            items = listOf("Finca 1", "Finca 2"),
            selectedItem = "Finca 1",
            onItemSelectedChange = {},
            labelResourceId = R.string.login_tt_user_label,
        )
    }

    @Test fun simple_dropdown_text_capiro() = snap {
        SimpleDropdownTextCapiro(
            items = listOf("1", "2", "3"),
            selectedItem = "1",
            onItemSelect = {},
            labelResourceId = R.string.login_tt_user_label,
        )
    }

    @Test fun simple_dropdown_underlined() = snap {
        SimpleDropDownUnderlined(labelResourceId = R.string.login_tt_user_label, selectedItem = "1", onClick = {})
    }

    // ---------- Toggles & atoms ----------

    @Test fun check_box_capiro() = snap(width = null) {
        CheckBoxCapiro(isChecked = true, onCheckedChange = {})
    }

    @Test fun check_box_and_text_capiro() = snap(width = null) {
        CheckBoxAndTextCapiro(isChecked = true, onSelectedItemChange = {}, text = "Acepto los términos")
    }

    @Test fun radio_button_capiro() = snap(width = null) {
        RadioButtonCapiro(selection = "A", identifier = "A", onClick = {})
    }

    @Test fun radio_button_text_capiro() = snap(width = null) {
        RadioButtonTextCapiro(selection = "Opción A", identifier = "Opción A", onClick = {})
    }

    @Test fun switch_capiro() = snap(width = null) {
        SwitchCapiro(text = "Activar", isChecked = true, onCheckedChange = {})
    }

    @Test fun card_capiro() = snap {
        CardCapiro(innerComposable = {
            ButtonCapiro(text = "Dentro de una Card", onClick = {})
        })
    }

    @Test fun image_capiro() = snap(width = null) {
        ImageCapiro(imageSourceId = R.drawable.test_app_logo, modifier = Modifier.size(120.dp))
    }

    @Test fun polygon_capiro() = snap(width = null) {
        PolygonCapiro(modifier = Modifier.size(110.dp))
    }

    // ---------- Toolbar & navigation ----------

    @Test fun toolbar_capiro() = snap(width = 380) {
        ToolbarCapiro(
            user = "Danilo Osorio",
            farm = "Flores El Capiro S.A.",
            section = "Inventario",
            userAbbreviation = "DO",
        )
    }

    @Test fun current_account_menu_top_bar() = snap {
        CurrentAccountMenuTopBar(onCloseSession = {})
    }

    @Test fun calendar_bar_capiro() = snap {
        CalendarBarCapiro(day = "04", dayOfWeek = "Jueves", month = "Junio", onCalendarClick = {})
    }

    @Test fun two_options_button_capiro() = snap {
        TwoOptionsButtonCapiro(text1 = "Activos", text2 = "Inactivos", onClick1 = {}, onClick2 = {})
    }

    @Test fun week_filter_capiro() = snap {
        WeekFilterCapiro(
            daysChecked = linkedMapOf(
                "L" to true, "M" to false, "W" to true, "J" to false,
                "V" to true, "S" to false, "D" to false,
            ),
            onDayCheckedChange = { _, _ -> },
        )
    }

    @Test fun dialog_title() = snap {
        DialogTitle(text = "Seleccione una finca", onCloseClick = {})
    }

    @Test fun dialog_title_2() = snap(width = null) {
        DialogTitle2(imageResource = R.drawable.flower)
    }

    // ---------- Items & labels ----------

    @Test fun item_manual_capiro() = snap {
        ItemManualCapiro(
            itemNumber = "01",
            scannedLabel = "A-1234-W45",
            mainComposable = { Text("Variedad Freedom", color = GreenCapiro) },
            onDeleteClick = {},
        )
    }

    @Test fun item_scanner_capiro() = snap {
        ItemScannerCapiro(
            itemNumber = "01",
            scannedLabel = "A-1234-W45",
            mainComposable = { Text("Variedad Freedom", color = GreenCapiro) },
            color = GreenCapiro,
            colorBackground = WhiteCapiro,
        )
    }

    @Test fun item_expandable_capiro() = snap {
        ItemExpandableCapiro(
            itemNumber = "01",
            customer = "Cliente Demo",
            mainComposable = { Text("Resumen del pedido", color = GreenCapiro) },
            listVarieties = listOf(
                VarietyReport(variety = "Freedom", stems = 120, bouquets = 10),
                VarietyReport(variety = "Explorer", stems = 80, bouquets = 8),
            ),
            color = GreenCapiro,
            colorBackground = WhiteCapiro,
        )
    }

    @Test fun label_manual_capiro() = snap {
        LabelManualCapiro(
            textValueA = "A",
            textValueY = "2024",
            textValueW = "45",
            textConsecutive = "001",
            onTextChangeA = {},
            onTextChangeY = {},
            onTextChangeW = {},
            itemsY = listOf("2024", "2025"),
            itemsW = listOf("44", "45"),
            onTextChangeConsecutive = {},
        )
    }

    // ---------- Dialogs ----------

    @Test fun loading_dialog() = snap {
        LoadingDialog(isDialogOpenState = true, title = "Cargando", message = "Por favor espere...", onClose = {})
    }

    @Test fun one_option_dialog_capiro() = snap {
        OneOptionDialogCapiro(
            imaResource = R.drawable.flower,
            boldText = "importante",
            message = "Este es un mensaje importante para el usuario.",
            positiveButtonText = "Aceptar",
            onPositiveButtonClickEvent = {},
            isDialogOpenState = true,
        )
    }

    @Test fun two_options_dialog_capiro() = snap {
        TwoOptionsDialogCapiro(
            imaResource = R.drawable.error,
            message = "¿Desea eliminar el registro?",
            positiveButtonText = "Sí",
            negativeButtonText = "No",
            onPositiveButtonClickEvent = {},
            onNegativeButtonClickEvent = {},
            isDialogOpenState = true,
        )
    }

    @Test fun custom_date_picker() = snap(width = 360) {
        CustomDatePicker(isOpenDialog = true, onDateSelected = {}, onCloseDialog = {})
    }

    @Test fun search_list_dialog_capiro() = snap(width = 360) {
        SearchListDialogCapiro(
            searchText = "",
            onSearchTextChange = {},
            titleIdRes = R.string.list_dialog,
            isTheDialogOpenState = true,
            allData = arrayOf("Finca 1", "Finca 2", "Finca 3"),
            onCleanText = {},
            onSearchItemSelectedChangeState = {},
            onCloseDialogEvent = {},
            item = { Text(it, color = GreenCapiro) },
        )
    }

    @Test fun simple_list_dialogs_capiro() = snap(width = 360) {
        SimpleListDialogsCapiro(
            titleIdRes = R.string.list_dialog,
            isTheDialogOpenState = true,
            allData = arrayOf("Finca 1", "Finca 2"),
            onCloseDialogEvent = {},
            items = { Text(it, color = GreenCapiro) },
        )
    }

    @Test fun radio_button_dialog_capiro() = snap(width = 360) {
        RadioButtonDialogCapiro(
            titleIdRes = R.string.list_dialog,
            isTheDialogOpenState = true,
            allData = arrayOf("Finca 1", "Finca 2"),
            onCloseDialogEvent = {},
            text = "Finca 1",
            onSelectionChanged = {},
        )
    }

    @Test fun simple_dialog_history_capiro() = snap(width = 360) {
        SimpleDialogHistoryCapiro(
            title = "Historial",
            isTheDialogOpenState = true,
            allData = arrayOf("Registro 1", "Registro 2"),
            composableItem = { Text(it, color = GreenCapiro) },
            onCloseDialogEvent = {},
        )
    }

    @Test fun dialog_history_capiro() = snap(width = 360) {
        DialogHistoryCapiro(
            searchText = "",
            onSearchTextChange = {},
            title = "Historial",
            isTheDialogOpenState = true,
            allData = arrayOf("Registro 1", "Registro 2"),
            composableItem = { Text(it, color = GreenCapiro) },
            onCleanText = {},
            onSearchItemSelectedChangeState = {},
            onCloseDialogEvent = {},
            daysState = emptyMap(),
            onDayCheckedChange = { _, _ -> },
        )
    }

    @Test fun list_text_field_dialog_capiro() = snap(width = 360) {
        ListTextFieldDialogCapiro(
            titleIdRes = R.string.list_dialog,
            isDialogOpenState = true,
            dataList = listOf("kg", "g", "lb"),
            text = "kg",
            onSelectionChanged = {},
            onClose = {},
            onAccept = {},
        )
    }

    @Test fun sync_dialog_capiro() = snap(width = 360) {
        SyncDialogCapiro(
            isTheDialogOpen = true,
            syncItems = arrayOf(
                SyncItem(mutableStateOf(R.string.general_lb_sync), mutableStateOf(SyncStatus.IsDone())),
                SyncItem(mutableStateOf(R.string.dialog_sync_bt_processing), mutableStateOf(SyncStatus.IsLoading())),
            ),
            onCloseDialogClickEvent = {},
            amountItemsSyncProcessFinish = mutableStateOf(1),
        )
    }
}
