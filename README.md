mvn clean install
cd eureka-server
java -jar target/eureka-server-0.0.1-SNAPSHOT.jar

cd eureka-client
java -Dserver.port=8080 -jar target/eureka-server-0.0.1-SNAPSHOT.jar
java -Dserver.port=8082 -jar target/eureka-server-0.0.1-SNAPSHOT.jar