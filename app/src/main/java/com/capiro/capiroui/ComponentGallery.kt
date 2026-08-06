package com.capiro.capiroui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.capiro.composables.R as CapiroR
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
import com.capiro.composables.qr_scanner.QrScannerButtonCapiro
import com.capiro.composables.model.SyncItem
import com.capiro.composables.model.SyncStatus
import com.capiro.composables.screens.LoginScreenCapiro
import com.capiro.composables.screens.SplashScreenCapiro
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
import com.capiro.composables.util_composables.ToolbarMenuLayoutDialogCapiro
import com.capiro.composables.util_composables.TwoOptionsButtonCapiro
import com.capiro.composables.util_composables.VarietyReport
import com.capiro.composables.util_composables.WeekFilterCapiro

/**
 * Live, scrollable showcase of every Capiro UI component.
 * This is the entry point rendered by [MainActivity]; it doubles as a manual visual QA tool.
 */
@Composable
fun ComponentGallery() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayClearCapiro)
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        Text(
            text = "🌿 Capiro UI — Component Gallery",
            style = androidx.compose.material3.MaterialTheme.typography.titleLarge,
            color = GreenCapiro,
            fontWeight = FontWeight.Bold,
        )

        Buttons()
        GalleryTextFields()
        SpinnersAndDropdowns()
        SelectionControls()
        DisplayAndLayout()
        Navigation()
        ItemsAndLabels()
        DateAndFilters()
        QrScanner()
        Dialogs()
        Screens()
    }
}

// ---------- helpers ----------

@Composable
private fun Section(title: String, content: @Composable () -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text(text = title, color = GreenCapiro, fontWeight = FontWeight.Bold)
        Divider(color = GreenCapiro)
    }
    content()
}

@Composable
private fun Demo(name: String, content: @Composable () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(WhiteCapiro)
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Text(text = name, color = GreenCapiro, fontWeight = FontWeight.Bold)
        content()
    }
}

/** A button that opens a dialog; [dialog] receives the open flag and a close callback. */
@Composable
private fun DialogDemo(name: String, dialog: @Composable (open: Boolean, onClose: () -> Unit) -> Unit) {
    var open by remember { mutableStateOf(false) }
    Demo(name) {
        ButtonCapiro(text = "Mostrar", onClick = { open = true })
        dialog(open) { open = false }
    }
}

// ---------- sections ----------

@Composable
private fun Buttons() = Section("Buttons") {
    Demo("ButtonCapiro") { ButtonCapiro(text = "Aceptar", onClick = {}) }
    Demo("ButtonCapiro (disabled)") { ButtonCapiro(text = "Aceptar", onClick = {}, isEnabled = false) }
    Demo("ButtonIconCapiro") { ButtonIconCapiro(image = Icons.Filled.Search, onClick = {}, label = "Buscar") }
    Demo("ButtonImageCapiro") {
        ButtonImageCapiro(imageRes = CapiroR.drawable.farm, onClick = {}, size = 72.dp, label = "Finca")
    }
    Demo("ButtonFabCapiro") {
        Box(Modifier.size(150.dp)) {
            ButtonFabCapiro(icon1 = painterResource(id = CapiroR.drawable.fab), onClick1 = {}, innerPadding = 0.dp, iconSize = 56.dp)
        }
    }
    Demo("ButtonMenu") {
        ButtonMenu(
            menuItem = MenuItemCapiro(
                image = CapiroR.drawable.farm, title = "Fincas",
                borderColor = GreenCapiro, colorBack = WhiteCapiro, execute = {},
            ),
            size = 120.dp,
        )
    }
}

