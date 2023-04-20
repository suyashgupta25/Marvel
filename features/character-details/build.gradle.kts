import com.marvel.buildsrc.Libs
import com.marvel.buildsrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.marvel.characterdetails"
    compileSdk = Versions.COMPILE_SDK

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation(project(":common"))
    implementation(project(":navigation"))

    //Compose
    api(platform(Libs.Compose.BOM))
    Libs.Compose.list.forEach(::implementation)
    debugImplementation(Libs.Compose.UI_TOOLING)

    //Timber
    implementation(Libs.timber)

    //Hilt
    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)
}