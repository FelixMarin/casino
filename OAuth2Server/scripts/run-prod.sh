#!/usr/bin/env bash

echo "=== Arrancando OAuth2Server en modo PRODUCCIÓN ==="

# Comprobación básica: ¿hay clave JWT?
if [ -z "$JWT_SIGNING_KEY" ]; then
  echo "ERROR: La variable JWT_SIGNING_KEY no está definida."
  exit 1
fi

export SPRING_PROFILES_ACTIVE=prod

mkdir -p /data

# ARRANQUE CORRECTO
java -jar /app/app.jar >> /data/oauth2server.log 2>&1
