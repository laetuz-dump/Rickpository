plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id ("kotlin-parcelize")
}
apply(from = "../shared_dependencies.gradle")

/*tasks.register("exportReleaseConsumerProguardFiles", ExportConsumerProguardFilesTask::class){
    mustRunAfter(":favorite:extractProguardFiles")
}*/

android {
    namespace = "com.neotica.rickandmorty"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.neotica.rickandmorty"
        minSdk = 27
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
    buildFeatures {
        viewBinding = true
    }
    dynamicFeatures += setOf(":favorite")
}

dependencies {
    implementation(project(":core"))
   // debugImplementation(project(path= ":favorite", configuration = "debug"))
   // releaseImplementation(project(path= ":favorite", configuration = "release"))
}