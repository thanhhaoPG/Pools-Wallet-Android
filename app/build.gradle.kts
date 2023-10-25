plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.wallet.pools"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.wallet.pools1"
        minSdk = 23
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
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
        viewBinding = true
        buildConfig = true

    }


    lint {
        // Turns off checks for the issue IDs you specify.
        disable += "ContentDescription"
        // Turns on checks for the issue IDs you specify. These checks are in
        // addition to the default lint checks.
        //   enable += "RtlHardcoded" + "RtlCompat" + "RtlEnabled"
        // To enable checks for only a subset of issue IDs and ignore all others,
        // list the issue IDs with the 'check' property instead. This property overrides
        // any issue IDs you enable or disable using the properties above.
        // checkOnly += "NewApi" + "InlinedApi"
        // If set to true, turns off analysis progress reporting by lint.
        //  quiet = true
        // If set to true (default), stops the build if errors are found.
        abortOnError = false
        // If set to true, lint only reports errors.
        //  ignoreWarnings = true
        // If set to true, lint also checks all dependencies as part of its analysis.
        // Recommended for projects consisting of an app with library dependencies.
        checkDependencies = true
        checkReleaseBuilds = true


    }

}

// Allow references to generated code
kapt {
    correctErrorTypes = true
}
dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    Navigation component
    val nav_version = "2.7.4"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Hilt
    implementation("com.google.dagger:hilt-android:2.48")
    kapt("com.google.dagger:hilt-android-compiler:2.48")


    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")

    //lottie-android
    implementation ("com.airbnb.android:lottie:6.1.0")

    // logging
    implementation ("com.jakewharton.timber:timber:5.0.1")

    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")

    //retrofit2
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")

    //okhttp3
    implementation  ("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    //moshi
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")


    //security
    implementation("androidx.security:security-crypto:1.1.0-alpha06")
    
    // Circle Image View
    implementation ("de.hdodenhof:circleimageview:3.1.0")

}