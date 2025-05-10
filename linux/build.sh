#!/bin/sh
gradle clean shadowJar
native-image -H:IncludeResources=".*/*.cow" -jar app/build/libs/cowsay.jar