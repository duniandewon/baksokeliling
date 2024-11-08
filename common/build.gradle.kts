@Suppress("DSL_SCOPE_VIOLATION") // TODO: Remove once KTIJ-19369 is fixed
plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kotlin.serialization)
    alias(libs.plugins.hilt)
    id("kotlin-kapt")
}

android {
    namespace = "com.example.common"
}

dependencies {

    implementation(projects.storage)
    implementation(projects.network)

    implementation(libs.core.ktx)


    implementation(libs.ktor.serialization)
    implementation(libs.hilt)
    implementation(libs.androidx.hilt.compose.navigation)
    kapt(libs.hilt.compiler)
    implementation(libs.datastore.preferences)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}