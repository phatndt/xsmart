plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("app.cash.sqldelight")
    kotlin("plugin.serialization")
}

kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        version = "1.0"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Dependencies.KOIN_CORE)
                implementation("dev.gitlive:firebase-auth:1.8.0")
                implementation("dev.gitlive:firebase-firestore:1.8.0")
                implementation(Dependencies.SQLDELIGHT_RUNTIME)
                implementation(Dependencies.Kotlin.COROUTINE_CORE)
                implementation(Dependencies.Kotlin.SERIALIZATION)
                implementation(Dependencies.Kotlin.DATETIME)
                implementation(Dependencies.Ktor.KTOR_CLIENT_CORE)
                implementation(Dependencies.Ktor.KTOR_CLIENT_CONTENT_NEGOTIATION)
                implementation(Dependencies.Ktor.KTOR_JSON)
                implementation(Dependencies.Ktor.KTOR_LOGGING)
                implementation(Dependencies.FirebaseKotlinSdk.CLOUD_FIRESTORE)
            }

        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting {
            dependencies  {
                implementation(Dependencies.SQLDELIGHT_ANDROID_DRIVER)
                implementation(Dependencies.Ktor.KTOR_CLIENT_OKHTTP)
                implementation(Dependencies.Ktor.KTOR_CLIENT_ANDROID)
            }
        }
        val androidTest by getting
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(Dependencies.SQLDELIGHT_NATIVE_DRIVER)
                implementation(Dependencies.Ktor.KTOR_CLIENT_DRAWIN)
            }
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    namespace = "my.phatndt.xsmart"
    compileSdk = 32
    defaultConfig {
        minSdk = 21
        targetSdk = 32
    }

    sourceSets {
        val main by getting
        main.resources.setSrcDirs(
            listOf(
                "src/commonMain/resources",
            )
        )
    }
}

sqldelight {
    databases {
        create("XSmartDatabase") {
            packageName.set("my.phatndt.xsmart.cache")
        }
    }
}