@Composable
private fun GalleryTextFields() = Section("Text fields") {
    var text by remember { mutableStateOf("Capiro") }
    Demo("TextFieldCapiro") { TextFieldCapiro(textInput = text, label = "Cantidad", onTextChangeEvent = { text = it }) }
    Demo("TextFieldOutlinedCapiro") { TextFieldOutlinedCapiro(textInput = text, label = "Usuario", onTextChangeEvent = { text = it }) }
    Demo("TextFieldRoundedBorderCapiro") { TextFieldRoundedBorderCapiro(textInput = text, onTextChangeEvent = { text = it }, label = "Usuario") }
    Demo("TextFieldAndTileRoundedBorderCapiro") {
        TextFieldAndTileRoundedBorderCapiro(textInput = text, onTextChangeEvent = { text = it }, label = "Usuario", title = "Usuario")
    }
    Demo("TextFieldSolidBackground") { TextFieldSolidBackground(text = text, onTextChanged = { text = it }) }
}

@Composable
private fun SpinnersAndDropdowns() = Section("Spinners & dropdowns") {
    val items = listOf("Finca 1", "Finca 2", "Finca 3")
    var selected by remember { mutableStateOf(items.first()) }
    Demo("SpinnerButtonCapiro") { SpinnerButtonCapiro(itemSelected = selected, labelResourceId = CapiroR.string.login_tt_user_label, onClick = {}) }
    Demo("SpinnerButtonUnderlined") { SpinnerButtonUnderlined(labelResourceId = CapiroR.string.login_tt_user_label, selectedItem = selected, onClick = {}) }
    Demo("SpinnerDropdownCapiro") {
        SpinnerDropdownCapiro(icon = painterResource(id = CapiroR.drawable.farm), items = items, selectedItem = selected, onItemSelect = { selected = it })
    }
    Demo("SpinnerDropdownUnderlined") {
        SpinnerDropdownUnderlined(items = items, selectedItem = selected, onItemSelectedChange = { selected = it }, labelResourceId = CapiroR.string.login_tt_user_label)
    }
    Demo("SimpleDropdownTextCapiro") {
        SimpleDropdownTextCapiro(items = items, selectedItem = selected, onItemSelect = { selected = it }, labelResourceId = CapiroR.string.login_tt_user_label)
    }
    Demo("SimpleDropDownUnderlined") { SimpleDropDownUnderlined(labelResourceId = CapiroR.string.login_tt_user_label, selectedItem = selected, onClick = {}) }
}

@Composable
private fun SelectionControls() = Section("Selection controls") {
    var checked by remember { mutableStateOf(true) }
    var choice by remember { mutableStateOf("A") }
    Demo("CheckBoxCapiro") { CheckBoxCapiro(isChecked = checked, onCheckedChange = { checked = it }) }
    Demo("CheckBoxAndTextCapiro") { CheckBoxAndTextCapiro(isChecked = checked, onSelectedItemChange = {}, text = "Acepto los términos") }
    Demo("RadioButtonCapiro") { RadioButtonCapiro(selection = choice, identifier = "A", onClick = { choice = it }) }
    Demo("RadioButtonTextCapiro") { RadioButtonTextCapiro(selection = choice, identifier = "Opción A", onClick = { choice = it }) }
    Demo("SwitchCapiro") { SwitchCapiro(text = "Activar", isChecked = checked, onCheckedChange = { checked = it }) }
}

@Composable
private fun DisplayAndLayout() = Section("Display & layout") {
    Demo("CardCapiro") { CardCapiro(innerComposable = { Text("Contenido de la Card", color = GreenCapiro) }) }
    Demo("ImageCapiro") { ImageCapiro(painter = painterResource(id = CapiroR.drawable.test_app_logo), modifier = Modifier.size(120.dp)) }
    Demo("PolygonCapiro") { PolygonCapiro(modifier = Modifier.size(110.dp)) }
    Demo("DialogTitle") { DialogTitle(text = "Seleccione una finca", onCloseClick = {}) }
    Demo("DialogTitle2") { DialogTitle2(painter = painterResource(id = CapiroR.drawable.flower)) }
}

@Composable
private fun Navigation() = Section("Navigation") {
    Demo("ToolbarCapiro") {
        ToolbarCapiro(user = "Danilo Osorio", farm = "Flores El Capiro S.A.", section = "Inventario", userAbbreviation = "DO")
    }
    Demo("CurrentAccountMenuTopBar") { CurrentAccountMenuTopBar(onCloseSession = {}) }
    DialogDemo("ToolbarMenuLayoutDialogCapiro (account menu)") { open, onClose ->
        ToolbarMenuLayoutDialogCapiro(user = "Danilo Osorio", isOpenDialog = open, onCloseDialog = onClose, onLogoutClick = {}, onBackUpClick = {}, onPrintersClick = {})
    }
}

