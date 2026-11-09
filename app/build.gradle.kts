plugins {
    id("com.android.application")
}

// La clave de subida a Google Play NO está en el repo: GitHub la arma desde los "secrets"
val uploadKeystore: String? = System.getenv("UPLOAD_KEYSTORE_FILE")

android {
    namespace = "com.palitos.arena"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.palitos.arena"
        minSdk = 24
        targetSdk = 36   // Google Play exige Android 16 (API 36) para apps nuevas y actualizaciones
        // GitHub pone el número de compilación, así cada subida a Play tiene un número mayor
        versionCode = (System.getenv("VERSION_CODE") ?: "12").toInt()
        versionName = System.getenv("VERSION_NAME") ?: "1.11"
    }

    signingConfigs {
        // Clave del APK para instalar a mano (pública en el repo: solo para pruebas entre amigos)
        create("palitos") {
            storeFile = file("palitos.keystore")
            storePassword = "palitos123"
            keyAlias = "palitos"
            keyPassword = "palitos123"
        }
        // Clave privada de subida a Google Play (viene de los secrets de GitHub)
        if (uploadKeystore != null) {
            create("upload") {
                storeFile = file(uploadKeystore)
                storePassword = System.getenv("UPLOAD_KEYSTORE_PASSWORD")
                keyAlias = System.getenv("UPLOAD_KEY_ALIAS")
                keyPassword = System.getenv("UPLOAD_KEY_PASSWORD")
            }
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.getByName("palitos")
        }
        release {
            isMinifyEnabled = false
            signingConfig = if (uploadKeystore != null) signingConfigs.getByName("upload") else signingConfigs.getByName("palitos")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}
