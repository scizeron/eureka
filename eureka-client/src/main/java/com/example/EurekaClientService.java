package com.example;

/**
 * 
 * @author stfciz
 *
 * 6 févr. 2016
 */
public interface EurekaClientService {

  /**
   * 
   * @param service
   * @return
   */
  public Services getServices(String service);
}
