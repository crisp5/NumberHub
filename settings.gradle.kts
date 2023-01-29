pluginManagement {
    includeBuild("build-logic")
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
        maven(url = "https://jitpack.io")
    }
}
rootProject.name = "Unitto"
include(":app")
include(":data")
include(":core:base")
include(":core:ui")
include(":feature:converter")
include(":feature:unitslist")
include(":feature:settings")
include(":feature:tools")
include(":feature:epoch")
