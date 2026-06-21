# AGENTS.md — X Smart (KMM Project)

## Project Overview

X Smart is a Kotlin Multiplatform Mobile (KMM) utility app for everyday calculations and conversions. The Android app is production-ready with Jetpack Compose UI; the iOS app is in early scaffolding stage using SwiftUI.

## Architecture

Clean Architecture with MVVM/MVI-inspired unidirectional data flow:

- **Presentation**: Platform-specific UI (Compose on Android, SwiftUI on iOS)
- **Domain**: Use cases and entities (shared via KMM `commonMain`)
- **Data**: Repositories and data sources (shared via KMM `commonMain`)
- **DI**: Koin modules wiring all layers together

State management uses `UiState`, `UiIntent`, and `UiEffect` patterns.

## Module Map

```
xsmart/
├── androidApp/                 → Android entry point (Application, MainActivity)
├── iosApp/                     → iOS entry point (SwiftUI, early stage)
├── feature/
│   ├── dashboard/              → Dashboard feature (Android library module)
│   └── salarycalculator/       → Vietnam salary calculator (Android library module)
├── share/
│   ├── common/                 → Base utilities, extensions (Android + iOS)
│   ├── domain/                 → Use cases, entities (Android + iOS)
│   ├── data/                   → Repositories, data sources (Android + iOS)
│   ├── di/                     → Koin DI configuration (Android + iOS)
│   ├── android/                → Android-only shared code (ViewModel base)
│   └── ui/                     → Shared Compose UI components (Android-only)
```

## Key Technologies

| Layer | Stack |
|---|---|
| Language | Kotlin 2.2.21, Swift 5.0 |
| Build | Gradle Kotlin DSL, Xcode |
| UI (Android) | Jetpack Compose, Material Design 3 |
| UI (iOS) | SwiftUI (planned) |
| DI | Koin 3.2.0 |
| Networking | Ktor Client 2.3.0 |
| Database | SQLDelight 2.0.2 |
| Async | Kotlin Coroutines 1.6.4 + Flow |
| Serialization | Kotlinx Serialization 1.6.3 |

## Build & Run

### Android
```bash
./gradlew assembleDebug
./gradlew installDebug
```

### iOS
Open `iosApp/iosApp.xcodeproj` in Xcode. The project has build phases that run:
```
./gradlew :share:common:embedAndSignAppleFrameworkForXcode
./gradlew :share:domain:embedAndSignAppleFrameworkForXcode
```
These produce `commonKit` and `domainKit` XCFrameworks linked into the iOS target.

### Requirements
- Android Studio, JDK 17+, Android SDK API 24+
- Xcode (for iOS), macOS

## KMM Framework Integration

Shared modules export iOS frameworks:
- `share:common` → `commonKit`
- `share:domain` → `domainKit`
- `share:data` → `dataKit`
- `share:di` → `diKit`

iOS targets: `iosX64`, `iosArm64`, `iosSimulatorArm64`. Deployment target: iOS 14.1.

## Coding Conventions

- Package root: `my.phatndt.xsmart` (shared), `my.xsmart.feature.*` (features)
- iOS bundle ID: `xsmart.iosApp`
- Android app ID: `my.phatndt.xsmart.android`
- Feature modules are self-contained Android library modules with their own Koin DI modules
- Shared modules use `commonMain` for cross-platform code, `androidMain`/`iosMain` for platform-specific implementations
- Gradle version catalog at `gradle/libs.versions.toml` manages all dependency versions

## Current Features

- Dashboard (feature hub)
- Vietnam Salary Calculator (gross-to-net, tax brackets, insurance deductions, regional support)
- Localization: Vietnamese (default), English

## Important Notes for Agents

- The `share/android/` and `share/ui/` modules are Android-only — do not reference them from iOS or `commonMain`
- iOS app currently only imports `commonKit` and `domainKit` — additional frameworks need Xcode build phase + linker flag additions
- Feature modules (`feature/*`) are Android library modules, not KMM — iOS features must be built natively in `iosApp/`
- When adding new shared KMM code, place it in `commonMain` source sets; use `expect`/`actual` for platform-specific implementations
