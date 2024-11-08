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


rootProject.name = "BaksoKeliling"

enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")
include(":network")
include(":storage")
include(":common")
include(":feature:auth")
include(":feature:home")
