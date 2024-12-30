pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.8.0"
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}


rootProject.name = "BaksoKeliling"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":network")
include(":storage")
include(":common")
include(":feature:auth")
include(":feature:home")
