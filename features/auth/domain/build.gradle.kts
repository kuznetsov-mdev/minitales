plugins {
    id("java-library")
    alias(libs.plugins.kotlin.jvm)
}
dependencies {
    implementation(projects.features.auth.data)
    implementation(projects.network)
    implementation(projects.storage)

    implementation(libs.javax.inject)
}