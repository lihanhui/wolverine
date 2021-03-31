package io.wolverine.dns.client.record;

public class ServiceHost {
	private String hostname;
	private String record;
	private int port;
	
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	@Override
	public String toString() {
		return "ServiceHost [hostname=" + hostname + ", record=" + record + ", port=" + port + "]";
	}
	public ServiceHost() {
		super();
		// TODO Auto-generated constructor stub
	}
}
