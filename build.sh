#!/usr/bin/env bash

export SPRING_DATASOURCE_PASSWORD="city"
export SPRING_DATASOURCE_URL="jdbc:postgresql://localhost:5432/city"
export SPRING_DATASOURCE_USERNAME="city"

bash ./mvnw clean install -DskipTests