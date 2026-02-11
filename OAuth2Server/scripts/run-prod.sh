#!/usr/bin/env bash

echo "=== Arrancando OAuth2Server en modo PRODUCCIÓN ==="

# Comprobación básica: ¿hay clave JWT?
if [ -z "$JWT_SIGNING_KEY" ]; then
  echo "ERROR: La variable JWT_SIGNING_KEY no está definida."
  echo "Define la clave JWT antes de arrancar:"
  echo "  export JWT_SIGNING_KEY=\"tu_clave_jwt_real\""
  exit 1
fi

# Activar perfil prod
export SPRING_PROFILES_ACTIVE=prod

# Crear carpeta de datos si no existe (H2 persistente)
mkdir -p /data

# Arrancar la aplicación
java -jar OAuth2Server-0.0.1-SNAPSHOT.jar >> /data/oauth2server.log 2>&1
