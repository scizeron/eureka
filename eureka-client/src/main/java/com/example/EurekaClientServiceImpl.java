package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class EurekaClientServiceImpl implements EurekaClientService {

  @Autowired
  @LoadBalanced
  private RestTemplate restTemplate;
  
  @Override
  @HystrixCommand(fallbackMethod = "gtwServicesFallback")
  public Services getServices(String service) {
    return this.restTemplate.exchange("http://web/services" + (service != null ? "?service=" + service : "") , HttpMethod.GET, null, new  ParameterizedTypeReference<Services>() {}).getBody();
  }

  /**
   * 
   * @param service
   * @return
   */
  public Services gtwServicesFallback(String service) {
    return new Services();
  }
}
