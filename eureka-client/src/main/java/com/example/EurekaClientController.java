package com.example;

import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

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
  public Services getServices(@RequestParam(name="service", required=false) String service, HttpServletRequest request) {
    if (service == null) {
      return new Services().withItems(this.discoveryClient.getServices())
    		  .withSource(getSource(request));
    }
    
    return new Services().withItems(
        this.discoveryClient.getInstances(service).stream().map(i -> i.getUri().toString()).collect(Collectors.toList()))
    		.withSource(getSource(request));
  }
  
  @RequestMapping("/gtwServices")
  public Services gtwServices(@RequestParam(name="service", required=false) String service) {
    return this.eurekaClientService.getServices(service);
  }
  
  /**
   * 
   * @param request
   * @return
   */
  private String getSource(HttpServletRequest request) {
	  return String.format("%d", request.getLocalPort());
  }
}
