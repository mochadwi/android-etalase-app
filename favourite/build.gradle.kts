plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

android {
    compileSdkVersion(Versions.androidCompileSdk)
    buildToolsVersion(Versions.androidBuildTools)

    defaultConfig {
        vectorDrawables.useSupportLibrary = true
        minSdkVersion(Versions.androidMinSdk)
        targetSdkVersion(Versions.androidTargetSdk)
        applicationId = Versions.applicationId
        // TODO: Use semantic versioning, use ribot guideliens
        // ref: https://medium.com/@IlyaEremin/npm-version-for-gradle-android-9137a7dc273c
        versionCode = 1000000
        versionName = "1.0.0"
        multiDexEnabled = true

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // used by Room, to test migrations
        javaCompileOptions {
            annotationProcessorOptions {
                arguments = mapOf("room.schemaLocation" to "$projectDir/schemas")
            }
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            isJniDebuggable = false
            isRenderscriptDebuggable = false
            isPseudoLocalesEnabled = false
            isZipAlignEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }

        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
            isTestCoverageEnabled = true
        }
    }

    // used by Room, to test migrations
    sourceSets {
        getByName("androidTest").assets.srcDir(files("$projectDir/schemas"))

    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    // support
    implementation(AndroidDependencies.androidSupportAppCompatV7)
    implementation(AndroidDependencies.androidSupportDesign)
    implementation(AndroidDependencies.androidSupportCardView)
    implementation(AndroidDependencies.androidSupportRecyclerView)
    implementation(AndroidDependencies.androidSupportConstraintLayout)
    implementation(AndroidDependencies.androidSupportMultidex)

    // google
    implementation(AndroidDependencies.roomRuntime)
    implementation(AndroidDependencies.roomKtx)
    kapt(AndroidDependencies.roomCompiler)


    // libs
    implementation(AppDependencies.retrofit)
    implementation(AppDependencies.retrofitConverterKotlinSerialization)
    implementation(AppDependencies.retrofitConverterGson)
    implementation(AppDependencies.retrofitConverterScalar)
    implementation(AppDependencies.okHttp)
    implementation(AppDependencies.okHttpLogger)
    implementation(AppDependencies.glide)
    kapt(AppDependencies.glideCompiler)
    implementation(AppDependencies.stetho)

    // unit test dependencies
    // Android Test
    testImplementation(TestDependencies.kotlinJUnit)
    testImplementation(TestDependencies.kotlinCoroutineTest)
    testImplementation(TestDependencies.mockitoCore)
    // fix this mixing version
    androidTestImplementation(TestDependencies.testJunit)
    androidTestImplementation(TestDependencies.testRules)
    androidTestImplementation(TestDependencies.testRunner)
    androidTestImplementation(TestDependencies.kotlinCoroutineTest)
    androidTestImplementation(TestDependencies.mockitoAndroid)
    androidTestUtil(TestDependencies.testOrchestrator)
}
