pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}
rootProject.name = "Marvel"
include(":app")
include(":features:characters-home")
include(":common")
include(":core")
include(":navigation")
include(":features:character-details")
include(":testing")