@Composable
private fun ItemsAndLabels() = Section("Lists, items & labels") {
    Demo("ItemManualCapiro") {
        ItemManualCapiro(itemNumber = "01", scannedLabel = "A-1234-W45", mainComposable = { Text("Variedad Freedom", color = GreenCapiro) }, onDeleteClick = {})
    }
    Demo("ItemScannerCapiro") {
        ItemScannerCapiro(itemNumber = "01", scannedLabel = "A-1234-W45", mainComposable = { Text("Variedad Freedom", color = GreenCapiro) }, color = GreenCapiro, colorBackground = WhiteCapiro)
    }
    Demo("ItemExpandableCapiro") {
        ItemExpandableCapiro(
            itemNumber = "01", customer = "Cliente Demo",
            mainComposable = { Text("Resumen del pedido", color = GreenCapiro) },
            listVarieties = listOf(VarietyReport("Freedom", 120, 10), VarietyReport("Explorer", 80, 8)),
            color = GreenCapiro, colorBackground = WhiteCapiro,
        )
    }
    Demo("LabelManualCapiro") {
        LabelManualCapiro(
            textValueA = "A", textValueY = "2024", textValueW = "45", textConsecutive = "001",
            onTextChangeA = {}, onTextChangeY = {}, onTextChangeW = {},
            itemsY = listOf("2024", "2025"), itemsW = listOf("44", "45"), onTextChangeConsecutive = {},
        )
    }
}

@Composable
private fun DateAndFilters() = Section("Date & filters") {
    Demo("CalendarBarCapiro") { CalendarBarCapiro(day = "04", dayOfWeek = "Jueves", month = "Junio", onCalendarClick = {}) }
    Demo("WeekFilterCapiro") {
        var days by remember {
            mutableStateOf(
                linkedMapOf("L" to true, "M" to false, "W" to true, "J" to false, "V" to true, "S" to false, "D" to false)
            )
        }
        WeekFilterCapiro(daysChecked = days, onDayCheckedChange = { day, c -> days = LinkedHashMap(days).apply { this[day] = c } })
    }
    Demo("TwoOptionsButtonCapiro") { TwoOptionsButtonCapiro(text1 = "Activos", text2 = "Inactivos", onClick1 = {}, onClick2 = {}) }
}

@Composable
private fun QrScanner() = Section("QR scanner") {
    var lastScan by remember { mutableStateOf("—") }
    Demo("QrScannerButtonCapiro") {
        QrScannerButtonCapiro(onQrCodeScanned = { lastScan = it }, label = "Escanear")
        Text(text = "Último código: $lastScan", color = GreenCapiro)
    }
}

