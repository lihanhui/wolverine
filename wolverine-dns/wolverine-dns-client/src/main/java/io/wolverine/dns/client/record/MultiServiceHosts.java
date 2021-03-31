package io.wolverine.dns.client.record;

import java.util.List;

public class MultiServiceHosts {
	private String servicename;
	private List<ServiceHost> serviceHosts;
	
	public String getServicename() {
		return servicename;
	}
	public void setServicename(String servicename) {
		this.servicename = servicename;
	}
	public List<ServiceHost> getServiceHosts() {
		return serviceHosts;
	}
	public void setServiceHosts(List<ServiceHost> serviceHosts) {
		this.serviceHosts = serviceHosts;
	}
	@Override
	public String toString() {
		return "MultiServiceHosts [servicename=" + servicename + ", serviceHosts=" + serviceHosts + "]";
	}
	public MultiServiceHosts() {
		super();
		// TODO Auto-generated constructor stub
	}
}
