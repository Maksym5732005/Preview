import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id(GradleConfig.Plugins.androidApplication) version GradleConfig.Plugins.Versions.androidApplication apply false
    id(GradleConfig.Plugins.androidLibrary) version GradleConfig.Plugins.Versions.androidLibrary apply false
    id(GradleConfig.Plugins.kotlinAndroid) version GradleConfig.Plugins.Versions.kotlin apply false
    id(GradleConfig.Plugins.benManes) version GradleConfig.Plugins.Versions.benManes
}

tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}

// https://github.com/ben-manes/gradle-versions-plugin
// gradlew dependencyUpdates
// see generated file in [outputDir]
tasks.withType<DependencyUpdatesTask> {
    tasks.withType<DependencyUpdatesTask> {
        resolutionStrategy {
            componentSelection {
                all {
                    if (!candidate.version.isStable() && currentVersion.isStable()) {
                        reject("Release candidate")
                    }
                }
            }
        }
    }

    // optional parameters
    checkForGradleUpdate = true
    outputFormatter = "html"
    outputDir = "build/dependencyUpdates"
    reportfileName = "report"
}

fun String.isStable(): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    return stableKeyword || regex.matches(this)
}