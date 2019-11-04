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
        applicationId = Versions.applicationIdFavourite
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

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "IMAGE_URL", "\"http://image.tmdb.org/t/p/w780/\"")
            buildConfigField("String", "API_KEY", "\"334879b2c8dc36a9f2c64f7bd4f0c91d\"")
        }

        getByName("debug") {
            applicationIdSuffix = ".debug"
            isMinifyEnabled = false
            isDebuggable = true
            isTestCoverageEnabled = true

            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "IMAGE_URL", "\"http://image.tmdb.org/t/p/w780/\"")
            buildConfigField("String", "API_KEY", "\"334879b2c8dc36a9f2c64f7bd4f0c91d\"")
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
    implementation(AndroidSupports.appCompat)
    implementation(AndroidSupports.support)
    implementation(AndroidSupports.design)
    implementation(AndroidSupports.recyclerView)
    implementation(AndroidSupports.cardView)
    implementation(AndroidSupports.constraintLayout)
    implementation(AndroidSupports.multiDex)

    // google
    implementation(AndroidSupports.archRoomRuntime)
    kapt(AndroidSupports.archRoomComp)


    // libs
    implementation(AndroidSupports.okhttp)
    debugImplementation(AndroidSupports.okhttpLogging)
    implementation(AndroidSupports.glide) {
        exclude(group = "com.android.support")
    }
    kapt(AndroidSupports.glideCompiler)
    implementation(AndroidSupports.retrofit)
    implementation(AndroidSupports.retrofitRx2)
    implementation(AndroidSupports.retrofitGson)
    implementation(AndroidSupports.retrofitScalar)
    implementation(AndroidSupports.butterKnife)
    kapt(AndroidSupports.butterKnifeAnnotation)
    implementation(AndroidSupports.localization)
    implementation(AndroidSupports.stetho)

    // unit test dependencies
    testImplementation(AndroidSupports.junit)
    androidTestImplementation(AndroidSupports.junitRunner)
    androidTestImplementation(AndroidSupports.espressoCore)
}
