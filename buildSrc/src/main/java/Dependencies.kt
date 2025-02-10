object Dependencies {
    const val ACTIVITY_COMPOSE =
        "androidx.activity:activity-compose:${Versions.ACTIVITY_COMPOSE_VERSION}"
    const val COMPOSE_UI = "androidx.compose.ui:ui:${Versions.COMPOSE_UI_VERSION}"
    const val COMPOSE_UI_PLATFORM = "androidx.compose.ui:ui-platform:${Versions.COMPOSE_UI_VERSION}"
    const val COMPOSE_UI_TOOL = "androidx.compose.ui:ui-tooling:${Versions.COMPOSE_UI_VERSION}"
    const val COMPOSE_UI_TOOL_PREVIEW =
        "androidx.compose.ui:ui-tooling-preview:${Versions.COMPOSE_UI_VERSION}"
    const val COMPOSE_UI_TEXT_GOOGLE_FONT =
        "androidx.compose.foundation:foundation:${Versions.COMPOSE_UI_VERSION}"
    const val COMPOSE_FOUNDATION =
        "androidx.compose.foundation:foundation:${Versions.COMPOSE_FOUNDATION_VERSION}"
    const val COMPOSE_MATERIAL3 = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val ANDROID_LIFECYCLE =
        "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.ANDROID_LIFECYCLE}"
    const val COMPOSE_MATERIAL2 = "androidx.compose.material:material:${Versions.MATERIAL2}"
    const val COMPOSE_NAVIGATION =
        "androidx.navigation:navigation-compose:${Versions.COMPOSE_NAVIGATIION}"
    const val COMPOSE_LIFECYCLE = "androidx.compose.material3:material3:${Versions.MATERIAL3}"
    const val LOTTIE = "com.airbnb.android:lottie-compose:${Versions.LOTTIE}"
    const val COMPOSE_MATERIAL_ICON_EXTENDED =
        "androidx.compose.material:material-icons-extended:${Versions.MATERIAL2}"

    const val KOTLIN_COROUTINE =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.KOTLIN_COROUTINE}"

    const val HILT_ANDROID = "com.google.dagger:hilt-android:${Versions.HILT}"
    const val HILT_COMPILER = "com.google.dagger:hilt-compiler:${Versions.HILT}"
    const val HILT_NAVIGATION_COMPOSE =
        "androidx.hilt:hilt-navigation-compose:${Versions.HILT_NAVIGATION_COMPSE}"
    const val LIFECYCLE_RUNTIME_COMPOSE =
        "androidx.lifecycle:lifecycle-runtime-compose:${Versions.LIFECYCLE_RUNTIME_COMPOSE}"
    const val SDP = "com.intuit.sdp:sdp-android:${Versions.SDP}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:2.9.0"
    const val GSON = "com.google.code.gson:gson"
    const val RETROFIT_KOTLIN_SERIALIZATION =
        "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    const val ACCOMPANIST_NAVIGATION_ANIMATION =
        "com.google.accompanist:accompanist-navigation-animation:${Versions.ACCOMPANIST_NAVIGATION_ANIMATION}"
    const val ACCOMPANIST_FLOW_LAYOUT =
        "com.google.accompanist:accompanist-flowlayout:${Versions.ACCOMPANIST_NAVIGATION_ANIMATION}"

    const val DATASOURCE = "androidx.datastore:datastore-preferences:1.0.0"
    const val SEEKER = "io.github.2307vivek:seeker:1.0.2"
    const val MPAndroidChart = "com.github.PhilJay:MPAndroidChart:v3.1.0"

    const val KOIN_ANDROID = "io.insert-koin:koin-android:3.2.0"
    const val KOIN_CORE = "io.insert-koin:koin-core:3.2.0"
    const val SQLDELIGHT_ANDROID_DRIVER = "app.cash.sqldelight:android-driver:${Versions.SQLDELIGHT}"
    const val SQLDELIGHT_NATIVE_DRIVER = "app.cash.sqldelight:native-driver:${Versions.SQLDELIGHT}"
    const val SQLDELIGHT_SQLITE_DRIVER = "app.cash.sqldelight:sqlite-driver:${Versions.SQLDELIGHT}"
    const val SQLDELIGHT_RUNTIME = "app.cash.sqldelight:runtime:${Versions.SQLDELIGHT}"

    object Kotlin {
        const val COROUTINE_CORE =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.KOTLIN_COROUTINE}"
        const val SERIALIZATION =
            "org.jetbrains.kotlinx:kotlinx-serialization-json:${Versions.KOTLIN_SERIALIZATION}"
        const val DATETIME = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.KOTLINX_DATETIME}"
    }

    object Koin {
        const val ANDROIDX_COMPOSE = "io.insert-koin:koin-androidx-compose:${Versions.KOIN}"
    }
    object Ktor {
        const val KTOR_CLIENT_CORE = "io.ktor:ktor-client-core:${Versions.KTOR}"
        const val KTOR_CLIENT_OKHTTP = "io.ktor:ktor-client-okhttp:${Versions.KTOR}"
        const val KTOR_CLIENT_DRAWIN = "io.ktor:ktor-client-darwin:${Versions.KTOR}"
        const val KTOR_CLIENT_ANDROID = "io.ktor:ktor-client-android:${Versions.KTOR}"
        const val KTOR_CLIENT_CONTENT_NEGOTIATION = "io.ktor:ktor-client-content-negotiation:${Versions.KTOR}"
        const val KTOR_JSON = "io.ktor:ktor-serialization-kotlinx-json:${Versions.KTOR}"
        const val KTOR_LOGGING = "io.ktor:ktor-client-logging:${Versions.KTOR}"
    }

    object Compose {
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout-compose:1.0.1"
    }
}