@Composable
private fun Dialogs() = Section("Dialogs") {
    DialogDemo("LoadingDialog") { open, onClose ->
        LoadingDialog(isDialogOpenState = open, title = "Cargando", message = "Por favor espere...", onClose = onClose)
    }
    DialogDemo("OneOptionDialogCapiro") { open, onClose ->
        OneOptionDialogCapiro(
            imaResource = CapiroR.drawable.flower, boldText = "importante",
            message = "Este es un mensaje importante para el usuario.",
            positiveButtonText = "Aceptar", onPositiveButtonClickEvent = onClose, isDialogOpenState = open,
        )
    }
    DialogDemo("TwoOptionsDialogCapiro") { open, onClose ->
        TwoOptionsDialogCapiro(
            image = painterResource(id = CapiroR.drawable.error), message = "¿Desea eliminar el registro?",
            positiveButtonText = "Sí", negativeButtonText = "No",
            onPositiveButtonClickEvent = onClose, onNegativeButtonClickEvent = onClose, isDialogOpenState = open,
        )
    }
    DialogDemo("CustomDatePicker") { open, onClose ->
        CustomDatePicker(isOpenDialog = open, onDateSelected = { onClose() }, onCloseDialog = onClose)
    }
    DialogDemo("SearchListDialogCapiro") { open, onClose ->
        SearchListDialogCapiro(
            searchText = "", onSearchTextChange = {}, titleIdRes = CapiroR.string.list_dialog,
            isTheDialogOpenState = open, allData = arrayOf("Finca 1", "Finca 2", "Finca 3"),
            onCleanText = {}, onSearchItemSelectedChangeState = { onClose() }, onCloseDialogEvent = onClose,
            item = { Text(it, color = GreenCapiro) },
        )
    }
    DialogDemo("SimpleListDialogsCapiro") { open, onClose ->
        SimpleListDialogsCapiro(
            titleIdRes = CapiroR.string.list_dialog, isTheDialogOpenState = open,
            allData = arrayOf("Finca 1", "Finca 2"), onCloseDialogEvent = onClose,
            items = { Text(it, color = GreenCapiro) },
        )
    }
    DialogDemo("RadioButtonDialogCapiro") { open, onClose ->
        RadioButtonDialogCapiro(
            titleIdRes = CapiroR.string.list_dialog, isTheDialogOpenState = open,
            allData = arrayOf("Finca 1", "Finca 2"), onCloseDialogEvent = onClose,
            text = "Finca 1", onSelectionChanged = {},
        )
    }
    DialogDemo("SimpleDialogHistoryCapiro") { open, onClose ->
        SimpleDialogHistoryCapiro(
            title = "Historial", isTheDialogOpenState = open,
            allData = arrayOf("Registro 1", "Registro 2"),
            composableItem = { Text(it, color = GreenCapiro) }, onCloseDialogEvent = onClose,
        )
    }
    DialogDemo("DialogHistoryCapiro") { open, onClose ->
        DialogHistoryCapiro(
            searchText = "", onSearchTextChange = {}, title = "Historial", isTheDialogOpenState = open,
            allData = arrayOf("Registro 1", "Registro 2"), composableItem = { Text(it, color = GreenCapiro) },
            onCleanText = {}, onSearchItemSelectedChangeState = {}, onCloseDialogEvent = onClose,
            daysState = linkedMapOf("L" to true, "M" to false, "W" to false, "J" to false, "V" to false, "S" to false, "D" to false),
            onDayCheckedChange = { _, _ -> },
        )
    }
    DialogDemo("ListTextFieldDialogCapiro") { open, onClose ->
        ListTextFieldDialogCapiro(
            titleIdRes = CapiroR.string.list_dialog, isDialogOpenState = open,
            dataList = listOf("kg", "g", "lb"), text = "kg",
            onSelectionChanged = {}, onClose = onClose, onAccept = onClose,
        )
    }
    DialogDemo("SyncDialogCapiro") { open, onClose ->
        SyncDialogCapiro(
            isTheDialogOpen = open,
            syncItems = arrayOf(
                SyncItem(mutableStateOf(CapiroR.string.general_lb_sync), mutableStateOf(SyncStatus.IsDone())),
                SyncItem(mutableStateOf(CapiroR.string.dialog_sync_bt_processing), mutableStateOf(SyncStatus.IsLoading())),
            ),
            onCloseDialogClickEvent = onClose, amountItemsSyncProcessFinish = remember { mutableStateOf(1) },
        )
    }
}

@Composable
private fun Screens() = Section("Screens") {
    Demo("SplashScreenCapiro") {
        Box(Modifier.fillMaxWidth().height(420.dp)) {
            SplashScreenCapiro(icon = painterResource(id = CapiroR.drawable.test_app_logo), version = "1.0.0")
        }
    }
    Demo("LoginScreenCapiro") {
        var user by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Box(Modifier.fillMaxWidth().height(560.dp)) {
            LoginScreenCapiro(
                icon = painterResource(id = CapiroR.drawable.test_app_logo), onBackUpClick = {},
                user = user, password = password,
                onUserTextChange = { user = it }, onPasswordTextChange = { password = it }, onLoginClick = {},
            )
        }
    }
}
