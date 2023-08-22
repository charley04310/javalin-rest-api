#!/bin/sh
set -eu
mvn clean compile assembly:single
mvn package
java -jar target/pokedex-back-1.0-SNAPSHOT-jar-with-dependencies.jar