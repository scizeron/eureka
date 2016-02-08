* mvn clean install

* cd eureka-server
* $JAVA_HOME/bin/java -jar target/eureka-server-0.0.1-SNAPSHOT.jar

* cd eureka-client
* $JAVA_HOME/bin/java -Dserver.port=8080 -jar target/eureka-client-0.0.1-SNAPSHOT.jar
* $JAVA_HOME/bin/java -Dserver.port=8082 -jar target/eureka-client-0.0.1-SNAPSHOT.jar

# Eureka tips

## Eureka Client - Ribbon

No real ping, they just assume that the server is up if Discovery Client says. 
The status comes from the Euraka Server.
The eureka status is not in a "leaseExpirationEnabled" mode, when a service is shutdown, 
it is still marked as UP by Eureka Server and at the level client, if the host count is 2 for example, 
we have the following sequence : ok, failed, ok, failed ...
When there are a several servers, the leaseExpirationEnabled is true and the failed server is removed, only the UP servers 
which are forwarded to the registry clients.
 
see NIWSDiscoveryPing.java for more details.

NIWSDiscoveryPing is instanciated in the EurekaRibbonClientConfiguration class if no other IPing class was previously instanciated.
In this case, if the default behaviour is troublesome, a real Ping can be registred to do a real ping and discard the DOWN 
servers in the load balancer servers list.
