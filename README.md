<div align="center">

# 🌿 Capiro UI

**A Jetpack Compose component library for the Android apps of Flores El Capiro S.A.**

Buttons, text fields, spinners, dialogs, scanner/labeling widgets and full screens — all themed with the Capiro brand palette and SF Pro typography, ready to drop into any Compose app.

[![](https://jitpack.io/v/Capiro-Dev/CapiroUI.svg)](https://jitpack.io/#Capiro-Dev/CapiroUI)
![Min SDK](https://img.shields.io/badge/minSdk-26-3DDC84?logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-7F52FF?logo=kotlin&logoColor=white)
![Compose BOM](https://img.shields.io/badge/Compose%20BOM-2023.08-4285F4?logo=jetpackcompose&logoColor=white)
![Java](https://img.shields.io/badge/JDK-17-orange?logo=openjdk&logoColor=white)

<table>
<tr>
<td><img src="docs/screenshots/login_screen_capiro.png" width="190"/></td>
<td><img src="docs/screenshots/custom_date_picker.png" width="240"/></td>
<td><img src="docs/screenshots/one_option_dialog_capiro.png" width="240"/></td>
</tr>
</table>

</div>

---

## Table of contents

- [Installation](#installation)
- [Quick start](#quick-start)
- [Theming](#theming)
  - [Colors](#colors)
  - [Typography](#typography)
- [Components](#components)
  - [Buttons](#buttons)
  - [Text fields](#text-fields)
  - [Spinners & dropdowns](#spinners--dropdowns)
  - [Selection controls](#selection-controls)
  - [Display & layout](#display--layout)
  - [Navigation](#navigation)
  - [Lists, items & labels](#lists-items--labels)
  - [Date & filters](#date--filters)
  - [Dialogs](#dialogs)
  - [Screens](#screens)
- [Generating the screenshots](#generating-the-screenshots)
- [Building from source](#building-from-source)
- [License](#license)

---

## Installation

The library is published through [JitPack](https://jitpack.io/#Capiro-Dev/CapiroUI).

**1. Add the JitPack repository** to your `settings.gradle` (or root `build.gradle`):

```groovy
dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven { url 'https://jitpack.io' }
    }
}
```

**2. Add the dependency** to your module `build.gradle`:

```groovy
dependencies {
    implementation 'com.github.Capiro-Dev:composables:1.12.55'
}
```

> The version must match a published git tag / JitPack build. Check the badge above for the latest available version.

**Requirements:** `minSdk 26`, Jetpack Compose, JDK 17.

---

## Quick start

Every component is stateless and driven by parameters + callbacks — hoist the state in your composable and pass it down:

```kotlin
import com.capiro.composables.athomic_composables.buttons.ButtonCapiro
import com.capiro.composables.athomic_composables.textfield.TextFieldOutlinedCapiro

@Composable
fun LoginForm() {
    var user by remember { mutableStateOf("") }

    Column {
        TextFieldOutlinedCapiro(
            textInput = user,
            label = "Usuario",
            onTextChangeEvent = { user = it },
        )
        ButtonCapiro(text = "Ingresar", onClick = { /* ... */ })
    }
}
```

---

## Theming

Capiro UI ships its own brand palette and typography rather than relying on `MaterialTheme.colorScheme`. Components reference these tokens directly, so they look consistent without any theme setup on your side.

### Colors

Defined in [`theme/Colors.kt`](composables/src/main/java/com/capiro/composables/theme/Colors.kt). `GreenCapiro` is the brand primary used across almost every component.

| Swatch | Token | Hex |
|:---:|---|---|
| <img src="https://placehold.co/16x16/41695B/41695B.png" width="16"/> | `GreenCapiro` *(primary)* | `#41695B` |
| <img src="https://placehold.co/16x16/00E192/00E192.png" width="16"/> | `GreenSecondCapiro` *(accent)* | `#00E192` |
| <img src="https://placehold.co/16x16/E4E1D9/E4E1D9.png" width="16"/> | `BeigeCapiro` | `#E4E1D9` |
| <img src="https://placehold.co/16x16/F18A00/F18A00.png" width="16"/> | `OrangeCapiro` | `#F18A00` |
| <img src="https://placehold.co/16x16/F0EEEE/F0EEEE.png" width="16"/> | `GrayClearCapiro` | `#F0EEEE` |
| <img src="https://placehold.co/16x16/DAD6CB/DAD6CB.png" width="16"/> | `GrayCapiro` | `#DAD6CB` |
| <img src="https://placehold.co/16x16/999999/999999.png" width="16"/> | `GrayDarkCapiro` | `#999999` |
| <img src="https://placehold.co/16x16/505050/505050.png" width="16"/> | `BlackCapiro` | `#505050` |
| <img src="https://placehold.co/16x16/FFFFFF/FFFFFF.png" width="16"/> | `WhiteCapiro` | `#FFFFFF` |
| <img src="https://placehold.co/16x16/CC1111/CC1111.png" width="16"/> | `RedCapiro` | `#CC1111` |
| <img src="https://placehold.co/16x16/F72129/F72129.png" width="16"/> | `ErrorCapiro` | `#F72129` |

```kotlin
import com.capiro.composables.theme.GreenCapiro
import com.capiro.composables.theme.OrangeCapiro

Text("Hola", color = GreenCapiro)
```

### Typography

Typography is built on a custom **SF Pro Display** font family and the Material 3 type scale, defined in [`theme/Type.kt`](composables/src/main/java/com/capiro/composables/theme/Type.kt). It **auto-scales by screen width**: devices ≤ 400dp wide get the base scale, wider devices get a larger scale.

```kotlin
import getTypography          // composable contexts
import TypographyProvider     // non-composable / static contexts

Text("Título", style = getTypography().titleLarge)
Text("Cuerpo", style = TypographyProvider.typography.bodyMedium)
```

> `getTypography()` and `TypographyProvider` live in the default package, so they are imported without a package prefix.

---

## Components

> All previews below are generated automatically from the real composables — see [Generating the screenshots](#generating-the-screenshots).
>
> 📖 Looking for every parameter? See the full **[API reference →](docs/COMPONENTS.md)**. To browse the components live on a device, run the demo app (`:app`), which renders the [interactive gallery](app/src/main/java/com/capiro/capiroui/ComponentGallery.kt).

### Buttons

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/button_capiro.png" width="150"/> | `ButtonCapiro` | Primary rounded button with enabled/disabled states and fully configurable colors. |
| <img src="docs/screenshots/button_capiro_disabled.png" width="150"/> | `ButtonCapiro` *(disabled)* | The same button in its disabled state (`isEnabled = false`). |
| <img src="docs/screenshots/button_icon_capiro.png" width="110"/> | `ButtonIconCapiro` | Icon button with an optional label and color changes on press. |
| <img src="docs/screenshots/button_image_capiro.png" width="110"/> | `ButtonImageCapiro` | Image button with optional label and a locked/disabled indicator. |
| <img src="docs/screenshots/button_fab_capiro.png" width="120"/> | `ButtonFabCapiro` | Floating action button with up to three expandable menu actions. |
| <img src="docs/screenshots/button_menu.png" width="120"/> | `ButtonMenu` | Large menu tile (image + title) built from a `MenuItemCapiro`. |

<details><summary>💻 Usage</summary>

```kotlin
ButtonCapiro(text = "Aceptar", onClick = { })
ButtonCapiro(text = "Aceptar", onClick = { }, isEnabled = false)

ButtonIconCapiro(image = Icons.Filled.Search, onClick = { }, label = "Buscar")

ButtonImageCapiro(imageRes = R.drawable.farm, onClick = { }, size = 72.dp, label = "Finca")

ButtonFabCapiro(imageRes1 = R.drawable.fab, onClick1 = { }, innerPadding = 0.dp, iconSize = 56.dp)

ButtonMenu(
    menuItem = MenuItemCapiro(
        image = R.drawable.farm, title = "Fincas",
        borderColor = GreenCapiro, colorBack = WhiteCapiro, execute = { },
    ),
    size = 120.dp,
)
```
</details>

### Text fields

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/text_field_capiro.png" width="200"/> | `TextFieldCapiro` | Labeled text field with a trailing lock icon when disabled. |
| <img src="docs/screenshots/text_field_outlined_capiro.png" width="200"/> | `TextFieldOutlinedCapiro` | Outlined field with password support and optional leading/trailing icons. |
| <img src="docs/screenshots/text_field_rounded_border_capiro.png" width="200"/> | `TextFieldRoundedBorderCapiro` | Rounded-border field with inline error message and password toggle. |
| <img src="docs/screenshots/text_field_and_tile_rounded_border_capiro.png" width="200"/> | `TextFieldAndTileRoundedBorderCapiro` | Rounded-border field with a title label above it. |
| <img src="docs/screenshots/text_field_solid_background.png" width="200"/> | `TextFieldSolidBackground` | Borderless field with a solid background, used for search inputs. |

<details><summary>💻 Usage</summary>

```kotlin
var text by remember { mutableStateOf("") }

TextFieldCapiro(textInput = text, label = "Cantidad", onTextChangeEvent = { text = it })

TextFieldOutlinedCapiro(textInput = text, label = "Usuario", onTextChangeEvent = { text = it })

TextFieldRoundedBorderCapiro(textInput = text, onTextChangeEvent = { text = it }, label = "Usuario")

TextFieldAndTileRoundedBorderCapiro(
    textInput = text, onTextChangeEvent = { text = it }, label = "Usuario", title = "Usuario",
)

TextFieldSolidBackground(text = text, onTextChanged = { text = it })
```
</details>

### Spinners & dropdowns

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/spinner_button_capiro.png" width="200"/> | `SpinnerButtonCapiro` | Clickable spinner showing the selected item (or placeholder) with a label. |
| <img src="docs/screenshots/spinner_button_underlined.png" width="200"/> | `SpinnerButtonUnderlined` | Underlined spinner button with an optional error state. |
| <img src="docs/screenshots/spinner_dropdown_capiro.png" width="200"/> | `SpinnerDropdownCapiro` | Dropdown with a leading image and an expandable list of items. |
| <img src="docs/screenshots/spinner_dropdown_underlined.png" width="200"/> | `SpinnerDropdownUnderlined` | Underlined dropdown with an optional label and error state. |
| <img src="docs/screenshots/simple_dropdown_text_capiro.png" width="200"/> | `SimpleDropdownTextCapiro` | Dropdown that includes a custom "otro" (other) free-text option. |
| <img src="docs/screenshots/simple_dropdown_underlined.png" width="200"/> | `SimpleDropDownUnderlined` | Minimal underlined dropdown field with a down arrow. |

<details><summary>💻 Usage</summary>

```kotlin
val items = listOf("Finca 1", "Finca 2")
var selected by remember { mutableStateOf(items.first()) }

SpinnerButtonCapiro(itemSelected = selected, labelResourceId = R.string.label, onClick = { })

SpinnerButtonUnderlined(labelResourceId = R.string.label, selectedItem = selected, onClick = { })

SpinnerDropdownCapiro(
    imageResourceId = R.drawable.farm, items = items,
    selectedItem = selected, onItemSelect = { selected = it },
)

SpinnerDropdownUnderlined(
    items = items, selectedItem = selected,
    onItemSelectedChange = { selected = it }, labelResourceId = R.string.label,
)

SimpleDropdownTextCapiro(
    items = items, selectedItem = selected,
    onItemSelect = { selected = it }, labelResourceId = R.string.label,
)

SimpleDropDownUnderlined(labelResourceId = R.string.label, selectedItem = selected, onClick = { })
```
</details>

### Selection controls

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/check_box_capiro.png" width="80"/> | `CheckBoxCapiro` | Branded checkbox with a configurable scale and enabled state. |
| <img src="docs/screenshots/check_box_and_text_capiro.png" width="180"/> | `CheckBoxAndTextCapiro` | Checkbox with an accompanying text label. |
| <img src="docs/screenshots/radio_button_capiro.png" width="70"/> | `RadioButtonCapiro` | Radio button driven by `selection`/`identifier` equality. |
| <img src="docs/screenshots/radio_button_text_capiro.png" width="150"/> | `RadioButtonTextCapiro` | Radio button with a text label. |
| <img src="docs/screenshots/switch_capiro.png" width="150"/> | `SwitchCapiro` | Toggle switch with a text label. |

<details><summary>💻 Usage</summary>

```kotlin
var checked by remember { mutableStateOf(true) }
var choice by remember { mutableStateOf("A") }

CheckBoxCapiro(isChecked = checked, onCheckedChange = { checked = it })

CheckBoxAndTextCapiro(isChecked = checked, onSelectedItemChange = { }, text = "Acepto los términos")

RadioButtonCapiro(selection = choice, identifier = "A", onClick = { choice = it })

RadioButtonTextCapiro(selection = choice, identifier = "Opción A", onClick = { choice = it })

SwitchCapiro(text = "Activar", isChecked = checked, onCheckedChange = { checked = it })
```
</details>

### Display & layout

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/card_capiro.png" width="200"/> | `CardCapiro` | Rounded, elevated container with inner padding for any content. |
| <img src="docs/screenshots/image_capiro.png" width="110"/> | `ImageCapiro` | Displays a drawable resource with fit scaling. |
| <img src="docs/screenshots/polygon_capiro.png" width="100"/> | `PolygonCapiro` | Hexagonal shape, optionally hosting image content (e.g. profile photos). |
| <img src="docs/screenshots/dialog_title.png" width="200"/> | `DialogTitle` | Centered dialog title with an optional close button. |
| <img src="docs/screenshots/dialog_title_2.png" width="110"/> | `DialogTitle2` | Centered image header with rounded corners for dialogs. |

<details><summary>💻 Usage</summary>

```kotlin
CardCapiro(innerComposable = { Text("Contenido") })

ImageCapiro(imageSourceId = R.drawable.logo, modifier = Modifier.size(120.dp))

PolygonCapiro(modifier = Modifier.size(110.dp))

DialogTitle(text = "Seleccione una finca", onCloseClick = { })

DialogTitle2(imageResource = R.drawable.flower)
```
</details>

### Navigation

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/toolbar_capiro.png" width="280"/> | `ToolbarCapiro` | App toolbar with farm name, section title and a user-menu button that opens the account dialog. |
| <img src="docs/screenshots/current_account_menu_top_bar.png" width="200"/> | `CurrentAccountMenuTopBar` | Top bar of the account menu with a "close session" action. |
| <img src="docs/screenshots/account_menu_dialog.png" width="190"/> | `ToolbarMenuLayoutDialogCapiro` | Account options dialog (logout / backup / printers). |

<details><summary>💻 Usage</summary>

```kotlin
ToolbarCapiro(
    user = "Danilo Osorio",
    farm = "Flores El Capiro S.A.",
    section = "Inventario",
    userAbbreviation = "DO",
    onLogoutClick = { },
    onBackUpClick = { },
    onPrintersClick = { },
)

var menuOpen by remember { mutableStateOf(false) }
ToolbarMenuLayoutDialogCapiro(
    user = "Danilo Osorio",
    isOpenDialog = menuOpen,
    onCloseDialog = { menuOpen = false },
    onLogoutClick = { },
)
```
</details>

### Lists, items & labels

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/item_manual_capiro.png" width="220"/> | `ItemManualCapiro` | Scanned-item row with number, label, custom content and optional delete. |
| <img src="docs/screenshots/item_scanner_capiro.png" width="220"/> | `ItemScannerCapiro` | Scanned-item row with number, label, custom content and optional expand. |
| <img src="docs/screenshots/item_expandable_capiro.png" width="220"/> | `ItemExpandableCapiro` | Expandable item card that reveals a list of variety details. |
| <img src="docs/screenshots/label_manual_capiro.png" width="240"/> | `LabelManualCapiro` | A/Y/W + consecutive manual label entry fields (farm & week variants available). |

<details><summary>💻 Usage</summary>

```kotlin
ItemManualCapiro(
    itemNumber = "01", scannedLabel = "A-1234-W45",
    mainComposable = { Text("Variedad Freedom", color = GreenCapiro) },
    onDeleteClick = { },
)

ItemScannerCapiro(
    itemNumber = "01", scannedLabel = "A-1234-W45",
    mainComposable = { Text("Variedad Freedom", color = GreenCapiro) },
    color = GreenCapiro, colorBackground = WhiteCapiro,
)

ItemExpandableCapiro(
    itemNumber = "01", customer = "Cliente Demo",
    mainComposable = { Text("Resumen", color = GreenCapiro) },
    listVarieties = listOf(VarietyReport(variety = "Freedom", stems = 120, bouquets = 10)),
    color = GreenCapiro, colorBackground = WhiteCapiro,
)

LabelManualCapiro(
    textValueA = "A", textValueY = "2024", textValueW = "45", textConsecutive = "001",
    onTextChangeA = { }, onTextChangeY = { }, onTextChangeW = { },
    itemsY = listOf("2024", "2025"), itemsW = listOf("44", "45"),
    onTextChangeConsecutive = { },
)
```
</details>

### Date & filters

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/calendar_bar_capiro.png" width="220"/> | `CalendarBarCapiro` | Date bar showing day / weekday / month with a calendar trigger. |
| <img src="docs/screenshots/week_filter_capiro.png" width="240"/> | `WeekFilterCapiro` | Row of day checkboxes for filtering by weekday. |
| <img src="docs/screenshots/two_options_button_capiro.png" width="220"/> | `TwoOptionsButtonCapiro` | Two mutually-exclusive toggle buttons (only one active at a time). |

<details><summary>💻 Usage</summary>

```kotlin
CalendarBarCapiro(day = "04", dayOfWeek = "Jueves", month = "Junio", onCalendarClick = { })

val days = remember {
    mutableStateOf(linkedMapOf("L" to true, "M" to false, "W" to true, "J" to false,
        "V" to true, "S" to false, "D" to false))
}
WeekFilterCapiro(
    daysChecked = days.value,
    onDayCheckedChange = { day, checked -> days.value = days.value.toMutableMap().apply { this[day] = checked } },
)

TwoOptionsButtonCapiro(text1 = "Activos", text2 = "Inactivos", onClick1 = { }, onClick2 = { })
```
</details>

### Dialogs

All dialogs follow the same pattern: pass an `isOpen` boolean and they render nothing while closed.

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/loading_dialog.png" width="200"/> | `LoadingDialog` | Blocking loading dialog with a title, message and spinner. |
| <img src="docs/screenshots/one_option_dialog_capiro.png" width="200"/> | `OneOptionDialogCapiro` | Single-action dialog with an image and (optionally bolded) message. |
| <img src="docs/screenshots/two_options_dialog_capiro.png" width="200"/> | `TwoOptionsDialogCapiro` | Confirm/cancel dialog with two buttons. |
| <img src="docs/screenshots/custom_date_picker.png" width="220"/> | `CustomDatePicker` | Custom calendar date picker with optional date-range limiting. |
| <img src="docs/screenshots/search_list_dialog_capiro.png" width="160"/> | `SearchListDialogCapiro<T>` | Generic searchable list dialog with a caller-supplied item slot. |
| <img src="docs/screenshots/simple_list_dialogs_capiro.png" width="160"/> | `SimpleListDialogsCapiro<T>` | Generic list dialog with a custom item slot. |
| <img src="docs/screenshots/radio_button_dialog_capiro.png" width="160"/> | `RadioButtonDialogCapiro` | Dialog with a list of radio-button options. |
| <img src="docs/screenshots/simple_dialog_history_capiro.png" width="160"/> | `SimpleDialogHistoryCapiro<T>` | Generic list dialog with a title and close button. |
| <img src="docs/screenshots/dialog_history_capiro.png" width="160"/> | `DialogHistoryCapiro<T>` | Searchable history dialog with a built-in week filter. |
| <img src="docs/screenshots/list_text_field_dialog_capiro.png" width="160"/> | `ListTextFieldDialogCapiro` | Dialog combining a selectable list with a text/units input. |
| <img src="docs/screenshots/sync_dialog_capiro.png" width="160"/> | `SyncDialogCapiro` | Progress dialog showing the status of multiple sync items. |

<details><summary>💻 Usage</summary>

```kotlin
var open by remember { mutableStateOf(true) }

LoadingDialog(isDialogOpenState = open, title = "Cargando", message = "Por favor espere...", onClose = { open = false })

OneOptionDialogCapiro(
    imaResource = R.drawable.flower, boldText = "importante",
    message = "Este es un mensaje importante.", positiveButtonText = "Aceptar",
    onPositiveButtonClickEvent = { open = false }, isDialogOpenState = open,
)

TwoOptionsDialogCapiro(
    imaResource = R.drawable.error, message = "¿Desea eliminar el registro?",
    positiveButtonText = "Sí", negativeButtonText = "No",
    onPositiveButtonClickEvent = { }, onNegativeButtonClickEvent = { open = false },
    isDialogOpenState = open,
)

CustomDatePicker(isOpenDialog = open, onDateSelected = { }, onCloseDialog = { open = false })

// Generic, slot-based list dialog
SimpleListDialogsCapiro(
    titleIdRes = R.string.title, isTheDialogOpenState = open,
    allData = arrayOf("Finca 1", "Finca 2"), onCloseDialogEvent = { open = false },
    items = { Text(it, color = GreenCapiro) },
)

SearchListDialogCapiro(
    searchText = "", onSearchTextChange = { }, titleIdRes = R.string.title,
    isTheDialogOpenState = open, allData = arrayOf("Finca 1", "Finca 2"),
    onCleanText = { }, onSearchItemSelectedChangeState = { }, onCloseDialogEvent = { open = false },
    item = { Text(it, color = GreenCapiro) },
)
```
</details>

### Screens

Full-screen composables ready to host in an `Activity` / nav destination.

| Preview | Component | Description |
|:---:|---|---|
| <img src="docs/screenshots/splash_screen_capiro.png" width="180"/> | `SplashScreenCapiro` | Splash screen with app icon, support message and version. |
| <img src="docs/screenshots/login_screen_capiro.png" width="180"/> | `LoginScreenCapiro` | Login screen with user/password fields, backup and login actions. |

<details><summary>💻 Usage</summary>

```kotlin
SplashScreenCapiro(iconAppResource = R.drawable.logo, version = "1.0.0")

var user by remember { mutableStateOf("") }
var password by remember { mutableStateOf("") }
LoginScreenCapiro(
    iconAppResource = R.drawable.logo,
    onBackUpClick = { },
    user = user, password = password,
    onUserTextChange = { user = it },
    onPasswordTextChange = { password = it },
    onLoginClick = { },
)
```
</details>

---

## Generating the screenshots

Every preview in this README is rendered directly from the library composables using [Paparazzi](https://github.com/cashapp/paparazzi) — no device or emulator required. The snapshot tests live in [`composables/src/test/java/com/capiro/composables/`](composables/src/test/java/com/capiro/composables/) (`ScreenshotTest`, `ScreenScreenshotTest`).

```bash
# Record / update the golden images
./gradlew :composables:recordPaparazziDebug

# Verify nothing changed (useful in CI)
./gradlew :composables:verifyPaparazziDebug
```

Golden images are written to `composables/src/test/snapshots/images/` and copied into [`docs/screenshots/`](docs/screenshots/) for this document. Add a new `@Test` to render any new component.

> 💡 **Ideas for the future:** wire `verifyPaparazziDebug` into CI to catch unintended visual regressions on every PR, and consider hosting an interactive component gallery (e.g. a Compose showcase app or a published Storybook-style page).

---

## Building from source

```bash
./gradlew :composables:assembleDebug        # build the library
./gradlew :composables:publishToMavenLocal  # publish locally for testing
```

This project targets **JDK 17** (AGP 8.4.1). If Gradle picks up an older JDK, point it at a 17+ runtime, e.g. Android Studio's bundled JBR:

```bash
# Windows (PowerShell)
$env:JAVA_HOME = "C:\Program Files\Android\Android Studio\jbr"
```

---

## License

No open-source license is currently declared for this repository. It is maintained by **Flores El Capiro S.A.**; add a `LICENSE` file to clarify usage terms for external consumers.
