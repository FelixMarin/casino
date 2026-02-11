#!/bin/bash

set -e

# ===== CONFIGURACIÓN =====
IMAGE_NAME="felixmurcia/oauth2server"
NAMESPACE="auth"
DEPLOYMENT="oauth2-server"
CONTAINER_NAME="oauth2-server"

# ===== GENERAR TAG AUTOMÁTICO =====
TAG=$(date +"v%Y%m%d-%H%M")
FULL_IMAGE="$IMAGE_NAME:$TAG"

echo "======================================"
echo "  🚀 Construyendo imagen: $FULL_IMAGE"
echo "======================================"

docker build -t $FULL_IMAGE .

echo "======================================"
echo "  📤 Subiendo imagen a Docker Hub"
echo "======================================"

docker push $FULL_IMAGE

echo "======================================"
echo "  📝 Actualizando Deployment en Kubernetes"
echo "======================================"

kubectl set image deployment/$DEPLOYMENT \
  $CONTAINER_NAME=$FULL_IMAGE \
  -n $NAMESPACE

echo "======================================"
echo "  🔄 Forzando rollout"
echo "======================================"

kubectl rollout restart deployment/$DEPLOYMENT -n $NAMESPACE

echo "======================================"
echo "  ⏳ Esperando a que el nuevo pod esté listo"
echo "======================================"

kubectl rollout status deployment/$DEPLOYMENT -n $NAMESPACE

echo "======================================"
echo "  🧹 Eliminando imágenes antiguas de oauth2server"
echo "======================================"

IMAGES_TO_DELETE=$(docker images $IMAGE_NAME --format "{{.Repository}}:{{.Tag}} {{.CreatedAt}}" \
  | sort -k2 -r \
  | tail -n +2 \
  | awk '{print $1}')

for IMG in $IMAGES_TO_DELETE; do
  echo "🗑️  Eliminando imagen antigua: $IMG"
  docker rmi -f "$IMG" || true
done

echo "======================================"
echo "  🧹 Limpiando imágenes antiguas de Docker"
echo "======================================"

docker image prune -f
docker container prune -f
docker image prune -a --filter "until=720h" -f

echo "======================================"
echo "  📜 Logs del nuevo pod"
echo "======================================"

kubectl logs -n $NAMESPACE -l app=$DEPLOYMENT -f
