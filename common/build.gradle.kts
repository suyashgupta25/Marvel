import com.marvel.buildsrc.Configs
import com.marvel.buildsrc.Libs
import com.marvel.buildsrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.marvel.common"
    compileSdk = Versions.COMPILE_SDK

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    defaultConfig {
        buildConfigField("String", "MARVEL_PUBLIC_KEY", Configs.MARVEL_PUBLIC_KEY)
        buildConfigField("String", "MARVEL_PRIVATE_KEY", Configs.MARVEL_PRIVATE_KEY)
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Versions.KOTLIN_COMPILER_EXTENSION
    }
}

dependencies {
    //Compose
    api(platform(Libs.Compose.BOM))
    Libs.Compose.list.forEach(::implementation)
    debugImplementation(Libs.Compose.UI_TOOLING)

    //Rx
    Libs.Rx.list.forEach(::implementation)

    //Hilt
    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    //Retrofit
    Libs.Retrofit.list.forEach(::implementation)

    //Timber
    implementation(Libs.timber)

    //Coil
    Libs.Coil.list.forEach(::implementation)
}