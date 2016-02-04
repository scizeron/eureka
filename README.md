* mvn clean install

* cd eureka-server
* $JAVA_HOME/bin/java -jar target/eureka-server-0.0.1-SNAPSHOT.jar

* cd eureka-client
* $JAVA_HOME/bin/java -Dserver.port=8080 -jar target/eureka-client-0.0.1-SNAPSHOT.jar
* $JAVA_HOME/bin/java -Dserver.port=8082 -jar target/eureka-client-0.0.1-SNAPSHOT.jar