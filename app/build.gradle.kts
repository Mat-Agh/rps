plugins {
    alias(
        libs.plugins.applicationPlugin
    )
    alias(
        libs.plugins.kotlinPlugin
    )
    alias(
        libs.plugins.hiltPlugin
    )
    alias(
        libs.plugins.kaptPlugin
    )
}

android {
    namespace = libs.versions.packageName.get()

    compileSdk = libs.versions.maxSdk.get().toInt()

    defaultConfig {
        applicationId = libs.versions.packageName.get()

        minSdk = libs.versions.minSdk.get().toInt()

        targetSdk = libs.versions.maxSdk.get().toInt()

        versionCode = libs.versions.versionCode.get().toInt()

        versionName = libs.versions.versionName.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false

            proguardFiles(
                getDefaultProguardFile(
                    "proguard-android-optimize.txt"
                ), "proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlin {
        jvmToolchain(
            libs.versions.jvmVersion.get().toInt()
        )
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompilerVersion.get()
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    //region Core
    implementation(
        libs.core
    )
    //endregion Core

    //region Hilt
    implementation(
        libs.hilt
    )
    kapt(
        libs.hiltCompiler
    )
    //endregion Hilt

    //region Hilt Navigation Compose
    implementation(
        libs.hiltNavigationCompose
    )
    //endregion Hilt Navigation Compose

    //region Lifecycle
    implementation(
        libs.lifecycleViewmodel
    )
    implementation(
        libs.lifecycleLivedata
    )
    implementation(
        libs.lifecycleCompose
    )
    implementation(
        libs.lifecycleViewmodelCompose
    )
    //endregion Lifecycle

    //region Activity for Compose
    implementation(
        libs.activityCompose
    )
    //endregion Activity for Compose

    //region Compose
    implementation(
        libs.composeUI
    )
    implementation(
        libs.composeFoundation
    )
    implementation(
        libs.composeAnimation
    )
    implementation(
        libs.composeUIToolingPreview
    )
    debugImplementation(
        libs.composeUITooling
    )
    implementation(
        libs.composeMaterial3WindowSize
    )
    implementation(
        libs.composeMaterialIconsExtended
    )
    implementation(
        libs.composeLiveData
    )
    implementation(
        libs.composeMaterial3
    )
    debugImplementation(
        libs.composeUITestManifest
    )
    androidTestImplementation(
        libs.composeUITestJUnit
    )
    implementation(
        libs.composeConstraintLayout
    )
    //endregion Compose
}