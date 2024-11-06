# Utilisez l'image OpenJDK 17 avec Alpine comme base
FROM openjdk:17-jdk-alpine

# Exposez le port 8089
EXPOSE 8089

# Copiez le fichier JAR de l'application dans l'image Docker
COPY target/tp-foyer-5.0.0.jar /tp-foyer-5.0.0.jar

# Commande pour exécuter l'application
ENTRYPOINT ["java", "-jar", "/tp-foyer-5.0.0.jar"]