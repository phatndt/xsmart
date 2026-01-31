plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
    alias(libs.plugins.kotlin.compose.compiler)
}

android {
    namespace = "my.phatndt.xsmart.android"
    compileSdk = libs.versions.compileSdk.get().toInt()
    defaultConfig {
        applicationId = "my.phatndt.xsmart.android"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk  = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.app.version.code.get().toInt()
        versionName =  libs.versions.app.version.name.get()
        vectorDrawables {
            useSupportLibrary = true
        }
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.4"
    }
    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    signingConfigs {
        getByName("debug") {

        }

        create("release") {
            storeFile = file("../upload-keystore.jks")
            storePassword = providers.gradleProperty("STORE_PASSWORD").getOrElse("")
            keyAlias = providers.gradleProperty("KEY_ALIAS").getOrElse("")
            keyPassword = providers.gradleProperty("KEY_PASSWORD").getOrElse("")
        }
    }
    buildTypes {
        release {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            signingConfig = signingConfigs.getByName("release")
        }
        debug {
            isMinifyEnabled = false
            isShrinkResources = false
            isDebuggable = true
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

dependencies {
    implementation(projects.share.common)
    implementation(projects.share.domain)
    implementation(projects.share.di)
    implementation(projects.share.android)
    implementation(projects.share.ui)

    implementation(projects.feature.salarycalculator)
    implementation(projects.feature.dashboard)
    
    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.activity.compose)

    implementation(libs.androidx.core)

    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.material.icons.extended)

    implementation(libs.androidx.constraintlayout.compose)

    implementation(libs.androidx.navigation.compose)
    
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.runtime.compose)

    implementation(libs.accompanist.navigation.animation)

    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.koin.androidx.compose)
    implementation(libs.kotlinx.coroutines.core)
}
