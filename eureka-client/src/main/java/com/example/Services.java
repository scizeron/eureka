package com.example;

import java.util.List;

/**
 * 
 * @author stfciz
 *
 *         6 févr. 2016
 */
public class Services {

	private List<String> items;

	private String source;

	/**
	 * 
	 * @param items
	 * @return
	 */
	public Services withItems(List<String> items) {
		this.items = items;
		return this;
	}
	
	/**
	 * 
	 * @param source
	 * @return
	 */
	public Services withSource(String source) {
		this.source = source;
		return this;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<String> getItems() {
		return items;
	}

	/**
	 * 
	 * @return
	 */
	public String getSource() {
		return source;
	}
}
