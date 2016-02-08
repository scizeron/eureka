package com.example;

import java.util.List;

/**
 * 
 * @author stfciz
 *
 * 6 févr. 2016
 */
public class Services {

  private List<String> items;

  public List<String> getItems() {
    return items;
  }

  public Services withItems(List<String> items) {
    this.items = items;
    return this;
  }
  
}
