// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}
val awsIotDeviceSdkJavaVersion by extra("1.3.13")
