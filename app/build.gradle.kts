plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.hungrydevops.app"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.hungrydevops.app"
        minSdk = 24
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
    viewBinding{
        enable=true
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

//    lottie loader
    implementation ("com.airbnb.android:lottie:3.4.0")
//    navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.5")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.5")
//   firebase

    implementation ("com.google.firebase:firebase-messaging:23.4.0")
    implementation ("com.google.firebase:firebase-messaging-ktx")
    //   firebase
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-firestore-ktx:24.10.0")
    implementation("com.google.firebase:firebase-core:21.1.1")
    implementation("com.google.firebase:firebase-auth")

    implementation ("com.google.firebase:firebase-database-ktx")

    implementation("com.google.android.gms:play-services-auth:20.7.0")

    implementation ("com.google.code.gson:gson:2.9.0")
    //pdf
    implementation ("com.github.afreakyelf:Pdf-Viewer:2.0.4")
    //firebase storage
    implementation(platform("com.google.firebase:firebase-bom:32.7.0"))
    implementation("com.google.firebase:firebase-storage")
    implementation ("com.facebook.shimmer:shimmer:0.5.0")


//    image slider
    implementation ("com.github.denzcoskun:ImageSlideshow:0.1.2")
}