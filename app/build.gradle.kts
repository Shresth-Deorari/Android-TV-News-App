import java.util.Properties

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.example.circuithouseassignment"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.circuithouseassignment"
        minSdk = 23
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    val localProperties = Properties()
    val localPropertiesFile = File(rootDir, "local.properties")
    if(localPropertiesFile.exists() && localPropertiesFile.isFile){
        localPropertiesFile.inputStream().use {
            localProperties.load(it)
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                localProperties.getProperty("NEWS_API_KEY")
            )
            buildConfigField(
                "String",
                "WORLD_NEWS_API_KEY",
                localProperties.getProperty("WORLD_NEWS_API_KEY")
            )
        }
        debug{
            buildConfigField(
                "String",
                "NEWS_API_KEY",
                localProperties.getProperty("NEWS_API_KEY")
            )
            buildConfigField(
                "String",
                "WORLD_NEWS_API_KEY",
                localProperties.getProperty("WORLD_NEWS_API_KEY")
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        buildConfig = true
        compose = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.tv.foundation)
    implementation(libs.androidx.tv.material)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.play.services.location)
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.androidx.ui.test.android)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)

    // Retrofit
    implementation(libs.retrofit)
    // Retrofit with Scalar Converter
    implementation(libs.converter.scalars)
    //Retrofit Dependency for Gson Converter
    implementation(libs.converter.gson)

    //News Api Dependency
    implementation(libs.news.api.java)

    //Okhttp
    implementation(libs.okhttp)

    // OkHttp Logging Interceptor
    implementation(libs.logging.interceptor)

    //Glide
    implementation(libs.compose)

    //Splash Screen
    implementation( libs.androidx.core.splashscreen)
}