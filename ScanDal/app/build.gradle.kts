plugins {
    id("com.android.application")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.example.scandal"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.scandal"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
}

dependencies {
    implementation("com.github.yuriy-budiyev:code-scanner:2.3.0")
    implementation("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation("com.google.android.gms:play-services-mlkit-barcode-scanning:18.3.0")


    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.mlkit:barcode-scanning:17.0.0")
    implementation("com.google.android.material:material:1.6.1") // Corrected version to 1.6.1 to match appcompat
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation(platform("com.google.firebase:firebase-bom:32.7.1"))
    implementation("com.google.firebase:firebase-firestore-ktx")
    implementation("com.github.dhaval2404:imagepicker:2.1")
    implementation("com.google.firebase:firebase-storage-ktx")
    implementation("com.google.mlkit:barcode-scanning-common:17.0.0")
    implementation("com.google.android.gms:play-services-code-scanner:16.1.0") // Using KTX version for Kotlin-friendly APIs
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
}
