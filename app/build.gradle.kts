import com.marvel.buildsrc.Configs
import com.marvel.buildsrc.Libs
import com.marvel.buildsrc.Versions

plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    namespace = "com.marvel"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        applicationId = "com.marvel"
        minSdk = Versions.MIN_SDK
        targetSdk = Versions.TARGET_SDK
        versionCode = Versions.CODE
        versionName = Versions.NAME

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//      Image cache values
        buildConfigField("String", "IMAGE_DISK_CACHE_NAME", Configs.IMAGE_DISK_CACHE_NAME)
        buildConfigField("double", "IMAGE_DISK_CACHE_PERCENT", Configs.IMAGE_DISK_CACHE_PERCENT)
        buildConfigField("double", "IMAGE_MEMORY_CACHE_PERCENT", Configs.IMAGE_MEMORY_CACHE_PERCENT)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        buildConfig = true
    }
}

dependencies {
    implementation(project(":navigation"))
    runtimeOnly(project(":features:characters-home"))
    runtimeOnly(project(":features:character-details"))
    runtimeOnly(project(":core"))

    implementation(Libs.GOOGLE_MATERIAL)

    //Hilt
    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    //Coil
    Libs.Coil.list.forEach(::implementation)

    //Timber
    implementation(Libs.timber)
}