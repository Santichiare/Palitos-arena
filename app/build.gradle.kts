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
        versionCode = 10
        versionName = "1.9"
    }

    // Misma firma en todas las compilaciones, así cada APK nuevo se instala encima del anterior
    signingConfigs {
        create("palitos") {
            storeFile = file("palitos.keystore")
            storePassword = "palitos123"
            keyAlias = "palitos"
            keyPassword = "palitos123"
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("palitos")
        }
        release {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("palitos")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
