package io.wolverine.dns.client.resolver;

import java.util.concurrent.ConcurrentHashMap;

import io.wolverine.dns.client.record.MultiServiceHosts;
import io.wolverine.dns.client.record.ServiceHost;

public class DefaultDnsResolver extends AbstractDnsResolver{
	private ConcurrentHashMap<String, ServiceHost> serviceHostMap	= new ConcurrentHashMap<>();
	private ConcurrentHashMap<String, MultiServiceHosts> multiServiceHostsMap	= new ConcurrentHashMap<>();
	
	@Override
	protected ServiceHost getServiceHostFromLocal(String hostname) {
		return serviceHostMap.get(hostname);
	}
	@Override
	protected MultiServiceHosts getMultiServiceHostsFromLocal(String servicename) {
		return multiServiceHostsMap.get(servicename);
	}
	@Override
	public void onServiceHost(ServiceHost serviceHost) {
		if(serviceHost != null && serviceHost.getHostname() != null) {
			this.serviceHostMap.put(serviceHost.getHostname(), serviceHost);
		}
	}
	@Override
	public void onMultiServiceHosts(MultiServiceHosts multiServiceHosts) {
		if(multiServiceHosts != null && multiServiceHosts.getServicename() != null) {
			this.multiServiceHostsMap.put(multiServiceHosts.getServicename(), multiServiceHosts);
		}
	}
}
