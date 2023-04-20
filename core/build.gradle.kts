import com.marvel.buildsrc.Configs
import com.marvel.buildsrc.Libs
import com.marvel.buildsrc.Versions

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.marvel.core"
    compileSdk = Versions.COMPILE_SDK

    defaultConfig {
        buildConfigField("String", "API_BASE_URL", Configs.API_BASE_URL)
        buildConfigField("String", "DATABASE_NAME", Configs.DATABASE_NAME)
        buildConfigField("int", "DATABASE_VERSION", Configs.DATABASE_VERSION)
        buildConfigField("boolean", "DATABASE_EXPORT_SCHEMA", Configs.DATABASE_EXPORT_SCHEMA)
    }
    buildFeatures {
        buildConfig = true
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
    implementation(project(":features:characters-home"))
    implementation(project(":features:character-details"))

    //Retrofit
    Libs.Retrofit.list.forEach(::implementation)

    implementation(Libs.Room.CORE_RXJAVA)
    kapt(Libs.Room.COMPILER)

    //Rx
    Libs.Rx.list.forEach(::implementation)

    //Hilt
    implementation(Libs.Hilt.ANDROID)
    kapt(Libs.Hilt.COMPILER)

    //Timber
    implementation(Libs.timber)

    //Testing
    testImplementation(project(":testing"))
    testImplementation(Libs.Testing.junit)
    Libs.Testing.Kotlin.list.forEach(::testImplementation)
    Libs.Testing.Mockito.list.forEach(::testImplementation)
}