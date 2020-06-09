#get container with jre to run application
FROM openjdk:11.0.7-jre-slim-buster



#get jar from builder container
COPY target/poolauto-api-0.0.1-SNAPSHOT.jar /poolauto-api-0.0.1-SNAPSHOT.jar

#get properties files
VOLUME $HOME/ApplicationProperties/poolauto-api

EXPOSE 8080

ENTRYPOINT java -Dspring.profiles.active=dev -jar poolauto-api-0.0.1-SNAPSHOT.jar
