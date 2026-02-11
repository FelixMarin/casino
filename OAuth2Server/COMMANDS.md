# 📘 **GUÍA COMPLETA DE OPERACIONES – KUBERNETES + DOCKER + OAUTH2SERVER**

---

# 🟦 1. COMANDOS GENERALES DE KUBERNETES

### 🔍 Ver todos los recursos de todos los namespaces
```bash
kubectl get all --all-namespaces
```

### 📦 Listar namespaces
```bash
kubectl get namespaces
```

### 🧩 Ver todos los pods
```bash
kubectl get pods -A
```

### 🖥️ Ver nodos
```bash
kubectl get nodes
```

---

# 🟩 2. NAMESPACE **cine** (cine-platform)

## 🔧 Recursos y despliegues

### Ver deployments
```bash
kubectl get deployments -n cine
```

### Aplicar todos los YAML del directorio k3s/
```bash
kubectl apply -f k3s/ -n cine
```

### Ver PVCs
```bash
kubectl get pvc -n cine
```

### Ver secret principal
```bash
kubectl get secret cine-platform-secrets -n cine
```

### Ver secrets y configmaps
```bash
kubectl get secret -n cine
kubectl get configmap -n cine
```

---

## 📜 Logs y debugging

### Logs de pods concretos
```bash
kubectl logs -n cine cine-platform-75c49667cc-4nh7d
kubectl logs -n cine pocketbase-645b664f8f-244qb
```

### Describe pod
```bash
kubectl describe pod -n cine cine-platform-8ccc9c75b-5lqrl
```

---

## 🔄 Reinicios y estado

### Reiniciar app por label
```bash
kubectl delete pod -n cine -l app=cine-platform
```

### Ver estado de pods
```bash
kubectl get pods -n cine
```

### Ver todos los recursos del namespace
```bash
kubectl get all -n cine
```

---

## 🐚 Acceso al pod

```bash
kubectl exec -n cine -it <NOMBRE_DEL_POD> -- sh
```

---

## 📦 Aplicar configuraciones específicas

```bash
kubectl apply -f ./k3s/cine-config.yaml
kubectl apply -f ./k3s/cine-deployment.yaml
kubectl apply -f ./k3s/cine-service.yaml
```

---

## 🧹 Borrar PVC (si queda atascado con finalizers)

```bash
kubectl patch pvc oauth2-pvc -n auth --type=json -p='[{"op": "remove", "path": "/metadata/finalizers"}]'
```

---

## 🔁 Reiniciar deployment
```bash
kubectl rollout restart deployment/cine-platform -n cine
```

---

# 🟧 3. NAMESPACE **default** (transcriberapp / oauth2-server)

## 📜 Logs

### Logs por label
```bash
kubectl logs -n default -l app=oauth2-server --tail=200
```

### Logs de un pod concreto
```bash
kubectl logs -n default <nombre-del-pod>
```

---

## 🔧 Recursos

### Ver deployments
```bash
kubectl get deployments -n default
```

### Aplicar todos los YAML
```bash
kubectl apply -f k3s/ -n default
```

### Ver PVCs
```bash
kubectl get pvc -n default
```

### Ver secret principal
```bash
kubectl get secret transcriberapp-secrets -n default
```

### Ver secrets y configmaps
```bash
kubectl get secret -n default
kubectl get configmap -n default
```

---

## 🧪 Debugging

### Describe pod
```bash
kubectl describe pod -n default transcriberapp-8ccc9c75b-5lqrl
```

---

## 🔄 Reinicios

### Reiniciar app por label
```bash
kubectl delete pod -n default -l app=transcriberapp
```

### Ver estado de pods
```bash
kubectl get pods -n default
```

### Reiniciar deployment
```bash
kubectl rollout restart deployment/transcriberapp -n default
```

---

## 🐚 Acceso al pod

```bash
kubectl exec -n default -it <NOMBRE_DEL_POD> -- sh
```

---

## 📦 Aplicar configuraciones específicas

```bash
kubectl apply -f ./k3s/deployment.yaml
kubectl apply -f ./k3s/service.yaml
kubectl apply -f ./k3s/ingress/transcriberapp-ingressroute.yaml
kubectl apply -f ./k3s/storage/transcriberapp-pvc.yaml
```

---

## 🚀 Importar imagen local en k3s

```bash
docker save oauth2-server:latest -o oauth2-server.tar
sudo k3s ctr images import oauth2-server.tar
```

---

# 🟥 4. OAuth2Server (local)

## 🔧 Build y ejecución local

