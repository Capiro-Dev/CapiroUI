# Capiro UI — API reference

Full parameter reference for every public composable. For previews, descriptions and usage snippets see the [README](../README.md).

> Convention: a `—` in the **Default** column means the parameter is required. Generic dialogs are `fun <T>` and take a caller-supplied `@Composable (T) -> Unit` item slot.

## Contents

- [Buttons](#buttons)
- [Text fields](#text-fields)
- [Spinners & dropdowns](#spinners--dropdowns)
- [Selection controls](#selection-controls)
- [Display & layout](#display--layout)
- [Navigation](#navigation)
- [Lists, items & labels](#lists-items--labels)
- [Date & filters](#date--filters)
- [Input utilities](#input-utilities)
- [Dialogs](#dialogs)
- [Screens](#screens)
- [QR scanner](#qr-scanner)

---

## Buttons

#### `ButtonCapiro`
Displays a customizable button with configurable colors and state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier to be applied to the button. |
| `text` | `String` | — | The text to be displayed on the button. |
| `onClick` | `() -> Unit` | — | The click handler for the button. |
| `isEnabled` | `Boolean` | `true` | Indicates whether the button is enabled or not. |
| `border` | `Color` | `GreenCapiro` | The color of the button's border when enabled. |
| `borderIsNotEnabled` | `Color` | `GrayDarkCapiro` | The color of the button's border when disabled. |
| `background` | `Color` | `GreenCapiro` | The background color of the button when enabled. |
| `backgroundIsNotEnabled` | `Color` | `GrayClearCapiro` | The background color of the button when disabled. |
| `textStyle` | `TextStyle` | `bodyMedium` (white, bold) | The text style for the button label. |

#### `ButtonIconCapiro`
Displays a customizable icon button with optional label and press-state colors.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier to be applied to the button. |
| `image` | `ImageVector` | — | The icon to be displayed on the button. |
| `onClick` | `() -> Unit` | — | The click handler for the button. |
| `isRounded` | `Boolean` | `true` | Indicates whether the button corners are rounded. |
| `label` | `String?` | `null` | Optional label text displayed below the icon. |
| `fontColor` | `Color` | `GreenCapiro` | The color of the label text. |
| `iconColor` | `Color` | `WhiteCapiro` | The color of the icon in the default state. |
| `iconColorPressed` | `Color` | `GreenCapiro` | The color of the icon when pressed. |
| `backgroundColor` | `Color` | `GreenCapiro` | The background color in the default state. |
| `backgroundColorPressed` | `Color` | `WhiteCapiro` | The background color when pressed. |
| `lineBorderColor` | `Color` | `GreenCapiro` | The border color in the default state. |
| `lineBorderColorPressed` | `Color` | `GreenCapiro` | The border color when pressed. |

#### `ButtonImageCapiro`
Displays a button with an image, optional label, and locked/disabled state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imageRes` | `@DrawableRes Int` | — | The drawable resource for the button image. |
| `onClick` | `() -> Unit` | — | The click handler for the button. |
| `size` | `Dp` | — | The size of the button. |
| `modifier` | `Modifier` | `Modifier` | Modifier to be applied to the button. |
| `label` | `String?` | `null` | Optional label text displayed below the image. |
| `borderColor` | `Color` | `GreenCapiro` | The border color of the button when enabled. |
| `isEnabled` | `Boolean` | `true` | Indicates whether the button is enabled. |

#### `ButtonFabCapiro`
Displays a floating action button (FAB) with up to three optional expandable items.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imageRes1` | `@DrawableRes Int?` | `null` | The drawable resource for the first FAB item. |
| `imageRes2` | `@DrawableRes Int?` | `null` | The drawable resource for the second FAB item. |
| `imageRes3` | `@DrawableRes Int?` | `null` | The drawable resource for the third FAB item. |
| `onClick1` | `(() -> Unit)?` | `null` | The click handler for the first FAB item. |
| `onClick2` | `(() -> Unit)?` | `null` | The click handler for the second FAB item. |
| `onClick3` | `(() -> Unit)?` | `null` | The click handler for the third FAB item. |
| `innerPadding` | `Dp` | — | The padding applied inside the FAB items. |
| `iconSize` | `Dp` | — | The size of the FAB icons. |

#### `ButtonMenu`
Displays a large menu tile with an image, title, and optional disabled state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `menuItem` | `MenuItemCapiro` | — | The menu item data (image, title, colors, click handler). |
| `size` | `Dp` | — | The size of the button. |
| `isEnabled` | `Boolean` | `true` | Indicates whether the button is enabled. |
| `borderColor` | `Color` | `Transparent` | The border color of the button. |

---

## Text fields

#### `TextFieldCapiro`
A labeled text field with an optional trailing lock icon when disabled.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textInput` | `String` | — | The current text input value. |
| `label` | `String` | — | The label text displayed above the field. |
| `onTextChangeEvent` | `(String) -> Unit` | — | Callback triggered when the text changes. |
| `isEnabled` | `Boolean` | `true` | Whether the field is enabled for interaction. |
| `isNumeric` | `Boolean` | `true` | Whether the field accepts numeric input only. |
| `isError` | `Boolean` | `false` | Whether the field is in an error state. |

#### `TextFieldOutlinedCapiro`
An outlined text field with optional password visibility toggle and icon support.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textInput` | `String` | — | The current text input value. |
| `label` | `String` | — | The label text displayed in the field. |
| `onTextChangeEvent` | `(String) -> Unit` | — | Callback triggered when the text changes. |
| `isEnabled` | `Boolean` | `true` | Whether the field is enabled. |
| `isPassword` | `Boolean` | `false` | Whether the field is for password input. |
| `isNumeric` | `Boolean` | `false` | Whether the field accepts numeric input only. |
| `iconStart` | `ImageVector?` | `null` | Optional leading icon. |
| `iconDisable` | `ImageVector?` | `Icons.Filled.Lock` | Icon shown when disabled. |
| `backgroundColor` | `Color` | `WhiteCapiro` | Background color of the field. |
| `backgroundColorDisabled` | `Color` | `GrayClearCapiro` | Background color when disabled. |
| `borderColor` | `Color` | `GreenCapiro` | Border color of the field. |
| `borderColorDisabled` | `Color` | `GrayDarkCapiro` | Border color when disabled. |
| `fontColor` | `Color` | `GreenSecondCapiro` | Text color of the input. |
| `fontColorDisabled` | `Color` | `GrayDarkCapiro` | Text color when disabled. |

#### `TextFieldRoundedBorderCapiro`
A rounded-border field with optional icons and support for password and numeric input.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textInput` | `String` | — | The current text input value. |
| `onTextChangeEvent` | `(String) -> Unit` | — | Callback triggered when the text changes. |
| `label` | `String` | — | The label for the field. |
| `isNumeric` | `Boolean` | `true` | Whether the field is for numeric input. |
| `isPassword` | `Boolean` | `false` | Whether the field is for password input. |
| `errorMessage` | `String?` | `null` | Optional error message displayed below the field. |
| `typography` | `Typography` | `getTypography()` | The typography style to use. |
| `leadingIcon` | `ImageVector?` | `null` | Optional leading icon. |
| `trailingIcon` | `ImageVector?` | `null` | Optional trailing icon. |
| `imeAction` | `ImeAction` | `ImeAction.Done` | The IME action for the field. |
| `lines` | `Int` | `1` | The number of lines for the field. |

#### `TextFieldAndTileRoundedBorderCapiro`
A rounded-border field with a title label displayed above it.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textInput` | `String` | — | The current text input value. |
| `onTextChangeEvent` | `(String) -> Unit` | — | Callback triggered when the text changes. |
| `label` | `String` | — | The label for the field. |
| `title` | `String` | — | The title displayed above the field. |
| `isNumeric` | `Boolean` | `false` | Whether the field is for numeric input. |
| `errorMessage` | `String?` | `null` | Optional error message displayed below the field. |
| `typography` | `Typography` | `TypographyProvider.typography` | The typography style to use. |
| `leadingIcon` | `ImageVector?` | `null` | Optional leading icon. |
| `trailingIcon` | `ImageVector?` | `null` | Optional trailing icon. |
| `isPassword` | `Boolean` | `false` | Whether the field is for password input. |
| `imeAction` | `ImeAction` | `ImeAction.Done` | The IME action for the field. |
| `lines` | `Int` | `1` | The number of lines for the field. |

#### `TextFieldSolidBackground`
A borderless field with a solid background color.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | The current text input value. |
| `onTextChanged` | `(String) -> Unit` | — | Callback triggered when the text changes. |
| `keyboardType` | `KeyboardType` | `KeyboardType.Text` | The type of keyboard to display. |

---

## Spinners & dropdowns

#### `SpinnerButtonCapiro`
A clickable spinner button displaying the selected item with a label.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `itemSelected` | `String` | — | The currently selected item to display. |
| `labelResourceId` | `@StringRes Int` | — | The resource ID for the label text. |
| `onClick` | `() -> Unit` | — | Callback invoked when the button is clicked. |

#### `SpinnerButtonUnderlined`
An underlined spinner button with an optional error state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `labelResourceId` | `@StringRes Int` | — | Resource ID for the label text. |
| `selectedItem` | `String` | — | The currently selected item. |
| `onClick` | `() -> Unit` | — | Callback to handle clicks. |
| `isErrorActive` | `Boolean` | `false` | Whether there's an error (affects label color). |

#### `SpinnerDropdownCapiro`
A dropdown with a leading image and an expandable list of items.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier for styling and layout. |
| `imageResourceId` | `@DrawableRes Int` | — | Drawable displayed alongside the spinner. |
| `items` | `List<String>` | — | List of items in the dropdown menu. |
| `selectedItem` | `String` | — | The currently selected item. |
| `onItemSelect` | `(String) -> Unit` | — | Callback invoked when an item is selected. |
| `isEnabled` | `Boolean` | `true` | Whether the dropdown is enabled. |

#### `SpinnerDropdownUnderlined`
A dropdown with an underlined selected item and optional label.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `items` | `List<String>` | — | List of items in the dropdown menu. |
| `selectedItem` | `String` | — | The currently selected item. |
| `labelResourceId` | `@StringRes Int?` | `null` | Resource ID for the label text. |
| `onItemSelectedChange` | `(String) -> Unit` | — | Callback to handle item selection changes. |
| `isErrorActive` | `Boolean` | `false` | Whether there's an error (affects label color). |

#### `SimpleDropdownTextCapiro`
A dropdown that includes a custom "otro" (other) free-text option.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier for styling and layout. |
| `items` | `List<String>` | — | List of items in the dropdown menu. |
| `selectedItem` | `String` | — | The currently selected item. |
| `onItemSelect` | `(String) -> Unit` | — | Callback invoked when an item is selected. |
| `isEnabled` | `Boolean` | `true` | Whether the dropdown is enabled. |
| `labelResourceId` | `Int` | — | Resource ID for the label text. |
| `isErrorActive` | `Boolean` | `false` | Whether there's an error (affects label color). |
| `maxLength` | `Int` | `5` | Maximum length for the custom "otro" text input. |
| `action` | `() -> Unit` | `{}` | Optional callback invoked when the dropdown is clicked. |

#### `SimpleDropDownUnderlined`
A minimal underlined dropdown field with a down arrow.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `labelResourceId` | `@StringRes Int` | — | Resource ID for the label text. |
| `selectedItem` | `String` | — | The currently selected item. |
| `onClick` | `() -> Unit` | — | Callback invoked when the field is clicked. |
| `isErrorActive` | `Boolean` | `false` | Whether there's an error (affects label color). |

---

## Selection controls

#### `CheckBoxCapiro`
A branded checkbox with a configurable scale and enabled state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `isChecked` | `Boolean` | — | The current checked state. |
| `onCheckedChange` | `(Boolean) -> Unit` | — | Callback invoked when the state changes. |
| `scale` | `Float` | `1.1f` | The scale factor for the checkbox size. |
| `isEnabled` | `Boolean` | `true` | Whether the checkbox is enabled. |

#### `CheckBoxAndTextCapiro`
A checkbox with an accompanying text label.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `isChecked` | `Boolean` | — | The current checked state. |
| `onSelectedItemChange` | `(String) -> Unit` | — | Callback invoked when the state changes, passing the text. |
| `text` | `String` | — | The label text next to the checkbox. |
| `scale` | `Float` | `1.1f` | The scale factor for the checkbox size. |
| `isEnabled` | `Boolean` | `true` | Whether the checkbox is enabled. |

#### `RadioButtonCapiro`
A radio button whose selected state is derived from `selection == identifier`.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `selection` | `String` | — | The currently selected identifier. |
| `identifier` | `String` | — | This radio button's unique identifier. |
| `onClick` | `(String) -> Unit` | — | Invoked when clicked, receiving this button's identifier. |
| `isEnabled` | `Boolean` | `true` | Whether the radio button is enabled. |

#### `RadioButtonTextCapiro`
A radio button with a text label.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `selection` | `String` | — | The currently selected identifier. |
| `identifier` | `String` | — | This radio button's unique identifier. |
| `onClick` | `(String) -> Unit` | — | Invoked when clicked, receiving this button's identifier. |
| `isEnabled` | `Boolean` | `true` | Whether the radio button is enabled. |

#### `SwitchCapiro`
A toggle switch with a text label.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | The label text alongside the switch. |
| `isChecked` | `Boolean` | — | The current state of the switch. |
| `onCheckedChange` | `(Boolean) -> Unit` | — | Callback triggered when the state changes. |
| `textStyle` | `TextStyle` | `getTypography().bodyMedium` | The style of the label text. |
| `textColor` | `Color` | `GreenCapiro` | The color of the label text. |

---

## Display & layout

#### `CardCapiro`
A rounded, elevated container with inner padding for any content.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `innerComposable` | `@Composable () -> Unit` | — | Content displayed inside the card. |
| `modifier` | `Modifier` | `Modifier` | Modifier to customize layout/appearance. |
| `backgroundColor` | `Color` | `Color.White` | The background color of the card. |
| `paddingInner` | `Dp` | `16.dp` | The padding applied inside the card. |

#### `ImageCapiro`
Displays a drawable resource with fit scaling.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imageSourceId` | `@DrawableRes Int` | — | The resource ID of the drawable to display. |
| `modifier` | `Modifier` | `Modifier` | Modifier to customize layout/appearance. |

#### `PolygonCapiro`
A hexagonal shape, optionally hosting image content (e.g. profile photos).

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | — | Modifier applied to the polygon. |
| `imageContent` | `@Composable (Modifier) -> Unit` | `{}` | Optional content displayed inside the polygon. |

#### `ToastCapiro`
Shows a toast message from a string resource ID held in mutable state.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `messageResourceId` | `MutableState<Int?>` | — | Mutable state holding the string resource ID to display. |

#### `DialogTitle`
A centered dialog title with an optional close button.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text` | `String` | — | The text to display in the dialog title. |
| `onCloseClick` | `(() -> Unit)?` | `null` | Invoked when the close button is clicked. If `null`, no close button. |

#### `DialogTitle2`
A centered image header with rounded corners for dialogs.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imageResource` | `@DrawableRes Int` | — | The drawable resource ID for the image. |

---

## Navigation

#### `ToolbarCapiro`
An app toolbar with farm info, a section title, and a user-menu button that opens the account dialog.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `user` | `String` | — | The username shown in the user menu button. |
| `farm` | `String` | — | The farm name shown on the toolbar. |
| `section` | `String` | — | The current section/area title. |
| `userAbbreviation` | `String` | — | Abbreviated user name shown in the menu button. |
| `onBackUpClick` | `(() -> Unit)?` | `null` | Invoked when "backup" is clicked in the menu dialog. |
| `onPrintersClick` | `(() -> Unit)?` | `null` | Invoked when "printers" is clicked in the menu dialog. |
| `onLogoutClick` | `(() -> Unit)?` | `null` | Invoked when "logout" is clicked in the menu dialog. |

#### `ToolbarMenuLayoutDialogCapiro`
The account options dialog (logout / backup / printers).

| Parameter | Type | Default | Description |
|---|---|---|---|
| `user` | `String` | — | The user name to display. |
| `onLogoutClick` | `(() -> Unit)?` | `null` | Invoked on logout. Hidden if `null`. |
| `onBackUpClick` | `(() -> Unit)?` | `null` | Invoked on backup. Hidden if `null`. |
| `onPrintersClick` | `(() -> Unit)?` | `null` | Invoked on printers. Hidden if `null`. |
| `isOpenDialog` | `Boolean` | — | Whether the dialog is open. |
| `onCloseDialog` | `() -> Unit` | `{}` | Invoked when the dialog is closed. |

#### `CurrentAccountMenuTopBar`
The top bar of the account menu with a "close session" action.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `onCloseSession` | `() -> Unit` | — | Invoked when the close-session action is triggered. |

---

## Lists, items & labels

#### `ItemManualCapiro`
A scanned-item row with number, label, custom content, and an optional delete button.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `itemNumber` | `String` | — | The number associated with the item. |
| `scannedLabel` | `String` | — | The label/description of the item. |
| `mainComposable` | `@Composable () -> Unit` | — | The main content displayed within the row. |
| `onDeleteClick` | `(() -> Unit)?` | `null` | Removes the item. Hidden if `null`. |
| `color` | `Color` | `GreenCapiro` | Background color for the label. |
| `colorBackground` | `Color` | `WhiteCapiro` | Background color for the card. |

#### `ItemScannerCapiro`
A scanned-item row with number, label, custom content, and an optional expand button.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `itemNumber` | `String` | — | The number associated with the item. |
| `scannedLabel` | `String` | — | The label/description of the item. |
| `mainComposable` | `@Composable () -> Unit` | — | The main content displayed within the row. |
| `onExpandClick` | `(() -> Unit)?` | `null` | Invoked when the expand button is clicked. |
| `color` | `Color` | — | Color for the label background. |
| `colorBackground` | `Color` | — | Background color for the card. |

#### `ItemExpandableCapiro`
An expandable item card that reveals a list of variety details.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `itemNumber` | `String` | — | The number associated with the item. |
| `customer` | `String` | — | The customer/label to display. |
| `mainComposable` | `@Composable () -> Unit` | — | The main content displayed. |
| `listVarieties` | `List<VarietyReport>` | — | Varieties shown when expanded. |
| `color` | `Color` | — | Color for the label background. |
| `colorBackground` | `Color` | — | Background color for the card. |

#### `LabelManualCapiro`
Manual A / Y / W + consecutive label entry fields (Y and W as dropdowns).

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textValueA` | `String` | — | The value for the "A" field. |
| `textValueY` | `String` | — | The value for the "Y" field. |
| `textValueW` | `String` | — | The value for the "W" field. |
| `textConsecutive` | `String` | — | The value for the consecutive field. |
| `onTextChangeA` | `(String) -> Unit` | — | Invoked when "A" changes. |
| `onTextChangeY` | `(String) -> Unit` | — | Invoked when "Y" changes. |
| `onTextChangeW` | `(String) -> Unit` | — | Invoked when "W" changes. |
| `itemsY` | `List<String>` | — | Items for the "Y" dropdown. |
| `itemsW` | `List<String>` | — | Items for the "W" dropdown. |
| `onTextChangeConsecutive` | `(String) -> Unit` | — | Invoked when the consecutive field changes. |

#### `LabelManualCapiroWeekText`
Variant of `LabelManualCapiro` where "W" is a free-text field instead of a dropdown.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textValueA` | `String` | — | The value for the "A" field. |
| `textValueY` | `String` | — | The value for the "Y" field. |
| `textValueW` | `String` | — | The value for the "W" field. |
| `textConsecutive` | `String` | — | The value for the consecutive field. |
| `onTextChangeA` | `(String) -> Unit` | — | Invoked when "A" changes. |
| `onTextChangeY` | `(String) -> Unit` | — | Invoked when "Y" changes. |
| `onTextChangeW` | `(String) -> Unit` | — | Invoked when "W" changes. |
| `itemsY` | `List<String>` | — | Items for the "Y" dropdown. |
| `onTextChangeConsecutive` | `(String) -> Unit` | — | Invoked when the consecutive field changes. |

#### `LabelManualFarmCapiro`
Variant of `LabelManualCapiro` with an additional "F" (farm) dropdown field.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `textValueA` | `String` | — | The value for the "A" field. |
| `textValueY` | `String` | — | The value for the "Y" field. |
| `textValueW` | `String` | — | The value for the "W" field. |
| `textValueF` | `String` | — | The value for the "F" field. |
| `textConsecutive` | `String` | — | The value for the consecutive field. |
| `onTextChangeA` | `(String) -> Unit` | — | Invoked when "A" changes. |
| `onTextChangeY` | `(String) -> Unit` | — | Invoked when "Y" changes. |
| `onTextChangeW` | `(String) -> Unit` | — | Invoked when "W" changes. |
| `onTextChangeF` | `(String) -> Unit` | — | Invoked when "F" changes. |
| `itemsY` | `List<String>` | — | Items for the "Y" dropdown. |
| `itemsW` | `List<String>` | — | Items for the "W" dropdown. |
| `itemsF` | `List<String>` | — | Items for the "F" dropdown. |
| `onTextChangeConsecutive` | `(String) -> Unit` | — | Invoked when the consecutive field changes. |

---

## Date & filters

#### `CalendarBarCapiro`
A date bar showing day / weekday / month with a calendar trigger.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `day` | `String` | — | The day of the month to display. |
| `dayOfWeek` | `String` | — | The weekday name (e.g. "Jueves"). |
| `month` | `String` | — | The month name (e.g. "Junio"). |
| `spacerHeight` | `Dp` | `6.dp` | The height of the space below the month text. |
| `onCalendarClick` | `() -> Unit` | — | Invoked when the calendar icon is clicked. |

#### `WeekFilterCapiro`
A row of day checkboxes for filtering by weekday. Renders one column per entry in `daysChecked`.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `daysChecked` | `Map<String, Boolean>` | — | Map of day labels to their checked state. |
| `onDayCheckedChange` | `(String, Boolean) -> Unit` | — | Invoked when a day's checkbox changes. |

#### `TwoOptionsButtonCapiro`
Two mutually-exclusive toggle buttons (only one active at a time).

| Parameter | Type | Default | Description |
|---|---|---|---|
| `text1` | `String` | — | Text on the first button. |
| `text2` | `String` | — | Text on the second button. |
| `onClick1` | `() -> Unit` | — | Action for the first button. |
| `onClick2` | `() -> Unit` | — | Action for the second button. |

---

## Input utilities

#### `ScannerFieldCapiro`
A hidden, auto-focused text field that processes barcode-scanner input.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `scanProcess` | `(String) -> Unit` | — | Invoked with the scanned text when the user enters input. |

---

## Dialogs

All dialogs take an open/close boolean and render nothing while closed.

#### `LoadingDialog`
A blocking loading dialog with a title, message, and progress indicator.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `isDialogOpenState` | `Boolean` | — | Controls the visibility of the dialog. |
| `title` | `String` | — | The title text. |
| `message` | `String` | — | The message text. |
| `onClose` | `() -> Unit` | — | Invoked when the dialog is closed. |

#### `OneOptionDialogCapiro`
A single-action dialog with an image and an (optionally bolded) message.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imaResource` | `@DrawableRes Int?` | — | Drawable for the image. |
| `backgroundColor` | `Color` | `WhiteCapiro` | The background color of the dialog. |
| `innerComposable` | `@Composable (() -> Unit)?` | `null` | Optional custom content inside the dialog. |
| `boldText` | `String?` | `null` | Substring of `message` to render bold. |
| `message` | `String?` | `null` | The message text. |
| `positiveButtonText` | `String` | — | Text for the positive button. |
| `onPositiveButtonClickEvent` | `() -> Unit` | — | Invoked when the positive button is clicked. |
| `isDialogOpenState` | `Boolean` | — | Controls the visibility of the dialog. |

#### `TwoOptionsDialogCapiro`
A confirm/cancel dialog with two buttons.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `imaResource` | `@DrawableRes Int` | — | Drawable for the image. |
| `boldText` | `String?` | `null` | Substring of `message` to render bold. |
| `message` | `String` | — | The message text. |
| `innerComposable` | `@Composable (() -> Unit)?` | `null` | Optional custom content inside the dialog. |
| `positiveButtonText` | `String` | — | Text for the positive button. |
| `negativeButtonText` | `String` | — | Text for the negative button. |
| `onPositiveButtonClickEvent` | `() -> Unit` | — | Invoked when the positive button is clicked. |
| `onNegativeButtonClickEvent` | `() -> Unit` | — | Invoked when the negative button is clicked. |
| `isDialogOpenState` | `Boolean` | — | Controls the visibility of the dialog. |

#### `CustomDatePicker`
A custom calendar date picker with optional date-range limiting.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `setLimit` | `String?` | `null` | Limit selectable dates: `"lower"`, `"upper"`, or `null` for no limit. |
| `isOpenDialog` | `Boolean` | `false` | Controls the visibility of the dialog. |
| `date` | `LocalDate` | `LocalDate.now()` | The initial date displayed. |
| `onDateSelected` | `(LocalDate) -> Unit` | — | Invoked when a date is selected. |
| `onCloseDialog` | `() -> Unit` | — | Invoked when the dialog is closed. |

#### `SearchListDialogCapiro<T>`
A generic searchable list dialog with a caller-supplied item slot.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `searchText` | `String` | — | The current search input text. |
| `onSearchTextChange` | `(String) -> Unit` | — | Handles changes to the search text. |
| `titleIdRes` | `@StringRes Int` | — | Resource ID for the title. |
| `overTitle` | `String?` | `null` | Optional text above the main title. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<T>` | — | Data items to display. |
| `onCleanText` | `() -> Unit` | — | Clears the search text. |
| `onSearchItemSelectedChangeState` | `(T) -> Unit` | — | Invoked when an item is selected. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |
| `item` | `@Composable (T) -> Unit` | — | Renders each item in the list. |

#### `SimpleListDialogsCapiro<T>`
A generic list dialog with a custom item slot.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `titleIdRes` | `@StringRes Int` | — | Resource ID for the title. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<T>` | — | Data items to display. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |
| `items` | `@Composable (T) -> Unit` | — | Renders each item in the list. |

#### `ListDialogCapiro`
A dialog with a list of string items.

**Deprecated:** use `SimpleListDialogsCapiro<T>` instead.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `titleIdRes` | `@StringRes Int` | — | Resource ID for the title. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<String>` | — | String items to display. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |
| `itemSelectedEvent` | `(String) -> Unit` | — | Invoked when an item is selected. |

#### `RadioButtonDialogCapiro`
A dialog with a list of radio-button options.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `titleIdRes` | `@StringRes Int` | — | Resource ID for the title. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<String>` | — | Items for the radio buttons. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |
| `text` | `String` | — | The currently selected value. |
| `onSelectionChanged` | `(String) -> Unit` | — | Handles selection changes. |

#### `SimpleDialogHistoryCapiro<T>`
A generic list dialog with a title and close button.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `title` | `String` | — | The title text. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<T>` | — | Data items to display. |
| `composableItem` | `@Composable (T) -> Unit` | — | Renders each item. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |

#### `DialogHistoryCapiro<T>`
A searchable history dialog with a built-in week filter.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `searchText` | `String` | — | The current search input text. |
| `onSearchTextChange` | `(String) -> Unit` | — | Handles changes to the search text. |
| `title` | `String` | — | The title text. |
| `isTheDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `allData` | `Array<T>` | — | Data items to display. |
| `composableItem` | `@Composable (T) -> Unit` | — | Renders each item. |
| `onCleanText` | `() -> Unit` | — | Clears the search text. |
| `onSearchItemSelectedChangeState` | `(String) -> Unit` | — | Invoked when a search filter is selected. |
| `onCloseDialogEvent` | `() -> Unit` | — | Closes the dialog. |
| `daysState` | `Map<String, Boolean>` | — | Day-abbreviation → checked state for filtering. |
| `onDayCheckedChange` | `(String, Boolean) -> Unit` | — | Invoked when a day filter changes. |

#### `ListTextFieldDialogCapiro`
A dialog combining a selectable list with a text/units input.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |
| `titleIdRes` | `@StringRes Int` | — | Resource ID for the title. |
| `isDialogOpenState` | `Boolean` | — | Whether the dialog is open. |
| `dataList` | `List<String>` | — | The list of data to display. |
| `text` | `String` | — | The text in the input field. |
| `onSelectionChanged` | `(String) -> Unit` | — | Invoked when the selection changes. |
| `onClose` | `() -> Unit` | — | Invoked when the dialog is closed. |
| `onAccept` | `() -> Unit` | — | Invoked when the accept button is clicked. |
| `units` | `String` | `""` | Units displayed next to the input field. |

#### `SyncDialogCapiro`
A progress dialog showing the status of multiple sync items.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `isTheDialogOpen` | `Boolean` | — | Controls the visibility of the dialog. |
| `syncItems` | `Array<SyncItem>` | — | Sync items to display with their status. |
| `onCloseDialogClickEvent` | `() -> Unit` | — | Invoked when the close button is clicked. |
| `amountItemsSyncProcessFinish` | `MutableState<Int>` | — | Tracks the number of completed sync items. |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the dialog. |

---

## Screens

#### `SplashScreenCapiro`
A splash screen with app icon, a support message, and the app version.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `iconAppResource` | `@DrawableRes Int` | — | Drawable of the app icon. |
| `version` | `String` | — | Version string shown at the bottom. |

#### `LoginScreenCapiro`
A login screen with user/password fields, a backup button, and a login button.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `iconAppResource` | `@DrawableRes Int` | — | Drawable of the app icon. |
| `onBackUpClick` | `() -> Unit` | — | Invoked when the backup button is clicked. |
| `user` | `String` | — | The current user-field value. |
| `password` | `String` | — | The current password-field value. |
| `errorMessage` | `String?` | `null` | Optional error message to display. |
| `onUserTextChange` | `(String) -> Unit` | — | Invoked when the user text changes. |
| `onPasswordTextChange` | `(String) -> Unit` | — | Invoked when the password text changes. |
| `onLoginClick` | `() -> Unit` | — | Invoked when the login button is clicked. |

---

## QR scanner

Built on CameraX + ML Kit. The `android.permission.CAMERA` permission is declared by the library and merged into the consuming app; the components request it at runtime on first open. The camera is released automatically when the dialog leaves the composition.

#### `QrScannerButtonCapiro`
A ready-to-use scan button that manages the scanner dialog — the easiest way to add QR scanning to a screen.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `onQrCodeScanned` | `(String) -> Unit` | — | Invoked with the raw value of the scanned code (the dialog closes first). |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the button. |
| `label` | `String?` | `null` | Optional label displayed under the scan icon. |
| `onlyQrCodes` | `Boolean` | `true` | When `true` only QR codes are detected; when `false` any supported barcode is. |
| `scanningText` | `String` | `"Escaneando…"` | Hint text overlaid at the bottom of the preview. |
| `permissionDeniedText` | `String` | `"Se necesita permiso de cámara para escanear"` | Toast shown when camera permission is denied. |

#### `QrScannerDialogCapiro`
A self-contained, state-hoisted scanner dialog. Renders nothing while `isOpen` is `false`; close it from `onQrCodeScanned`.

| Parameter | Type | Default | Description |
|---|---|---|---|
| `isOpen` | `Boolean` | — | Whether the scanner dialog is shown. |
| `onQrCodeScanned` | `(String) -> Unit` | — | Invoked with the raw value of the first code detected. |
| `onDismiss` | `() -> Unit` | — | Invoked when dismissed (tap outside, or permission denied). |
| `modifier` | `Modifier` | `Modifier` | Modifier applied to the camera preview container. |
| `onlyQrCodes` | `Boolean` | `true` | When `true` only QR codes are detected; when `false` any supported barcode is. |
| `scanningText` | `String` | `"Escaneando…"` | Hint text overlaid at the bottom of the preview. |
| `permissionDeniedText` | `String` | `"Se necesita permiso de cámara para escanear"` | Toast shown when camera permission is denied. |
