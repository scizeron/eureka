package com.example;

import java.io.IOException;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.AbstractLoadBalancerPing;
import com.netflix.loadbalancer.Server;
import com.netflix.niws.loadbalancer.DiscoveryEnabledServer;

/**
 * 
 * @author 212552868
 *
 */
public class PingInstance extends AbstractLoadBalancerPing {

	private CloseableHttpClient httpClient;

	private static final Logger LOG = LoggerFactory.getLogger(PingInstance.class);

	/**
	 * 
	 */
	public PingInstance() {
		super();
		this.httpClient = HttpClients.createDefault();
	}

	/**
	 * 
	 * @param httpClient
	 */
	public PingInstance(CloseableHttpClient httpClient) {
		super();
		this.httpClient = httpClient;
	}

	@Override
	public void initWithNiwsConfig(IClientConfig arg0) {
		/** EMPTY **/
	}

	@Override
	public boolean isAlive(Server server) {
		boolean isAlive = true;
		if (server != null && server instanceof DiscoveryEnabledServer) {
			DiscoveryEnabledServer dServer = (DiscoveryEnabledServer) server;
			InstanceInfo instanceInfo = dServer.getInstanceInfo();
			if (instanceInfo != null) {
				HttpUriRequest getRequest = new HttpGet(instanceInfo.getHealthCheckUrl());
				try {
					isAlive = (this.httpClient.execute(getRequest).getStatusLine().getStatusCode() == 200);
				} catch (IOException e) {
					isAlive = false;
					LOG.error("A 'ping' error occured", e);
				} finally {
					getRequest.abort();
				}
			}
		}
		return isAlive;
	}
}
