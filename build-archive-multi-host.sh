#!/bin/bash

./gradlew :archive-multi-host:archive-host:uberJar
./gradlew :archive-multi-host:archive-client:uberJar