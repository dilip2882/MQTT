plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.dilip.awsiotmqttconn"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.dilip.awsiotmqttconn"
        minSdk = 30
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

    packagingOptions {
        // Exclude the conflicting files
        exclude("META-INF/DEPENDENCIES")
    }


}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    implementation("com.amazonaws:aws-iot-device-sdk-java:${rootProject.extra["awsIotDeviceSdkJavaVersion"]}")
}