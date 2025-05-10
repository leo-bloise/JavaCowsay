@file:OptIn(ExperimentalPathApi::class)
import kotlin.io.path.ExperimentalPathApi

buildscript {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
    dependencies {
        classpath("com.gradleup.shadow:shadow-gradle-plugin:8.3.5")
    }
}

apply { plugin("com.gradleup.shadow") }

plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
    implementation(project(":parser"))
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "leobloise.cowsay.App"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
    archiveBaseName.set("cowsay")
    archiveClassifier.set("")
}