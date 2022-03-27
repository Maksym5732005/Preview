/**
 * Any code under buildSrc should use a package similar to application code.
 * @see `https://docs.gradle.org/current/userguide/organizing_gradle_projects.html#sec:build_sources`
 */
repositories {
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
}
