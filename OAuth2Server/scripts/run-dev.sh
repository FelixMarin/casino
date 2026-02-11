#!/usr/bin/env bash

echo "=== Arrancando OAuth2Server en modo DEV ==="

# Activar el perfil dev
export SPRING_PROFILES_ACTIVE=dev

# Crear carpeta de datos si no existe
mkdir -p data

# Ejecutar la aplicación
java -jar OAuth2Server-0.0.1-SNAPSHOT.jar >> /data/oauth2server.log 2>&1
