# AGENTS.md — X Smart iOS App

## Overview

The iOS app for X Smart, built with SwiftUI. Currently in early scaffolding stage — the shared KMM frameworks (`commonKit`, `domainKit`) are integrated but no features are implemented yet.

## Current State

- Minimal SwiftUI app with a placeholder `ContentView`
- Imports `commonKit` from the KMM shared module
- Folder structure is prepared but empty: `App/`, `DI/`, `Features/`, `Shared/` (Components, Extensions, Theme)
- No navigation, no feature screens, no DI setup yet

## Project Structure

```
iosApp/
├── iosApp/
│   ├── iOSApp.swift              → App entry point (@main)
│   ├── ContentView.swift         → Root view (placeholder)
│   ├── Info.plist                → App configuration
│   ├── App/                      → App-level setup (empty)
│   ├── DI/                       → Dependency injection (empty)
│   ├── Features/                 → Feature screens (empty)
│   ├── Shared/
│   │   ├── Components/           → Reusable UI components (empty)
│   │   ├── Extensions/           → Swift extensions (empty)
│   │   └── Theme/                → Colors, typography, styling (empty)
│   ├── Assets.xcassets/          → App icons, colors
│   └── Preview Content/          → SwiftUI preview assets
├── iosApp.xcodeproj/             → Xcode project
└── MyLibrary/                    → (unused)
```

## Build Configuration

- Xcode project: `iosApp.xcodeproj`
- Swift version: 5.0
- Deployment target: iOS 14.1
- Bundle ID: `xsmart.iosApp`
- Device families: iPhone + iPad

## KMM Framework Integration

Two Xcode build phases run Gradle tasks before compilation:
```
./gradlew :share:common:embedAndSignAppleFrameworkForXcode
./gradlew :share:domain:embedAndSignAppleFrameworkForXcode
```

Framework search paths point to:
```
$(SRCROOT)/../share/common/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)
$(SRCROOT)/../share/domain/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)
```

Linked frameworks: `commonKit`, `domainKit`

To add more shared modules (e.g. `dataKit`, `diKit`):
1. Add a new "Run Script" build phase with `./gradlew :share:<module>:embedAndSignAppleFrameworkForXcode`
2. Add the framework search path: `$(SRCROOT)/../share/<module>/build/xcode-frameworks/$(CONFIGURATION)/$(SDK_NAME)`
3. Add `-framework <frameworkName>` to `OTHER_LDFLAGS`

## Coding Conventions

- Use SwiftUI for all UI — no UIKit unless absolutely necessary
- Follow the prepared folder structure: features go in `Features/`, shared components in `Shared/Components/`
- Mirror the Android architecture: MVVM with `UiState`/`UiIntent`/`UiEffect` patterns adapted for Swift (ObservableObject + @Published)
- Import KMM frameworks (`commonKit`, `domainKit`) to access shared domain models and use cases
- Keep feature screens self-contained with their own ViewModel
- Use Swift naming conventions (camelCase properties, PascalCase types)

## Important Notes for Agents

- Only `commonKit` and `domainKit` are currently linked — importing other KMM frameworks will fail until build phases are added
- The `share/android/` and `share/ui/` modules have no iOS targets — never try to import them
- Feature modules under `feature/*` are Android-only library modules — iOS features must be built natively here in SwiftUI
- KMM shared code uses Kotlin coroutines/Flow — on iOS these surface as callbacks or async/await bridges; handle accordingly
- The root project must be built with Gradle first (`./gradlew build`) before Xcode can resolve the KMM frameworks
- When adding new features, create a subfolder under `Features/` matching the feature name (e.g. `Features/Dashboard/`, `Features/SalaryCalculator/`)
