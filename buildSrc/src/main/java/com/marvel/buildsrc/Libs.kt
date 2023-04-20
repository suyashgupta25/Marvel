package com.marvel.buildsrc

object Libs {

    object Compose {

        private const val composeVersion = "2023.04.00"
        const val BOM = "androidx.compose:compose-bom:${composeVersion}"
        private const val FOUNDATION = "androidx.compose.foundation:foundation"
        private const val RUNTIME = "androidx.compose.runtime:runtime-rxjava3"
        private const val MATERIAL = "androidx.compose.material:material"
        private const val UI_TOOLING_PREVIEW = "androidx.compose.ui:ui-tooling-preview"
        val list = listOf(
            FOUNDATION,
            RUNTIME,
            MATERIAL,
            UI_TOOLING_PREVIEW
        )
        const val UI_TOOLING = "androidx.compose.ui:ui-tooling"
    }

    const val GOOGLE_MATERIAL = "com.google.android.material:material:1.8.0"
    const val APP_COMPAT = "androidx.appcompat:appcompat:1.6.1"

    object Rx {

        private const val JAVA = "io.reactivex.rxjava3:rxjava:3.1.5"
        private const val ANDROID = "io.reactivex.rxjava3:rxandroid:3.0.2"
        val list = listOf(
            JAVA,
            ANDROID
        )
    }

    object Hilt {

        private const val hiltVersion = "2.45"
        const val ANDROID = "com.google.dagger:hilt-android:${hiltVersion}"
        const val COMPILER = "com.google.dagger:hilt-android-compiler:${hiltVersion}"
        const val PLUGIN = "com.google.dagger:hilt-android-gradle-plugin:${hiltVersion}"
    }

    object Retrofit {

        private const val retrofitVersion = "2.9.0"
        const val CORE = "com.squareup.retrofit2:retrofit:${retrofitVersion}"
        private const val CONVERTER_MOSHI =
            "com.squareup.retrofit2:converter-moshi:${retrofitVersion}"
        private const val ADAPTER_RX = "com.squareup.retrofit2:adapter-rxjava3:${retrofitVersion}"
        private const val MOSHI_KOTLIN = "com.squareup.moshi:moshi-kotlin:1.14.0"
        private const val OKHTTP_INTERCEPTOR = "com.squareup.okhttp3:logging-interceptor:4.10.0"
        val list = listOf(
            CORE,
            CONVERTER_MOSHI,
            ADAPTER_RX,
            MOSHI_KOTLIN,
            OKHTTP_INTERCEPTOR
        )
    }

    const val timber = "com.jakewharton.timber:timber:5.0.1"

    object Coil {

        private const val coilVersion = "2.3.0"
        private const val KT = "io.coil-kt:coil:${coilVersion}"
        private const val COMPOSE = "io.coil-kt:coil-compose:${coilVersion}"
        val list = listOf(
            KT,
            COMPOSE
        )
    }

    object Room {

        const val CORE_RXJAVA = "androidx.room:room-rxjava3:2.5.1"
        const val COMPILER = "androidx.room:room-compiler:2.5.1"
    }

    object Testing {

        const val junit = "junit:junit:4.13.2"

        object Kotlin {

            private const val kotlinTestVersion = "1.8.0"
            private const val CORE = "org.jetbrains.kotlin:kotlin-test:${kotlinTestVersion}"
            private const val JUNIT = "org.jetbrains.kotlin:kotlin-test-junit:${kotlinTestVersion}"
            val list = listOf(
                CORE,
                JUNIT
            )
        }

        object Mockito {

            private const val CORE = "org.mockito:mockito-core:4.8.0"
            private const val INLINE = "org.mockito:mockito-inline:4.8.0"
            private const val KOTLIN = "org.mockito.kotlin:mockito-kotlin:4.1.0"
            val list = listOf(
                CORE,
                INLINE,
                KOTLIN
            )
        }
    }
}