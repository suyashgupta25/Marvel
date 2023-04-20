import com.marvel.buildsrc.Libs
import com.marvel.buildsrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.marvel.navigation"
    compileSdk = Versions.COMPILE_SDK

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    //Android X App Compat
    implementation(Libs.APP_COMPAT)

    //Hilt
    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    //Timber
    implementation(Libs.timber)
}