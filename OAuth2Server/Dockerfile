FROM eclipse-temurin:17-jdk

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos el JAR ya compilado
COPY target/OAuth2Server-0.0.1-SNAPSHOT.jar /app/app.jar

# Copiamos los scripts desde tu repo local
COPY scripts/run-dev.sh /app/run-dev.sh
COPY scripts/run-prod.sh /app/run-prod.sh

# Damos permisos de ejecución
RUN chmod +x /app/run-dev.sh /app/run-prod.sh

# Directorio donde H2 guardará la BD en producción (/data lo montará Kubernetes)
RUN mkdir -p /data

# Puerto expuesto (opcional, solo informativo)
EXPOSE 8080

# Comando por defecto (se puede sobrescribir en Kubernetes con `command:`)
CMD ["java", "-jar", "/app/app.jar"]