```bash
mvn clean package
java -jar target/OAuth2Server-0.0.1-SNAPSHOT.jar
```

### Comprobar puerto en uso
```bash
sudo lsof -i :8080
```

### Ejecutar con Spring Boot plugin
```bash
mvn spring-boot:run -X
```

---

## 🐳 Docker local

```bash
docker build -t oauth2server .
docker run -p 8080:8080 oauth2server
```

```bash
mvn clean package
docker build -t oauth2-server:latest .
```

---

# 🟪 5. BBDD H2 en Kubernetes (namespace auth)

## 🐚 Entrar al pod

```bash
kubectl exec -it -n auth $(kubectl get pod -n auth -l app=oauth2-server -o jsonpath='{.items[0].metadata.name}') -- bash
```

o

```bash
kubectl exec -n auth -it <NOMBRE_DEL_POD> -- sh
```

---

## 🧹 Borrar PVC

```bash
kubectl delete pvc oauth2-pvc -n auth --force --grace-period=0
kubectl patch pvc oauth2-pvc -n auth --type=json -p='[{"op": "remove", "path": "/metadata/finalizers"}]'
kubectl apply -f ./k8s/pvc.yaml
```

---

## 📤 Copiar la base de datos desde el pod al host

```bash
kubectl cp auth/<POD_NAME>:/data/oauth2db.mv.db ./oauth2db.mv.db
```

## 📥 Copiar la base de datos desde el host al pod

```bash
kubectl cp ./oauth2db.mv.db auth/<POD_NAME>:/data/oauth2db.mv.db
```

---

## 🔐 Cambiar contraseña en H2

```sql
UPDATE usuarios
SET password = '$2b$12$t3XDd8U5098eeYodNTlJp.u6Rze/P8zdjmEZ.SklfEl6lFvMyUCtS'
WHERE username = 'juanaco';
```

---

## 🔑 Generar bcrypt

```bash
python3 - <<'PY'
import bcrypt
print(bcrypt.hashpw(b"user2", bcrypt.gensalt(rounds=10)).decode())
PY
```

---

# 🟦 6. Docker Hub privado (imagePullSecret)

```bash
kubectl create secret docker-registry regcred \
  --docker-server=https://index.docker.io/v1/ \
  --docker-username=USERNAME \
  --docker-password="PASSWORD_DE_DOCKER_HUB" \
  --docker-email="EMAIL" \
  -n auth
```

---

# 🟫 7. Variables de entorno en producción (DB, OAuth, JWT)

## 📌 ¿Dónde se definen?

👉 **En un Secret de Kubernetes.**

### 1) Codificar valores en base64

```bash
echo -n "mi_clave_jwt_super_segura" | base64
echo -n "jdbc:postgresql://postgres:5432/oauth2" | base64
echo -n "postgres" | base64
echo -n "mi_password" | base64
```

### 2) Crear el Secret

```yaml
apiVersion: v1
kind: Secret
metadata:
  name: oauth2-secrets
  namespace: auth
type: Opaque
data:
  jwt-key: <clave_base64>
  db-url: <jdbc_url_base64>
  db-user: <db_user_base64>
  db-pass: <db_pass_base64>
  oauth-client-id: <client_id_base64>
  oauth-client-secret: <client_secret_base64>
```

### 3) Referenciar en el Deployment

```yaml
env:
  - name: JWT_SIGNING_KEY
    valueFrom:
      secretKeyRef:
        name: oauth2-secrets
        key: jwt-key
```

---

# 🟧 8. Comandos adicionales que pediste

## 🔌 Port-forward del servicio OAuth2Server

```bash
kubectl port-forward -n auth svc/oauth2-server 8080:8080
```

## 📜 Logs en tiempo real

```bash
kubectl logs -n auth -l app=oauth2-server --tail=200 -f
```

## 🔐 Petición OAuth2 (password grant)

```bash
curl -X POST \
  -u "cine-platform:supersecreto" \
  -d "grant_type=password" \
  -d "username=admin" \
  -d "password=PASSWORD" \
  http://localhost:8080/oauth/token
```

## 🔁 Reiniciar deployment oauth2-server

```bash
kubectl rollout restart deployment oauth2-server -n auth
```

## 🧼 Crear pod temporal (cleaner)

```bash
kubectl run cleaner -n auth --image=felixmurcia/oauth2server:v20260211-1751 --command -- sleep 3600
```

## 🧹 Eliminar finalizers de un PVC (versión merge)

```bash
kubectl patch pvc oauth2-pvc -n auth -p '{"metadata":{"finalizers":null}}' --type=merge
```
