buildscript {

    repositories {
        google()
        jcenter()
        maven { setUrl("https://maven.fabric.io/public") }
        maven { setUrl("https://jitpack.io") }
    }
    dependencies {
        classpath(AndroidDependencies.androidGradle)
        classpath("com.google.gms:google-services:4.0.1")
        classpath(AndroidDependencies.kotlinGradle)
        classpath(AndroidDependencies.kotlinSerialization)
        classpath(AndroidDependencies.navigationSafeArgs)
        classpath(TestDependencies.jacocoReport)
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
        maven { setUrl("https://maven.fabric.io/public") }
        maven { setUrl("https://jitpack.io") }
        // artifacts are published to this repository
        maven { setUrl("https://kotlin.bintray.com/kotlinx") }
        maven { setUrl("https://maven.google.com") }
    }
}

tasks {
    val clean by registering(Delete::class) {
        delete(buildDir)
    }
}

