# X Smart - Project Summary

## Overview

X Smart is a Kotlin Multiplatform Mobile (KMM) utility application focused on everyday calculations and conversions. Currently shipping on Android with Jetpack Compose UI, with iOS support planned. The app follows Clean Architecture with modular feature design.

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin 2.2.21 |
| Platform | KMM (Android + iOS targets) |
| UI | Jetpack Compose + Material Design 3 |
| Architecture | MVVM / MVI-inspired, Clean Architecture |
| DI | Koin 3.2.0 |
| Networking | Ktor Client 2.3.0 |
| Database | SQLDelight 2.0.2 |
| Async | Kotlin Coroutines 1.6.4 + Flow |
| Serialization | Kotlinx Serialization 1.6.3 |
| Build | Gradle Kotlin DSL, Version Catalog |
| Min SDK | 24 / Compile SDK 36 |

## Module Structure

```
xsmart/
├── androidApp/                 # Android app entry point (Application + MainActivity)
│
├── feature/                    # Feature modules
│   ├── dashboard/              # Main dashboard with feature overview
│   └── salarycalculator/       # Vietnam salary calculator
│
├── share/                      # Shared KMM modules
│   ├── common/                 # Base utilities, extensions (Android + iOS)
│   ├── domain/                 # Use cases, entities, business logic (Android + iOS)
│   ├── data/                   # Repositories, data sources (Android + iOS)
│   ├── di/                     # Koin DI module configuration (Android + iOS)
│   ├── android/                # Android-only shared code (ViewModel base, etc.)
│   └── ui/                     # Shared Compose UI components (Android-only)
│
└── iosApp/                     # iOS app (planned)
```

## Module Dependency Graph

```
androidApp
├── feature:dashboard
├── feature:salarycalculator
├── share:common
├── share:domain
├── share:di
├── share:android
└── share:ui

feature:dashboard / feature:salarycalculator
├── share:common
├── share:domain
├── share:di
├── share:android
└── share:ui

share:di → share:common, share:domain, share:data
share:data → share:common, share:domain
share:domain → share:common
```

## Current Features

- **Dashboard** — Feature hub with quick access, Material 3 UI, dark mode
- **Vietnam Salary Calculator** — Gross-to-net calculation with tax brackets, insurance deductions (social, health, unemployment), regional area support, configurable rules
- **Localization** — Vietnamese (default) and English

## Architecture Highlights

- Unidirectional data flow with `UiState`, `UiIntent`, `UiEffect`
- Repository pattern abstracting data sources
- Use case pattern for single-responsibility business operations
- Koin modules organized per layer, initialized in `MainApplication`
- KMM shared modules produce iOS XCFrameworks (`commonKit`, `domainKit`, `dataKit`, `diKit`)

## Build & Run

```bash
# Sync and build
./gradlew assembleDebug

# Run on connected device/emulator
./gradlew installDebug
```

Requires: Android Studio, JDK 17+, Android SDK API 24+

## Roadmap

BMI calculator, currency converter, unit converter, compass, iOS implementation, cloud sync, widget support, additional languages.
