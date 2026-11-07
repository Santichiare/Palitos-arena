plugins {
    id("com.android.application")
}

android {
    namespace = "com.palitos.arena"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.palitos.arena"
        minSdk = 24
        targetSdk = 34
        versionCode = 3
        versionName = "1.2"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
