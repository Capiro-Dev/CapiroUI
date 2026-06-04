# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## What this is

A reusable Jetpack Compose UI component library for Capiro's Android apps, published to JitPack as `com.github.Capiro-Dev:composables`. The repo has two Gradle modules:

- **`composables`** — the library itself (the product). All shippable code lives here under package `com.capiro.composables`. This is the module that gets published.
- **`app`** — a throwaway demo/showcase harness (package `com.capiro.capiroui`). It only exists to visually preview components; `MainActivity` just renders whatever component is being worked on (one call is uncommented at a time inside `setContent`). The `Test*.kt` files here are Compose previews, not unit tests. Do not treat `app` as production code.

## Build & verify

This is a Windows environment with the Gradle wrapper (`gradlew.bat`). Use `&&`-free chaining in PowerShell (`;` + `if ($?)`).

```powershell
.\gradlew.bat :composables:assembleDebug      # build the library
.\gradlew.bat :app:installDebug               # build + install the demo app on a device/emulator
.\gradlew.bat :composables:publishToMavenLocal # publish locally to test consumption
.\gradlew.bat build                           # build everything
```

There is **no real test suite** — `ExampleUnitTest.kt` / `ExampleInstrumentedTest.kt` are the default Android Studio stubs. Verification is visual: run a component in `app`'s `MainActivity` or use the `@Preview` functions (Android Studio preview pane). The `virtual-user-quality` and `run`/`verify` skills can drive the app on a device when end-to-end confirmation is needed.

## Releasing a new version

Bump `version` in `composables/build.gradle` (the `afterEvaluate { publishing { ... } }` block, currently `1.12.55`), then tag/push so JitPack builds from the tag. The publication coordinates are `com.github.Capiro-Dev:composables:<version>`.

## Architecture & conventions

**Component layering.** Components are organized by composition level, not by feature:
- `athomic_composables/` (sic — "atomic") — primitives: buttons, textfield, checkbox, radiobutton, switchcapiro, spinners, card, image, toast, photohexagon.
- `util_composables/` — mid-level reusable pieces (calendar bar, scanner field, expandable items, menus, week filter, labels).
- `dialogs/`, `dropdown/`, `toolbar/`, `screens/` — higher-level assemblies (full dialogs, a login screen, a splash screen).
- `model/`, `theme/`, plus root-level `constant.kt` / `util.kt`.

**Naming.** Public composables are suffixed `Capiro` (e.g. `ButtonCapiro`, `SearchListDialogCapiro`, `TextFieldOutlinedCapiro`). Theme colors are suffixed `Capiro` (`GreenCapiro`, `BeigeCapiro`, etc.) in `theme/Colors.kt` — `GreenCapiro` (`0xFF41695B`) is the brand primary and the default accent across components. Source files are named in `PascalCase.kt`; keep new files PascalCase. (Directory/package names still use `snake_case`, e.g. `athomic_composables`, `util_composables` — leave those as-is to avoid touching package declarations and imports.)

**Theming is custom, not MaterialTheme.** Typography lives in `composables/src/main/java/com/capiro/composables/theme/Type.kt`, which is declared in the **default (root) package** — it has no `package` statement. Consequently other files import its members bare: `import getTypography`, `import TypographyProvider`. Preserve this; don't add a package to `Type.kt` without updating every bare import.
- Use `getTypography()` (a `@Composable` reading `LocalConfiguration`) inside composables, and `TypographyProvider.typography` for non-composable/static contexts.
- Typography auto-scales: screens ≤ 400dp wide get the base `Typography`; wider get `TypographyLarge`. Don't hardcode `fontSize`/`TextStyle` when a typography token fits.
- The font family (`customFont`) is loaded from `res/font` (SF Pro Display). Components reference brand colors and typography directly rather than going through `MaterialTheme.colorScheme`.

**Generic, slot-based composables.** The current direction (see recent commits deprecating `ListDialogCapiro` in favor of `SimpleListDialogsCapiro`) is to make list/dialog components generic `fun <T>` with a caller-supplied item slot `item: @Composable (T) -> Unit`, rather than hardcoding the row content. Follow this pattern for new list-like components: take `Array<T>`/data + an `item` slot + selection callbacks.

**Stability.** Wrap values passed into composables that Compose can't infer as stable with `StableWrapper<T>` (`@Stable data class` in `util_composables/StableWrapper.kt`) to avoid unnecessary recomposition.

**State is hoisted.** Components are stateless and driven by parameters + callbacks (`textInput` + `onTextChangeEvent`, `isTheDialogOpenState` + `onCloseDialogEvent`, etc.). The dialog pattern is: the composable takes an `isOpen` boolean and returns early / renders nothing when false, wrapping content in `androidx.compose.ui.window.Dialog`.

**Public API expectations.** Every public composable should have a KDoc block documenting each `@param`, and a `private` `@Preview` function alongside it. Match this when adding components.

**App context.** `BaseApplicationCapiroUI` exposes `appContext` and `activity` statically via its companion — consuming apps that need a Context from the library rely on this.

## Toolchain

Kotlin 1.9.0, AGP 8.4.1, Java/JVM 17, Compose Compiler 1.5.1, Compose BOM 2023.08.00. `compileSdk 34`; library `minSdk 26`, app `minSdk 29`. Dependencies are managed via the version catalog at `gradle/libs.versions.toml` — add/upgrade deps there, not inline in `build.gradle`. JitPack is configured as a repository in `settings.gradle`.
