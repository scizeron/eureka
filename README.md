[![Build Status](https://travis-ci.org/scizeron/eureka.svg?branch=master)](https://travis-ci.org/scizeron/eureka)

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

* By default, no real ping is performed, they just assume that the server is up if Discovery Client says. The status comes from the Euraka Server.
* If the eureka status is not in a "leaseExpirationEnabled" mode, so when a service is shutdown, it is still marked as UP by Eureka Server and at the level client.
* if the initial number of instances is 2 for example and then 1, we have the following sequence : ok, ko, ok, ko ...
* When there are several servers, the leaseExpirationEnabled is true and the dead instance is removed and only the UP instances are forwarded to the registry client.

* see NIWSDiscoveryPing.java for more details.

* NIWSDiscoveryPing is instantiated in the EurekaRibbonClientConfiguration class if no other IPing class was previously instantiated. 
* In this case, if this default behaviour is troublesome, a real Ping can be registered to do a real ping but the Discovery update override it and the expected outcome is not good.

* Ensure the number of instaces is sufficient to allow the lease expiration be enabled.
