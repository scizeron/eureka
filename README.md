# Eureka Client - Ribbon tips

## Build

* mvn clean install

## Launch server

* cd eureka-server
* $JAVA_HOME/bin/java -jar target/eureka-server-0.0.1-SNAPSHOT.jar

## Launch clients

* cd eureka-client

```
2 instances
```

* $JAVA_HOME/bin/java -Dserver.port=8080 -jar target/eureka-client-0.0.1-SNAPSHOT.jar
* $JAVA_HOME/bin/java -Dserver.port=8082 -jar target/eureka-client-0.0.1-SNAPSHOT.jar

```
You can specify a different service Id with -Dservice.id=myservice. By default, it's web.
Enable a ping request with the profile "ping.enabled" 
```

* $JAVA_HOME/bin/java -Dserver.port=8080 -jar target/eureka-client-0.0.1-SNAPSHOT.jar --spring.profiles.active=ping.enabled

----------

* By default, No real ping, they just assume that the server is up if Discovery Client says. The status comes from the Euraka Server.
* The eureka status is not in a "leaseExpirationEnabled" mode, when a service is shutdown, it is still marked as UP by Eureka Server and at the level client, if the host count is 2 for example, 
we have the following sequence : ok, failed, ok, failed ...
* When there are a several servers, the leaseExpirationEnabled is true and the failed server is removed, only the UP servers which are forwarded to the registry clients.
*see NIWSDiscoveryPing.java for more details.

* NIWSDiscoveryPing is instanciated in the EurekaRibbonClientConfiguration class if no other IPing class was previously instanciated. In this case, if the default behaviour is troublesome, a real Ping can be registered to do a real ping but the Discovery update override it.

* Ensure the lease expiration be enabled.
