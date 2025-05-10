@echo off
gradlew clean shadowJar
native-image -H:IncludeResources=".*\\.cow" -jar app\build\libs\cowsay.jar
