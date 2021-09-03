FROM openjdk:11
ADD target/*.jar starlarky-service.jar
CMD ["java", "-Dgrpc.port=6565", "-jar", "starlarky-service.jar"]