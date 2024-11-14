plugins {
    alias(libs.plugins.kotlin.compose)
    alias(libs.plugins.android.application)
    alias (libs.plugins.kotlin.android)
    alias (libs.plugins.hilt)
    alias (libs.plugins.protobuf)
    alias(libs.plugins.ksp)
}

android {
    namespace = "ru.sandbox.minitales"

    defaultConfig {
        applicationId = "ru.sandbox.minitales"
        versionCode = 1
        versionName = "1.0"

    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField("String", "MINI_TALES_HOST", "Not given")
        }

        debug {
            buildConfigField("String", "MINI_TALES_HOST", "\"192.168.0.88\"")
        }
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

}

dependencies {
    implementation(projects.theme)
    implementation(projects.features.auth)
    implementation(projects.features.auth.data)
    implementation(projects.features.auth.domain)
    implementation(projects.network)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)

    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    debugImplementation(libs.androidx.compose.ui.tooling)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material3)

    implementation (libs.androidx.compose.navigation)

    //Dagger Hilt
    implementation(libs.hilt)
    implementation(libs.androidx.hilt.compose.navigation)
    ksp(libs.hilt.compiler)

    implementation(libs.ktor.client.core)

    implementation(libs.datastore)
    implementation(libs.protobuf.javalite)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.compose.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    
}