package io.wolverine.dns.client;

public class HostToIp {
	private String hostname;
	private String record;
	
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
	@Override
	public String toString() {
		return "HostToIp [hostname=" + hostname + ", record=" + record + "]";
	}
	public HostToIp() {
		super();
		// TODO Auto-generated constructor stub
	}
}