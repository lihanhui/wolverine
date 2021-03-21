package io.wolverine.dns.client;

import java.util.List;

public class HostToIpList {
	private String servicename;
	private List<String> records;
	
	public String getServicename() {
		return servicename;
	}

	public void setServicename(String servicename) {
		this.servicename = servicename;
	}

	public List<String> getRecords() {
		return records;
	}

	public void setRecords(List<String> records) {
		this.records = records;
	}
	@Override
	public String toString() {
		return "HostToIpList [servicename=" + servicename + ", records=" + records + "]";
	}
	public HostToIpList() {
		super();
		// TODO Auto-generated constructor stub
	}
}
