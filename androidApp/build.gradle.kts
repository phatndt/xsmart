plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("com.google.gms.google-services")
}

android {
    namespace = "my.phatndt.xsmart.android"
    compileSdk = Config.COMPILE_SDK
    defaultConfig {
        applicationId = "my.phatndt.xsmart.android"
        minSdk = Config.MIN_SDK
        targetSdk  = Config.TARGET_SDK
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    // A - androidx
    implementation(libs.androidx.activity.ktx)
    implementation(Dependencies.COMPOSE_UI)
    implementation(Dependencies.COMPOSE_UI_TOOL)
    implementation(Dependencies.COMPOSE_UI_TOOL_PREVIEW)
    implementation(Dependencies.COMPOSE_FOUNDATION)
    implementation(Dependencies.ACTIVITY_COMPOSE)
    implementation(Dependencies.COMPOSE_MATERIAL2)
    implementation(Dependencies.COMPOSE_MATERIAL3)
    implementation(Dependencies.ANDROID_LIFECYCLE)
    implementation(Dependencies.LIFECYCLE_RUNTIME_COMPOSE)
    implementation(Dependencies.COMPOSE_UI_TEXT_GOOGLE_FONT)
    implementation(Dependencies.COMPOSE_NAVIGATION)
    implementation(Dependencies.COMPOSE_MATERIAL_ICON_EXTENDED)
    implementation(Dependencies.ACCOMPANIST_NAVIGATION_ANIMATION)
    implementation(Dependencies.Compose.CONSTRAINT_LAYOUT)
    implementation("com.github.PhilJay:MPAndroidChart:v3.1.0")
    implementation("io.insert-koin:koin-android:3.2.0")
    implementation("io.insert-koin:koin-core:$3.2.0")
    runtimeOnly("io.insert-koin:koin-androidx-compose:3.2.0")

    implementation(Dependencies.Koin.ANDROIDX_COMPOSE)
    implementation(libs.kotlin.couroutines.core)
}
