@file:OptIn(ExperimentalPathApi::class)

import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.attribute.PosixFilePermissions
import kotlin.io.path.ExperimentalPathApi
import kotlin.io.path.absolute
import kotlin.io.path.deleteRecursively


plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(libs.junit.jupiter)
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

tasks.register<Task>("uninstall") {
    doLast {
        val homePath = System.getenv("HOME");
        val localPath = Paths.get(homePath, ".local", "java-cowsay");
        localPath.deleteRecursively()
    }
}

tasks.register<Task>("install") {
    dependsOn("jar")
    doLast {
        try {
            val homePath = System.getenv("HOME");
            val localPath = Paths.get(homePath, ".local", "java-cowsay");
            localPath.toFile().mkdir();
            val cowsayJarPath = Paths.get("./app/build/libs/cowsay.jar");
            val jarFile = Paths.get(localPath.absolute().toString(), "cowsay.jar")
            Files.copy(cowsayJarPath, jarFile)
            val listString = listOf<String>(
                "#!/bin/sh",
                "CMD=(java -jar $jarFile)",
                "[ -n \"\$1\" ] && CMD+=(\"\$1\")",
                "[ -n \"$2\" ] && CMD+=(\"$2\")",
                "\"\${CMD[@]}\""
            )
            val javaCowsayFile = Paths.get(localPath.absolute().toString(), "java-cowsay.sh")
            Files.writeString(javaCowsayFile, listString.joinToString("\n"));
            Files.setPosixFilePermissions(javaCowsayFile, PosixFilePermissions.fromString("rwxr-xr-x"))
        } catch (err: FileAlreadyExistsException) {
            println("Cowsay already installed!")
        }
    }
}

tasks.withType<Jar>() {
    manifest {
        attributes["Main-Class"] = application.mainClass
    }
    archiveBaseName.set("cowsay")
}