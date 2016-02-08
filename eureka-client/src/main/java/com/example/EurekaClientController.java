package com.example;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EurekaClientController {

  @Autowired
  private DiscoveryClient discoveryClient;
  
  @Autowired
  private EurekaClientService eurekaClientService;
  
  @RequestMapping("/services")
  public Services getServices(@RequestParam(name="service", required=false) String service) {
    if (service == null) {
      return new Services().withItems(this.discoveryClient.getServices());
    }
    
    return new Services().withItems(
        this.discoveryClient.getInstances(service).stream().map(i -> i.getUri().toString()).collect(Collectors.toList()));
  }
  
  @RequestMapping("/gtwServices")
  public Services gtwServices(@RequestParam(name="service", required=false) String service) {
    return this.eurekaClientService.getServices(service);
  }
}
