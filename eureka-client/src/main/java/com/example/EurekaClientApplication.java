package com.example;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableEurekaClient
@RestController
public class EurekaClientApplication {

  @Autowired
  private DiscoveryClient discoveryClient;
  
  @Autowired
  @LoadBalanced
  private RestTemplate restTemplate;
  
	public static void main(String[] args) {
		SpringApplication.run(EurekaClientApplication.class, args);
	}
	
	 /**
   * 
   * @author stfciz
   *
   * 3 févr. 2016
   */
  static class Services {
    private List<String> items;

    public List<String> getItems() {
      return items;
    }

    public Services withItems(List<String> items) {
      this.items = items;
      return this;
    }
    
  }
	
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
    return this.restTemplate.exchange("http://web/services" + (service != null ? "?service=" + service : "") , HttpMethod.GET, null, new  ParameterizedTypeReference<Services>() {}).getBody();
  }
	

}
