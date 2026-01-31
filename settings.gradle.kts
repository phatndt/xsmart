enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
        maven("https://jitpack.io")
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
        maven("https://jitpack.io")
    }
}

rootProject.name = "X_Smart"
include(":androidApp")
include(":share:data")
include(":share:domain")
include(":share:common")
include(":share:di")
include(":share:android")
include(":share:ui")

include(":feature")
include(":feature:salarycalculator")
include(":feature:dashboard")
