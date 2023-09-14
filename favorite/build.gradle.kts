import com.android.build.gradle.internal.tasks.ExportConsumerProguardFilesTask

plugins {
    id("com.android.dynamic-feature")
    id("org.jetbrains.kotlin.android")
}
//apply(from = "../shared_dependencies.gradle")
/*tasks.named("exportReleaseConsumerProguardFiles"){
    dependsOn(":favorite:extractProguardFiles")
}

tasks.register("exportReleaseConsumerProguardFiles", ExportConsumerProguardFilesTask::class){
    mustRunAfter(":favorite:extractProguardFiles")
}*/

/*tasks {
    named<ExportConsumerProguardFilesTask>("exportReleaseConsumerProguardFiles") {
        dependsOn("extractProguardFiles")
    }
}*/

android {
    namespace = "com.neotica.favorite"
    compileSdk = 34

    defaultConfig {
        minSdk = 27
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

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    apply(from = "../shared_dependencies.gradle")
    implementation(project(":app"))
    implementation(project(":core"))
}





/*tasks.named<ExportConsumerProguardFilesTask>("exportReleaseConsumerProguardFiles") {
    dependsOn("extractProguardFiles")
}*/
/*
tasks.register("exportReleaseConsumerProguardFiles"){
    dependsOn("extractProguardFiles")
}*/
