#!/usr/bin/env bash

echo "Generando clave JWT segura (64 bytes base64)..."
JWT_KEY=$(openssl rand -base64 64)
echo
echo "Clave generada:"
echo "$JWT_KEY"
echo
echo "En base64 para el Secret de Kubernetes:"
echo -n "$JWT_KEY" | base64
echo
