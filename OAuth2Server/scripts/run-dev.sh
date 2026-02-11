#!/usr/bin/env bash

echo "=== Arrancando OAuth2Server en modo DEV ==="

export SPRING_PROFILES_ACTIVE=dev

# Ejecutar la aplicación (sin redirección a /data)
java -jar /app/app.jar
