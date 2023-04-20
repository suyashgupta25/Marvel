// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(com.marvel.buildsrc.Libs.Hilt.PLUGIN)
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts.kts files
    }
}

plugins {
    id(com.marvel.buildsrc.Plugins.AndroidApp.PACAKGE) version com.marvel.buildsrc.Plugins.AndroidApp.VERSION apply false
    id(com.marvel.buildsrc.Plugins.AndroidLibrary.PACAKGE) version com.marvel.buildsrc.Plugins.AndroidLibrary.VERSION apply false
    id(com.marvel.buildsrc.Plugins.KotlinAndroid.PACAKGE) version com.marvel.buildsrc.Plugins.KotlinAndroid.VERSION apply false
}