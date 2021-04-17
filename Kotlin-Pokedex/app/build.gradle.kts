plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
    id("kotlin-kapt")
    id("androidx.navigation.safeargs.kotlin")
}
apply(from = "../ktlint.gradle.kts")

android {
    compileSdkVersion(30)
    buildToolsVersion("29.0.3")
    defaultConfig {
        applicationId = "dev.marcosfarias.pokedex"
        minSdkVersion(24)
        targetSdkVersion(30)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.3.72")

    // Architecture
    implementation("androidx.core:core-ktx:1.3.2")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")

    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")
    implementation("androidx.navigation:navigation-fragment-ktx:2.3.5")

    implementation("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation("androidx.coordinatorlayout:coordinatorlayout:1.1.0")

    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.1")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1")

    // Material
    implementation("com.google.android.material:material:1.3.0")

    // Third Party
    implementation("com.leinardi.android:speed-dial:3.1.1")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Persistence
    implementation("android.arch.persistence.room:runtime:1.1.1")
    implementation ("androidx.room:room-common:2.2.6")
    implementation ("androidx.room:room-runtime:2.2.6")
    kapt ("androidx.room:room-compiler:2.2.6")
    kapt("android.arch.persistence.room:compiler:1.1.1")

    // Glide
    kapt("android.arch.lifecycle:compiler:1.1.1")
    kapt("com.github.bumptech.glide:compiler:4.10.0")
    implementation("com.github.bumptech.glide:glide:4.12.0")

    // Koin
    implementation("org.koin:koin-android:2.1.6")
    implementation("org.koin:koin-androidx-scope:2.1.6")
    implementation("org.koin:koin-androidx-viewmodel:2.1.6")

    // Test
    testImplementation("junit:junit:4.13.2")

    // Fragment
    debugImplementation("androidx.fragment:fragment-testing:1.3.2")

    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
    androidTestImplementation("com.android.support.test.espresso:espresso-contrib:3.0.2")
    androidTestImplementation("androidx.navigation:navigation-testing:2.3.5")
    androidTestImplementation("io.mockk:mockk-android:1.10.6")
    androidTestImplementation("androidx.arch.core:core-testing:2.1.0")
}
