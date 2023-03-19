#FROM amd64/maven:3.8.6-openjdk-11
##will get jar file and copy to image
#
##to  direct any folder
#WORKDIR /usr/app
#
##COPY ./target/Spring-24-Docker-0.0.1-SNAPSHOT.jar  /usr/app/
#COPY  .  .
#
##will provide exacutable application when container starts the run
#ENTRYPOINT ["mvn","spring-boot:run"]


#Base image
FROM openjdk:11
COPY ./target/ticketing-project-rest-0.0.1-SNAPSHOT.jar /usr/app/
WORKDIR /usr/app
ENTRYPOINT ["java","-jar","ticketing-project-rest-0.0.1-SNAPSHOT.jar"]