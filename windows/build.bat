@echo off
REM Clean and build the shadow JAR using Gradle
gradlew clean shadowJar

REM Generate native image using GraalVM
native-image -H:IncludeResources=".*\\.cow" -jar app\build\libs\cowsay.jar
