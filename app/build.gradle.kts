plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.cropoptima.cropoptima"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.cropoptima.cropoptima"
        minSdk = 21
        targetSdk = 34
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
    }

}


dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    implementation("com.google.firebase:firebase-auth:22.3.0")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    // dataStore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    // coroutine support
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1") //viewModelScope
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1") //liveData
    implementation("androidx.activity:activity-ktx:1.4.0")

    // splash screen
    implementation("androidx.core:core-splashscreen:1.0.1")

    // glide
    implementation("com.github.bumptech.glide:glide:4.16.0")

    // workr manager
    implementation("androidx.work:work-runtime:2.8.1")

    // firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))

    // carousel
    implementation("com.tbuonomo:dotsindicator:5.0")

}