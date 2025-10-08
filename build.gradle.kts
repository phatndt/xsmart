plugins {
    //trick: for the same plugin versions in all sub-modules
    alias(libs.plugins.androidApplication).apply(false)
    alias(libs.plugins.androidLibrary).apply(false)
    alias(libs.plugins.kotlinAndroid).apply(false)
    alias(libs.plugins.kotlinMultiplatform).apply(false)
    alias(libs.plugins.kotlinCocoapods).apply(false)
    id("app.cash.sqldelight").version("2.0.0-alpha05").apply(false)
    kotlin("plugin.serialization").version("1.8.0").apply(false)
    id("com.google.gms.google-services").version("4.3.15").apply(false)
    alias(libs.plugins.androidKotlinMultiplatformLibrary) apply false
    alias(libs.plugins.compose.compiler) apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}

tasks.register("testClasses")
