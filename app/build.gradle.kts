plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.clinicaldecisions"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.clinicaldecisions"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            // Name
            resValue("string", "app_name", "Clinical Decisions")
            // Debug
            isDebuggable = false
            // Code
            isMinifyEnabled = true
            // Resource
            isShrinkResources = true
            // Includes
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        getByName("debug") {
            resValue("string", "app_name", "Clinical Decisions demo")
            isDebuggable = true
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        buildConfig = true
        viewBinding = true
    }
    kotlin {
        jvmToolchain(11)
    }
}

dependencies {
    implementation(libs.androidx.core)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.recyclerview)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.fragment)
    implementation(libs.androidx.splashscreen)
    implementation(libs.androidx.test.core)
    implementation(libs.com.glide)
    implementation(libs.com.material)
    implementation(libs.com.hilt)
    implementation(libs.org.coroutines)
    implementation(libs.bundles.androidx.lifecycle.libs)
    implementation(libs.bundles.androidx.room.libs)
    implementation(libs.bundles.androidx.room.libs)
    implementation(libs.bundles.navigation)

    ksp(libs.androidx.room.compiler)
    ksp(libs.com.hilt.compiler)
    ksp(libs.com.glide.compiler)

    testImplementation(libs.test.junit.junit)
    testImplementation(libs.test.coroutines)
    testImplementation(libs.test.robolectric.robolectric)
    testImplementation(libs.bundles.test.mockito.libs)

    androidTestImplementation(libs.androidTest.junit.junit)
    androidTestImplementation(libs.androidTest.espresso.core)
}